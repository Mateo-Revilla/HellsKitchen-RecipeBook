package HellsKitchenRecipeBook;

import java.util.*;
import java.io.*;

public class Recipe {

	// object properties
	private String title;
	private String description;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;

	Recipe(String title, String description) {
		this.title = title;
		this.description = description;
		this.ingredients = new ArrayList<String>();
		this.instructions = new ArrayList<String>();
	}

	Recipe(String title, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
		this.title = title;
		this.description = description;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public ArrayList<String> getIngredients() {
		return this.ingredients;
	}

	public ArrayList<String> getInstructions() {
		return this.instructions;
    }

	// print the contents of the recipe for the user to read
	public void readAllRecipe() {
		System.out.println("Name: " + this.title);
		System.out.println("Description: " + this.description);

		ArrayList<String> instr = this.getInstructions();
		ArrayList<String> ing = this.getIngredients();
		int instrlen = instr.size();
		int inglen = ing.size();

		System.out.println("Ingredients: ");
		for (int a = 0; a < inglen; a++) {
            System.out.println(ing.get(a));
		}

		System.out.println("Instructions: ");
		for (int b = 0; b < instrlen; b++) {
			System.out.println(instr.get(b));
		}
		System.out.println("End of Recipe! Enjoy!\n");
	}

	// print the recipe and the instructions step by step
	public void readStepRecipe(Scanner scanner) {
		System.out.println("Name: " + this.title);
		System.out.println("Description: " + this.description);

		ArrayList<String> instr = this.getInstructions();
		ArrayList<String> ing = this.getIngredients();
		int instrlen = instr.size();
		int inglen = ing.size();

		System.out.println("Ingredients: ");
		for (int a = 0; a < inglen; a++) {
	        System.out.println(ing.get(a));
		}

		int step = 0;
		System.out.println("Instructions: (press ENTER to see the next step)");
		while (scanner.hasNextLine() && step < instrlen) {
		     System.out.println(instr.get(step));
		     step += 1;
		     scanner.nextLine();
    	}
		System.out.println("End of Recipe! Enjoy!\n");
	}
}
