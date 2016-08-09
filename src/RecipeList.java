import java.util.ArrayList;
import java.util.*;
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  RecipeWrangler.java

/**
 * This class represents a list of all the recipes. It uses an ArrayList to 
 * store the recipes, but also has other features that allow for more 
 * flexibility. The recipe list has the ability to display the recipes in the
 * list, print the number of recipes, and check the ArrayList to see if there
 * exists another recipe that has the same recipe name.
 *
 * <p>
 * Bugs: (No known bugs)
 */
public class RecipeList
	{
	//ArrayList field to hold instances of the Recipe class
	ArrayList<Recipe> recipesList;

	/**
	 * Constructor for the RecipeList class that instantiates the field
	 * that holds the a reference to an ArrayList for the recipes 
	 *
	 */
	public RecipeList()
	{
		recipesList = new ArrayList<Recipe>();
	}
	
	/**
	 * This method adds a new recipe to the ArrayList in this class
	 * 
	 * @param newrecipe new recipe that needs to be assigned to the ArrayList
	 * 
	 */
	public void add(Recipe newrecipe)
	{
		recipesList.add(newrecipe);
	}
	
	/**
	 * Displays all the recipes in the ArrayList to the console when called
	 *
	 */
	public void displayRecipes()
	{
		for(int i = 0; i < recipesList.size(); i++){
			String soloRecipe = "" + recipesList.get(i).getRecipeName();
			System.out.println(soloRecipe);
		}
	}
	
	/**
	 * Sorts ArrayList holding recipes according to comparable 
	 * interface implemented by the Recipe class
	 * 
	 */
	public void sort()
	{
		recipesList.sort(null);
	}
	
	/**
	 * Returns size of ArrayList holding recipes in this class
	 * 
	 *@return returns size of ArrayList holding recipes
	 *
	 */
	public int size()
	{
		return recipesList.size();
	}
	



	/**
	 * Recipe that gives the RecipeWrangler class a way to access a
	 * reference to a specific recipe
	 *
	 * @param int i number that is passed in to access a recipe at the 
	 * specified index in the ArrayList 
	 * 
	 * @return Recipe that the main class may need to edit
	 */
	public Recipe getRecipe(int i)
	{
		return recipesList.get(i);
	}		
	
	/**
	 * When the user enters a recipe name this method checks to
	 * see if there already exists a recipe with this name. If there 
	 * is a match it returns a reference to that recipe, so the main
	 * class can edit it.
	 *
	 * @param name	 this parameter is the name of the recipe that the 
	 * user entered
	 * @return 		returns a reference to the recipe if it finds a 
	 * recipe with the same name
	 *
	 */
	public Recipe checkExistence(String name)
	{
		for(int i = 0; i < recipesList.size(); i++){
			if(recipesList.get(i).getRecipeName().equals(name)){
				Recipe foundRecipe = recipesList.get(i);
				return foundRecipe;
			}
		}
		return null;
	}
	
	@Override 
	/**
	 * Method that overides the toString method and returns a String
	 * with the number of recipes in the ArrayList
	 *
	 *
	 * @return 		Returns number of recipes in ArrayList as String
	 */
	public String toString(){	
		  return recipesList.size() + "\n";
	}
}