package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Trips;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class TripsControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/trips";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";

    @Test
    public void addTrips() {
        Trips trips = createTrips();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Trips> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Trips.class,
                trips.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    private Trips createTrips() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Trips trips = prefillTrips();

        HttpEntity<Trips> httpEntity = new HttpEntity<>(trips, headers);
        RestTemplate template = new RestTemplate();

        Trips createdTrips = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Trips.class
        ).getBody();

        assertNotNull(createdTrips);
        return createdTrips;
    }

    private Trips prefillTrips() {
        Trips trips = new Trips();
        trips.setDeparture("Moscow");
        trips.setDestination("Ekaterinburg");
        trips.setPrice("1000");
        trips.setDateDeparture(LocalDate.of(2018, 10, 25));
        return trips;
    }
}
