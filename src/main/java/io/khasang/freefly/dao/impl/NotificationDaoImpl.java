package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.NotificationDao;
import io.khasang.freefly.entity.Notification;

public class NotificationDaoImpl extends BasicDaoImpl<Notification> implements NotificationDao {
    public NotificationDaoImpl(Class<Notification> entityClass) {
        super(entityClass);
    }
}
