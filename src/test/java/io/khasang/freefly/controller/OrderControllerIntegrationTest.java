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

    private static final String ROOT_APP = "http://localhost:8080";

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
     * 6.search orderDTO by deleted order's id
     */
    @Test
    public void complexOrderControllerTest() {
        String description = "Тестовое описание";
        LocalDate created = LocalDate.of(2017, 03, 05);
        LocalDate updated = LocalDate.of(2018, 01, 23);
        OrderDTO createdOrderDTO = null;
        OrderDTO orderDTOFromDB = null;
        try {
            //1.adding new order, check field's values
            createdOrderDTO = createOrder(description, created, updated);
            assertNotNull(createdOrderDTO.getId());
            checkFields(createdOrderDTO, description, created, updated);

            //2.getting created order by id, check result
            orderDTOFromDB = getOrderDTO(createdOrderDTO.getId());
            assertNotNull(orderDTOFromDB.getId());
            checkFields(orderDTOFromDB, description, created, updated);

            //3.update created order
            description = "Обновленное тестовое описание";
            created = LocalDate.of(2017, 12, 12);
            updated = LocalDate.of(2017, 12, 23);
            createdOrderDTO.setDescription(description);
            createdOrderDTO.setCreated(created);
            createdOrderDTO.setUpdated(updated);
            OrderDTO updatedOrderDTO = updateOrder(createdOrderDTO);
            checkFields(updatedOrderDTO, description, created, updated);

            //4.getting updated order by id, check field's values
            orderDTOFromDB = getOrderDTO(createdOrderDTO.getId());
            checkFields(orderDTOFromDB, description, created, updated);
        } finally {
            if (createdOrderDTO.getId() != null) {
                //5.delete created order
                OrderDTO deletedOrderDTO = deleteOrder(createdOrderDTO.getId());
                assertNotNull(deletedOrderDTO.getId());
                checkFields(deletedOrderDTO, description, created, updated);

                //6.search order by deleted order's id
                orderDTOFromDB = getOrderDTO(createdOrderDTO.getId());
                assertNull("order must not exist in db after delete", orderDTOFromDB);
            }
        }
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
                "описание1",
                LocalDate.of(2012, 12, 12),
                LocalDate.of(2014, 11, 12));
        OrderDTO orderDTO2 = prefillOrderDTO(
                "описание2",
                LocalDate.of(2013, 3, 12),
                LocalDate.of(2015, 12, 12));
        try {
            //1.getting all orderDTO from db
            List<OrderDTO> orderDTOListBefore = getAllOrderDTO();

            //2.adding 2 orders
            orderDTO1 = createOrder(orderDTO1);
            orderDTO2 = createOrder(orderDTO2);

            //3.getting all orderDTO from db and check distinction
            List<OrderDTO> orderDTOList = getAllOrderDTO();
            orderDTOList.removeAll(orderDTOListBefore);
            assertEquals(2, orderDTOList.size());
            assertTrue(orderDTOList.contains(orderDTO1));
            assertTrue(orderDTOList.contains(orderDTO2));
        } finally {
            //4.delete added orders
            if (orderDTO1.getId() != null) {
                deleteOrder(orderDTO1.getId());
            }
            if (orderDTO2.getId() != null) {
                deleteOrder(orderDTO2.getId());
            }
        }
    }

    private List<OrderDTO> getAllOrderDTO() {
        RestTemplate template = new RestTemplate();
        return template.exchange(
                ROOT_APP + OrderController.ROOT + OrderController.GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDTO>>() {
                }).getBody();
    }

    private OrderDTO updateOrder(OrderDTO orderDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<OrderDTO> httpEntity = new HttpEntity<>(orderDTO, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderDTO> responseEntity = template.exchange(
                ROOT_APP + OrderController.ROOT + OrderController.UPDATE,
                HttpMethod.PUT,
                httpEntity,
                OrderDTO.class);
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }

    private OrderDTO deleteOrder(Long id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderDTO> responseEntity = template.exchange(
                ROOT_APP + OrderController.ROOT + OrderController.DELETE_BY_ID,
                HttpMethod.DELETE,
                null,
                OrderDTO.class,
                id
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }

    private void checkFields(OrderDTO createdOrderDTO, String description, LocalDate created, LocalDate updated) {
        assertNotNull(createdOrderDTO.getId());
        assertEquals(description, createdOrderDTO.getDescription());
        assertEquals(created, createdOrderDTO.getCreated());
        assertEquals(updated, createdOrderDTO.getUpdated());
    }

    private OrderDTO getOrderDTO(Long id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderDTO> responseEntity = template.exchange(
                ROOT_APP + OrderController.ROOT + OrderController.GET_BY_ID,
                HttpMethod.GET,
                null,
                OrderDTO.class,
                id
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }

    private OrderDTO createOrder(OrderDTO orderDTO) {
        return createOrder(orderDTO.getDescription(), orderDTO.getCreated(), orderDTO.getUpdated());
    }

    private OrderDTO createOrder(String descrition, LocalDate created, LocalDate updated) {
        OrderDTO orderDTO = prefillOrderDTO(descrition, created, updated);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<OrderDTO> httpEntity = new HttpEntity<>(orderDTO, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<OrderDTO> responseEntity = template.exchange(
                ROOT_APP + OrderController.ROOT + OrderController.ADD,
                HttpMethod.POST,
                httpEntity,
                OrderDTO.class
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }

    private OrderDTO prefillOrderDTO(String description, LocalDate created, LocalDate updated) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDescription(description);
        orderDTO.setCreated(created);
        orderDTO.setUpdated(updated);
        return orderDTO;
    }
}
