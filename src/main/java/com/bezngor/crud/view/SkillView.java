package com.bezngor.crud.view;

import com.bezngor.crud.model.Skill;
import com.bezngor.crud.repository.JavaIOSkillRepositoryImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SkillView {
    public static void main(String[] args) {
        System.out.println("1 - Сохранить новый Skill;\n2 - Обновить Skill по индексу;\n" +
                "3 - Вывести Skill по индексу;\n4 - Вывести все Skill;\n" +
                "5 - Удалить Skill по индексу;\nexit - Выход из модуля.");

        JavaIOSkillRepositoryImpl skillRepo = new JavaIOSkillRepositoryImpl();
        boolean isExist = false;
        String buf;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));)
        {
            while (!isExist)
            {
                System.out.println("Введите код операции:");
                buf = reader.readLine();
                switch (buf) {
                    case "1":
                        System.out.println("Введите название Skill:");
                        skillRepo.save(new Skill(reader.readLine()));
                        break;
                    case "2":
                        System.out.println("Введите id обновляемого Skill:");
                        Integer id2 = Integer.parseInt(reader.readLine());
                        System.out.println("Введите название Skill:");
                        String name = reader.readLine();
                        skillRepo.update(new Skill(id2, name));
                        break;
                    case "3":
                        System.out.println("Введите id вызываемого Skill:");
                        Integer id3 = Integer.parseInt(reader.readLine());
                        System.out.println(skillRepo.getById(id3));
                        break;
                    case "4":
                        skillRepo.getAll().forEach(System.out::println);
                        break;
                    case "5":
                        System.out.println("Введите id вызываемого Skill:");
                        Integer id5 = Integer.parseInt(reader.readLine());
                        skillRepo.deleteById(id5);
                        break;
                    case "exit":
                        isExist = buf.equals("exit");
                        break;
                    default:
                        System.out.println("Вы ввели неверный код!");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e );
        }
    }
}