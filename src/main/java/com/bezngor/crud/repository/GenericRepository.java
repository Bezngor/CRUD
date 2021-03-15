package com.bezngor.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> getAll();
    <T> T getById(ID id);
    T save(T t);
    T update(T t);
    void deleteById(ID id);
}