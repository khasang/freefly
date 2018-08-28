package io.khasang.freefly.controller;

import io.khasang.freefly.entity.OrderStatus;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class OrderStatusControllerIntegrationTest {
    private final static String ROOT = "http://localhost:8080/orderStatus";
    private final static String ADD = "/add";
    private final static String GET_BY_ID = "/get";

    @Test
    public void addOrderStatus() {
        OrderStatus orderStatus = createOrderStatus();
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderStatus> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                OrderStatus.class,
                orderStatus.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }
    private OrderStatus createOrderStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        OrderStatus orderStatus = prefillOrderStatus();
        HttpEntity<OrderStatus> httpEntity = new HttpEntity<>(orderStatus, headers);
        RestTemplate template = new RestTemplate();
        OrderStatus createdOrderStatus = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                OrderStatus.class
        ).getBody();
        assertNotNull(createdOrderStatus);
        return createdOrderStatus;
    }
    private OrderStatus prefillOrderStatus() {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setDescription("Booked");
        return orderStatus;
    }
}
