import java.util.*;
import java.io.*;

public class Recipe {

	//object properties
	private String id;
	private String title;
	private String description;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;

	Recipe(String id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.ingredients = new ArrayList<>();
		this.instructions = new ArrayList<>();
	}
	
	public String getId() {
		return this.id;
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
	
	public void displayRecipe() {
		System.out.println("Recipe: " + this.id);
		System.out.println("Title: " + this.title);
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
		System.out.println("END OF RECIPE " + this.id + "------------");
	}
	
	//Exploration
//	public boolean exploreRecipe(Scanner scanner);
//	public boolean readAllRecipe();
//	public boolean readStepRecipe();

	
	
}



	
	
	
	
