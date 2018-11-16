package io.khasang.freefly.dto;

import io.khasang.freefly.entity.Order;
import io.khasang.freefly.entity.User;
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


    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setLock(user.isLock());
        return userDTO;
    }
}
