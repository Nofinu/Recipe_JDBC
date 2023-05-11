package model;

public class Step {
    private int id;
    private String textStep;
    private int idRecipe;

    public Step(String textStep,int idRecipe) {
        this.textStep = textStep;
        this.idRecipe = idRecipe;
    }

    public Step(int id, String textStep,int idRecipe) {
        this(textStep,idRecipe);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextStep() {
        return textStep;
    }

    public void setTextStep(String textStep) {
        this.textStep = textStep;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", testStep='" + textStep + '\'' +
                '}';
    }
}
