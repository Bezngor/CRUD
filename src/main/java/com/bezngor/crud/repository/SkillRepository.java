package com.bezngor.crud.repository;

import com.bezngor.crud.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Integer> {
    @Override
    List<Skill> getAll();

    @Override
    Skill getById(Integer integer);

    @Override
    Skill save(Skill skill);

    @Override
    Skill update(Skill skill);

    @Override
    void deleteById(Integer integer);
}
