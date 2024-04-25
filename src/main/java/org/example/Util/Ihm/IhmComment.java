package org.example.Util.Ihm;

import org.example.Exception.NotFoundException;
import org.example.Util.Validator;
import org.example.model.Comment;
import org.example.model.Recipe;
import org.example.service.CommentService;
import org.example.service.RecipeService;

import java.util.List;
import java.util.Scanner;

public class IhmComment {
    private final CommentService commentService;
    private final Scanner scanner;
    private final RecipeService recipeService;
    private final Validator<Recipe> recipeValidator;
    private final Validator<Comment> commentValidator;

    public IhmComment (Scanner scanner){
        this.scanner = scanner;
        commentService = new CommentService();
        recipeService = new RecipeService();
        recipeValidator = new Validator<>(scanner);
        commentValidator = new Validator<>(scanner);
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
        }while (true);
    }

    private void addCommentToRecipe (){
        System.out.println("-- Add comment to recipe --");
        System.out.println("Recipe Id :");
        int recipeId = scanner.nextInt();
        scanner.nextLine();
        try{
            Recipe recipeFound = recipeService.findById(recipeId);
            if (recipeValidator.validateRecipe(recipeFound,"Add Comment to ",false,false)){
                System.out.println("- Text for the comment :");
                String commentStr = scanner.nextLine();
                Comment comment = new Comment(commentStr,recipeId);
                if (commentValidator.validate(comment,"Add ")){
                    commentService.addComment(commentStr,recipeId);
                    System.out.println("Comment add");
                }
            }
        }catch(NotFoundException ex){
            System.out.println("Recipe not found at id :"+ recipeId);
        }
    }

    public void showAllCommentForRecipe (){
    System.out.println("-- Add comment to recipe --");
        System.out.println("Recipe Id :");
        int recipeId = scanner.nextInt();
        scanner.nextLine();
        try{
            Recipe recipeFound = recipeService.findById(recipeId);
            if (recipeValidator.validateRecipe(recipeFound,"Show all Comment from",false,false)){
                List<Comment> comments = commentService.findAllByRecipeId(recipeId);
                System.out.println("- comments :");
                for(Comment comment : comments){
                    System.out.println(comment.getTextComment());
                }
            }
        }catch(NotFoundException ex){
            System.out.println("Recipe not found at id :"+ recipeId);
        }
    }
}
