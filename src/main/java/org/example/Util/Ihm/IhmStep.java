package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.Util.IngredientTable;
import org.example.model.Ingredient;
import org.example.model.Recipe;
import org.example.model.Step;
import org.example.service.RecipeService;
import org.example.service.StepService;

import java.sql.SQLOutput;
import java.text.MessageFormat;
import java.util.Scanner;

public class IhmStep {
    private final StepService stepService;
    private final Scanner scanner;
    private final RecipeService recipeService ;

    public IhmStep (Scanner scanner){
        this.scanner = scanner;
        stepService = new StepService();
        recipeService = new RecipeService();
    }

    public void start (){
        int entry;
        do{
            System.out.println("--- --- Recipe Menu --- ---");
            System.out.println("1/ Add step to recipe");
            System.out.println("2/ Remove step from recipe");
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry){
                case 1:
                    addStepToRecipe();
                    break;
                case 2:
                    removeStepFromRecipe();
                    break;
                default:
                    return;
            }

        }while (entry != 0);
    }


    private void addStepToRecipe (){
        System.out.println("-- Add Steps to recipe --");

        boolean SelectRecipe = true;
        while (SelectRecipe){
            System.out.println("Recipe Id :");
            int recipeId = scanner.nextInt();
            scanner.nextLine();
            try{
                Recipe recipeFound = recipeService.findRecipeById(recipeId);
                System.out.println("Add Steps to ");
                recipeFound.showRecipe(false,true);
                System.out.println("? y/n");
                String entryValidRecipe = scanner.nextLine();
                if (entryValidRecipe.toLowerCase().contains("y")){
                    System.out.println("How many steps would you add ?");
                    int nbrIngredient = scanner.nextInt();
                    scanner.nextLine();

                    for (int i =0; i< nbrIngredient ; i++){
                        System.out.println("- add step n° "+ (i+1));

                        boolean addStep = true;
                        while (addStep){
                            System.out.println("- Text for the step :");
                            String stepStr = scanner.nextLine();

                                System.out.println(MessageFormat.format("Add : \n{0} \n? y/n",stepStr));
                                String entryValidIngredient = scanner.nextLine();
                                if (entryValidIngredient.toLowerCase().contains("y")){

                                    stepService.addStep(stepStr,recipeId);

                                    addStep = false;
                                }else if(entryValidIngredient.toLowerCase().contains("n")){
                                    System.out.println("Add cancel");
                                    addStep = false;
                                }else{
                                    System.out.println("Please enter y (Yes) or n (No)");
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

    private void removeStepFromRecipe (){
        System.out.println("-- Remove step from recipe --");

        boolean SelectRecipe = true;
        while (SelectRecipe){
            System.out.println("Recipe Id :");
            int recipeId = scanner.nextInt();
            scanner.nextLine();
            try{
                Recipe recipeFound = recipeService.findRecipeById(recipeId);
                System.out.println("Remove step from ");
                recipeFound.showRecipe(false,true);
                System.out.println("? y/n");
                String entryValidRecipe = scanner.nextLine();
                if (entryValidRecipe.toLowerCase().contains("y")){

                    boolean RemoveStep = true;
                    while (RemoveStep){
                        System.out.println("\nStep Id:");
                        int stepId = scanner.nextInt();
                        scanner.nextLine();
                        try{
                            Step step = stepService.findStepById(stepId);
                            System.out.println(MessageFormat.format("Remove : \n{0} \n? y/n",step.getTextStep()));
                            String entryValidStep = scanner.nextLine();
                            if (entryValidStep.toLowerCase().contains("y")){

                                stepService.deleteStep(stepId);

                                RemoveStep = false;
                            }else if(entryValidStep.toLowerCase().contains("n")){
                                System.out.println("Remove cancel");
                                RemoveStep = false;
                            }else{
                                System.out.println("Please enter y (Yes) or n (No)");
                            }
                        }catch(NotFoundException ex){
                            System.out.println("Ingredient not found at id :"+ stepId);
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
