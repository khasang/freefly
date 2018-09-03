package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.OrderDao;
import io.khasang.freefly.dto.OrderDTO;
import io.khasang.freefly.dto.Util;
import io.khasang.freefly.entity.Order;
import io.khasang.freefly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Util util;

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        return util.getDTOFromOrder(orderDao.create(util.getOrderFromDTO(orderDTO)));
    }

    @Override
    public OrderDTO getOrderDTOById(long id) {
        if (Objects.nonNull(orderDao.getById(id))) {
            return util.getDTOFromOrder(orderDao.getById(id));
        }

        throw new IllegalArgumentException("order with id = " + id + " not exists");
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderDao.update(util.getOrderFromDTO(orderDTO));
        return orderDTO;
    }

    @Override
    public OrderDTO deleteOrderById(long id) {
        if (Objects.nonNull(orderDao.getById(id))){
            return util.getDTOFromOrder(orderDao.delete(orderDao.getById(id)));
        }

        throw new IllegalArgumentException("order with id = " + id + " not exists");
    }

    @Override
    public List<OrderDTO> getAllOrdersDTO() {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderDao.getList()) {
            orderDTOList.add(util.getDTOFromOrder(order));
        }
        return orderDTOList;
    }
}
