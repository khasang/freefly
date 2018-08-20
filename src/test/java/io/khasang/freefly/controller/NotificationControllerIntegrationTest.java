package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Notification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class NotificationControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/notification";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";
    private final static String ALL = "/all";

    @Before
    public void setup(){

    }

    @Test
    public void addNotification() {
        Notification notification = createNotification();


        RestTemplate template = new RestTemplate();

        ResponseEntity<Notification> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Notification.class,
                notification.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());

    }

    private Notification createNotification() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Notification notification = prefillNotification();

        HttpEntity<Notification> httpEntity = new HttpEntity<>(notification, headers);
        RestTemplate template = new RestTemplate();

        Notification createdNotification = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Notification.class
        ).getBody();

        assertNotNull(createdNotification);

        return createdNotification;
    }

    private Notification prefillNotification() {
        Notification notification = new Notification();
        notification.setSendedTime((Time) new Date());
        notification.setDelivered(false);

        return notification;
    }

    @After
    public void clean() {

    }

}
