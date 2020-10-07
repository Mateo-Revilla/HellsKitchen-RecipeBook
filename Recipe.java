import java.util.*;
import org.json.simple.JSONObject;

public class Recipe {

	//object properties
	private String title;
	private String description;
	private ArrayList<String> ingredients = new ArrayList<>();
	private ArrayList<String> instructions = new ArrayList<>();

	//class properties
	static JSONObject database = new JSONObject();


	//class methods

	static public void addRecipe();

	static public void extractRecipe();


	

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
	
	
	
	
