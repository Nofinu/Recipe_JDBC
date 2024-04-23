package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.Util.IngredientTable;
import org.example.model.Ingredient;
import org.example.service.IngredientService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

public class IhmIngredient {
    private IngredientService ingredientService;
    private Scanner scanner;

    public IhmIngredient (Scanner scanner){
        this.scanner = scanner;
        ingredientService = new IngredientService();
    }

    public void start (){
        int entry;
        do{
            System.out.println("--- --- Ingredient Menu --- ---");
            System.out.println("1/ Create ingredient");
            System.out.println("2/ Delete ingredient");
            System.out.println("3/ Update ingredient");
            System.out.println("4/ Show all ingredient ");
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry){
                case 1:
                    createIngredient();
                    break;
                case 2:
                    deleteIngredient();
                    break;
                case 3:
                    updateIngredient();
                    break;
                case 4:
                    findAllIngredient();
                    break;
                default:
                    return;
            }

        }while (entry != 0);
    }

    private void createIngredient (){
        System.out.println("-- Create Ingredient --");
        System.out.println("enter ingredient name :");
        String name = scanner.nextLine();

        if(ingredientService.addIngredient(name)){
            System.out.println("Ingredient Add");
        }else{
            System.out.println("Error when adding Ingredient");
        }
    }

    private void deleteIngredient (){
        System.out.println("-- Delete Ingredient --");
        System.out.println("enter ingredient Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            Ingredient ingredientFound = ingredientService.findIngredientById(id);
            boolean deleteNotFInish = true;
            while (deleteNotFInish){
                System.out.println(MessageFormat.format("Delete : {0} ? y/n",ingredientFound.toString()));
                String entryValid = scanner.nextLine();
                if (entryValid.toLowerCase().contains("y")){
                    ingredientService.deleteIngredient(id);
                    System.out.println("Ingredient Delete");
                    deleteNotFInish = false;
                }else if(entryValid.toLowerCase().contains("n")){
                    System.out.println("Delete cancel");
                    deleteNotFInish = false;
                }else{
                    System.out.println("Please enter y (Yes) or n (No)");
                }
            }
        }catch (NotFoundException ex){
            System.out.println("Ingredient not found");
        }
    }

    private void updateIngredient (){
        System.out.println("-- Update Ingredient --");
        System.out.println("enter ingredient Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            Ingredient ingredientFound = ingredientService.findIngredientById(id);
            boolean updateNotFInish = true;
            while (updateNotFInish){
                System.out.println(MessageFormat.format("Update : {0} ? y/n",ingredientFound.toString()));
                String entryValid = scanner.nextLine();
                if (entryValid.toLowerCase().contains("y")){
                    System.out.printf("New name for : " + ingredientFound.getName());
                    String name = scanner.nextLine();
                    ingredientService.editIngredient(id,name);
                    System.out.println("Ingredient Update");
                    updateNotFInish = false;
                }else if(entryValid.toLowerCase().contains("n")){
                    System.out.println("Update cancel");
                    updateNotFInish = false;
                }else{
                    System.out.println("Please enter y (Yes) or n (No)");
                }
            }
        }catch (NotFoundException ex){
            System.out.println("Ingredient not found");
        }
    }

    public void findAllIngredient (){
        List<Ingredient> ingredients = ingredientService.findAllIngredient();
        System.out.println("-- Ingredients --");

        IngredientTable.table(ingredients,false);
    }
}
