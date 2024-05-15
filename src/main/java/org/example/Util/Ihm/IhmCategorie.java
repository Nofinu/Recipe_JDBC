package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.Util.Table;
import org.example.Util.Validator;
import org.example.model.BaseShowMethod;
import org.example.model.Categorie;
import org.example.model.Ingredient;
import org.example.service.CategorieService;
import org.example.service.IngredientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IhmCategorie {
    private final CategorieService categorieService;
    private final Scanner scanner;
    private final Validator<Categorie> validator;


    public IhmCategorie( Scanner scanner) {
        this.categorieService = new CategorieService();
        this.scanner = scanner;
        this.validator = new Validator<>(scanner);
    }

    public void start (){
        int entry;
        do{
            System.out.println("--- --- Categorie Menu --- ---");
            System.out.println("1/ Create Categorie");
            System.out.println("32/ Edit Categorie");
            System.out.println("3/ Show all Categorie");
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry){
                case 1:
                    createCategorie();
                    break;
                case 2:
                    editCategorie();
                    break;
                case 3:
                    findAllCategorie();
                    break;
                default:
                    return;
            }

        }while (true);
    }

    private void createCategorie (){
        System.out.println("-- Create Categorie --");
        System.out.println("enter Categorie name :");
        String name = scanner.nextLine();

        if(categorieService.addCategorie(name)){
            System.out.println("Categorie Add");
        }else{
            System.out.println("Error when adding Categorie");
        }
    }

    private void editCategorie (){
        System.out.println("-- Update Categorie --");
        System.out.println("enter Categorie Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            Categorie categorieFound = categorieService.findById(id);
            if (validator.validate(categorieFound,"Update")) {
                System.out.printf("New name for : " + categorieFound.getName());
                String name = scanner.nextLine();
                categorieService.editCategorie(id, name);
                System.out.println("Categorie Update");
            }
        }catch (NotFoundException ex){
            System.out.println("Categorie not found");
        }
    }

    private void findAllCategorie(){
        List<BaseShowMethod> categorie = new ArrayList<>(categorieService.findAllICategorie());
        System.out.println("-- Categorie --");
        Table.table(categorie,false);
    }
}
