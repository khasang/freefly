package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.TripsDao;
import io.khasang.freefly.entity.Trips;

public class TripsDaoImpl extends BasicDaoImpl<Trips> implements TripsDao {

    public TripsDaoImpl(Class<Trips> entityClass) {
        super(entityClass);
    }
}
