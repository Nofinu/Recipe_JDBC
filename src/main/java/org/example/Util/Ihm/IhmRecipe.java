package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.model.Recipe;
import org.example.service.RecipeService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

public class IhmRecipe {
    private final RecipeService recipeService;
    private final Scanner scanner;
    private final IhmIngredientRecipe ihmIngredientRecipe;
    private final IhmStep ihmStep;

    public IhmRecipe (Scanner scanner){
        this.scanner = scanner;
        recipeService = new RecipeService();
        ihmIngredientRecipe = new IhmIngredientRecipe(scanner);
        ihmStep = new IhmStep(scanner);
    }

    public void start (){
        int entry;
        do{
            System.out.println("--- --- Recipe Menu --- ---");
            System.out.println("1/ Create Recipe");
            System.out.println("2/ Delete Recipe");
            System.out.println("3/ Update Recipe");
            System.out.println("4/ Manage Ingredient to Recipe");
            System.out.println("5/ Manage Step to Recipe");
            System.out.println("6/ Show all Recipe ");
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry){
                case 1:
                    createRecipe();
                    break;
                case 2:
                    deleteRecipe();
                    break;
                case 3:
                    updateRecipe();
                    break;
                case 4:
                    ihmIngredientRecipe.start();
                    break;
                case 5:
                    ihmStep.start();
                    break;
                case 6:
                    findAllRecipe();
                    break;
                default:
                    return;
            }

        }while (entry != 0);
    }

    private void createRecipe (){
        System.out.println("-- Create Recipe --");
        System.out.println("Enter recipe name :");
        String name = scanner.nextLine();

        System.out.println("Enter Preparation time for the recipe (in minutes)");
        int prepTime = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter cooking time for the recipe (in minutes)");
        int cookTime = scanner.nextInt();
        scanner.nextLine();


        boolean entryIsValidNotvalid = true;
        int difficulty = 0;
        while (entryIsValidNotvalid){
            System.out.println("Enter the difficulty of the recipe (1 : Easy / 2 : Medium / 3 : Hard)");
            difficulty = scanner.nextInt();
            scanner.nextLine();
            if(difficulty<4 && difficulty>0){
                entryIsValidNotvalid = false;
            }
        }

        if(recipeService.addRecipe(name,prepTime,cookTime,difficulty)){
            System.out.println("Recipe Add");
        }else{
            System.out.println("Error when adding Recipe");
        }
    }

    private void deleteRecipe (){
        System.out.println("-- Delete Recipe --");
        System.out.println("enter recipe Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            Recipe recipeFound = recipeService.findRecipeById(id);
            boolean deleteNotFInish = true;
            while (deleteNotFInish){
                System.out.println("Delete : ");
                recipeFound.showRecipe(false,false);
                System.out.println(" ? y/n");
                String entryValid = scanner.nextLine();
                if (entryValid.toLowerCase().contains("y")){
                    recipeService.deleteRecipe(id);
                    System.out.println("Recipe Delete");
                    deleteNotFInish = false;
                }else if(entryValid.toLowerCase().contains("n")){
                    System.out.println("Delete cancel");
                    deleteNotFInish = false;
                }else{
                    System.out.println("Please enter y (Yes) or n (No)");
                }
            }
        }catch (NotFoundException ex){
            System.out.println("Recipe not found");
        }
    }

    private void updateRecipe (){
        System.out.println("-- Update Recipe --");
        System.out.println("enter Recipe Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            Recipe recipeFound = recipeService.findRecipeById(id);
            boolean updateNotFinish = true;
            while (updateNotFinish){
                System.out.println("Update : ");
                recipeFound.showRecipe(false ,false);
                System.out.println("? y/n");
                String entryValid = scanner.nextLine();
                if (entryValid.toLowerCase().contains("y")){

                    System.out.println("New name for : " + recipeFound.getName());
                    String name = scanner.nextLine();

                    System.out.println("New preparation time, current preparation time : " + recipeFound.getPrepTime());
                    int prepTime = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("New cooking time, current cooking time : " + recipeFound.getCookTime());
                    int cookTime = scanner.nextInt();
                    scanner.nextLine();

                    boolean entryIsValidNotvalid = true;
                    int difficulty = 0;
                    while (entryIsValidNotvalid){
                        System.out.println("Enter the difficulty of the recipe (1 : Easy / 2 : Medium / 3 : Hard)");
                        difficulty = scanner.nextInt();
                        scanner.nextLine();
                        if(difficulty<4 && difficulty>0){
                            entryIsValidNotvalid = false;
                        }
                    }

                    recipeService.editRecipe(id,name,prepTime,cookTime,difficulty);
                    System.out.println("Recipe Update");
                    updateNotFinish = false;
                }else if(entryValid.toLowerCase().contains("n")){
                    System.out.println("Update cancel");
                    updateNotFinish = false;
                }else{
                    System.out.println("Please enter y (Yes) or n (No)");
                }
            }
        }catch (NotFoundException ex){
            System.out.println("Ingredient not found");
        }
    }

    public void findAllRecipe (){
        List<Recipe> recipes = recipeService.findAllRecipe();
        System.out.println("-- Recipes --");

        System.out.println();
        for(Recipe recipe : recipes){

            recipe.showRecipe(false,false);
            System.out.println();
        }
    }



}

