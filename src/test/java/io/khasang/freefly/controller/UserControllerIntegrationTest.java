package io.khasang.freefly.controller;

import io.khasang.freefly.entity.User;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserControllerIntegrationTest {

    public static final String ROOT = "http://localhost:8080/user";
    public static final String ADD = "/add";
    public static final String GET_BY_ID = "/get";
    public static final String ALL = "/all";
    public static final String UPDATE = "/update";

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
        createUser();
        createUser();

        RestTemplate template = new RestTemplate();
        List<User> users = template.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();

        assertNotNull(users.get(0));
        assertNotNull(users.get(1));
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
        user.setEmail("ivanov@mail.ru");
        user.setLogin("vivan");
        user.setPassword("vivan");
        user.setLock(false);

        return user;
    }

    @Test
    public void updateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = createUser();
        user.setEmail("ivanov2018@mail.ru");

        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        RestTemplate template = new RestTemplate();
        User updateUser = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                User.class
        ).getBody();

         assertEquals("ivanov2018@mail.ru", updateUser.getEmail());
    }
}