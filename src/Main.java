import java.util.List;

public class Main {
    public static void main(String[] args) {
        SkillRepository sr = new SkillRepository();

        sr.save(new Skill(1, "Java"));
        sr.save(new Skill(2, "SQL"));
        sr.save(new Skill(3, "C++"));
        sr.save(new Skill(4, "Python"));
        System.out.println();

        System.out.println("----- Содержимое файла после сохранения -----");
        List<Skill> skills = sr.getAll();
        skills.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("----- Попытка сохранить объект с уже существующим id -----");
        sr.save(new Skill(3, "C++"));
        System.out.println();

        System.out.println("----- Содержимое файла после обновления id = 2 -----");
        sr.update(new Skill(2, "MySQL"));
        skills = sr.getAll();
        skills.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("----- Содержимое файла после удаления id = 2 -----");
        sr.deleteById(2);
        skills = sr.getAll();
        skills.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("----- Вызов объекта с id = 4 -----");
        System.out.println(sr.getById(4));
    }
}
