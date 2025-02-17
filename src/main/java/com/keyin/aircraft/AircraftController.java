package com.keyin.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/airports")
    public List<Aircraft> getAllAirports() {
        return aircraftService.findAllAirports();
    }

    @GetMapping("/airport/{id}")
    public Aircraft getAirportByID(@PathVariable long id) {
        return aircraftService.findAirportById(id);
    }

    @GetMapping("/airport_search")
    public List<Aircraft> searchAirports(@RequestParam(value = "name", required = false) String name) {
        List<Aircraft> results = new ArrayList<Aircraft>();

        Aircraft airport = aircraftService.findByName(name);

        if (airport != null) {
            results.add(airport);
        }

        return results;
    }

    @PostMapping("/airport")
    public Aircraft createAirport(@RequestBody Aircraft newAirport) {
        return aircraftService.createAirport(newAirport);
    }

    @PutMapping("/airport/{id}")
    public Aircraft updateAirport(@PathVariable long id, @RequestBody Aircraft updatedAircraft) {
        return aircraftService.updateAirport(id, updatedAircraft);
    }
}
