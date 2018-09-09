package io.khasang.freefly.controller;

import io.khasang.freefly.dto.UserDTO;
import io.khasang.freefly.entity.User;
import io.khasang.freefly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    //cods error. apply during check data for creation or updating user
    private final int NO_ERROR = 0;
    private final int NON_UNIQUE_LOGIN = 1;
    private final int NON_UNIQUE_EMAIL = 2;
    private final int NOT_VALID_EMAIL = 3;
    private final int UNDEFINITED_FIST_NAME = 4;
    private final int UNDEFINITED_SECOND_NAME = 5;
    private final int UNDEFINITED_LOGIN = 6;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * method for creation user.
     *
     * @param user for creation
     * @return created user. created user has user.password's value encodes by BCryptPasswordEncoder
     * throws IEA with reason's cod in message
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        int cod = checkCorrectDataForCreation(user);
        if (cod == NO_ERROR) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userService.addUser(user);
        } else {
            throw new IllegalArgumentException("User can not be added. Cod reason = " + cod);
        }
    }

    /**
     * method for creation no locked user.
     *
     * @param user for creation
     * @return created user. created user has user.password's value encodes by BCryptPasswordEncoder and user.isLock = false
     * throws IEA with reason's cod in message
     */
    @RequestMapping(value = "/add/nolocked", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User addNotLockedUser(@RequestBody User user) {
        user.setLock(false);
        return addUser(user);
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

    /**
     * method for renovation user
     *
     * @param user data for updating
     * @return updated user.
     * throws IEA with reason's cod in message specific in method checkCorrectDataForCreation's doc
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        int cod = checkCorrectDataForUpdating(user);
        if (cod == NO_ERROR) {
            return userService.updateUser(user);
        } else {
            throw new IllegalArgumentException("User can not be updated. Cod reason = " + cod);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User deleteUser(@PathVariable(name = "id") String id) {
        return userService.deleteUserById(Long.parseLong(id));
    }

    /**
     * check data about new user: unique login, e-mail, and non empty info
     *
     * @param newUser
     * @return NO_ERROR if data correct or error's cod
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Integer checkCorrectDataForCreation(@RequestBody User newUser) {
        if (Objects.nonNull(userService.getUserByLogin(newUser.getLogin()))) {
            return NON_UNIQUE_LOGIN;
        }
        if (Objects.nonNull(userService.getUserByEmail(newUser.getEmail()))) {
            return NON_UNIQUE_EMAIL;
        }
        if (!verificationEmail(newUser.getEmail())) {
            return NOT_VALID_EMAIL;
        }

        return checkNonEmptyFields(newUser);
    }

    /**
     * check data for updated user: unique login, e-mail, and non empty info
     *
     * @param updatedUser new data for user
     * @return NO_ERROR if data correct or error's cod
     */
    public Integer checkCorrectDataForUpdating(User updatedUser) {
        //if exists user with specific id
        if (Objects.nonNull(userService.getUserById(updatedUser.getId()))) {

            User userSameLogin = userService.getUserByLogin(updatedUser.getLogin());
            if (Objects.nonNull(userSameLogin)) {
                //if user with the same login is not the user for update
                if (!userSameLogin.getId().equals(updatedUser.getId())) {
                    return NON_UNIQUE_LOGIN;
                }
            }

            User userSameEmail = userService.getUserByEmail(updatedUser.getEmail());
            if (Objects.nonNull(userSameEmail)) {
                //if user with the same email is not the user for update
                if (!userSameEmail.getId().equals(updatedUser.getId())) {
                    return NON_UNIQUE_EMAIL;
                }
            }

            if (!verificationEmail(updatedUser.getEmail())) {
                return NOT_VALID_EMAIL;
            }

            return checkNonEmptyFields(updatedUser);

        } else {
            //not exists user with specific id
            return NO_ERROR;
        }
    }

    private int checkNonEmptyFields(User user) {
        if (user.getFirstName().isEmpty()) {
            return UNDEFINITED_FIST_NAME;
        }
        if (user.getLastName().isEmpty()) {
            return UNDEFINITED_SECOND_NAME;
        }
        if (user.getLogin().isEmpty()) {
            return UNDEFINITED_LOGIN;
        }

        return NO_ERROR;
    }

    private boolean verificationEmail(String email) {
        return email.matches("(.+)@(.+)\\.(.+)");
    }
}
