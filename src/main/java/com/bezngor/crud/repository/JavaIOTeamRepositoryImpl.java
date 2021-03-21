package com.bezngor.crud.repository;

import com.bezngor.crud.model.Developer;
import com.bezngor.crud.model.Skill;
import com.bezngor.crud.model.Team;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOTeamRepositoryImpl implements TeamRepository {

    public static final String FILE_NAME =
            "C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\teams.txt";

    private JavaIODeveloperRepositoryImpl devRepo = new JavaIODeveloperRepositoryImpl();

    @Override
    public List<Team> getAll() {
        return readingFromFile();
    }

    @Override
    public Team getById(Integer id) {
        List<Team> teams = readingFromFile();
        return teams.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Team save(Team team) {
        writingToFile(convertTeamsToString(this.generationId(team)));
        return team;
    }

    private List<Team> generationId(Team team) {
        List<Team> teams = readingFromFile();
        if (teams.size() == 0) {
            team.setId(1);
        } else {
            Integer id = Collections.max(
                    teams.stream().map(Team::getId).collect(Collectors.toList()));
            team.setId(id + 1);
        }
        teams.add(team);
        return teams;
    }

    @Override
    public Team update(Team team) {
        List<Team> teams = readingFromFile();
        Team teamUpd = teams.stream().filter(t -> team.getId() == t.getId()).findFirst().orElse(null);
        teamUpd.setName(team.getName());
        teamUpd.setDevs(team.getDevs());
        writingToFile(convertTeamsToString(teams));
        return teamUpd;
    }

    @Override
    public void deleteById(Integer id) {
        List<Team> teams = readingFromFile();
        teams.removeIf(t -> id == t.getId());
        writingToFile(convertTeamsToString(teams));
    }

    private void writingToFile(String teams) {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            fileWriter.write(teams);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }

    private List<Team> readingFromFile() {
        StringBuilder sb = new StringBuilder();

        try (FileReader fileReader = new FileReader(FILE_NAME)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }

        List<Team> listTeams = new ArrayList<>();
        String strTeams = sb.toString();
        if (!strTeams.isEmpty()) {
            listTeams = Arrays.stream(strTeams.split("\n"))
                    .map(t -> {
                        List<Developer> devs = new ArrayList<>();
                        String[] arrTm = t.split("<>");
                        String[] arrTmFields = arrTm[0].split(",");
                        if (arrTm.length > 1) {
                            devs = Arrays.stream(arrTm[1].split(","))
                                    .map(d -> devRepo.getById(Integer.parseInt(d)))
                                    .collect(Collectors.toList());
                        }
                        return new Team(Integer.parseInt(arrTmFields[0]), arrTmFields[1], devs);
                    })
                    .collect(Collectors.toList());
        }
        return listTeams;
    }

    private String convertTeamsToString(List<Team> teams) {
        StringBuilder sb = new StringBuilder();
        if (teams.size() != 0) {
            for (Team t : teams) {
                sb.append(t.getId()).append(",").append(t.getName()).append("<>");
                if (t.getDevs() != null) {
                    for (Developer d : t.getDevs()) {
                        sb.append(d.getId()).append(",");
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
