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
        Aircraft aircraft = aircraftService.findById(id);
        if (aircraft != null){
            return aircraft.getPassengers();
        }
        else return null;
    }

    @GetMapping("/whichAirportsCanAircraftTakeOffFromAndLandAt/{id}")
    public List<Airport> getAllAircraftAirports(@PathVariable long id){
        return aircraftService.findById(id).getAirports();
    }
}
