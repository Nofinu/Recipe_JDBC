package org.example.dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.Util.DatabaseManager;
import org.example.model.Categorie;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO extends BaseDAO<Categorie> {
    @Override
    public boolean save(Categorie element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "INSERT INTO categorie(name) VALUES (?)";
            statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            int rows = statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                element.setId(resultSet.getInt(1));
            }
            return rows == 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean delete(Categorie element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "DELETE FROM categorie WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, element.getId());
            int rows = statement.executeUpdate();
            return rows == 1;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Categorie element) throws  SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "UPDATE categorie SET name = ? WHERE id =?";
            statement = _connection.prepareStatement(request);
            statement.setString(1, element.getName());
            statement.setInt(2, element.getId());
            int rows = statement.executeUpdate();
            return rows == 1;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public Categorie findById(int id) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "SELECT id,name FROM categorie WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Categorie.builder()
                        .id(id)
                        .name(resultSet.getString("name"))
                        .build();
            }
            return null;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }
    }

    @Override
    public List<Categorie> findAll() throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            List<Categorie> categories = new ArrayList<>();
            request = "SELECT id,name FROM categorie";
            statement = _connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(Categorie.builder().id(resultSet.getInt("id")).name(resultSet.getString("name")).build());
            }
            return categories;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }
    }
}
