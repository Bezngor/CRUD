import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository {

    public List<Skill> getAll() {
        return readingFromFile();
    }

    public Skill getById(Integer id) {
        List<Skill> skills = readingFromFile();
        Skill result = null;
        for (Skill s : skills) {
            if (s.getId() == id)
                result = s;
        }
        return result;
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
        Skill updSkill = null;
        for (Skill s : skills) {
            if (s.getId() == skill.getId()) {
                updSkill = s;
                break;
            }
        }
        updSkill.setName(skill.getName());
        writingToFile(skills);
    }

    public void deleteById(Integer id) {
        List<Skill> skills = readingFromFile();
        Skill delSkill = null;
        for (Skill s : skills) {
            if (s.getId() == id) {
                delSkill = s;
                break;
            }
        }
        skills.remove(delSkill);
        writingToFile(skills);
    }

    private static void writingToFile(List<Skill> skills) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main.resources.files.skills")))
        {
            outputStream.writeObject(skills);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }

    private static List<Skill> readingFromFile() {
        List<Skill> skills = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main.resources.files.skills")))
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
