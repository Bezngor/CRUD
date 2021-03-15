package com.bezngor.crud.model;

import java.io.Serializable;

public class Skill implements Serializable {
    private Integer id;
    private String name;

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
}
