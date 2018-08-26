package io.khasang.freefly.controller;

import io.khasang.freefly.dto.OrderDTO;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class OrderControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/order";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get/{id}";
    private static final String UPDATE = "/update";
    private static final String DELETE_BY_ID = "/delete/{id}";
    private static final String GET_ALL = "/get/all";

    /**
     * integration test OrderController's methods:
     * addOrder, getOrderDTOById, updateOrder, deleteOrderById
     * <p>
     * actions:
     * 1.adding new order, check DTO field's values
     * 2.getting created orderDTO by id, check result
     * 3.update created order
     * 4.getting updated order's DTO, check field's values
     * 5.delete created order
     */
    @Test
    public void testCRUD() {
        String description = "test description";
        LocalDate created = LocalDate.of(2017, 03, 05);
        LocalDate updated = LocalDate.of(2018, 01, 23);

        OrderDTO createdOrderDTO = createOrder(description, created, updated);

        OrderDTO orderDTOFromDB = getOrderDTO(createdOrderDTO.getId());
        checkFields(orderDTOFromDB, description, created, updated);

        description = "updated test description";
        created = LocalDate.of(2017, 12, 12);
        updated = LocalDate.of(2017, 12, 23);
        alterFields(createdOrderDTO, description, created, updated);
        updateOrder(createdOrderDTO);

        orderDTOFromDB = getOrderDTO(createdOrderDTO.getId());
        checkFields(orderDTOFromDB, description, created, updated);

        OrderDTO deletedOrderDTO = deleteOrder(createdOrderDTO.getId());
        assertNotNull(deletedOrderDTO.getId());
        checkFields(deletedOrderDTO, description, created, updated);
    }

    /**
     * integration test OrderController's method getAllOrdersDTO
     * <p>
     * actions:
     * 1.getting all orderDTO from db
     * 2.adding 2 orders
     * 3.getting all orderDTO from db and check distinction
     * 4.delete added orders
     */
    @Test
    public void testGettingAllOrders() {
        OrderDTO orderDTO1 = prefillOrderDTO(
                "description",
                LocalDate.of(2012, 12, 12),
                LocalDate.of(2014, 11, 12));
        OrderDTO orderDTO2 = prefillOrderDTO(
                "other description",
                LocalDate.of(2013, 3, 12),
                LocalDate.of(2015, 12, 12));

        List<OrderDTO> orderDTOListBefore = getAllOrderDTO();

        orderDTO1 = createOrder(orderDTO1);
        orderDTO2 = createOrder(orderDTO2);

        List<OrderDTO> orderDTOList = getAllOrderDTO();
        orderDTOList.removeAll(orderDTOListBefore);
        assertEquals(2, orderDTOList.size());
        assertTrue(orderDTOList.contains(orderDTO1));
        assertTrue(orderDTOList.contains(orderDTO2));

        assertEquals(orderDTO1, deleteOrder(orderDTO1.getId()));
        assertEquals(orderDTO2, deleteOrder(orderDTO2.getId()));
    }

    private List<OrderDTO> getAllOrderDTO() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<OrderDTO>> responseEntity = template.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDTO>>() {
                });

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());

        return responseEntity.getBody();
    }

    private OrderDTO updateOrder(OrderDTO orderDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<OrderDTO> httpEntity = new HttpEntity<>(orderDTO, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderDTO> responseEntity = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                OrderDTO.class);

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(orderDTO, responseEntity.getBody());

        return responseEntity.getBody();
    }

    private OrderDTO deleteOrder(Long id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderDTO> responseEntity = template.exchange(
                ROOT + DELETE_BY_ID,
                HttpMethod.DELETE,
                null,
                OrderDTO.class,
                id
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());

        return responseEntity.getBody();
    }

    private void checkFields(OrderDTO createdOrderDTO, String description, LocalDate created, LocalDate updated) {
        assertNotNull(createdOrderDTO.getId());
        assertEquals(description, createdOrderDTO.getDescription());
        assertEquals(created, createdOrderDTO.getCreationDate());
        assertEquals(updated, createdOrderDTO.getModificationDate());
    }

    private OrderDTO getOrderDTO(Long id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderDTO> responseEntity = template.exchange(
                ROOT + GET_BY_ID,
                HttpMethod.GET,
                null,
                OrderDTO.class,
                id
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
        assertEquals(id, responseEntity.getBody().getId());

        return responseEntity.getBody();
    }

    private OrderDTO createOrder(OrderDTO orderDTO) {
        return createOrder(orderDTO.getDescription(), orderDTO.getCreationDate(), orderDTO.getModificationDate());
    }

    private OrderDTO createOrder(String description, LocalDate created, LocalDate updated) {
        OrderDTO orderDTO = prefillOrderDTO(description, created, updated);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<OrderDTO> httpEntity = new HttpEntity<>(orderDTO, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderDTO> responseEntity = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                OrderDTO.class
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        OrderDTO orderFromResponse = responseEntity.getBody();
        assertNotNull(orderFromResponse);
        assertNotNull(orderFromResponse.getId());
        checkFields(orderFromResponse, description, created, updated);

        return orderFromResponse;
    }

    private OrderDTO prefillOrderDTO(String description, LocalDate creationDate, LocalDate modificationDate) {
        OrderDTO orderDTO = new OrderDTO();
        alterFields(orderDTO, description, creationDate, modificationDate);
        return orderDTO;
    }

    private void alterFields(OrderDTO orderDTO, String newDescription, LocalDate newCreationDate, LocalDate newModificationDate) {
        orderDTO.setDescription(newDescription);
        orderDTO.setCreationDate(newCreationDate);
        orderDTO.setModificationDate(newModificationDate);
    }
}
