package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.TripsDao;
import io.khasang.freefly.entity.Trips;
import io.khasang.freefly.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripsServiceImpl implements TripsService {

    @Autowired
    private TripsDao tripsDao;

    @Override
    public Trips addTrips(Trips trips) {
        return tripsDao.create(trips);
    }

    @Override
    public Trips getTripsById(long id) {
        return tripsDao.getById(id);
    }

    @Override
    public List<Trips> getAllTrips() {
        return tripsDao.getList();
    }

    @Override
    public Trips updateTrips(Trips trips) {
        return null;
    }

    @Override
    public void deleteTripsById(long id) {

    }
}
