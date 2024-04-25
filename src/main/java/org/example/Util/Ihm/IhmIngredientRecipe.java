package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.Util.IngredientTable;
import org.example.Util.Validator;
import org.example.model.Ingredient;
import org.example.model.Recipe;
import org.example.service.IngredientRecipeService;
import org.example.service.IngredientService;
import org.example.service.RecipeService;

import java.util.Scanner;

public class IhmIngredientRecipe {
    private final IngredientRecipeService ingredientRecipeService;
    private final IngredientService ingredientService;
    private final Scanner scanner;
    private final RecipeService recipeService;
    private final Validator<Recipe> recipeValidator;
    private final Validator<Ingredient> ingredientValidator;


    public IhmIngredientRecipe(Scanner scanner) {
        this.scanner = scanner;
        ingredientRecipeService = new IngredientRecipeService();
        ingredientService = new IngredientService();
        recipeService = new RecipeService();
        recipeValidator = new Validator<>(scanner);
        ingredientValidator = new Validator<>(scanner);
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
        }while (true);
    }

    private void addIngredientToRecipe (){
        System.out.println("-- Add ingredient to recipe --");
        System.out.println("Recipe Id :");
        int recipeId = scanner.nextInt();
        scanner.nextLine();
        try{
            Recipe recipeFound = recipeService.findById(recipeId);
            if (recipeValidator.validateRecipe(recipeFound,"Add ingredient to ",true,false)) {
                System.out.println("How many ingredient would you add ?");
                int nbrIngredient = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < nbrIngredient; i++) {
                    System.out.println("- add ingredient n° " + (i + 1));
                    IngredientTable.table(ingredientService.findAllIngredient(), true);
                    System.out.println("\nIngredient Id:");
                    int ingredientId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Ingredient ingredientFound = ingredientService.findById(ingredientId);
                        if (ingredientValidator.validate(ingredientFound,"Add")) {
                            System.out.println("Quantity : ");
                            String quantity = scanner.nextLine();
                            ingredientRecipeService.addIngredientRecipe(ingredientId, recipeId, quantity);
                        }
                    } catch (NotFoundException ex) {
                        System.out.println("Ingredient not found at id :" + ingredientId);
                    }
                }
            }
        }catch(NotFoundException ex){
            System.out.println("Recipe not found at id :"+ recipeId);
        }
    }

    private void removeIngredientFromRecipe (){
        System.out.println("-- Remove ingredient to recipe --");
        System.out.println("Recipe Id :");
        int recipeId = scanner.nextInt();
        scanner.nextLine();
        try{
            Recipe recipeFound = recipeService.findById(recipeId);
            if (recipeValidator.validateRecipe(recipeFound,"Remove ingredient from ",true,false)){
                System.out.println("\nIngredient Id:");
                int ingredientId = scanner.nextInt();
                scanner.nextLine();
                try{
                    Ingredient ingredientFound = ingredientService.findById(ingredientId);
                    if (ingredientValidator.validate(ingredientFound,"Remove")){
                        ingredientRecipeService.deleteIngredientRecipe(ingredientId,recipeId);
                    }
                }catch(NotFoundException ex){
                    System.out.println("Ingredient not found at id :"+ ingredientId);
                }
            }
        }catch(NotFoundException ex){
            System.out.println("Recipe not found at id :"+ recipeId);
        }
    }
}
