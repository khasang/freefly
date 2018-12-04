package io.khasang.freefly.controller;


import io.khasang.freefly.entity.Event;
import io.khasang.freefly.entity.SecurityLog;
import io.khasang.freefly.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Controller
public class LoginController {

    private final SecurityLog log;
    private final Event event;
    private final UserService userService;
    private final SecurityLogController securityLogController;
    private static final int CODE_EVENT_LOGIN = 1;
    private static final int CODE_EVENT_LOGOUT = 2;

    public LoginController(SecurityLog securityLog, Event event, UserService userService, SecurityLogController securityLogController) {
        this.log = securityLog;
        this.event = event;
        this.userService = userService;
        this.securityLogController = securityLogController;
    }

    /**
     * User login
     */
    @RequestMapping(value = {"/login"})
    public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }
        model.setViewName("login");
        return model;
    }

    /**
     * Home page
     */

    @RequestMapping("/")
    public ModelAndView getHelloPage() {
        ModelAndView model = new ModelAndView();
        if ("anonymousUser".equals(getCurrentUser())) {
            model.addObject("author", "Guest");
        } else {
            model.addObject("author", getCurrentUser());
            addSecurityLog(CODE_EVENT_LOGIN);
        }
        model.setViewName("helloPage");
        return model;
    }

    /**
     * User logout
     */
    @RequestMapping(value = {"/logout"})
    public String getLogoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (getCurrentUser() != null) {
            addSecurityLog(CODE_EVENT_LOGOUT);
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    /**
     * add event to Security Log
     */
    private void addSecurityLog(int codeEvent) {
        log.setUser(userService.getUserByLogin(getCurrentUser()));
        log.setDate(LocalDate.now());
        event.setId(Long.valueOf(codeEvent));
        log.setEvent(event);
        securityLogController.addSecurityLog(log);
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
