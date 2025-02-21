package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityService;
import com.keyin.passengers.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;

    @Autowired
    private CityService cityService;  // Inject CityService to use it for finding a city by ID

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
    public Airport createAirport(@RequestBody AirportRequest airportRequest) {
        City AirportCity = cityService.findCityById(airportRequest.cityID);
        System.out.println(AirportCity);
        if (AirportCity == null) {
            System.out.println("City not found with ID: " + airportRequest.cityID);
        }
        Airport modifiedAirport = new Airport(airportRequest.code, airportRequest.name, AirportCity);
        return airportService.createAirport(modifiedAirport);
    }

    @PutMapping("/airport/{id}")
    public Airport updateAirport(@PathVariable long id, @RequestBody Airport updatedAirport) {
        return airportService.updateAirport(id, updatedAirport);
    }
    // Inner class to represent the passenger request
    public static class AirportRequest {
        public String code;
        public String name;
        public int cityID;
    }
}
