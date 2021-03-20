package com.bezngor.crud;

import com.bezngor.crud.model.Developer;
import com.bezngor.crud.model.Skill;
import com.bezngor.crud.model.Team;
import com.bezngor.crud.repository.JavaIODeveloperRepositoryImpl;
import com.bezngor.crud.repository.JavaIOSkillRepositoryImpl;
import com.bezngor.crud.repository.JavaIOTeamRepositoryImpl;

import java.util.Arrays;

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
        sr.save(new Skill("SQL"));

        System.out.println("2. Обновление Skill id = 2");
        sr.update(new Skill(2, "MySQL"));

        System.out.println("3. Удаление Skill id = 2");
        sr.deleteById(2);

        System.out.println("4. Вызов Skill id = 4");
        System.out.println(sr.getById(4));

        System.out.println("5. Вызов всех Skill");
        sr.getAll().forEach(System.out::println);

        System.out.println();
// Testing Developer class

        JavaIODeveloperRepositoryImpl dr = new JavaIODeveloperRepositoryImpl();

        System.out.println("TESTING DEVELOPER CLASS");
        System.out.println("1. Создание четырех сущностей Developer");

        dr.save(new Developer("Mike", "Johnson"));
        dr.save(new Developer("Joe", "Smith"));
        dr.save(new Developer("July", "Brown"));
        dr.save(new Developer("Ann", "Lee"));
        dr.save(new Developer("Ivan", "Pegoff"));


        System.out.println("2. Содержимое файла после обновления id = 1,2,3");
        dr.update(new Developer(2, "Joe-Joe", "Smith-Smith", Arrays.asList(sr.getById(1), sr.getById(3))));
        dr.update(new Developer(1, "Bill", null, Arrays.asList(sr.getById(1))));
        dr.update(new Developer(5, "Ivan", "Pegoff", Arrays.asList(sr.getById(1), sr.getById(5))));

        System.out.println("3. Удаление Developer id = 4");
        dr.deleteById(4);

        System.out.println("4. Вызов Developer с id = 1");
        System.out.println(dr.getById(1));

        System.out.println("5. Вызов всех Developer");
        dr.getAll().forEach(System.out::println);

        System.out.println();

// Testing Team class
        System.out.println("TESTING TEAM CLASS");
        JavaIOTeamRepositoryImpl tr = new JavaIOTeamRepositoryImpl();

        System.out.println("1. Создание двух сущностей Team");
        tr.save(new Team("Team #1", Arrays.asList(dr.getById(1), dr.getById(2))));
        tr.save(new Team("Team #2", Arrays.asList(dr.getById(3))));
        tr.save(new Team("Team #3", Arrays.asList(dr.getById(2), dr.getById(5))));

        System.out.println("2. Содержимое файла после обновления id = 2");
        tr.update(new Team(2, "Team #2-2", Arrays.asList(dr.getById(3), dr.getById(2), dr.getById(1))));

        System.out.println("3. Удаление Team id = 1");
        tr.deleteById(1);

        System.out.println("4. Вызов Team с id = 2");
        System.out.println(tr.getById(2));

        System.out.println("5. Вызов всех Team");
        tr.getAll().forEach(System.out::println);
    }
}
