package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.EventDao;
import io.khasang.freefly.entity.Event;
import io.khasang.freefly.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public Event addEvent(Event event) {
        return eventDao.create(event);
    }

    @Override
    public Event getEventById(long id) {
        return eventDao.getById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.getList();
    }
}
