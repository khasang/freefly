package com.demo.soap;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demo.soap.Hello")
public class HelloImpl implements Hello {
    private String message = "Hello, ";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String sayHello(String name) {
        return message + name + "!";
    }
}
