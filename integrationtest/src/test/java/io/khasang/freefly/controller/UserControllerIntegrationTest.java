package io.khasang.freefly.controller;

import io.khasang.freefly.entity.User;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserControllerIntegrationTest {

    public static final String ROOT = "http://localhost:8080/user";
    public static final String ADD = "/add";
    public static final String GET_BY_ID = "/get";
    public static final String ALL = "/all";
    public static final String UPDATE = "/update";

    /**
     * integration test adding user with unique login, email. valid email, field are filled
     */
    @Test
    public void addUser() {
        User user = createUser();

        RestTemplate template = new RestTemplate();
        ResponseEntity<User> entity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                User.class,
                user.getId()
        );

        assertEquals("OK", entity.getStatusCode().getReasonPhrase());
        assertNotNull(entity.getBody());
    }

    @Test
    public void checkGetAllUsers() {
        RestTemplate template = new RestTemplate();
        List<User> usersBeforeCreating = template.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();

        User user1 = createUser();
        User user2 = createUser();

        List<User> usersAfterCreating = template.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();

        assertNotNull(usersAfterCreating.get(0));
        assertNotNull(usersAfterCreating.get(1));
        assertEquals(2, usersAfterCreating.size() - usersBeforeCreating.size());
    }

    private User createUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser();

        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        RestTemplate template = new RestTemplate();

        ResponseEntity<User> entity = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                User.class
        );
        User createdUser = entity.getBody();
        assertNotNull(createdUser);
        assertEquals("Ivan", createdUser.getFirstName());

        return createdUser;
    }

    private User prefillUser() {
        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setEmail(UUID.randomUUID().toString() + "@mail.ru");
        user.setLogin(UUID.randomUUID().toString() + "_vivan");
        user.setPassword("vivan");
        user.setLock(false);

        return user;
    }

    /**
     * integration test updating user with unique login, email. valid email, field are filled
     */
    @Test
    public void updateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = createUser();
        String newEmail = UUID.randomUUID().toString() + "@mail.ru";
        user.setEmail(newEmail);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        RestTemplate template = new RestTemplate();
        User updateUser = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                User.class
        ).getBody();

         assertEquals(newEmail, updateUser.getEmail());
    }

    @Test (expected = Exception.class)
    public void addingUserNonUniqueLogin(){
        User userInDB =  createUser();
        User newUser = new User();
        newUser.setLogin(userInDB.getLogin());
        newUser.setEmail(UUID.randomUUID().toString() + "@google.com");
        newUser.setFirstName("firstName");
        newUser.setLastName("lastName");

        sendRequestForCreationUser(newUser);
    }

    @Test (expected = Exception.class)
    public void addingUserNonUniqueEmail(){
        User userInDB =  createUser();
        User newUser = new User();
        newUser.setLogin(UUID.randomUUID().toString());
        newUser.setEmail(userInDB.getEmail());
        newUser.setFirstName("firstName");
        newUser.setLastName("lastName");

        sendRequestForCreationUser(newUser);
    }

    @Test (expected = Exception.class)
    public void addingUserNoValidEmail(){
        User newUser = new User();
        newUser.setLogin(UUID.randomUUID().toString());
        newUser.setEmail(UUID.randomUUID().toString());
        newUser.setFirstName("firstName");
        newUser.setLastName("lastName");

        sendRequestForCreationUser(newUser);
    }

    @Test (expected = Exception.class)
    public void addingUserNoDefinedName(){
        User newUser = new User();
        newUser.setLogin(UUID.randomUUID().toString());
        newUser.setEmail(UUID.randomUUID().toString());
        newUser.setFirstName("");
        newUser.setLastName("lastName");

        sendRequestForCreationUser(newUser);
    }

    private User sendRequestForCreationUser(User newUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<User> httpEntity = new HttpEntity<>(newUser, headers);
        RestTemplate template = new RestTemplate();

        ResponseEntity<User> entity = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                User.class
        );
        return entity.getBody();
    }

    @Test (expected = Exception.class)
    public void updateUserToNonUniqueLogin(){
        User userInDB1 = creationUser();
        User userInDB2 = creationUser();

        userInDB2.setLogin(userInDB1.getLogin());

        sendRequestForUpdatingUser(userInDB2);
    }

    @Test (expected = Exception.class)
    public void updateUserToNonUniqueEmail(){
        User userInDB1 = creationUser();
        User userInDB2 = creationUser();

        userInDB2.setEmail(userInDB1.getEmail());

        sendRequestForUpdatingUser(userInDB2);
    }

    @Test
    public void updateUserToUniqueLogin(){
        User userInDB = creationUser();
        userInDB.setLogin(UUID.randomUUID().toString());
        sendRequestForUpdatingUser(userInDB);
    }

    private User creationUser(){
        User user = new User();
        user.setLogin(UUID.randomUUID().toString());
        user.setEmail( UUID.randomUUID().toString() + "@yandex.ru");
        user.setFirstName("name");
        user.setLastName("name");
        user.setLock(false);
        user.setPassword("123");

        return sendRequestForCreationUser(user);
    }

    private User sendRequestForUpdatingUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        RestTemplate template = new RestTemplate();

        ResponseEntity<User> entity = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                User.class
        );
        return httpEntity.getBody();
    }
}