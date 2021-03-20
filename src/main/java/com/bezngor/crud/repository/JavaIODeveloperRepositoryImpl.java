package com.bezngor.crud.repository;

import com.bezngor.crud.model.Developer;
import com.bezngor.crud.model.Skill;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {

    public static final String FILE_NAME =
            "C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\developers.txt";

    @Override
    public List<Developer> getAll() {
        return readingFromFile();
    }

    @Override
    public Developer getById(Integer id) {
        List<Developer> devs = readingFromFile();
        return devs.stream().filter(d -> id == d.getId()).findFirst().orElse(null);
    }

    @Override
    public Developer save(Developer dev) {
        writingToFile(convertDevsToString(this.generationId(dev)));
        return dev;
    }

    private List<Developer> generationId(Developer dev) {
        List<Developer> devs = readingFromFile();
        if (devs.size() == 0) {
            dev.setId(1);
        } else {
            Integer id = Collections.max(
                    devs.stream().map(Developer::getId).collect(Collectors.toList()));
            dev.setId(id + 1);
        }
        devs.add(dev);
        return devs;
    }

    @Override
    public Developer update(Developer dev) {
        List<Developer> devs = readingFromFile();
        Developer updDev = devs.stream().filter(s -> s.getId() == dev.getId()).findFirst().orElse(null);
        updDev.setFirstName(dev.getFirstName());
        updDev.setLastName(dev.getLastName());
        updDev.setSkills(dev.getSkills());
        writingToFile(convertDevsToString(devs));
        return updDev;
    }

    @Override
    public void deleteById(Integer id) {
        List<Developer> devs = readingFromFile();
        devs.removeIf(d -> id == d.getId());
        writingToFile(convertDevsToString(devs));
    }

    private void writingToFile(String devs) {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            fileWriter.write(devs);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }

    private List<Developer> readingFromFile() {
        List<Developer> listDevs = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (FileReader fileReader = new FileReader(FILE_NAME)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }

        String strDevs = sb.toString();
        if (!strDevs.isEmpty()) {
            listDevs = Arrays.stream(strDevs.split("\n"))
                    .map(d -> {
                        List<Skill> skills = new ArrayList<>();
                        String[] arrDev = d.split("--");
                        String[] arrDevFields = arrDev[0].split(",");

                        if (arrDev.length > 1) {
                            String[] arrSklFields = arrDev[1].split(";");

                            for (String s : arrSklFields) {
                                Object[] arrIdName = s.split(",");
                                skills.add(new Skill(Integer.parseInt((String) arrIdName[0]), (String) arrIdName[1]));
                            }
                        }
                        return new Developer(Integer.parseInt(arrDevFields[0]), arrDevFields[1], arrDevFields[2], skills);
                    })
                    .collect(Collectors.toList());
        }
        return listDevs;
    }

    private String convertDevsToString(List<Developer> devs) {
        StringBuilder sb = new StringBuilder();
        for (Developer d : devs) {
            sb.append(d.getId()).append(",").append(d.getFirstName())
                    .append(",").append(d.getLastName()).append("--");
            if (d.getSkills() != null) {
                for (Skill s : d.getSkills()) {
                    sb.append(s.getId()).append(",").append(s.getName()).append(";");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
