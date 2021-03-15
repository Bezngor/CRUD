package main.java.com.bezngor.crud.model;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Skill implements Serializable {
    private Integer id;
    private String name;

    public Skill(String name) {
        this.name = name;
    }

    public Skill(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "," + name + '/';
    }
/*

    static void writingToFile(String fileName, main.java.com.bezngor.crud.model.Skill skill.txt) {
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                Paths.get(fileName),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE))
        {
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            byteBuffer.put(skill.txt.toString().getBytes());
            byteBuffer.rewind();
            fileChannel.write(byteBuffer);
        } catch (InvalidPathException e) {
            System.out.println("Ошибка указания пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }

    static void readingFromFile(String fileName) {
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                Paths.get(fileName) ))
        {
            long fileSize = fileChannel.size();
            MappedByteBuffer mapBuffer = fileChannel.map(
                    FileChannel.MapMode.READ_ONLY, 0, fileSize);
            for (int i = 0; i < fileSize; i++) {
                System.out.print((char) mapBuffer.get());
            }
            System.out.println();
        } catch (InvalidPathException e) {
            System.out.println("Ошибка указания пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }
*/
}