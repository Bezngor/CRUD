package com.bezngor.crud;

import java.util.Scanner;

import static com.bezngor.crud.view.DeveloperView.*;
import static com.bezngor.crud.view.SkillView.*;
import static com.bezngor.crud.view.TeamView.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Выберите тип операции:\n1 - Операции с Skill\n" +
                "2 - Операции с Developer\n3 - Операции с Team\nquit - Завершить работу.");

        Scanner scan = new Scanner(System.in);
        String str;
        boolean hasNext = true;

        while (hasNext) {
            str = scan.nextLine();
            switch (str) {
                case "1":
                    skillViewStart();
                    break;
                case "2":
                    devViewStart();
                    break;
                case "3":
                    teamViewStart();
                    break;
                case "quit":
                    hasNext = false;
                    break;
                default:
                    System.out.println("Вы ввели некорректный индекс!\nПопробуйте ещё раз...");
                    break;
            }
        }
    }
}
