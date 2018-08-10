package io.khasang.freefly.controller;


import io.khasang.freefly.entity.Cat;
import io.khasang.freefly.entity.CatWoman;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CatControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/cat";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";


    @Before
    //@BeforeClass
    public void setup() {

    }

    @Test
    public void addCat() {
        Cat cat = createCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    private Cat createCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();

        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, headers);
        RestTemplate template = new RestTemplate();

        Cat createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals("Barsik", createdCat.getName());
        assertNotEquals(0, createdCat.getCatWomanList().get(0).getId());

        return createdCat;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setDescription("Angry");
        cat.setColorId(2);

        CatWoman catWoman = new CatWoman();
        catWoman.setName("Murka");
        catWoman.setDescription("Good");

        CatWoman catWoman1 = new CatWoman();
        catWoman1.setName("Riska");
        catWoman1.setDescription("Hungry");

        List<CatWoman> catWomanList = new ArrayList<>();
        catWomanList.add(catWoman);
        catWomanList.add(catWoman1);

        cat.setCatWomanList(catWomanList);
        return cat;
    }

    @After
//    @AfterClass
    public void clean() {

    }
}
