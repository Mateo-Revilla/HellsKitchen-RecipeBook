import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;

public class RecipeList {

	final private String fileName = "database.json";
	private ArrayList<Recipe> recipeList; // list of recipes (local memory)
	
    private JSONArray database; // JSON database to be stored in a JSON file
    
    RecipeList() {
    	recipeList = new ArrayList<>();
    	database = new JSONArray();
    }

	// reads a json file and parses each object and adds it to local memory and JSON database
	@SuppressWarnings("unchecked")
	public void extractRecipe() {
		JSONParser jsonParser = new JSONParser();
		try {
			File inFile = new File(this.fileName);
			if (inFile.length() != 0) {
				FileReader reader = new FileReader(this.fileName);
				Object obj = jsonParser.parse(reader);
				JSONArray jsonArray = (JSONArray) obj;
			
				jsonArray.forEach(rec -> addRecipeToDatabase(generateRecipeObject((JSONObject) rec)));
			}
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	// adds recipe to local memory and JSON database
	@SuppressWarnings("unchecked")
	public void addRecipeToDatabase(Recipe recipe) {
		
		// add to local list of recipes 
		this.recipeList.add(recipe); 
		
		// add to persistent storage 
		JSONObject jsonObj = generateJSONObject(recipe);
		database.add(jsonObj);
		System.out.println("New recipe now added!");
	}
	
	// converts the JSON object to a Recipe object 
	@SuppressWarnings("unchecked")
	public Recipe generateRecipeObject(JSONObject recId) {
		
		// complicated way to get the value without knowing the key
		Set keys = recId.keySet();
	    Iterator iter = keys.iterator();
    	String id = (String) iter.next();
    	JSONObject rec = (JSONObject) recId.get(id);
	   
		String name = (String) rec.get("title");
		String des = (String) rec.get("description");
		Recipe newRecipe = new Recipe(id, name, des);
		
		ArrayList<String> ingredients = newRecipe.getIngredients();
		Object ingsObj = rec.get("ingredients");
		JSONArray ings = (JSONArray) ingsObj;
		ings.forEach(ing -> ingredients.add((String) ing));
		
		ArrayList<String> instructions = newRecipe.getInstructions();
		JSONArray instrs = (JSONArray) rec.get("instructions");
		instrs.forEach(instr -> instructions.add((String) instr));
		
		return newRecipe;
	}
	
	// converts the Recipe object into a JSON object
	@SuppressWarnings("unchecked")
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
	
	// prompts user for new recipe information then stores it
	public void createRecipe(Scanner scanner) {
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
		addRecipeToDatabase(newRecipe);
	}

	// display a list of all stored recipes
	public void displayRecipeList() {
		for (int i = 0; i < this.recipeList.size(); i++) {
			Recipe rec = this.recipeList.get(i);
			rec.displayRecipe();
		}
	}
	
	public void retrieveRecipe(Scanner scanner) {
		System.out.println("How would you like to retrieve a recipe? Type 's' to search and 'b' to browse");
		String searchType = scanner.nextLine();
		if (searchType.equals('s')) {
			System.out.println("Please enter the search phrase");
			String searchString = scanner.nextLine();
			searchRecipe(searchString);
		} else if (searchType.equals('b')) {
			browseRecipe(scanner);
		}
	}

	public void searchRecipe(String searchTitle) {
		for (int i = 0; i < this.recipeList.size(); i++) {
			Recipe rec = this.recipeList.get(i);
			recTitle = rec.getTitle();
			if (recTitle.equals(searchTitle)) {
				rec.exploreRecipe();
			}
		}
		System.out.println("No matches found");
	}

	public void browseRecipe(Scanner scanner) {
		for (int i = 0; i < this.recipeList.size(); i++) {
			Recipe rec = this.recipeList.get(i);
			recTitle = rec.getTitle();
			redId = rec.getId();
			System.out.println(redId + " " + recTitle);
		}
		System.out.println("Please enter the ID number of the recipe you want to select")
		String id = scanner.nextLine();
		for (int i = 0; i < this.recipeList.size(); i++) {
			Recipe rec = this.recipeList.get(i);
			redId = rec.getId();
			if (id.equals(redId)) {
				rec.exploreRecipe();
			}
		}
	}

	// saves the json database to an external file
	public void saveDatabase() {
		try {
			FileWriter writer = new FileWriter(this.fileName);
			writer.write(database.toJSONString());
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// JSON DATABASE STRUCTURE

/*
database = [
	{
		"id1": {
			"title": "Title",
			"description": "This is the description",
			"ingredients": ["ingredientOne", "ingredientTwo", "ingredientThree"],
			"instructions": ["instructionOne", "instructionTwo", "instructionThree"]
		}
	},
	{
		"id2": {
			"title": "Title",
			"description": "This is the description",
			"ingredients": ["ingredientOne", "ingredientTwo", "ingredientThree"],
			"instructions": ["instructionOne", "instructionTwo", "instructionThree"]
		}
	},
	{
		"id3": {
			"title": "Title",
			"description": "This is the description",
			"ingredients": ["ingredientOne", "ingredientTwo", "ingredientThree"],
			"instructions": ["instructionOne", "instructionTwo", "instructionThree"]
		}
	}
]
*/	