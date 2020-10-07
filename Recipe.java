import java.util.*;
import org.json.simple.JSONObject;
import java.io.*

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
	
	//Exploration
	public boolean exploreRecipe(Scanner scanner);
	public boolean readAllRecipe();
	public boolean readStepRecipe();

	
	
}



	
	
	
	
