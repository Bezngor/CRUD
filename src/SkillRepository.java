import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository {

    public List<Skill> getAll() {
        return readingFromFile();
    }

    public Skill getById(Integer id) {
        List<Skill> skills = readingFromFile();
        return skills.stream().filter(s -> id == s.getId()).findFirst().get();
    }

    public void save(Skill skill) {
        List<Skill> skills = readingFromFile();
        for (Skill s : skills) {
            if (s.getId() == skill.getId()) {
                System.out.println("Объект с id = " + s.getId() + " уже существует.");
                return;
            }
        }
        skills.add(skill);
        writingToFile(skills);
    }

    public void update(Skill skill) {
        List<Skill> skills = readingFromFile();
        Skill updSkill = skills.stream().filter(s -> s.getId() == skill.getId()).findFirst().get();
        updSkill.setName(skill.getName());
        writingToFile(skills);
    }

    public void deleteById(Integer id) {
        List<Skill> skills = readingFromFile();
        skills.removeIf(s -> s.getId() == id);
        writingToFile(skills);
    }

    private void writingToFile(List<Skill> skills) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\skill.txt")))
        {
            outputStream.writeObject(skills);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }

    private List<Skill> readingFromFile() {
        List<Skill> skills = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\skill.txt")))
        {
            skills = (List<Skill>) inputStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Файл не найден " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
        return skills;
    }
}
