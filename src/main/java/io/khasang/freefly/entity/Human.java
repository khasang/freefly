package io.khasang.freefly.entity;

import javax.persistence.*;

@Entity
@Table(name = "humans")
public class Human {

    public Human() {
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;


    private String name;

    @OneToOne
    @JoinColumn(name = "id")
    private Passport passport;

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

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
