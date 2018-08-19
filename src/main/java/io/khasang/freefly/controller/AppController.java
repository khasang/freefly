package io.khasang.freefly.controller;

import io.khasang.freefly.model.Call;
import io.khasang.freefly.model.CreateTable;
import io.khasang.freefly.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//controller MVC
@Controller
public class AppController {
    @Value("Hello new Bean")
    private final Message message;
    private final Call callimpl;
    private final CreateTable createTable;

    @Autowired
    public AppController(Call callimpl, Message message, CreateTable createTable) {
        this.callimpl = callimpl;
        this.message = message;
        this.createTable = createTable;
    }

    @RequestMapping("/")
    public String getHelloPage(Model model){
 //       message = new Message("test1");
        model.addAttribute("message", message.getInfo());
        model.addAttribute("call", callimpl.getInfo());
        return "hello";
    }

    @RequestMapping("/create")
    public String CreateTableStatus(Model model){
        model.addAttribute("status", createTable.create());
        return "create";
    }

    @RequestMapping("/admin")
    public String getSecurePage(Model model){
        model.addAttribute("secure", "Very secure content");
        return "secure";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adm")
    public String getSecurePage2(Model model){
        model.addAttribute("secure", "Very secure content");
        return "secure";
    }

    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    public String getCryptPassword(@PathVariable("password") String password, Model model){
        model.addAttribute("password", password);
        model.addAttribute("encodePassword", new BCryptPasswordEncoder().encode(password));
        return "password";
    }

}