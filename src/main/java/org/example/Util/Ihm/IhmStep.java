package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.Util.Validator;
import org.example.model.Recipe;
import org.example.model.Step;
import org.example.service.RecipeService;
import org.example.service.StepService;

import java.util.Scanner;

public class IhmStep {
    private final StepService stepService;
    private final Scanner scanner;
    private final RecipeService recipeService ;
    private final Validator<Recipe> recipeValidator;
    private  final  Validator<Step> stepValidator;

    public IhmStep (Scanner scanner){
        this.scanner = scanner;
        stepService = new StepService();
        recipeService = new RecipeService();
        recipeValidator = new Validator<>(scanner);
        stepValidator = new Validator<>(scanner);
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

        System.out.println("Recipe Id :");
        int recipeId = scanner.nextInt();
        scanner.nextLine();
        try{
            Recipe recipeFound = recipeService.findById(recipeId);
            if (recipeValidator.validateRecipe(recipeFound,"Add Steps to ",false,true)){
                System.out.println("How many steps would you add ?");
                int nbrIngredient = scanner.nextInt();
                scanner.nextLine();

                for (int i =0; i< nbrIngredient ; i++){
                    System.out.println("- add step n° "+ (i+1));

                    System.out.println("- Text for the step :");
                    String stepStr = scanner.nextLine();
                    Step step = new Step(stepStr,recipeId);

                    if (stepValidator.validate(step,"Add")){

                        stepService.addStep(stepStr,recipeId);


                    }
                }
            }
        }catch(NotFoundException ex){
            System.out.println("Recipe not found at id :"+ recipeId);
        }
    }


    private void removeStepFromRecipe (){
        System.out.println("-- Remove step from recipe --");

        System.out.println("Recipe Id :");
        int recipeId = scanner.nextInt();
        scanner.nextLine();
        try{
            Recipe recipeFound = recipeService.findById(recipeId);
            if (recipeValidator.validateRecipe(recipeFound,"Remove step from ",false,true)){

                System.out.println("\nStep Id:");
                int stepId = scanner.nextInt();
                scanner.nextLine();
                try{
                    Step step = stepService.findById(stepId);
                    if (stepValidator.validate(step,"Remove")){
                        stepService.deleteStep(stepId);
                        System.out.println(step.getTextStep() + " Deleted");
                    }
                }catch(NotFoundException ex){
                    System.out.println("Ingredient not found at id :"+ stepId);
                }
            }
        }catch(NotFoundException ex){
            System.out.println("Recipe not found at id :"+ recipeId);
        }
    }


}
