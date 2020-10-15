package HellsKitchenRecipeBook;

import java.util.*;
import java.io.*;


public class UserInterface {

	private Scanner scanner;
	private Server server;

	UserInterface() {
		this.scanner = new Scanner(System.in);
		this.server = new Server();
	}

	public void execute() {
		
		System.out.println("Welcome to the Recipe Book. Here you will be able to save and retrieve your favorite Recipes.");
		createOrRetrieve();
	}

	public void createOrRetrieve() {
		System.out.println("Enter 'C' if you wish to create a new recipe or 'R' to retrieve a recipe. Enter 'Q' to quit.");
		String userAnswer = scanner.nextLine().toUpperCase();
		if (userAnswer.equals("C")) {
			// CREATE
			this.server.createRecipe(this.scanner);
			createOrRetrieve();
		} 
		else if (userAnswer.equals("R")) {
			// RETRIEVE
			retrieveRecipe();
		} 
		else if (userAnswer.equals("Q")) {
			System.out.println("Bye. Have a nice day.");
			this.scanner.close();
			System.exit(0);
		} 
		else {
			createOrRetrieve();
		}
	}

	public void retrieveRecipe() {
		System.out.println("Enter 'N' to search recipe by name or 'A' to browse all recipes and select one or 'B' to go back");
		String userAnswer = scanner.nextLine().toUpperCase();
		if (userAnswer.equals("N")) {
			// SEARCH BY NAME
			System.out.println("Please enter title.");
			String title = scanner.nextLine().toLowerCase();
			Recipe rec = server.searchRecipe(title);
			if (rec == null) {
				System.out.println("Not found");
				retrieveRecipe();
			} 
			else {
				exploreRecipe(rec);
			}
		} 
		else if (userAnswer.equals("A")) {
			// BROWSE ALL
			Recipe rec = server.browseRecipe(scanner);
			if (rec == null) {
				System.out.println("Not found");
				retrieveRecipe();
			} 
			else {
				exploreRecipe(rec);
			}
		} 
		else if (userAnswer.equals("B")) {
			createOrRetrieve();
		}
		else {
			retrieveRecipe();
		}
	}

	public void exploreRecipe(Recipe recipe) {
		System.out.println("Enter 'E' to read entire recipe or 'S' to step through the instructions one at the time or 'B' to go back");
		String userAnswer = scanner.nextLine().toUpperCase();
		if (userAnswer.equals("E")) {
			// READ ENTIRE RECIPE
			recipe.readAllRecipe();
			createOrRetrieve();
		} 
		else if (userAnswer.equals("S")) {
			// STEP THROUGH INSTRUCTIONS
			recipe.readStepRecipe(scanner);
			createOrRetrieve();
		} 
		else if (userAnswer.equals("B")) {
			createOrRetrieve();
		}
		else {
			exploreRecipe(recipe);
		}
	}
}
