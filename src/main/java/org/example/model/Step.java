package org.example.model;


import lombok.Data;

@Data
public class Step {
    private int id;
    private String textStep;
    private final int idRecipe;

    public Step(String textStep,int idRecipe) {
        this.textStep = textStep;
        this.idRecipe = idRecipe;
    }

    public Step(int id, String textStep,int idRecipe) {
        this(textStep,idRecipe);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", testStep='" + textStep + '\'' +
                '}';
    }
}
