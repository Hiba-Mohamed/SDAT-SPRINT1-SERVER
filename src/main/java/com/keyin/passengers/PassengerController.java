package com.keyin.passengers;

import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import com.keyin.city.City;
import com.keyin.city.CityService;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private CityService cityService;

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.findAllPassengers();
    }

    @GetMapping("/passenger/{id}")
    public Passenger getPassengerByID(@PathVariable("id") long id) {
        return passengerService.findPassengerById(id);
    }

    @GetMapping("/passenger_search")
    public List<Passenger> searchPassengers(@RequestParam(value = "phoneNumber", required = false) int phoneNumber) {
        List<Passenger> results = new ArrayList<Passenger>();

        Passenger passenger = passengerService.findByPhoneNumber(phoneNumber);

        if (passenger != null) {
            results.add(passenger);
        }

        return results;
    }

    @PostMapping("/passenger")
    public Passenger createPassenger(@RequestBody Passenger newPassenger) {
        City city = cityService.findCityById(newPassenger.getCity().getId());
        if (city == null) {
            throw new RuntimeException("City not found with ID: " + newPassenger.getCity().getId());
        }
        newPassenger.setCity(city);
        return passengerService.createPassenger(newPassenger);
    }

    @PutMapping("/passenger/{id}")
    public Passenger updatePassenger(@PathVariable("id") long id, @RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(id, updatedPassenger);
    }

//    @GetMapping("/airportsPassengerUsed/{id}")
//    public List<Airport> getAirportsPassengerUsed(@PathVariable("id") long id){
//        return
//    }
}
