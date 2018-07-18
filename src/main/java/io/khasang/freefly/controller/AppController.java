package io.khasang.freefly.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// controller mvc
@Controller
// localhost:8080 /
public class AppController {

    @RequestMapping("/")
    public String getHelloPage() {
        return "hello";
    }
}
