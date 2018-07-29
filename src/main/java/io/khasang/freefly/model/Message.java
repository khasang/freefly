package io.khasang.freefly.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Scope("prototype")
public class Message {
    public Message() {
    }

    @PostConstruct
    public void setup() {
        System.out.println("apprandre");
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("close db connection");
    }

    public Message(String info) {

        this.info = info;
    }

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
