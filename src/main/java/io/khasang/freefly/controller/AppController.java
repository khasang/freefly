package io.khasang.freefly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//controller MVC
@Controller
//local:8080
public class AppController {

    @RequestMapping("/")
    public String getHellowPage(){
        return "hello";
    }


}
