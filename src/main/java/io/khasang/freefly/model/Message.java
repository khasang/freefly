package io.khasang.freefly.model;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Message {
    private String info;

    @PostConstruct
    public void setup() {
        System.out.println("wait");
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("db connection");
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
