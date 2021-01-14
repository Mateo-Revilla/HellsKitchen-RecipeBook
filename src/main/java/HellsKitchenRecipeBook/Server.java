package HellsKitchenRecipeBook;

import java.util.*;
import java.io.*; 
import org.bson.Document;
import com.mongodb.Block;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.MongoClientSettings;

public class Server {

	private MongoCollection<Document> collection;

	Server() {
		ConnectionString connString = new ConnectionString(
    	//HERE ADD CONNECTION STRING
		);
		MongoClientSettings settings = MongoClientSettings.builder()
    	.applyConnectionString(connString)
    	.retryWrites(true)
    	.build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("RecipeBook");
		collection = database.getCollection("Recipes");
	}
	
	// adds recipe to MongoDB database
	public void addRecipeToDatabase(Recipe recipe) {
		Document document = new Document();
		document.append("title", recipe.getTitle());
		document.append("description", recipe.getDescription());
		document.append("ingredients", recipe.getIngredients());
		document.append("instructions", recipe.getInstructions());
		collection.insertOne(document);
	}
	
	// prompts user for new recipe information then stores it
	public void createRecipe(Scanner scanner) {
		System.out.println("   __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __");
		System.out.println("  |                                                              |");
		System.out.println("  |               Welcome to creating a new recipe!              |");
		System.out.println("  |__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __|");
		System.out.println();

		System.out.println("What will the recipe be called?");
		String name = scanner.nextLine().toLowerCase();
		System.out.println("Please add a description then press ENTER.");
		String des = scanner.nextLine();
		
		Recipe newRecipe = new Recipe(name, des);
		
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
		
		System.out.println("   __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __");
		System.out.println("  |                                                              |");
		System.out.println("  |          Adding the following recipe to recipe book          |");
		System.out.println("  |__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __|");
		System.out.println();
		newRecipe.readAllRecipe();
		addRecipeToDatabase(newRecipe);
	}

	// converts mongocursor to arraylist of recipes
	public ArrayList<Recipe> convertToArrayListHelper(FindIterable<Document> documents) {
		MongoCursor<Document> cursor = documents.iterator();
		ArrayList<Recipe> recipes = new ArrayList<>();
		
		try {
    		while (cursor.hasNext()) {
    			recipes.add(generateRecipeFromDocument(cursor.next()));
    		}
		}
		finally {
    		cursor.close();
		}
		return recipes;
	}

	// converts MongoDB Document object to Recipe object
	public Recipe generateRecipeFromDocument(Document document) {

		String title = document.getString("title");
		String description = document.getString("description");
		ArrayList<String> ingredients = new ArrayList(document.getList​("ingredients", String.class));
		ArrayList<String> instructions = new ArrayList(document.getList​("instructions", String.class));
		return new Recipe(title, description, ingredients, instructions);
	}

	// returns a recipe based on user search query
	public Recipe searchRecipe(String searchTitle, Scanner scanner) {
		// finds a document(s) in MongoDB by title using regex and stores all matches in an arraylist of recipes
		FindIterable<Document> documents = collection.find(regex("title", searchTitle));
		ArrayList<Recipe> recipes = convertToArrayListHelper(documents);

		if (recipes.size() > 0) {
			System.out.println("   __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __");
			System.out.println("  |                                                              |");
			System.out.println("  |   Here are the recipes we found matching your search query   |");
			System.out.println("  |__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __|");
			System.out.println();
			int i = 1;
			for (Recipe r: recipes) {
				System.out.println(i + ". " + r.getTitle());
				i++;
			}
			System.out.println();
	
			// select a recipe from the list of all matching recipes
			System.out.println("Enter the number from the list of recipes you want to select");
			String indexStr = scanner.nextLine();
			int index = Integer.parseInt(indexStr) - 1;
			if (index > -1 && index < recipes.size()) {
				return recipes.get(index);
			}
		}
		return null;
	}

	// display all recipes in the recipe book so the user can select one
	public Recipe browseRecipe(Scanner scanner) {
		// get list of all recipes from MongoDB database
		FindIterable<Document> documents = collection.find();
		ArrayList<Recipe> recipes = convertToArrayListHelper(documents);

		System.out.println("   __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __");
		System.out.println("  |                                                              |");
		System.out.println("  |         Here are all the recipes in your recipe book         |");
		System.out.println("  |__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __|");
		System.out.println();
		int i = 1;
		for (Recipe r: recipes) {
			System.out.println(i + ". " + r.getTitle());
			i++;
		}
		System.out.println();

		System.out.println("Enter the number from the list of recipes you want to select");
		String indexStr = scanner.nextLine();
		int index = Integer.parseInt(indexStr) - 1;
		if (index > -1 && index < recipes.size()) {
			return recipes.get(index);
		}
		return null;
	}
}
