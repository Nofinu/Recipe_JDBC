package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.Util.IngredientTable;
import org.example.model.Ingredient;
import org.example.model.Recipe;
import org.example.service.IngredientRecipeService;
import org.example.service.IngredientService;
import org.example.service.RecipeService;

import java.text.MessageFormat;
import java.util.Scanner;

public class IhmIngredientRecipe {
    private final IngredientRecipeService ingredientRecipeService;
    private final IngredientService ingredientService;
    private final Scanner scanner;
    private final RecipeService recipeService;

    public IhmIngredientRecipe(Scanner scanner) {
        this.scanner = scanner;
        ingredientRecipeService = new IngredientRecipeService();
        ingredientService = new IngredientService();
        recipeService = new RecipeService();
    }

    public void start (){
        int entry;
        do{
            System.out.println("--- --- Recipe Menu --- ---");
            System.out.println("1/ Add ingredient to recipe");
            System.out.println("2/ Remove ingredient from recipe");
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry){
                case 1:
                    addIngredientToRecipe();
                    break;
                case 2:
                    removeIngredientFromRecipe();
                    break;
                default:
                    return;
            }

        }while (entry != 0);
    }

    private void addIngredientToRecipe (){
        System.out.println("-- Add ingredient to recipe --");

        boolean SelectRecipe = true;
        while (SelectRecipe){
            System.out.println("Recipe Id :");
            int recipeId = scanner.nextInt();
            scanner.nextLine();
            try{
                Recipe recipeFound = recipeService.findRecipeById(recipeId);
                System.out.println("Add ingredient to ");
                recipeFound.showRecipe(true, false);
                System.out.println("? y/n");
                String entryValidRecipe = scanner.nextLine();
                if (entryValidRecipe.toLowerCase().contains("y")){
                    System.out.println("How many ingredient would you add ?");
                    int nbrIngredient = scanner.nextInt();
                    scanner.nextLine();

                    for (int i =0; i< nbrIngredient ; i++){
                        System.out.println("- add ingredient n° "+ (i+1));
                        IngredientTable.table(ingredientService.findAllIngredient(),true);

                        boolean addIngredient = true;
                        while (addIngredient){
                            System.out.println("\nIngredient Id:");
                            int ingredientId = scanner.nextInt();
                            scanner.nextLine();
                            try{
                                Ingredient ingredientFound = ingredientService.findIngredientById(ingredientId);
                                System.out.println(MessageFormat.format("Add : {0} ? y/n",ingredientFound.toString()));
                                String entryValidIngredient = scanner.nextLine();
                                if (entryValidIngredient.toLowerCase().contains("y")){
                                    System.out.println("Quantity : ");
                                    String quantity = scanner.nextLine();

                                    ingredientRecipeService.addIngredientRecipe(ingredientId,recipeId,quantity);

                                    addIngredient = false;
                                }else if(entryValidIngredient.toLowerCase().contains("n")){
                                    System.out.println("Add cancel");
                                    addIngredient = false;
                                }else{
                                    System.out.println("Please enter y (Yes) or n (No)");
                                }
                            }catch(NotFoundException ex){
                                System.out.println("Ingredient not found at id :"+ ingredientId);
                            }
                        }
                    }
                    SelectRecipe = false;
                }else if(entryValidRecipe.toLowerCase().contains("n")){
                    System.out.println("Add cancel");
                    SelectRecipe = false;
                }else{
                    System.out.println("Please enter y (Yes) or n (No)");
                }
            }catch(NotFoundException ex){
                System.out.println("Recipe not found at id :"+ recipeId);
            }
        }
    }

    private void removeIngredientFromRecipe (){
        System.out.println("-- Remove ingredient to recipe --");

        boolean SelectRecipe = true;
        while (SelectRecipe){
            System.out.println("Recipe Id :");
            int recipeId = scanner.nextInt();
            scanner.nextLine();
            try{
                Recipe recipeFound = recipeService.findRecipeById(recipeId);
                System.out.println("Remove ingredient from ");
                recipeFound.showRecipe(true,false);
                System.out.println("? y/n");
                String entryValidRecipe = scanner.nextLine();
                if (entryValidRecipe.toLowerCase().contains("y")){

                        boolean RemoveIngredient = true;
                        while (RemoveIngredient){
                            System.out.println("\nIngredient Id:");
                            int ingredientId = scanner.nextInt();
                            scanner.nextLine();
                            try{
                                Ingredient ingredientFound = ingredientService.findIngredientById(ingredientId);
                                System.out.println(MessageFormat.format("Remove : {0} ? y/n",ingredientFound.toString()));
                                String entryValidIngredient = scanner.nextLine();
                                if (entryValidIngredient.toLowerCase().contains("y")){

                                    ingredientRecipeService.deleteIngredientRecipe(ingredientId,recipeId);

                                    RemoveIngredient = false;
                                }else if(entryValidIngredient.toLowerCase().contains("n")){
                                    System.out.println("Remove cancel");
                                    RemoveIngredient = false;
                                }else{
                                    System.out.println("Please enter y (Yes) or n (No)");
                                }
                            }catch(NotFoundException ex){
                                System.out.println("Ingredient not found at id :"+ ingredientId);
                            }

                    }
                    SelectRecipe = false;
                }else if(entryValidRecipe.toLowerCase().contains("n")){
                    System.out.println("Remove cancel");
                    SelectRecipe = false;
                }else{
                    System.out.println("Please enter y (Yes) or n (No)");
                }
            }catch(NotFoundException ex){
                System.out.println("Recipe not found at id :"+ recipeId);
            }
        }
    }

}
