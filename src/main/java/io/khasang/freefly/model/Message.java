package io.khasang.freefly.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Scope("")
public class Message {
    private String info;

    @PostConstruct
    public void setup(){
        System.out.println("test5");
    }

    @PreDestroy
    public void cleanUp(){
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
