package io.khasang.freefly.controller;

import io.khasang.freefly.dto.EventDTO;
import io.khasang.freefly.entity.Event;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class EventControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/event";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";
    private final static String ALL = "/all";
    private final static String DELETE_BY_ID = "/delete";
    private final static String UPDATE = "/update";

    @Test
    public void addTestAndCheck() {
        Event event = createEvent();

        RestTemplate template = new RestTemplate();

        ResponseEntity<EventDTO> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                EventDTO.class,
                event.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void checkGetAllEvents() {
        createEvent();
        createEvent();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Event>> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Event>>() {
                }
        );

        List<Event> events = responseEntity.getBody();

        assertNotNull(events.get(0));
        assertNotNull(events.get(1));
    }

    private Event createEvent() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Event event = prefillEvent();

        HttpEntity<Event> entity = new HttpEntity<>(event, headers);
        RestTemplate template = new RestTemplate();
        Event createdEvent = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Event.class
        ).getBody();

        assertNotNull(createdEvent);
        assertEquals(event.getName(), createdEvent.getName());

        return createdEvent;
    }

    private Event prefillEvent() {
        Event event = new Event();
        event.setName("HotDeals");
        event.setDescription("Hot deal flight deals");
        return event;
    }
}
