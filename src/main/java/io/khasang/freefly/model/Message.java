package io.khasang.freefly.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Scope("prototype")
public class Message {

    private String info;

    public Message(String info) {
        this.info = info;
    }

    public Message() {

    }

    @PostConstruct
    public void doAfterInit(){
        System.out.println("postConstruct");
    }

    @PreDestroy
    public void doBefore(){
        System.out.println("preDestroy");
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
