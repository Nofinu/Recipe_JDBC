package org.example.service;

import org.example.Exception.NotFoundException;
import org.example.dao.CategorieDAO;
import org.example.model.Categorie;
import org.example.model.Ingredient;

import java.sql.SQLException;
import java.util.List;

public class CategorieService implements  IService<Categorie>{

    private CategorieDAO categorieDAO;

    public CategorieService() {
        this.categorieDAO = new CategorieDAO();
    }

    public boolean addCategorie(String name) {
        Categorie categorie = Categorie.builder().name(name).build();
        try {
            if (categorieDAO.save(categorie)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Categorie> findAllICategorie() {
        try {
            return categorieDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean editCategorie (int id, String name) {
        Categorie categorie = Categorie.builder().id(id).name(name).build();
        try {
            if (categorieDAO.update(categorie)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Categorie findById(int id) throws NotFoundException {
        try{
            Categorie categorie = categorieDAO.findById(id);
            if(categorie != null){
                return categorie;
            }
            throw new NotFoundException();
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
