package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.Util.Table;
import org.example.Util.Validator;
import org.example.dao.CategorieDAO;
import org.example.model.BaseShowMethod;
import org.example.model.Categorie;
import org.example.model.Recipe;
import org.example.service.CategorieService;
import org.example.service.RecipeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IhmRecipe {
    private final RecipeService recipeService;
    private final Scanner scanner;
    private final IhmIngredientRecipe ihmIngredientRecipe;
    private final IhmStep ihmStep;
    private final Validator<Recipe> recipeValidator;
    private final Validator<Categorie> categorieValidator;
    private final CategorieService categorieService;

    public IhmRecipe (Scanner scanner){
        this.scanner = scanner;
        recipeService = new RecipeService();
        ihmIngredientRecipe = new IhmIngredientRecipe(scanner);
        ihmStep = new IhmStep(scanner);
        recipeValidator = new Validator<>(scanner);
        categorieService = new CategorieService();
        categorieValidator = new Validator<>(scanner);
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
        }while (true);
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

        entryIsValidNotvalid = true;
        Categorie categorie = null;
        while (entryIsValidNotvalid){
            try{
                int categorie_id;
                List<BaseShowMethod> categories = new ArrayList<>(categorieService.findAllICategorie());
                Table.table(categories,true);
                System.out.println("Categorie Id :");
                categorie_id = scanner.nextInt();
                scanner.nextLine();
                categorie = categorieService.findById(categorie_id);
                if(categorieValidator.validate(categorie,"Add :"))
                    entryIsValidNotvalid = false;
            }catch(NotFoundException ex){
            System.out.println("Enter a valid Id.");
            }
        }

        if(recipeService.addRecipe(name,prepTime,cookTime,difficulty, categorie)){
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
            Recipe recipeFound = recipeService.findById(id);
            if (recipeValidator.validateRecipe(recipeFound,"Delete : ",false,false)){
                recipeService.deleteRecipe(id);
                System.out.println("Recipe Delete");
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
            Recipe recipeFound = recipeService.findById(id);

                if (recipeValidator.validateRecipe(recipeFound,"Delete : ",false,false)){
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

                    entryIsValidNotvalid = true;
                    Categorie categorie = null;
                    while (entryIsValidNotvalid){
                        int categorie_id = 0;
                        System.out.println("Categorie Id :");
                        categorie_id = scanner.nextInt();
                        scanner.nextLine();
                        try{
                            categorie = categorieService.findById(categorie_id);
                            if(categorieValidator.validate(categorie,"Add :"))
                                entryIsValidNotvalid = false;

                        } catch (NotFoundException ex){
                            System.out.println("Enter a valid Id.");
                        }
                    }
                    recipeService.editRecipe(id,name,prepTime,cookTime,difficulty,categorie);
                    System.out.println("Recipe Update");
                }
        }catch (NotFoundException ex){
            System.out.println("Recipe not found");
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

