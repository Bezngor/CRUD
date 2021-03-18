package com.bezngor.crud;

import com.bezngor.crud.model.Developer;
import com.bezngor.crud.model.Skill;
import com.bezngor.crud.repository.JavaIODeveloperRepositoryImpl;
import com.bezngor.crud.repository.JavaIOSkillRepositoryImpl;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

// Testing Skill class

        JavaIOSkillRepositoryImpl sr = new JavaIOSkillRepositoryImpl();

        System.out.println("TESTING SKILL CLASS");
        System.out.println("1. Создание четырех сущностей Skill");

        sr.save(new Skill("Java"));
        sr.save(new Skill("SQL"));
        sr.save(new Skill("C++"));
        sr.save(new Skill("Python"));

        System.out.println("2. Обновление Skill id = 2");
        sr.update(new Skill(2, "MySQL"));

        System.out.println("3. Удаление Skill id = 2");
        sr.deleteById(2);

        System.out.println("4. Вызов Skill id = 4");
        System.out.println(sr.getById(4));


// Testing Developer class

        JavaIODeveloperRepositoryImpl dr = new JavaIODeveloperRepositoryImpl();

        System.out.println("TESTING DEVELOPER CLASS");
        System.out.println("1. Создание четырех сущностей Developer");

        dr.save(new Developer("Mike", "Johnson"));
        dr.save(new Developer("Joe", "Smith"));
        dr.save(new Developer("July", "Brown"));
        dr.save(new Developer("Ann", "Lee"));


        System.out.println("2. Содержимое файла после обновления id = 1,2,3");
        dr.update(new Developer(2, Arrays.asList(sr.getById(1), sr.getById(3))));
        dr.update(new Developer(3, Arrays.asList(sr.getById(4))));
        dr.update(new Developer(1, "Bill", null, Arrays.asList(sr.getById(1))));

        System.out.println("3. Удаление Developer id = 4");
        dr.deleteById(4);

        System.out.println("4. Вызов Developer с id = 1");
        System.out.println(dr.getById(1));
    }
}
