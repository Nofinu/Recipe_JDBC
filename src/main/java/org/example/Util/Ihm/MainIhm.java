package org.example.Util.Ihm;

import java.util.Scanner;

public class MainIhm {
    private final Scanner scanner;
    private final IhmIngredient ihmIngredient;
    private final IhmRecipe ihmRecipe;
    private final IhmComment ihmComment;
    private final IhmCategorie ihmCategorie;

    public MainIhm(){
        scanner = new Scanner(System.in);
        ihmIngredient = new IhmIngredient(scanner);
        ihmRecipe = new IhmRecipe(scanner);
        ihmComment = new IhmComment(scanner);
        ihmCategorie = new IhmCategorie(scanner);
    }

    public void start (){
        int entry;
        do {
            System.out.println("** --- --- Main Menu --- --- **");
            System.out.println("1/ Ingredient Menu");
            System.out.println("2/ Recipe Menu");
            System.out.println("3/ Comment menu");
            System.out.println("4/ Categorie Menu");
            entry = scanner.nextInt();
            scanner.nextLine();
            switch(entry){
                case 1:
                    ihmIngredient.start();
                    break;
                case 2:
                    ihmRecipe.start();
                    break;
                case 3:
                    ihmComment.start();
                    break;
                case 4:
                    ihmCategorie.start();
                    break;
                default:
                    System.out.println("Aurevoir");
                    break;
            }
        }while (entry != 0);
    }
}
