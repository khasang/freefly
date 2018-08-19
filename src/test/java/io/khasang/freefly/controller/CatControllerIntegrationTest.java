package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Cat;
import io.khasang.freefly.service.CatService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CatControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/cat";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";

    @Before
    public void setup(){}

    @Test
    public void addCat(){
        Cat cat = createCat();
    }

    private Cat createCat() {

    }


    @After
    public  void clean(){}

}
