import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RecipeWrangler2 {
	//input variable to select menu choices
	private  int choice;
	private  RecipeList ourList = new RecipeList();
	private  Scanner scnr = new Scanner(System.in); 
	private  Scanner filescnr;
	private  PrintWriter printWriter;

	

	public  boolean mainMenu(){
		System.out.println("Recipe Wrangler 2015\nLet us help you "+ "track"
				+ " your favorite recipes");
		//Create scanner to read input
		
		System.out.println("Main Menu\n---------\n1. "
				+ "Display recipe names (sorted)\n"
				+ "2. Display/Edit/Add a recipe\n"
				+ "3. Load recipes from a file\n"
				+ "4. Save recipes to a file\n"
				+ "5. Exit\n"
				+ "Enter choice:");
		choice = scnr.nextInt();
		if (subMenu(choice)){ 
			return true;
		}else {
			return false;
		}	
	}
	
	public boolean subMenu(int choice){
		switch(choice){
		case 1: 
			displayRecipes();
			return true;
	
		case 2: System.out.println("What is the name of the recipe?");
			String recipe = scnr.nextLine();
			recipe = recipe.trim().toUpperCase();
			scnr.nextLine();
			if(ourList.checkExistence(recipe) != null){
				Recipe updateRecipe = ourList.checkExistence(recipe);
				System.out.println(updateRecipe);
				editMenu(updateRecipe);
			}
			else{
				createRecipe(recipe);
			}
			return true;
	
		case 3: 
			System.out.println("Enter Filename: ");
			String loadFile = scnr.nextLine();
			int recipesAdded=0;
			try{
				fileLoading(loadFile);	
			}catch(FileNotFoundException e){
				System.out.println("Unable to read from file: " + loadFile);
				
			}finally{
				System.out.println("Added " + recipesAdded + " recipes from: " + loadFile);
			}
			return true;
		case 4: 
		System.out.println("Enter Filename:");
		String fileName = scnr.nextLine();
			try{
				writeFile(fileName);	
			} catch (FileNotFoundException e){
				System.out.println(fileName + " not found");
			}
		System.out.println("Saved" + " "+  ourList.size() + " recipes to " + fileName);
		return true;
		
		case 5:System.out.println("Thanks for using RecipeWrangler!");
			System.exit(0);	
			return false;
			
		}
		return false;
	}
	
	public  void editMenu(Recipe updateRecipe){
		boolean stillEditing = true;
		while(stillEditing){
			System.out.println("Edit Recipe Menu\n1.Edit Ingredients\n2.Edit Instructions");
			int userChoice = scnr.nextInt();
			scnr.nextLine();
			if(userChoice == 1){
				System.out.println("What are the new ingredients?");
				String newIngredients = scnr.nextLine();
				updateRecipe.setIngredients(newIngredients);
			}else if (userChoice == 2){
				System.out.println("What are the new instructions?");
				String newInstructions = scnr.nextLine();
				updateRecipe.setInstructions(newInstructions);
			}else {
				stillEditing = false;	
			}
		}
	}
	public  void displayRecipes(){
		if(ourList.size() ==0){
			System.out.println("No recipes");
		}else{
			ourList.displayRecipes();
		}
	}

	public  void createRecipe(String recipe){
		System.out.println("Adding recipe for: "+ recipe);
		System.out.println("What are the ingredients?");
		String ingredients = scnr.nextLine();
		System.out.println("What are the instructions?");
		String instructions = scnr.nextLine();
		Recipe newRecipe = new Recipe(recipe,ingredients, instructions);
		ourList.add(newRecipe);
		ourList.sort();
		ourList.displayRecipes();
	}
	public  void fileLoading(String loadFile) throws FileNotFoundException{
		File importFile = new File(loadFile);
		filescnr = new Scanner(importFile);
		int recipesAdded = filescnr.nextInt();
		filescnr.nextLine();
		for (int i =1; i <= recipesAdded; i++){
			String recipeName = filescnr.nextLine();
			recipeName = recipeName.toUpperCase();
			recipeName = recipeName.trim();
			String recipeIngredients = filescnr.nextLine();
			String recipeInstructions = filescnr.nextLine();
			Recipe r = new Recipe(recipeName, recipeIngredients, recipeInstructions); 
			ourList.add(r);
			System.out.println("Added " + recipeName);
		}
	}
	public void writeFile(String fileName) throws FileNotFoundException{
		File recipeFile = new File(fileName);
		printWriter = new PrintWriter(recipeFile);
		printWriter.print(ourList);
		for(int i = 0; i < ourList.size(); i++){
			printWriter.print(ourList.getRecipe(i));
		}
		printWriter.close();
	}
	public static void main(String[] args){
		RecipeWrangler wrangler = new RecipeWrangler();
		mainMenu();
		while(mainMenu()){
			subMenu(choice);
		}
	}
}