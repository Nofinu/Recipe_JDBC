package dao;

import jdk.jshell.spi.ExecutionControl;
import model.Ingredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IngredienRecipesDAO extends BaseDAO<Ingredient> {
    public IngredienRecipesDAO(Connection _connection) {
        super(_connection);
    }

    @Override
    public boolean save(Ingredient element) throws SQLException {
        request = "INSERT INTO ingredient_recipe(id_ingredient,id_recipe,quantity) SET (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,element.getId());
        statement.setInt(2, element.getIdRecipe());
        statement.setString(3, element.getQuantity());
        int rows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setIdIngredientRecipe(resultSet.getInt(1));
        }
        return rows == 1;
    }

    @Override
    public boolean delete(Ingredient element) throws SQLException {
        request = "DELETE FROM ingredient_recipe WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,element.getIdIngredientRecipe());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    @Override
    public boolean update(Ingredient element) throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("ingredient_recipe");
    }

    @Override
    public Ingredient findById(int id) throws SQLException{
        Ingredient ingredient = null;
        request = "SELECT id FROM ingredient_recipe WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if(resultSet.next()){
            ingredient = Ingredient.builder().idIngredientRecipe(resultSet.getInt("id")).build();
        }
        return ingredient;
    }

    @Override
    public List<Ingredient> findAll() throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("ingredient_recipe");
    }

    @Override
    public List<Ingredient> findAllByRecipeId(int recipeId) throws SQLException{
        List<Ingredient> ingredients = null;
        request = "SELECT i.id as ingredientId,i.name as ingredientName,ir.quantity as quantity FROM ingredient as i, ir.id_recipe as id-recipe, ir.id as id_ingredient_recipe INNER JOIN ingredient_recipe as ir on ir.id_ingredient = i.id WHERE ir.id_recipe = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,recipeId);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            ingredients.add(Ingredient.builder()
                    .name(resultSet.getString("ingredientName"))
                    .id(resultSet.getInt("ingredientId"))
                    .quantity(resultSet.getString("quantity"))
                    .idRecipe(resultSet.getInt("id_recipe"))
                    .idIngredientRecipe(resultSet.getInt("id_ingredient_recipe"))
                    .build());
        }
        return null;
    }
}
