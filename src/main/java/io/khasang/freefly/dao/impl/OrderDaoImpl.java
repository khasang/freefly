package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.OrderDao;
import io.khasang.freefly.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoImpl extends BasicDaoImpl<Order> implements OrderDao {

    @Autowired
    SessionFactory sessionFactory;

    public OrderDaoImpl(Class<Order> entityClass) {
        super(entityClass);
    }

    @Override
    public Order update(Order entity) {
        getSessionFactory().update(entity);
        return entity;
    }

    private Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Order deleteById(long id) {
        Order entity = getById(id);
        if (entity == null) {
            return null;
        } else {
            getSessionFactory().delete(entity);
            return entity;
        }
    }
}
