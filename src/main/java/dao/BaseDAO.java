package dao;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
    protected Connection _connection;
    protected PreparedStatement statement;
    protected String request;
    protected ResultSet resultSet;

    public BaseDAO(Connection _connection) {
        this._connection = _connection;
    }

    public abstract boolean save (T element) throws ExecutionControl.NotImplementedException,SQLException;
    public abstract boolean delete(T element)throws ExecutionControl.NotImplementedException,SQLException;
    public abstract boolean update(T element)throws ExecutionControl.NotImplementedException,SQLException;
    public abstract T findById (int id)throws ExecutionControl.NotImplementedException,SQLException;
    public abstract List<T> findAll ()throws ExecutionControl.NotImplementedException,SQLException;
    public abstract List<T> findAllByRecipeId (int recipeId) throws ExecutionControl.NotImplementedException,SQLException;
}
