package io.khasang.freefly.util;

import io.khasang.freefly.entity.User;
import io.khasang.freefly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SecurityUtil {

    @Autowired
    UserService userService;

    /**
     * method for getting authorized user
     * @return authorized user
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

    /**
     * method for verification password for specific user
     * @param login
     * @param password
     * @return if not exists user with specific login, returns false,
     * else
     * true if password is correct for user with specific login, else false
     *
     */
    public boolean checkPassword(String login, String password){
        User user = userService.getUserByLogin(login);
        if (Objects.isNull(user)) {
            return false;
        } else {
            return new BCryptPasswordEncoder().matches(password, user.getPassword());
        }
    }
}
