package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.UserDao;
import io.khasang.freefly.dto.UserDTO;
import io.khasang.freefly.entity.User;
import io.khasang.freefly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDTO userDTO;

    @Override
    public User addUser(User user) {
        return userDao.create(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getById(id);
    }

    @Override
    public UserDTO getUserDTOById(long id) {
        return userDTO.getUserDTO(userDao.getById(id));
    }

    @Override
    public List<UserDTO> getAllUserDTO() {
        return userDTO.getUserDTOList(userDao.getList());
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User deleteUserById(long id) {
        return userDao.delete(getUserById(id));
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> users = userDao.getUsersByLogin(login);
        if (users.size() > 1) {
            throw new IllegalStateException("found several users with the same login");
        }
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> users = userDao.getUsersByEmail(email);
        if (users.size() > 1) {
            throw new IllegalStateException("found several users with the same e-mail");
        }
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
}