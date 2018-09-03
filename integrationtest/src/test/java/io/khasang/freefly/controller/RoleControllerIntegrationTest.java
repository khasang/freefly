package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Role;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/role";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get";
    private static final String ALL = "/all";

    @Test
    public void addRole() {
        Role role = createRole();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Role> entity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );

        assertEquals("OK", entity.getStatusCode().getReasonPhrase());
        assertNotNull(entity.getBody());
    }

    @Test
    public void checkGetAllRoles() {
        createRole();
        createRole();

        RestTemplate template = new RestTemplate();
        List<Role> roles = template.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Role>>() {
                }
        ).getBody();

        assertNotNull(roles.get(0));
        assertNotNull(roles.get(1));
    }

    private Role createRole() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role = prefillRole();

        HttpEntity<Role> httpEntity = new HttpEntity<>(role, headers);
        RestTemplate template = new RestTemplate();

        ResponseEntity<Role> entity = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Role.class
        );
        Role createdRole = entity.getBody();
        assertNotNull(createdRole);
        assertEquals("ROLE_ADMIN", createdRole.getName());

        return createdRole;
    }

    private Role prefillRole() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");

        return role;
    }
}
