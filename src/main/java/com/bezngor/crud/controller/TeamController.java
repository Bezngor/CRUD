package com.bezngor.crud.controller;

import com.bezngor.crud.model.Developer;
import com.bezngor.crud.model.Team;
import com.bezngor.crud.repository.JavaIOTeamRepositoryImpl;

import java.util.List;

public class TeamController {
    public static JavaIOTeamRepositoryImpl teamRepo = new JavaIOTeamRepositoryImpl();

    public Team create(String name, List<Developer> devs) {
        return teamRepo.save(new Team(name, devs));
    }

    public Team update(Integer id, String name, List<Developer> devs) {
        return teamRepo.update(new Team(id, name, devs));
    }

    public List<Team> getAll() {
        return teamRepo.getAll();
    }

    public Team getById(Integer id) {
        return teamRepo.getById(id);
    }

    public void deleteById(Integer id) {
        teamRepo.deleteById(id);
    }
}
