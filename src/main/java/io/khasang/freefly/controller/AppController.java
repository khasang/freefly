package io.khasang.freefly.controller;

import io.khasang.freefly.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//controller MVC
@Controller
//local:8080
public class AppController {

    public AppController(Message message) {
        this.message = message;
    }

    @Value("такое сообщение1")
    private Message message; //почему бин инициализируется отсюда первым, а не из AppConfig?

    @Autowired
    private Call call;

    @Autowired
    private CreateTable createTable;

    @Autowired
    private InsertRow insertRow;

    @Autowired
    private DeleteRow deleteRow;

    @Autowired
    private UpdateRow updateRow;

    @Autowired
    private Select select;


    @RequestMapping("/")
    public String getHellowPage(Model model) {
        model.addAttribute("message", message.getInfo());
        model.addAttribute("fromcall", call.doFoo());
        model.addAttribute("fromString", "string");
        return "hello";
    }

    @RequestMapping("/create")
    public String getCreateTableStatus(Model model) {
        model.addAttribute("status", createTable.create());
        return "jdbc";
    }

    @RequestMapping("/insert")
    public String getInsertRowTableStatus(Model model) {
        model.addAttribute("status", insertRow.insert());
        return "jdbc";
    }

    @RequestMapping("/delete")
    public String getDeleteRowTableStatus(Model model) {
        model.addAttribute("status", deleteRow.delete());
        return "jdbc";
    }

    @RequestMapping("/update")
    public String getUpdateRowTableStatus(Model model) {
        model.addAttribute("status", updateRow.update());
        return "jdbc";
    }

    @RequestMapping("/select")
    public String getSelectStatus(Model model) {
        model.addAttribute("status", select.select());
        return "jdbc";
    }

    @RequestMapping("/admin")
    public String getSecurePage(Model model) {
        model.addAttribute("secure", "very secure content! from 1");
        return "secure";

    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adm")
    public String getSecurePage2(Model model) {
        model.addAttribute("secure", "very secure content! from 2");
        return "secure";

    }


    @RequestMapping(value = "/passwordForAdminAndUser")
    public String getCryptPasswordForAdminAndUser(Model model) {
        model.addAttribute("encoderForAdmin", "admin = "  + new BCryptPasswordEncoder().encode("admin"));
        model.addAttribute("encoderForUser", "user = "  + new BCryptPasswordEncoder().encode("user"));
        return "passwordForAdminAndUser";
    }


    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    public String getCryptPassword(@PathVariable("password") String password, Model model) {
        model.addAttribute("password", password);
        model.addAttribute("encodePassword", new BCryptPasswordEncoder().encode(password));
        return "password";
    }



}
