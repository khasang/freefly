package io.khasang.freefly.service;

import io.khasang.freefly.entity.Event;

import java.util.List;

public interface EventService {
    /**
     * method for add event
     *
     * @param event - new event for creation
     * @return created event
     */
    Event addEvent(Event event);

    /**
     * method for getting event by specific id
     *
     * @param id - event's id
     * @return event by id
     */
    Event getEventById(long id);

    /**
     * method for getting all events
     *
     * @return event's list
     */
    List<Event> getAllEvents();

    /**
     * method for deleting event by id
     *
     * @param id for deleting
     */
    void deleteEventById(long id);

    /**
     * method for updating event
     *
     * @param event - for updating
     * @return updated event
     */
    Event updateEvent(Event event);
}
