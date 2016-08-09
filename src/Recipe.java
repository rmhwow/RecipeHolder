

/**
 * This class represents one recipe. It holds the name of the recipe, the
 * ingredients required, and the instructions to the recipe. It also implements
 * the Comparable interface so we can compare one instance of a recipe to
 * another.
 *
 * <p>
 * Bugs: (No known bugs)
 *
 * @author Morgan O-Leary and Taylor Ross
 */
public class Recipe implements Comparable<Recipe> {
	// Private variables to hold the ingredients, instructions, and recipe name
	private String ingredients;
	private String instructions;
	private String recipeName;

	/**
	 * Constructor for the recipe class that takes in 3 parameters to create and
	 * instance of the recipe class.
	 *
	 * @param recipeName
	 *            Name of the recipe to be instantiated
	 * @param ingredients
	 *            Ingredients needed for the recipe
	 * @param instructions
	 *            Instructions for the recipe
	 */
	public Recipe(String recipeName, String ingredients, String instructions) {
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	/**
	 * Mutator method to allow the user to change the ingredients of a specific
	 * recipe
	 *
	 * @param ingredients
	 *            ingredients needed for recipe
	 */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Mutator method to allow the user to change the instructions for a
	 * specific recipe
	 *
	 * @param instructions
	 *            instructions for the recipe
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * Getter method to access the private recipe name
	 *
	 * @param recipeName
	 *            Name of recipe
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * Getter method to access the private ingredients to the recipe
	 *
	 * @param ingredients
	 */
	public String getIngredients() {
		return ingredients;
	}

	/**
	 * Getter method to access the private instructions to the recipe
	 *
	 * @param instructions
	 *            instructions to the recipe
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * Returns a negative int value if the name of "this" recipe is
	 * lexigraphically less than the name of otherRecipe.
	 * 
	 * Returns a positive int value if the name of "this" recipe is
	 * lexigraphically greater than the name of otherRecipe.
	 *
	 * returns 0, if the name of "this" recipe is the same as the name of
	 * otherRecipe.
	 *
	 * @param r
	 *            Recipe that "this" recipe is being compared to
	 * @return returns a positive int if "this" recipe is greater than 
	 * other, returns a negative int if "this" recipe is less than the other
	 * recipe, and returns zero if "this" name is the same as the other
	 */
	@Override
	public int compareTo(Recipe r) {
		return this.recipeName.compareTo(r.getRecipeName());
	}

	/**
	 * Overridden toString method that upon being called displays the recipe
	 * name, ingredients, and instructions
	 *
	 * @return returns the name of the recipe, its ingredients, and its
	 *         instructions all seperated by a new line.
	 */
	@Override
	public String toString() {
		return  this.recipeName + "\n"+  this.ingredients + "\n" + 
				this.instructions + "\n";
	}
}