import java.util.*;
import org.json.simple.JSONObject;
import java.io.*;

public class Recipe {

	//object properties
	private String id;
	private String title;
	private String description;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;
	private int step = 0;

	Recipe(String id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.ingredients = new ArrayList<String>();
		this.instructions = new ArrayList<String>();
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

	//Exploration
	public boolean exploreRecipe(Scanner scanner);
	public boolean readAllRecipe(){
		ArrayList<String> instru = getInstructions();
		ArrayList<String> ing = getIngredients();
		int instrulen = instru.size();
		int inglen = ing.size();
		for(int a = 0; a < inglen; a++){
			System.out.println(ing.get(a));
		}
		for(int b = 0; b < instrulen; b++){
	            Systems.out.println(instru.get(b));
		}
		Systems.out.prinln("End of Recipe! Enjoy!");
	}
	public boolean readStepRecipe(){
		ArrayList<String> instru = getInstructions();
		int instrulen = instru.size();
		ArrayList<String> ing = getIngredients();
		int inglen = ing.size();
		for(int a = 0; a < inglen; a++){
	            System.out.println(ing.get(a));
		}
		Scanner inputReader = new Scanner(System.in);
		while (inputReader.hasNextLine() && step < instrulen) {
		     System.out.println(instru.get(step));
		     step += 1;
                } 
                // close the scanner 
                inputReader.close(); 
		step = 0;
		System.out.prinln("End of Recipe! Enjoy!");
	}


}
