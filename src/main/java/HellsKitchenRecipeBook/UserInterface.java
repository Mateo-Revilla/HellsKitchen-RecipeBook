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
		System.out.println("   __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __");
		System.out.println("  |                  __      __        __     __                 |");
		System.out.println("  |                /   /   /   /     /   /  /   /                |");
		System.out.println("  |               /   /__ /   /     /   /_ /   /                 |");
		System.out.println("  |              /    ___    /     /    _    <                   |");
		System.out.println("  |             /   /   /   /     /   /  \\   \\                   |");
		System.out.println("  |            /__ /   /__ /     /__ /   /__ /                   |");
		System.out.println("  |                                                              |");
		System.out.println("  |   Welcome to Hell's Kitchen Recipe Book! Your one place to   |");
		System.out.println("  |        store and retrieve all your favorite recipes.         |");
		System.out.println("  |__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __|");
		System.out.println();

		createOrRetrieve();
	}

	public void createOrRetrieve() {
		System.out.println("Enter 'C' if you wish to create a new recipe or 'R' to retrieve a recipe. Enter 'Q' to quit.");
		String userAnswer = scanner.nextLine().toUpperCase();
		if (userAnswer.equals("C")) {
			// create a recipe
			this.server.createRecipe(this.scanner);
			createOrRetrieve();
		} 
		else if (userAnswer.equals("R")) {
			// retrieve a recipe
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
	    System.out.println("   __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __");
		System.out.println("  |                                                              |");
		System.out.println("  |               Welcome to retrieving a recipe!                |");
		System.out.println("  |__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __|");
		System.out.println();

		System.out.println("Enter 'N' to search recipe by name or 'A' to browse all recipes and select one or 'B' to go back");
		String userAnswer = scanner.nextLine().toUpperCase();
		if (userAnswer.equals("N")) {
			// search recipe by name
			System.out.println("Please enter the title of the recipe you want to find");
			String title = scanner.nextLine().toLowerCase();
			Recipe rec = server.searchRecipe(title, scanner);
			if (rec == null) {
				System.out.println("There were no recipes matching that title");
				System.out.println();
				retrieveRecipe();
			} 
			else {
				exploreRecipe(rec);
			}
		} 
		else if (userAnswer.equals("A")) {
			// browse all recipes
			Recipe rec = server.browseRecipe(scanner);
			if (rec == null) {
				System.out.println("There were no recipes matching that title");
				System.out.println();
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
		System.out.println("Enter 'E' to read entire recipe or 'S' to step through the instructions one at a time or 'B' to go back");
		String userAnswer = scanner.nextLine().toUpperCase();
		if (userAnswer.equals("E")) {
			// read entire recipe
			recipe.readAllRecipe();
			createOrRetrieve();
		} 
		else if (userAnswer.equals("S")) {
			// step-by-step instructions
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
