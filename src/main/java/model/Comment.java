package model;

public class Comment {
    private int id ;
    private String textComment;

    public Comment(String textComment) {
        this.textComment = textComment;
    }

    public Comment(int id, String textComment) {
        this(textComment);
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", textComment='" + textComment + '\'' +
                '}';
    }
}
