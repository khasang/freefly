package io.khasang.freefly.entity;

import javax.persistence.*;

@Entity
@Table(name = "cats_woman")
public class CatWoman {

    public CatWoman() {
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
