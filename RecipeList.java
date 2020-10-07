import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.*;

public class RecipeList {

	final private String fileName = "database.json";
	private ArrayList<Recipe> recipeList = new ArrayList<>();//array of recipes representing the json database


	//class methods

	// prompts user for new recipe information then stores it
	public boolean createRecipe(Scanner scanner) {
		System.out.println("Welcome to creating a recipe!");
		
		System.out.println("What will the recipe be called? (Enter one word with no whitespace)");
		String name = scanner.nextLine().toLowerCase();
		System.out.println("Please add a description then press ENTER.");
		String des = scanner.nextLine();
		
		Recipe newRecipe = new Recipe(UUID.randomUUID().toString(), name, des);
		
		ArrayList<String> ingredients = newRecipe.getIngredients();
		System.out.println("Enter the list of ingredients one at a time. Enter 'OK' when done");
		String ing = scanner.nextLine().toLowerCase();
		while (!ing.equals("ok")) {
			ingredients.add(ing);
			System.out.println("Enter next ingredient or enter 'OK' when done");
			ing = scanner.nextLine().toLowerCase();
		}
		
		ArrayList<String> instructions = newRecipe.getInstructions();
		System.out.println("Enter cooking instructions one step at a time. After each step, press ENTER. Enter 'OK' when done");
		String instr = scanner.nextLine();
		String instrLowerCase = instr.toLowerCase();
		int i = 1;
		while (!instrLowerCase.equals("ok")) {
			instr = i + ". " + instr;
			instructions.add(instr);
			System.out.println("Enter the next step of the cooking instructions then press ENTER or enter 'OK' when done");
			instr = scanner.nextLine();
			instrLowerCase = instr.toLowerCase();
			i++;
		}
		
		System.out.println("Adding the following recipe to recipe book...");
		newRecipe.displayRecipe();
		return addRecipeToDatabase(newRecipe);
	}
	
	// adds recipe to local memory and database
	public boolean addRecipeToDatabase(Recipe recipe) {
		
		// add to local list of recipes
		this.recipeList.add(recipe);
		
		// add to persistent storage 
		JSONObject jsonObj = generateJSONObject(recipe);
		try {
			FileWriter outFile = new FileWriter(this.fileName);
			outFile.write(jsonObj.toJSONString());
			outFile.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("New recipe now added!");
		return true;
	}
	
	// converts the Recipe object into a JSON object
	public JSONObject generateJSONObject(Recipe recipe) {
		JSONObject newObject = new JSONObject();
		
		JSONObject newRecipe = new JSONObject();
		newRecipe.put("title", recipe.getTitle());
		newRecipe.put("description", recipe.getDescription());
		
		JSONArray ingsJSON = new JSONArray();
		ArrayList<String> ings = recipe.getIngredients();
		for (int i = 0; i < ings.size(); i++) {
			ingsJSON.add(ings.get(i));
		}
		newRecipe.put("ingredients", ingsJSON);
		
		JSONArray instrsJSON = new JSONArray();
		ArrayList<String> instrs = recipe.getInstructions();
		for (int i = 0; i < instrs.size(); i++) {
			instrsJSON.add(instrs.get(i));
		}
		newRecipe.put("instructions", instrsJSON);
		
		newObject.put(recipe.getId(), newRecipe);
		
		return newObject;
	}

	//Retrieve
//	public boolean retrieveRecipe(Scanner scanner);
//	public boolean searchRecipe(String title);
//	public boolean browseRecipe();

	//Main Helper
	//public void extractRecipe();//reads json file and creates recipesList arraylist
}

//JSON DATABASE STRUCTURE

/*
database = {
	"id1": {
		"title": "Title",
		"description": "This is the description",
		"ingredients": ["ingredientOne", "ingredientTwo", "ingredientThree"],
		"instructions": ["instructionOne", "instructionTwo", "instructionThree"
	},
	"id2": {
		"title": "Title",
		"description": "This is the description",
		"ingredients": ["ingredientOne", "ingredientTwo", "ingredientThree"],
		"instructions": ["instructionOne", "instructionTwo", "instructionThree"
	},
	"id3": {
		"title": "Title",
		"description": "This is the description",
		"ingredients": ["ingredientOne", "ingredientTwo", "ingredientThree"],
		"instructions": ["instructionOne", "instructionTwo", "instructionThree"
	}
}
*/	