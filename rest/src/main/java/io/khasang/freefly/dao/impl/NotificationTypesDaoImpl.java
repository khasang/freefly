package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.NotificationTypesDao;
import io.khasang.freefly.entity.NotificationTypes;

public class NotificationTypesDaoImpl extends BasicDaoImpl<NotificationTypes> implements NotificationTypesDao {
    public NotificationTypesDaoImpl(Class<NotificationTypes> entityClass) { super(entityClass); }

}
