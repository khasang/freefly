package io.khasang.freefly.model;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Repository
//@Scope("application")
public class Message {
    private String info;

    @PostConstruct
    public void setup() {
        System.out.println("asdasd");
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("close db conn");
    }

    public Message() {
    }

    public Message(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
