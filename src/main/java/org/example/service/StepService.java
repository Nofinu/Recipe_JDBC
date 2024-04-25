package org.example.service;


import org.example.Exception.NotFoundException;
import org.example.dao.StepDAO;
import org.example.model.Recipe;
import org.example.model.Step;

import java.sql.SQLException;
import java.util.List;

public class StepService implements IService<Step> {
    private final StepDAO stepDAO;

    public StepService() {
        stepDAO = new StepDAO();
    }

    public boolean addStep(String stepText, int idRecipe) {
        Step step = new Step(stepText, idRecipe);
        try {
            if (stepDAO.save(step)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteStep(int id) {
        Step step = null;
        try {
            step = stepDAO.findById(id);
            if (step != null) {
                if (stepDAO.delete(step)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editStep(int id, String stepText, int idRecipe) {
        Step step = new Step(id, stepText, idRecipe);
        try {
            if (stepDAO.update(step)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Step> findAllStep() {
        try {
            return stepDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Step findById(int id) throws NotFoundException {
        try {
            Step stepfound = stepDAO.findById(id);
            if(stepfound != null){
                return stepfound;
            }
            throw new NotFoundException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
