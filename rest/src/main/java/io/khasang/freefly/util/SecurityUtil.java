package io.khasang.freefly.util;

import io.khasang.freefly.entity.User;
import io.khasang.freefly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    @Autowired
    UserService userService;

    /**
     * method for getting authorized user
     * @return authorized user
     * !!!TODO throws NPE if no authentication object is found
     */
    public User getAuthorizedUser(){
        try {
            String login = ((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
            User user = userService.getUserByLogin(login);
            return user;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }
}
