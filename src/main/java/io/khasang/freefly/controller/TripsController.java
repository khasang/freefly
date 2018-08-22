package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Trips;
import io.khasang.freefly.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trips")
public class TripsController {
    private final TripsService tripsService;

    @Autowired
    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Trips addTrips(@RequestBody Trips trips) {
        return tripsService.addTrips(trips);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Trips getTripsById(@PathVariable(value = "id") String id) {
        return tripsService.getTripsById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Trips> getAllTrips() {
        return tripsService.getAllTrips();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Trips updateTrips(@RequestBody Trips trips) {
        return tripsService.updateTrips(trips);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public void deleteTripsById(@PathVariable(value = "id") String id) {
        tripsService.deleteTripsById(Long.parseLong(id));
    }
}
