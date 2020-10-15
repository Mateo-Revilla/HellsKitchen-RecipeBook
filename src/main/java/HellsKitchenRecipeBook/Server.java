package HellsKitchenRecipeBook;

import java.util.*;
import java.io.*;
import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClientSettings;

public class Server {

	private MongoCollection collection;

	Server() {
		ConnectionString connString = new ConnectionString(
    	"mongodb+srv://user1:hellskitchen@hellskitchenrecipebook.vempc.mongodb.net/HellsKitchenRecipeBook?retryWrites=true&w=majority"
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
	
	// retrieves a recipe from MongoDB database by title and converts it into a recipe object
	// TODO MATEO: need to find the document with "title" = title and extract the title, description, ingredients, and instructions
	// then use the recipe constructor to convert that into a recipe object. Please find a way to extract the fields and Bing will use them
	// to create the recipe object
	// you an remove this function if it's the same as searchRecipe();
	public Recipe retrieveRecipeFromDatabase(String title) {
		//BasicDBObject searchQuery = new BasicDBObject();
		//searchQuery.put("title", title);
		//DBCursor cursor = collection.find(searchQuery);
		//System.out.println(cursor);
		return null;
	}

	// prompts user for new recipe information then stores it
	public void createRecipe(Scanner scanner) {
		System.out.println("Welcome to creating a recipe!");
		
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
		
		System.out.println("Adding the following recipe to recipe book...");
		newRecipe.readAllRecipe();
		addRecipeToDatabase(newRecipe);
	}

	// TODO MATEO: this might have the same functionality as retrieveRecipeFromDatabase actually. This needs to be made in such a way
	// that it can be called by browseRecipe.
	public Recipe searchRecipe(String searchTitle) {
		return null;	
	}

	// TODO DAWSON: Coordinate with mateo on this. This should use the collection variable (recipes database) and print out 
	// each document (recipe) in the database. Then the user will choose a recipe by title, then you call the searchRecipe(title)
	// function to get the recipe. 
	public Recipe browseRecipe(Scanner scanner) {
		/*for (int i = 0; i < this.recipeList.size(); i++) {
			Recipe rec = this.recipeList.get(i);
			String recTitle = rec.getTitle();
			String redId = rec.getId();
			System.out.println(redId + " " + recTitle);
		}
		System.out.println("Please enter the ID number of the recipe you want to select");
		String id = scanner.nextLine();
		for (int i = 0; i < this.recipeList.size(); i++) {
			Recipe rec = this.recipeList.get(i);
			String redId = rec.getId();
			if (id.equals(redId)) {
				return rec;
			}
		}
		//INVALID ID
		System.out.println("No matches found");*/
		return null;
	}
}
