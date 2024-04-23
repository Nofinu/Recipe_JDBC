package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.model.Comment;
import org.example.model.Recipe;
import org.example.service.CommentService;
import org.example.service.RecipeService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

public class IhmComment {
    private CommentService commentService;
    private Scanner scanner;
    private RecipeService recipeService;

    public IhmComment (Scanner scanner){
        this.scanner = scanner;
        commentService = new CommentService();
        recipeService = new RecipeService();
    }

    public void start (){
        int entry;
        do{
            System.out.println("--- --- Recipe Menu --- ---");
            System.out.println("1/ Add comment to recipe");
            System.out.println("2/ Show all comment from recipe");
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry){
                case 1:
                    addCommentToRecipe();
                    break;
                case 2:
                    showAllCommentForRecipe();
                    break;
                default:
                    return;
            }

        }while (entry != 0);
    }

    private void addCommentToRecipe (){
        System.out.println("-- Add comment to recipe --");

        boolean SelectRecipe = true;
        while (SelectRecipe){
            System.out.println("Recipe Id :");
            int recipeId = scanner.nextInt();
            scanner.nextLine();
            try{
                Recipe recipeFound = recipeService.findRecipeById(recipeId);
                System.out.println("Add Comment to ");
                recipeFound.showRecipe(false,false);
                System.out.println("? y/n");
                String entryValidComment = scanner.nextLine();
                if (entryValidComment.toLowerCase().contains("y")){
                        boolean addComment = true;
                        while (addComment){
                            System.out.println("- Text for the comment :");
                            String commentStr = scanner.nextLine();

                            System.out.println(MessageFormat.format("Add : \n{0} \n? y/n",commentStr));
                            String entryValidIngredient = scanner.nextLine();
                            if (entryValidIngredient.toLowerCase().contains("y")){

                                commentService.addComment(commentStr,recipeId);

                                addComment = false;
                            }else if(entryValidIngredient.toLowerCase().contains("n")){
                                System.out.println("Add cancel");
                                addComment = false;
                            }else{
                                System.out.println("Please enter y (Yes) or n (No)");
                            }
                        }
                    SelectRecipe = false;
                }else if(entryValidComment.toLowerCase().contains("n")){
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

    public void showAllCommentForRecipe (){
        System.out.println("-- Add comment to recipe --");

        boolean SelectRecipe = true;
        while (SelectRecipe){
            System.out.println("Recipe Id :");
            int recipeId = scanner.nextInt();
            scanner.nextLine();
            try{
                Recipe recipeFound = recipeService.findRecipeById(recipeId);
                System.out.println("Show all Comment from ");
                recipeFound.showRecipe(false,false);
                System.out.println("? y/n");
                String entryValidComment = scanner.nextLine();
                if (entryValidComment.toLowerCase().contains("y")){
                    List<Comment> comments = commentService.findAllByRecipeId(recipeId);

                    System.out.println("- comments :");

                    for(Comment comment : comments){
                        System.out.println(comment.getTextComment());
                    }

                    SelectRecipe = false;
                }else if(entryValidComment.toLowerCase().contains("n")){
                    System.out.println("Show cancel");
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
