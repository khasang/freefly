package io.khasang.freefly.service;

import io.khasang.freefly.entity.Trips;

import java.util.List;

public interface TripsService {

    /**
     * method for add trips
     *
     * @param trips - new trips for creation
     * @return created trips
     */
    Trips addTrips(Trips trips);

    /**
     * method for getting trip by specific id
     *
     * @param id - trip's id
     * @return trips by id
     */
    Trips getTripsById(long id);

    /**
     * method for getting all trips
     *
     * @return trip's list
     */
    List<Trips> getAllTrips();

    /**
     * method for update trips
     *
     * @return trip's list
     */
    Trips updateTrips(Trips trips);


    /**
     * method for delete trips by specific id
     *
     * @param id for deleting trips
     */
    void deleteTripsById(long id);
}
