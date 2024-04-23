package org.example.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Comment {
    private int id ;
    private String textComment;
    private final int idRecipe;

    public Comment(String textComment,int idRecipe) {
        this.textComment = textComment;
        this.idRecipe = idRecipe;
    }

    public Comment(int id, String textComment,int idRecipe) {
        this(textComment,idRecipe);
        this.id = id;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", textComment='" + textComment + '\'' +
                '}';
    }
}
