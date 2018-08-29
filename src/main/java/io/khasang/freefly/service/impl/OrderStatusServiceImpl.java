package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.OrderStatusDao;
import io.khasang.freefly.entity.OrderStatus;
import io.khasang.freefly.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusDao orderStatusDao;

    @Override
    public OrderStatus addOrderStatus(OrderStatus orderStatus) {
        return orderStatusDao.create(orderStatus);
    }

    @Override
    public OrderStatus getOrderStatusById(long id) {
        return orderStatusDao.getById(id);
    }

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusDao.getList();
    }

    @Override
    public OrderStatus updateOrderStatus(OrderStatus orderStatus) {
        orderStatusDao.update(orderStatus);
        return orderStatus;
    }

    @Override
    public OrderStatus deleteOrderStatusById(long id) {
        return orderStatusDao.delete(getOrderStatusById(id));
    }
}
