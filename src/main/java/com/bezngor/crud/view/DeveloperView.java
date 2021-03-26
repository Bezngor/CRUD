package com.bezngor.crud.view;

import com.bezngor.crud.controller.DeveloperController;
import com.bezngor.crud.model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.bezngor.crud.controller.SkillController.skillRepo;

public class DeveloperView {
    static DeveloperController devController = new DeveloperController();

    public void devViewStart() {
        System.out.println("1 - Сохранить новый Developer;\n2 - Обновить Developer по индексу;\n" +
                "3 - Вывести Developer по индексу;\n4 - Вывести все Developer;\n" +
                "5 - Удалить Developer по индексу;\nexit - Выход из модуля.");

        boolean isExist = false;
        String buf;
        Scanner scan = new Scanner(System.in);

        while (!isExist){
            System.out.println("Введите код операции:");
            buf = scan.nextLine();
            switch (buf) {
                case "1":
                    System.out.println("Введите имя Developer:");
                    String firstName1 = scan.nextLine();
                    System.out.println("Введите фамилию Developer:");
                    String lastName1 = scan.nextLine();

                    System.out.println("Для добавления Skill введите его id.\nПо окончании введите end");
                    List<Skill> skills1 = new ArrayList<>();
                    boolean isNext1 = true;
                    while (isNext1) {
                        String str1 = scan.nextLine();
                        if (!str1.equals("end")) {
                            skills1.add(skillRepo.getById(Integer.parseInt(str1)));
                            System.out.println("Введите id следующего Skill:");
                        } else isNext1 = false;
                    }

                    devController.create(firstName1, lastName1, skills1);
                    break;
                case "2":
                    System.out.println("Введите id обновляемого Developer:");
                    Integer id2 = Integer.parseInt(scan.nextLine());
                    System.out.println("Введите имя Developer:");
                    String firstName2 = scan.nextLine();
                    System.out.println("Введите фамилию Developer:");
                    String lastName2 = scan.nextLine();

                    System.out.println("Для добавления Skill введите его id.\nПо окончании введите end");
                    List<Skill> skills2 = new ArrayList<>();
                    boolean isNext2 = true;
                    while (isNext2) {
                        String str2 = scan.nextLine();
                        if (!str2.equals("end")) {
                            skills2.add(skillRepo.getById(Integer.parseInt(str2)));
                            System.out.println("Введите id следующего Skill:");
                        } else isNext2 = false;
                    }

                    devController.update(id2, firstName2, lastName2, skills2);
                    break;
                case "3":
                    System.out.println("Введите id вызываемого Developer:");
                    Integer id3 = Integer.parseInt(scan.nextLine());
                    System.out.println(devController.getById(id3));
                    break;
                case "4":
                    devController.getAll().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println("Введите id удаляемого Developer:");
                    Integer id5 = Integer.parseInt(scan.nextLine());
                    devController.deleteById(id5);
                    break;
                case "exit":
                    isExist = buf.equals("exit");
                    break;
                default:
                    System.out.println("Вы ввели неверный код!");
                    break;
            }
        }
    }
}
