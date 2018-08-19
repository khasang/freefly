package io.khasang.freefly.entity;

import javax.persistence.*;

@Entity
@Table(name = "passports")
public class Passport {

    public Passport() {
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
