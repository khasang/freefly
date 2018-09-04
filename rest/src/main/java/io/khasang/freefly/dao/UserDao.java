package io.khasang.freefly.dao;

import io.khasang.freefly.entity.User;

import java.util.List;

public interface UserDao extends BasicDao<User> {

    /**
     * method for getting users by login
     * @param login for search
     * @return list users
     */
    List<User> getUsersByLogin(String login);

    /**
     * method for getting users by e-mail
     * @param email for search
     * @return list users
     */
    List<User> getUsersByEmail(String email);
}
