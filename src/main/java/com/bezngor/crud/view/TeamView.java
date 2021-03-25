package com.bezngor.crud.view;

import com.bezngor.crud.controller.TeamController;
import com.bezngor.crud.model.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.bezngor.crud.controller.DeveloperController.devRepo;

public class TeamView {
    static TeamController teamController = new TeamController();

    public static void teamViewStart() {
        System.out.println("1 - Сохранить новый Team;\n2 - Обновить Team по индексу;\n" +
                "3 - Вывести Team по индексу;\n4 - Вывести все Team;\n" +
                "5 - Удалить Team по индексу;\nexit - Выход из модуля.");

        boolean isExist = false;
        String buf;
        Scanner scan = new Scanner(System.in);

        while (!isExist) {
            System.out.println("Введите код операции:");
            buf = scan.nextLine();
            switch (buf) {
                case "1":
                    System.out.println("Введите имя Team:");
                    String name1 = scan.nextLine();

                    System.out.println("Для добавления Developer введите его id.\nПо окончании введите end");
                    List<Developer> devs1 = new ArrayList<>();
                    boolean isNext1 = true;
                    while (isNext1) {
                        String str1 = scan.nextLine();
                        if (!str1.equals("end")) {
                            devs1.add(devRepo.getById(Integer.parseInt(str1)));
                            System.out.println("Введите id следующего Developer:");
                        } else isNext1 = false;
                    }

                    teamController.create(name1, devs1);
                    break;
                case "2":
                    System.out.println("Введите id обновляемого Team:");
                    Integer id2 = Integer.parseInt(scan.nextLine());
                    System.out.println("Введите имя Team:");
                    String name2 = scan.nextLine();

                    System.out.println("Для добавления Developer введите его id.\nПо окончании введите end");
                    List<Developer> devs2 = new ArrayList<>();
                    boolean isNext2 = true;
                    while (isNext2) {
                        String str2 = scan.nextLine();
                        if (!str2.equals("end")) {
                            devs2.add(devRepo.getById(Integer.parseInt(str2)));
                            System.out.println("Введите id следующего Developer:");
                        } else isNext2 = false;
                    }

                    teamController.update(id2, name2, devs2);
                    break;
                case "3":
                    System.out.println("Введите id вызываемого Team:");
                    Integer id3 = Integer.parseInt(scan.nextLine());
                    System.out.println(TeamController.teamRepo.getById(id3));
                    break;
                case "4":
                    teamController.getAll().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println("Введите id удаляемого Team:");
                    Integer id5 = Integer.parseInt(scan.nextLine());
                    teamController.deleteById(id5);
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
