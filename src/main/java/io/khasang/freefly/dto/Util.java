package io.khasang.freefly.dto;

import io.khasang.freefly.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class Util {

    public Order getOrderFromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setDescription(orderDTO.getDescription());
        order.setCreationDate(orderDTO.getCreationDate());
        order.setModificationDate(orderDTO.getModificationDate());
        return order;
    }

    public OrderDTO getDTOFromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setDescription(order.getDescription());
        orderDTO.setCreationDate(order.getCreationDate());
        orderDTO.setModificationDate(order.getModificationDate());
        return orderDTO;
    }
}
