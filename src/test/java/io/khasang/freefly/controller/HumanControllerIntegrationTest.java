package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Human;
import io.khasang.freefly.entity.Passport;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HumanControllerIntegrationTest {

    public static final String ROOT = "http://localhost:8080/human";
    public static final String ADD = "/add";
    public static final String GET_BY_ID = "/get/{id}";
    public static final String DEL_BY_ID = "delete/{id}";

    @Test
    public void complexTest(){
        Human human = addingHuman();
        Human actualHumanFromDB = getHumanById(human.getId());
        assertEquals(human.getId(), actualHumanFromDB.getId());
        assertEquals(human.getPassport().getId(), actualHumanFromDB.getPassport().getId());
    }

    private Human getHumanById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT+GET_BY_ID,
                HttpMethod.GET,
                null,
                Human.class,
                id).getBody();
    }

    private Human addingHuman() {
        Human expectedHuman = prefillHuman();
        Human actualHuman;

        //построение запроса на создание
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Human> entity = new HttpEntity<>(expectedHuman, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<Human>  responseEntity = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Human.class);
        actualHuman = responseEntity.getBody();

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotEquals(0, actualHuman.getId());
        assertEquals(expectedHuman.getName(), actualHuman.getName());
        assertEquals(expectedHuman.getPassport().getNumber(), actualHuman.getPassport().getNumber());
        return actualHuman;
    }

    private Human prefillHuman() {
        Human human = new Human();
        human.setName("Petya!");
        Passport passport = new Passport();
        passport.setNumber("111-222");
        human.setPassport(passport);
        return human;
    }

}
