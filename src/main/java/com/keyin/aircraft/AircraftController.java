package com.keyin.aircraft;

import com.keyin.airport.Airport;
import com.keyin.passengers.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/aircrafts")
    public List<Aircraft> getAllAircrafts() {
        return aircraftService.findAllAircrafts();
    }

    @GetMapping("/aircraft/{id}")
    public Aircraft getAircraftByID(@PathVariable long id) {
        return aircraftService.findById(id);
    }

//    @GetMapping("/aircraft_search")
//    public List<Aircraft> searchAircrafts(@RequestParam(value = "name", required = false) long id) {
//        List<Aircraft> results = new ArrayList<Aircraft>();
//
//        Aircraft aircraft = aircraftService.findById(id);
//
//        if (aircraft != null) {
//            results.add(aircraft);
//        }
//
//        return results;
//    }

    @PostMapping("/aircraft")
    public Aircraft createAircraft(@RequestBody Aircraft newAircraft) {
        return aircraftService.createAircraft(newAircraft);
    }

    @PutMapping("/aircraft/{id}")
    public Aircraft updateAircraft(@PathVariable long id, @RequestBody Aircraft updatedAircraft) {
        return aircraftService.updateAircraft(id, updatedAircraft);
    }

    @GetMapping("/whichPassengersTravelledOnAircraft/{id}")
    public List<Passenger> getAllPassengers(@PathVariable long id){
        return aircraftService.findById(id).getPassengers();
    }

    @GetMapping("/whichAirportsCanAircraftTakeOffFromAndLandAt/{id}")
    public List<Airport> getAllAircraftAirports(@PathVariable long id){
        return aircraftService.findById(id).getAirportList();
    }
}
