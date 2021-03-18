package com.bezngor.crud.repository;

import com.bezngor.crud.model.Team;

import java.util.List;

public interface TeamRepository extends GenericRepository<Team, Integer> {
    @Override
    List<Team> getAll();

    @Override
    Team getById(Integer id);

    @Override
    Team save(Team team);

    @Override
    Team update(Team team);

    @Override
    void deleteById(Integer id);
}
