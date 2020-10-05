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
