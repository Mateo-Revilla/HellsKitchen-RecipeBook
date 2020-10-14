package HellsKitchenRecipeBook;

import java.util.*;
import java.io.*;

public class Recipe {

	//object properties
	private String title;
	private String description;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;
	private int step = 0;

	Recipe(String title, String description) {
		this.title = title;
		this.description = description;
		this.ingredients = new ArrayList<String>();
		this.instructions = new ArrayList<String>();
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

	// print the contents of the recipe
	public void displayRecipe() {
		System.out.println("Recipe: " + this.title);
		System.out.println("Description: " + this.description);
		System.out.print("Ingredients: [");
		for (int i = 0; i < this.ingredients.size(); i++) {
			if (i == this.ingredients.size() - 1) {
				System.out.print(this.ingredients.get(i) + "]");
			}
			else {
				System.out.print(this.ingredients.get(i) + ", ");
			}
		}
		System.out.println("\nInstructions: ");
		for (int i = 0; i < this.instructions.size(); i++) {
			System.out.println(this.instructions.get(i));
		}
		System.out.println("END OF RECIPE: " + this.title+ " ------------\n");
	}


	//Exploration
	public boolean readAllRecipe() {
		System.out.println(this.getTitle());
		System.out.println(this.getDescription());
		ArrayList<String> instru = this.getInstructions();
		ArrayList<String> ing = this.getIngredients();
		int instrulen = instru.size();
		int inglen = ing.size();
		for(int a = 0; a < inglen; a++) {
                    System.out.println(a + " " + ing.get(a));
		}
		for(int b = 0; b < instrulen; b++) {
	            System.out.println(b + " " + instru.get(b));
	            System.out.println();
		}
		System.out.println("End of Recipe! Enjoy!");
		return true;
	}

	public boolean readStepRecipe() {
		System.out.println(this.getTitle());
		System.out.println(this.getDescription());
		ArrayList<String> instru = this.getInstructions();
		int instrulen = instru.size();
		ArrayList<String> ing = this.getIngredients();
		int inglen = ing.size();
		for(int a = 0; a < inglen; a++) {
	        System.out.println(a + " " + ing.get(a));
		}
		Scanner inputReader = new Scanner(System.in);
		while (inputReader.hasNextLine() && step < instrulen) {
		     System.out.println();
		     System.out.println((step + 1) + " " + instru.get(step));
		     step += 1;
		     inputReader.nextLine();
    	}
        // close the scanner
    	inputReader.close();
		step = 0;
		System.out.println("End of Recipe! Enjoy!");
		return true;
	}
}
