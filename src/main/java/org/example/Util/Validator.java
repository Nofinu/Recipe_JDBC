package org.example.Util;

import org.example.model.Recipe;
import org.example.service.IService;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Validator<T> {
    private Scanner scanner;

    public Validator (Scanner scanner){
        this.scanner = scanner;
    }

    public boolean validateRecipe (T element, String action,boolean withIngredient , boolean withStep){
        while (true) {
            Recipe recipe = (Recipe) element;
            System.out.println(action);
            recipe.showRecipe(withIngredient, withStep);
            System.out.println("? y/n");
            String entryValidComment = scanner.nextLine();
            if (entryValidComment.toLowerCase().contains("y")) {
                return true;
            } else if (entryValidComment.toLowerCase().contains("n")) {
                System.out.println(action + " cancel");
                return false;
            } else {
                System.out.println("Please enter y (Yes) or n (No)");
            }
        }
    }

    public boolean validate (T element, String action){
        while (true) {
            System.out.println(MessageFormat.format("{0} : {1} ? y/n",action,element.toString()));
            String entryValidComment = scanner.nextLine();
            if (entryValidComment.toLowerCase().contains("y")) {
                return true;
            } else if (entryValidComment.toLowerCase().contains("n")) {
                System.out.println(action + " cancel");
                return false;
            } else {
                System.out.println("Please enter y (Yes) or n (No)");
            }
        }
    }
}
