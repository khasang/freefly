package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.EventDao;
import io.khasang.freefly.entity.Event;

public class EventDaoImpl extends BasicDaoImpl<Event> implements EventDao {
    public EventDaoImpl(Class<Event> entityClass) {
        super(entityClass);
    }

}
