package io.khasang.freefly.service;

import io.khasang.freefly.dto.UserDTO;
import io.khasang.freefly.entity.User;

import java.util.List;

public interface UserService {

    /**
     * method for add user
     *
     * @param user -  new user for creation
     * @return created user
     */
    User addUser(User user);

    /**
     * method for getting user by specific id
     *
     * @param id - user's id
     * @return user by id
     */
    UserDTO getUserDTOById(long id);

    /**
     * method for getting all users
     *
     * @return user's list
     */
    List<UserDTO> getAllUserDTO();

    /**
     * method for update user
     *
     * @param user
     * @return update user
     */
    User updateUser(User user);
}