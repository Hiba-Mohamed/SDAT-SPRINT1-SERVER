package com.keyin.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return airportService.findAllAirports();
    }

    @GetMapping("/airportsByCity/{city_id}")
    public List<Airport> getAllCityAirports(@PathVariable Long city_id) {
        return airportService.findAirportsByCity(city_id);
    }

    @GetMapping("/airport/{id}")
    public Airport getAirportByID(@PathVariable long id) {
        return airportService.findAirportById(id);
    }

//    @GetMapping("/airport_search")
//    public List<Airport> searchAirports(@RequestParam(value = "name", required = false) String name) {
//        List<Airport> results = new ArrayList<Airport>();
//
//        Airport airport = airportService.findByName(name);
//
//        if (airport != null) {
//            results.add(airport);
//        }
//
//        return results;
//    }

    @PostMapping("/airport")
    public Airport createAirport(@RequestBody Airport newAirport) {
        return airportService.createAirport(newAirport);
    }

    @PutMapping("/airport/{id}")
    public Airport updateAirport(@PathVariable long id, @RequestBody Airport updatedAirport) {
        return airportService.updateAirport(id, updatedAirport);
    }

    @GetMapping("/airportsUsedByPassenger/{passengerId}")
    public List<Airport> getAirportsUsedByPassenger(@PathVariable long passengerId) {
        List<Airport> airports = airportService.getAirportsUsedByPassenger(passengerId);
        return airports;
    }
}
