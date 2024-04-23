package org.example.dao;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {

    protected PreparedStatement statement;
    protected String request;
    protected ResultSet resultSet;
    protected Connection _connection;

    public BaseDAO() {

    }

    public abstract boolean save (T element) throws ExecutionControl.NotImplementedException,SQLException;
    public abstract boolean delete(T element)throws ExecutionControl.NotImplementedException,SQLException;
    public abstract boolean update(T element)throws ExecutionControl.NotImplementedException,SQLException;
    public abstract T findById (int id)throws ExecutionControl.NotImplementedException,SQLException;
    public abstract List<T> findAll ()throws ExecutionControl.NotImplementedException,SQLException;

    protected void close () throws  SQLException{
        if(resultSet != null){
            resultSet.close();
        }
        if(statement != null){
            statement.close();
        }
        if(_connection != null){
            _connection.close();
        }
    }
}
