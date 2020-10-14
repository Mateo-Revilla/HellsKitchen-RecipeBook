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

	public void testInsert() {
		Document document = new Document();
		document.append("title", "THETITLE");
		document.append("description", "DESCRIPTION");
		List<String> ingredients = Arrays.asList("a","b","C");
		document.append("ingredients", ingredients);
		List<String> instructions = Arrays.asList("e","d","f");
		document.append("instructions", instructions);
		collection.insertOne(document);
	}
	
	// adds recipe to local memory and JSON database
	public void addRecipeToDatabase(Recipe recipe) {
		Document document = new Document();
		document.append("id", recipe.getId());
		document.append("title", recipe.getTitle());
		document.append("description", recipe.getDescription());
		document.append("ingredients", recipe.getIngredients());
		document.append("instructions", recipe.getInstructions());
		collection.insertOne(document);
	}
	
	// prompts user for new recipe information then stores it
	public void createRecipe(Scanner scanner) {
		System.out.println("Welcome to creating a recipe!");
		
		System.out.println("What will the recipe be called?");
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

	public Recipe searchRecipe(String searchTitle) {
		return null;	
	}

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
