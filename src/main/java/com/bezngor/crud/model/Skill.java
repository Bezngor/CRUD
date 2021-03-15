package main.java.com.bezngor.crud.model;

import java.io.Serializable;
gi
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
}
