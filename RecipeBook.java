import java.util.*;
//import org.json.simple.JSONObject;
import java.io.*;


public class RecipeBook {

	public static void main(String[] args) {

		//START
		Scanner scanner = new Scanner(System.in);
		RecipeList recipeList = new RecipeList();
		//recipeList.extractRecipe();

		// TEST
		recipeList.createRecipe(scanner);
		//UI
		



		//END
		scanner.close();

	}

}