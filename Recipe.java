import java.util.*;
import org.json.simple.JSONObject;
import java.io.*

public class Recipe {

	//object properties
	private String id;
	private String title;
	private String description;
	private ArrayList<String> ingredients = new ArrayList<>();
	private ArrayList<String> instructions = new ArrayList<>();

	
	//Exploration
	public boolean exploreRecipe(Scanner scanner);
	public boolean readAllRecipe();
	public boolean readStepRecipe();

	
}



	
	
	
	
