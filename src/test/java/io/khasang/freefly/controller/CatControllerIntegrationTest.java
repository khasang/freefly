package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Cat;
import io.khasang.freefly.entity.CatWoman;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CatControllerIntegrationTest {

    public static final String ROOT = "http://localhost:8080/cat";
    public static final String ADD = "/add";
    public static final String GET_BY_ID = "/get/{id}";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete/{id}";
    public static final String GET_ALL = "/get/all";

    @Test
    public void testGetAll() {

        int before = getAll().size();
        long id1 = createCat().getId();
        long id2 = createCat().getId();
        int after = getAll().size();
        assertEquals(2, after-before);
        deleteCat(id1);
        deleteCat(id2);
    }

    private List<Cat> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Cat>> responseEntity = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                });

        return responseEntity.getBody();
    }


    @Test
    public void addingCatTest() {
        Cat actualCat = createCat();
        Cat expectedCat = getCatById(actualCat.getId());
        actualCat.getCatWomans().remove(0);
        Cat updatedCat = updateCat(actualCat);
        Cat deletedCat = deleteCat(actualCat.getId());
    }

    private Cat deleteCat(long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(ROOT + DELETE, HttpMethod.DELETE, null, Cat.class, id);
        return responseEntity.getBody();
    }

    private Cat updateCat(Cat cat) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(ROOT + UPDATE, HttpMethod.PUT, httpEntity, Cat.class);

        Cat updatedCat = responseEntity.getBody();
        assertEquals(cat.getName(), updatedCat.getName());
        assertEquals(cat.getCatWomans().size(), updatedCat.getCatWomans().size());

        return updatedCat;
    }

    private Cat getCatById(long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat> responseEntity = template.exchange(ROOT + GET_BY_ID, HttpMethod.GET, null, Cat.class, id);
        return responseEntity.getBody();
    }

    private Cat createCat() {
        Cat expextedCat = prefillCat();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Cat> httpEntity = new HttpEntity<>(expextedCat, headers);
        RestTemplate restTemplate = new RestTemplate();
        Cat actualCat =
                restTemplate.exchange(
                        ROOT + ADD,
                        HttpMethod.POST,
                        httpEntity,
                        Cat.class).
                        getBody();
        assertEquals(expextedCat.getName(), actualCat.getName());
        return actualCat;
    }


    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("Vasya2");
        cat.setDescription("Good2");
        cat.setColorId(2);

        List<CatWoman> catWomanList = new ArrayList<>();
        CatWoman catWoman1 = new CatWoman();
        catWoman1.setName("Murka");
        CatWoman catWoman2 = new CatWoman();
        catWoman2.setName("Manka");
        catWomanList.add(catWoman1);
        catWomanList.add(catWoman2);

        cat.setCatWomans(catWomanList);
        return cat;
    }


}
