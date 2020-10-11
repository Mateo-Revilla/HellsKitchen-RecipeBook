import java.util.*;
import org.json.simple.*;
import java.io.*;


public class RecipeBook {

	public static void main(String[] args) {

		//START
		Scanner scanner = new Scanner(System.in);
		RecipeList recipeList = new RecipeList();
		UserInterface ui = new UserInterface(scanner, recipeList);
		recipeList.extractRecipe();
		
		//UI
		ui.execute();

		//END
		recipeList.saveDatabase();
		scanner.close();

	}

}

/*

recipeList.displayRecipeList();

// testing creation of recipes
		System.out.println("Enter random character or 'end' to end. ");
		String end = scanner.nextLine().toLowerCase();
		while (!end.equals("end")) {
			recipeList.createRecipe(scanner);
			System.out.println("Enter random character or 'end' to end");
			end = scanner.nextLine().toLowerCase();
		}

*/