import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class SkillRepository {

    public List<Skill> getAll() {
        return readingFromFile();
    }

    public Skill getById(Integer id) {
        List<Skill> skills = readingFromFile();
        return skills.stream().filter(s -> id == s.getId()).findFirst().get();
    }

    public Skill save(Skill skill) {
        List<Skill> skills = readingFromFile();
        if (skills.size() == 0) {
            skill.setId(1);
        } else {
            Integer id = Collections.max(
                    skills.stream().map(s -> s.getId()).collect(Collectors.toList()));
            skill.setId(id + 1);
        }

        skills.add(skill);
        String strSkills = convertSkillsToString(skills);
        writingToFile(strSkills);
        return skill;
    }

    public Skill update(Skill skill) {
        List<Skill> skills = readingFromFile();
        Skill updSkill = skills.stream().filter(s -> s.getId() == skill.getId()).findFirst().get();
        updSkill.setName(skill.getName());
        String strSkills = convertSkillsToString(skills);
        writingToFile(strSkills);

        return updSkill;
    }

    public void deleteById(Integer id) {
        List<Skill> skills = readingFromFile();
        skills.removeIf(s -> s.getId() == id);
        String strSkills = convertSkillsToString(skills);
        writingToFile(strSkills);
    }

    private void writingToFile(String skills) {
        try (FileWriter fileWriter = new FileWriter(
                "C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\skill.txt"))
        {
            fileWriter.write(skills);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }

 /*   private void writingToFile(String skills) {
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                Paths.get("C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\skill.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE))
        {
            ByteBuffer byteBuffer = ByteBuffer.wrap(skills.getBytes());
            byteBuffer.rewind();
            fileChannel.write(byteBuffer);
        } catch (InvalidPathException e) {
            System.out.println("Ошибка указания пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }
*/

    private List<Skill> readingFromFile() {
        List<Skill> listSkills = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (FileReader fileReader = new FileReader(
                "C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\skill.txt"))
        {
            int c;

            while ((c = fileReader.read()) != -1) {
                sb.append((char) c);
            }

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }

        String strSkills = sb.toString();
        List<String> listString = Arrays.asList(strSkills.split("\n"));
        if (!strSkills.isEmpty()) {
            for (String s : listString) {
                if (!s.isEmpty()) {
                    String[] arr = s.split(",");
                    listSkills.add(new Skill(Integer.parseInt(arr[0]), arr[1]));
                }
            }
        }
        return listSkills;
    }

    /*private List<Skill> readingFromFile() {
        List<Skill> listSkills = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                Paths.get("C:\\Users\\User\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\skill.txt") ))
        {
            long fileSize = fileChannel.size();
            MappedByteBuffer mapBuffer = fileChannel.map(
                    FileChannel.MapMode.READ_ONLY, 0, fileSize);

            for (int i = 0; i < fileSize; i++) {
                sb.append((char) mapBuffer.get());
            }

        } catch (InvalidPathException e) {
            System.out.println("Ошибка указания пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }

        String strSkills = sb.toString();
        List<String> listString = Arrays.asList(strSkills.split("\n"));
        if (!strSkills.isEmpty()) {
            for (String s : listString) {
                if (!s.isEmpty()) {
                    String[] arr = s.split(",");
                    listSkills.add(new Skill(Integer.parseInt(arr[0]), arr[1]));
                }
            }
        }
        return listSkills;
    }*/

    private static String convertSkillsToString(List<Skill> skills) {
        StringBuilder sb = new StringBuilder();
        for (Skill s : skills) {
            sb.append(s.getId()).append(",").append(s.getName()).append("\n");
        }
        String strSkills = sb.toString();

        return strSkills;
    }
}
