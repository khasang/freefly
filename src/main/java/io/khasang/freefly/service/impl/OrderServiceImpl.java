package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.OrderDao;
import io.khasang.freefly.dto.OrderDTO;
import io.khasang.freefly.entity.Order;
import io.khasang.freefly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = getOrderFromDTO(orderDTO);
        Order createdOrder = orderDao.create(order);
        return getDTOfromOrder(createdOrder);
    }

    @Override
    public OrderDTO getOrderDTOById(long id) {
        Order order = orderDao.getById(id);
        if (order != null) {
            return getDTOfromOrder(order);
        } else {
            return null;
        }
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderDao.update(getOrderFromDTO(orderDTO));
        return orderDTO;
    }

    @Override
    public OrderDTO deleteOrderById(long id) {
        Order order = orderDao.deleteById(id);
        if (order != null) {
            return getDTOfromOrder(order);
        } else {
            return null;
        }
    }

    @Override
    public List<OrderDTO> getAllOrdersDTO() {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderDao.getList()) {
            orderDTOList.add(getDTOfromOrder(order));
        }
        return orderDTOList;
    }

    private Order getOrderFromDTO(OrderDTO orderDTO){
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setDescription(orderDTO.getDescription());
        order.setCreated(orderDTO.getCreated());
        order.setUpdated(orderDTO.getUpdated());
        return order;
    }

    private OrderDTO getDTOfromOrder(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setDescription(order.getDescription());
        orderDTO.setCreated(order.getCreated());
        orderDTO.setUpdated(order.getUpdated());
        return orderDTO;
    }
}
