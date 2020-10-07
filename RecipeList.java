import java.util.*;
import org.json.simple.JSONObject;
import java.io.*

public class RecipeList {

	final private String fileName = "database.json";
	private ArrayList<Recipe> recipesList = new ArrayList<>();//array of recipes representing the json database


	//class methods

	//Create
	public boolean createRecipe(Scanner scanner); 
	public boolean addRecipeToDatabase(Recipe recipe);//append to recipesList arraylist

	//Retrieve
	public boolean retrieveRecipe(Scanner scanner);
	public boolean searchRecipe(String title);
	public boolean browseRecipe();

	//Main Helper
	public void extractRecipe();//reads json file and creates recipesList arraylist
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