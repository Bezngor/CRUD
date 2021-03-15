package main;

import com.bezngor.crud.model.Skill;
import com.bezngor.crud.repository.SkillRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SkillRepository sr = new SkillRepository();

        sr.save(new Skill("Java"));
        sr.save(new Skill("SQL"));
        sr.save(new Skill("C++"));
        sr.save(new Skill("Python"));
        System.out.println();

        System.out.println("----- Содержимое файла после сохранения -----");
        List<Skill> skills = sr.getAll();
        skills.forEach(System.out::println);
        System.out.println();

        System.out.println("----- Содержимое файла после обновления id = 2 -----");
        sr.update(new Skill(2, "MySQL"));
        skills = sr.getAll();
        skills.forEach(System.out::println);
        System.out.println();

        System.out.println("----- Содержимое файла после удаления id = 2 -----");
        sr.deleteById(2);
        skills = sr.getAll();
        skills.forEach(System.out::println);
        System.out.println();

        System.out.println("----- Вызов объекта с id = 4 -----");
        System.out.println(sr.getById(4));
    }
}
