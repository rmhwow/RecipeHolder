import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.*;

public class RecipeWrangler 
{
	//instantiate a new object called ourList that will later contain Objects
	//of the type Recipe
	private static RecipeList ourList = new RecipeList();

	public static void main(String[] args)
	{
		//create a new printwriter object that can be initialized when needed

		//Print welcome message
		System.out.println("Recipe Wrangler 2015\nLet us help you keep "
				+ "track of your favorite recipes.");
		//Create scanner to read input
		Scanner scnr = new Scanner(System.in); 
		Scanner filescnr;
		//input variable to select menu choices
		int choice;
		//While the user is still playing display the menu options and create 
		// a case statement with the specific instructions for that option
		boolean stillPlaying = true;
		while(stillPlaying == true)
		{
			//displaying the menu			
			System.out.println("Main Menu\n---------\n1. "
					+ "Display recipe names (sorted)\n"
					+ "2. Display/Edit/Add a recipe\n"
					+ "3. Load recipes from a file\n"
					+ "4. Save recipes to a file\n"
					+ "5. Exit\n"
					+ "Enter choice:");
			boolean end = false;
			boolean valid = false;
			//set choice in order to validate the user input
			choice = -1;
			//create a loop that checks if the user input is valid.		
			do
			{
				try
				{
					choice = scnr.nextInt();
					//If the number is not valid print message
					if(choice <= 0 || choice > 5)
					{
						System.out.print("Enter integer between 1-5:");
					}else
					{
						//exit the loop	
						valid = true;
					}
					//if the input is a string catch the exception 
					//and print message					
				}catch(InputMismatchException e)
				{
					System.out.print("Enter integer between 1-5:");
					//clear scanner					
					scnr.nextLine();
				}
			}while(valid == false);

			//clear the scanner			
			scnr.nextLine();
			//When input is correct and while the user is playing			
			while(!end)
			{
				//create case statement to write specific instructions for 
				//each user option				
				switch(choice)
				{				
				case 1: 
					//if there are no recipe objects in ourList, print out a 
					//message otherwise display the recipes					
					if(ourList.size() == 0)
					{
						System.out.println("No recipes");
					}else
					{
						ourList.displayRecipes();
					}
					//end the inner loop and display the main menu					
					end = true;
					//ends specific case					
					break;
				case 2: System.out.println("What is the name of the recipe?");
				//save user input to a string variable, make the recipe name 
				//upper cased and remove any extra whitespace				
				String recipe = scnr.nextLine();
				recipe = recipe.toUpperCase();
				recipe = recipe.trim();
				//checks to see if the user input matches an existing recipe				
				if(ourList.checkExistence(recipe) != null)
				{
					//save the existing recipe to a variable then display recipe					
					Recipe updateRecipe = ourList.checkExistence(recipe);

					//create boolean to represent when we are currently editing 
					//our recipe					
					boolean stillEditing = true;
					//create a new loop to display the editing menu					
					while(stillEditing)
					{
						System.out.println("Found recipe for: " + 
								updateRecipe.getRecipeName() + "\n" + "Recipe name: " + 
								updateRecipe.getRecipeName() + "\n"+ 
								"Ingredients: "+  updateRecipe.getIngredients()
								+ "\n"+ "Instructions: "+
								updateRecipe.getInstructions());
						//display the edit menu						
						System.out.println("1. Edit ingredient list\n"
								+ "2. Edit instructions\n3. Done editing\n"
								+ "Enter choice: ");
						// get user input and then clear the scanner						
						int userChoice = scnr.nextInt();
						scnr.nextLine();
						// If the user input is one or two, change the Recipe 
						//attribute to the string supplied by the user					
						if(userChoice == 1)
						{
							System.out.println("What are the new ingredients?");
							String newIngredients = scnr.nextLine();
							updateRecipe.setIngredients(newIngredients);
						}else if (userChoice == 2)
						{
							System.out.println("What are the "
									+ "new instructions?");
							String newInstructions = scnr.nextLine();
							updateRecipe.setInstructions(newInstructions);
						}
						//if the input is not one or two, exit the loop						
						else 
						{
							stillEditing = false;
						}
					}
				}
				//if the recipe does not exist in ourList (in the instantiate 
				//RecipesList object) create the recipe and add it to ourList			
				else
				{
					System.out.println("Adding recipe for: " + recipe);
					System.out.println("Enter the ingredients:");
					//grab the new ingredients and instructions from the user 
					//and create a new instance of the Recipe class.  					
					String ingredients = scnr.nextLine();

					System.out.println("Enter the instructions: ");

					String instructions = scnr.nextLine();

					Recipe newRecipe = new Recipe(recipe,ingredients, 
							instructions);
					//add the new Recipe to ourList, 
					//sort the list of recipes, and display the recipes					
					ourList.add(newRecipe);
					ourList.sort();
					//ourList.displayRecipes();
				}
				//end inner loop and display main menu				
				end = true;
				//end specific case				
				break;
				case 3: 
					System.out.println("Enter filename: ");
					//save the user input to a String variable					
					String loadFile = scnr.nextLine();
					// set the number of recipes added to 0. 					
					int recipesAdded = 0;
					//If the file exists, load the recipes from the file, 
					//otherwise catch the FileNotFound Exception 
					//and display a message to the user. 					
					try
					{
						File importFile = new File(loadFile);
						//create a new scanner to read from file						
						filescnr = new Scanner(importFile);
						//grab the first number displayed and 
						//assign it to recipesAdded						
						recipesAdded = filescnr.nextInt();
						filescnr.nextLine();
						//loop through the file up to the number of recipes 
						//specified at the top of the file	 
						for (int i = 1; i <= recipesAdded; i++)
						{
							//assign the recipe name to a variable, trim the 
							//white space and uppercase the name. 							
							String recipeName = filescnr.nextLine();
							recipeName = recipeName.toUpperCase();
							recipeName = recipeName.trim();
							// assign a variable to the line with ingredients 							
							String recipeIngredients = filescnr.nextLine();
							//assign a variable to the line with instrutions
							String recipeInstructions = filescnr.nextLine();
							//add name,ingredients,and instructions to a new
							//instance of Recipe
							Recipe r = new Recipe(recipeName, recipeIngredients,
									recipeInstructions); 
							//add recipe instances to ourList
							ourList.add(r);
							//display the name of the recipe added
							System.out.println("Added " + recipeName);
						}
						ourList.sort();
					}
					//if the file doesn't exist display a message to the user
					catch(FileNotFoundException e)
					{
						System.out.println("Unable to read from file: " + 
								loadFile);

					}
					//No matter if the file worked or not, display the number 
					//of recipes added from the file name given by the user					
					finally
					{
						System.out.println("Added " + recipesAdded + 
								" recipes from " + loadFile);
					}
					//end the loop and display main menu again
					end = true;
					//end the case statement
					break;

				case 4: 
					System.out.println("Enter Filename:");
					//save user input to a String variable fileName
					String fileName = scnr.nextLine();
					try
					{

						//create a new file with the name from the user input
						File recipeFile = new File(fileName);
						//create a new printwriter for the specified file
						PrintWriter pw = new PrintWriter(recipeFile);
						//writes to the specified file with the recipes 
						//contained in ourList
						//prints the number of recipes in ourList (via toString)
						pw.print(ourList);
						//write each recipe to in ourList with the appropriate
						//information to the file 
						for(int i = 0; i < ourList.size(); i++){
							pw.print(ourList.getRecipe(i));
						}
						//display the number of recipes save and 
						//file name to the user
						System.out.println("Saved" + " "+  ourList.size() + 
								" recipes to " + fileName);
						//close the print writer so no extra 
						//information is taken
						pw.close();
					} 
					//if the file isn't found print a message to the user
					catch (FileNotFoundException e)
					{
						System.out.println("Unable to write to file: "+ 
								fileName );
						//Print message to user about file status and 
						//number of recipes not added 
						System.out.println("Saved" + " "+  "0" + 
								" recipes to " + fileName);
					}

					//end the current loop and display the main menu
					end = true;
					//end the specific case
					break;

				case 5:
					//print exit message to the user
					System.out.println("Thanks for using RecipeWrangler!");
					//end inner loop
					end = true;
					//end main menu loop
					stillPlaying = false; 
					//exit successfully 
					System.exit(0);
					//ends specific case
					break;
					//ends switch statement
				}
				//end inner loop
			}
			//end main menu loop	
		}
		//close the scanner 
		scnr.close();
		//ends main method		
	}
	//ends the RecipeWrangler Class
}
