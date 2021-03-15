package com.bezngor.crud.repository;

import com.bezngor.crud.model.Skill;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SkillRepository implements GenericRepository<Skill, Integer>{

    public static final String FILE_NAME =
            "C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\skill.txt";

    public List<Skill> getAll() {
        return readingFromFile();
    }

    public Skill getById(Integer id) {
        List<Skill> skills = readingFromFile();
        return skills.stream().filter(s -> id == s.getId()).findFirst().orElse(null);
    }

    public Skill save(Skill skill) {
        List<Skill> skills = readingFromFile();
        if (skills.size() == 0) {
            skill.setId(1);
        } else {
            Integer id = Collections.max(
                    skills.stream().map(Skill::getId).collect(Collectors.toList()));
            skill.setId(id + 1);
        }

        skills.add(skill);
        writingToFile(convertSkillsToString(skills));
        return skill;
    }

    public Skill update(Skill skill) {
        List<Skill> skills = readingFromFile();
        Skill updSkill = skills.stream().filter(s -> s.getId() == skill.getId()).findFirst().orElse(null);
        updSkill.setName(skill.getName());
        writingToFile(convertSkillsToString(skills));
        return updSkill;
    }

    public void deleteById(Integer id) {
        List<Skill> skills = readingFromFile();
        skills.removeIf(s -> s.getId() == id);
        writingToFile(convertSkillsToString(skills));
    }

    private void writingToFile(String skills) {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            fileWriter.write(skills);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }

    private List<Skill> readingFromFile() {
        List<Skill> listSkills = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (FileReader fileReader = new FileReader(FILE_NAME)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }

        String strSkills = sb.toString();
        if (!strSkills.isEmpty()) {
            listSkills = Arrays.stream(strSkills.split("\n"))
                .map(s -> {
                    String[] arr = s.split(",");
                    return new Skill(Integer.parseInt(arr[0]), arr[1]);
                })
                .collect(Collectors.toList());
        }
        return listSkills;
    }

    private static String convertSkillsToString(List<Skill> skills) {
        StringBuilder sb = new StringBuilder();
        for (Skill s : skills) {
            sb.append(s.getId()).append(",").append(s.getName()).append("\n");
        }
        return sb.toString();
    }
}