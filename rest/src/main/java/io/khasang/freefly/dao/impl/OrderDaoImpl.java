package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.OrderDao;
import io.khasang.freefly.entity.Order;
import org.hibernate.Session;

public class OrderDaoImpl extends BasicDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl(Class<Order> entityClass) {
        super(entityClass);
    }

    @Override
    public Order update(Order entity) {
        getSessionFactory().update(entity);
        return entity;
    }

    @Override
    public Order delete(Order entity) {
        getSessionFactory().delete(entity);
        return entity;
    }

    private Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }
}
