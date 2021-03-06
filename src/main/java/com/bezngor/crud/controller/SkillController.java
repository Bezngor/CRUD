package com.bezngor.crud.controller;

import com.bezngor.crud.model.Skill;
import com.bezngor.crud.repository.JavaIOSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    public static JavaIOSkillRepositoryImpl skillRepo = new JavaIOSkillRepositoryImpl();

    public Skill create(String name) {
        return skillRepo.save(new Skill(name));
    }

    public Skill update(Integer id, String name) {
        return skillRepo.update(new Skill(id, name));
    }

    public List<Skill> getAll() {
        return skillRepo.getAll();
    }

    public Skill getById(Integer id) {
        return skillRepo.getById(id);
    }

    public void deleteById(Integer id) {
        skillRepo.deleteById(id);
    }
}
