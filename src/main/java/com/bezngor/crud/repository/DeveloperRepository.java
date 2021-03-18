package com.bezngor.crud.repository;

import com.bezngor.crud.model.Developer;

import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer, Integer> {
    @Override
    List<Developer> getAll();

    @Override
    Developer getById(Integer integer);

    @Override
    Developer save(Developer developer);

    @Override
    Developer update(Developer developer);

    @Override
    void deleteById(Integer integer);
}
