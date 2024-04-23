package org.example.service;

import org.example.Exception.NotFoundException;
import org.example.dao.IngredientDAO;
import org.example.model.Ingredient;

import java.sql.SQLException;
import java.util.List;

public class IngredientService {
    private final IngredientDAO ingredientDAO;

    public IngredientService() {
        ingredientDAO = new IngredientDAO();
    }

    public boolean addIngredient(String name) {
        Ingredient ingredient = Ingredient.builder().name(name).build();
        try {
            if (ingredientDAO.save(ingredient)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteIngredient(int id) {
        Ingredient ingredient = null;
        try {
            ingredient = ingredientDAO.findById(id);
            if (ingredient != null) {
                if (ingredientDAO.delete(ingredient)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editIngredient(int id, String name) {
        Ingredient ingredient = Ingredient.builder().id(id).name(name).build();
        try {
            if (ingredientDAO.update(ingredient)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Ingredient> findAllIngredient() {
        try {
            return ingredientDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ingredient findIngredientById(int id) throws NotFoundException {
        try {
            Ingredient ingredientFound = ingredientDAO.findById(id);
            if(ingredientFound != null){
                return ingredientFound;
            }
            throw new NotFoundException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
