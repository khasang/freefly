package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Cat;
import io.khasang.freefly.model.Call;
import io.khasang.freefly.model.CreateTable;
import io.khasang.freefly.model.Message;
import io.khasang.freefly.service.CatService;
import io.khasang.freefly.util.CheckText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;

// controller MVC
@Controller
// localhost:8080 /
public class AppController {

    @Value("Hello new Bean!")
    private final Message message;
    private final Call callImpl;
    private final CreateTable createTable;
    private final CheckText checkText;
    private final CatService catService;

    @Autowired
    public AppController(Call callImpl, Message message, CreateTable createTable, CheckText checkText, CatService catService) {
        this.callImpl = callImpl;
        this.message = message;
        this.createTable = createTable;
        this.checkText = checkText;
        this.catService = catService;
    }

    @RequestMapping("/")
    public String getHelloPage(Model model) {
        // nok
//        message = new Message("asdasd");
        model.addAttribute("message", message.getInfo());
        model.addAttribute("call", callImpl.getInfo());
        return "hello";
    }

    /**
     * User login
     */
    @RequestMapping(value = {"/login"})
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping("/create")
    public String createTableStatus(Model model) {
        model.addAttribute("status", createTable.create());
        return "create";
    }

    @RequestMapping("/admin")
    public String getSecurePage(Model model) {
        model.addAttribute("secure", "Very Secure content!");
        return "secure";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adm")
    public String getSecurePage2(Model model) {
        model.addAttribute("secure", "Very Secure content!");
        return "secure";
    }

    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    public String getCryptPassword(@PathVariable("password") String password, Model model) {
        model.addAttribute("password", password);
        model.addAttribute("encodePassword", new BCryptPasswordEncoder().encode(password));
        return "password";
    }

    @ResponseBody
    @RequestMapping(value = "/check/{text}", method = RequestMethod.GET)
    public String checkText(@PathVariable("text") String text) throws MalformedURLException {
        return checkText.checkWord(text);
    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    @ResponseBody
    public Object messagePost(@RequestBody Cat cat, HttpServletResponse response){
        try {
            catService.addCat(cat);
            return cat;
        } catch (NumberFormatException e) {
            response.setStatus(-1);
            return response;
        }
    }
}
