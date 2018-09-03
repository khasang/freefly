package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.OrderStatusDao;
import io.khasang.freefly.entity.OrderStatus;

public class OrderStatusDaoImpl extends BasicDaoImpl<OrderStatus> implements OrderStatusDao {
    public OrderStatusDaoImpl(Class<OrderStatus> entityClass) {
        super(entityClass);
    }
}
