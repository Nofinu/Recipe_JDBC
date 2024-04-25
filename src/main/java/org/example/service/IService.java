package org.example.service;

import org.example.Exception.NotFoundException;

public interface IService<T> {
    public T findById(int id) throws NotFoundException;
}
