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
}