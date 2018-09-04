package io.khasang.freefly.controller;

import io.khasang.freefly.dto.UserDTO;
import io.khasang.freefly.entity.User;
import io.khasang.freefly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User getUserById(@PathVariable(value = "id") String id) {
        return userService.getUserById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
        return userService.getAllUserDTO();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User deleteUser(@PathVariable(name = "id") String id) {
        return userService.deleteUserById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/login/{login}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<User> getUsersByLogin(@PathVariable(name = "login") String login) {
        return userService.getUsersByLogin(login);
    }

    /**
     * method for adding user by registration
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/registration/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Long registrationNewUser(@RequestBody User user){
        if (checkCorrectData(user) < 0) {
            return checkCorrectData(user);
        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setLock(false);
            userService.addUser(user);
            return user.getId();
        }
    }

    /**
     * check data for correct for registration new user
     * @param user
     * @return 0L then data corrected
     *         < 0L if data uncorrected
     *
     *  -1 : exists user with specific login
     *  -2 : exists user with specific email
     *  -3 : email not valid
     *  -4 : no info about firstName
     *  -5 : no info about lastName
     */
    private Long checkCorrectData(User user) {
        if (userService.getUsersByLogin(user.getLogin()).size() > 0) {
            return -1L;
        }
        if (userService.getUsersByEmail(user.getEmail()).size() > 0) {
            return -2L;
        }
        if (!verificationEmail(user.getEmail())) {
            return -3L;
        }
        if (Objects.isNull(user.getFirstName())) {
            return -5L;
        }
        if (user.getFirstName().isEmpty()) {
            return -5L;
        }
        if (Objects.isNull(user.getLastName())) {
            return -5L;
        }
        if (user.getLastName().isEmpty()) {
            return -5L;
        }

        return 0L;
    }

    //TODO: should improve regular for email
    private boolean verificationEmail(String email) {
        return email.matches("(.+)@(.+)\\.(.+)");
    }
}
