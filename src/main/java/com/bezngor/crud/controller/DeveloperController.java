package com.bezngor.crud.controller;

import com.bezngor.crud.model.Developer;
import com.bezngor.crud.model.Skill;
import com.bezngor.crud.repository.JavaIODeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    public static JavaIODeveloperRepositoryImpl devRepo = new JavaIODeveloperRepositoryImpl();

    public Developer create(String firstName, String lastName, List<Skill> skills) {
        return devRepo.save(new Developer(null, firstName, lastName, skills));
    }

    public Developer update(Integer id, String firstName, String lastName, List<Skill> skills) {
        return devRepo.update(new Developer(id, firstName, lastName, skills));
    }

    public List<Developer> getAll() {
        return devRepo.getAll();
    }

    public Developer getById(Integer id) {
        return devRepo.getById(id);
    }

    public void deleteById(Integer id) {
        devRepo.deleteById(id);
    }
}
