package io.khasang.freefly.controller;

import io.khasang.freefly.dto.UserDTO;
import io.khasang.freefly.entity.User;
import io.khasang.freefly.model.Call;
import io.khasang.freefly.model.CreateTable;
import io.khasang.freefly.model.Message;
import io.khasang.freefly.service.UserService;
import io.khasang.freefly.util.CheckText;
import io.khasang.freefly.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.Objects;

// controller MVC
@Controller
// localhost:8080 /
public class AppController {

    @Value("Hello new Bean!")
    private final Message message;
    private final Call callImpl;
    private final CreateTable createTable;
    private final CheckText checkText;
    private final UserService userService;
    private final SecurityUtil securityUtil;

    public AppController(Call callImpl, Message message, CreateTable createTable, CheckText checkText, UserService userService, SecurityUtil securityUtil) {
        this.callImpl = callImpl;
        this.message = message;
        this.createTable = createTable;
        this.checkText = checkText;
        this.userService = userService;
        this.securityUtil = securityUtil;
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

    @RequestMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @RequestMapping("/users/update/password")
    public String getUpdatingPasswordPage() {
        return "user/updating/password_by_admin";
    }

    /**
     * method for update password for user by admin
     *
     * @param infoAboutPassword container for info about required login and password
     *                          infoAboutPassword.getLogin()  - user's login, which required
     *                          infoAboutPassword.getPassword() - new password
     * @return true on success, other false
     */
    // @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.PUT, path = "/rest/users/change/password", produces = "application/json;charset=utf-8")
    @ResponseBody
    public boolean changePassword(@RequestBody UserDTO infoAboutPassword) {
        User userInDb = userService.getUserByLogin(infoAboutPassword.getLogin());
        if (Objects.isNull(userInDb)) {
            return false;
        } else {
            userInDb.setPassword(new BCryptPasswordEncoder().encode(infoAboutPassword.getPassword()));
            userService.updateUser(userInDb);
            return true;
        }
    }

    /**
     * return current authentication user
     */
    private String getCurrentUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}
