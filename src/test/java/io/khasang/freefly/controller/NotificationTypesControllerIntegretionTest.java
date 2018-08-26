package io.khasang.freefly.controller;

import io.khasang.freefly.dto.NotificationTypesDTO;
import io.khasang.freefly.entity.NotificationTypes;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NotificationTypesControllerIntegretionTest {

    private final static String ROOT = "http://localhost:8080/notification_types";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";
    private final static String ALL = "/all";

    @Test
    public void addNotificationTypesAndCheck() {
        NotificationTypes notificationTypes = createNotificationTypes();

        RestTemplate template = new RestTemplate();

        ResponseEntity<NotificationTypesDTO> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                NotificationTypesDTO.class,
                notificationTypes.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void checkGetAllNotificationTypes() {
        createNotificationTypes();
        createNotificationTypes();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<NotificationTypes>> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NotificationTypes>>() {
                }
        );

        List<NotificationTypes> notificationTypes = responseEntity.getBody();
        assertNotNull(notificationTypes.get(0));
        assertNotNull(notificationTypes.get(1));

    }

    private NotificationTypes createNotificationTypes() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        NotificationTypes notificationTypes = prefillNotificationTypes();

        HttpEntity<NotificationTypes> entity = new HttpEntity<>(notificationTypes, headers);
        RestTemplate template = new RestTemplate();
        NotificationTypes createdNotificationTypes = template.exchange(
                ROOT+ ADD,
                HttpMethod.POST,
                entity,
                NotificationTypes.class
        ).getBody();

        assertNotNull(createdNotificationTypes);
        assertEquals(notificationTypes.getNameType(), createdNotificationTypes.getNameType());
        return createdNotificationTypes;
    }


    private NotificationTypes prefillNotificationTypes() {
        NotificationTypes notificationTypes = new NotificationTypes();
        notificationTypes.setNameType("RemindThreeDays");
        notificationTypes.setDescription("Reminding about consumed flight before three days");
        notificationTypes.setTextNotification("Your flight goes in tree days!");

        return notificationTypes;
    }
}
