package model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", textComment='" + textComment + '\'' +
                '}';
    }
}
