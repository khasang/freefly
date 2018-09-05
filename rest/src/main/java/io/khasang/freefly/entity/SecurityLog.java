package io.khasang.freefly.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "securitylog")
public class SecurityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "Date")
    private LocalDate date;

    @ManyToOne
    User user;

    @ManyToOne
    Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}