package io.khasang.freefly.util;

import io.khasang.freefly.entity.User;
import io.khasang.freefly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CheckDataForAddingUser {

    @Autowired
    private UserService userService;

    public boolean checkCorrectData(User user) {
        if (userService.getUsersByLogin(user.getLogin()).size() > 0) {
            return false;
        }
        if (userService.getUsersByEmail(user.getEmail()).size() > 0) {
            return false;
        }
        if (!verificationEmail(user.getEmail())) {
            return false;
        }
        if (Objects.isNull(user.getFirstName())) {
            return false;
        }
        if (user.getFirstName().isEmpty()) {
            return false;
        }
        if (Objects.isNull(user.getLastName())) {
            return false;
        }
        if (user.getLastName().isEmpty()) {
            return false;
        }

        return true;
    }

    public String getErrDescription(User user) {
        StringBuilder err = new StringBuilder();

        if (userService.getUsersByLogin(user.getLogin()).size() > 0) {
            err.append("user with login '" + user.getLogin() + "' already exists; ");
        }
        if (userService.getUsersByEmail(user.getEmail()).size() > 0) {
            err.append("user with email '" + user.getEmail() + "' already exists; ");
        }
        if (!verificationEmail(user.getEmail())) {
            err.append("email '" + user.getEmail() + "' not correct; ");
        }
        if (Objects.isNull(user.getFirstName())) {
            err.append("first name not defined; ");
        } else if (user.getFirstName().isEmpty()) {
            err.append("first name not defined; ");
        }
        if (Objects.isNull(user.getLastName())) {
            err.append("last name not defined; ");
        } else if (user.getLastName().isEmpty()) {
            err.append("last name not defined; ");
        }

        return err.toString();
    }

    //TODO: should improve regular for email
    private boolean verificationEmail(String email) {
        return email.matches("(.+)@(.+)\\.(.+)");
    }
}
