package com.keyin.passengers;

import com.keyin.city.City;
import com.keyin.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@CrossOrigin
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private CityService cityService;  // Inject CityService to use it for finding a city by ID

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
    public Passenger createPassenger(@RequestBody PassengerRequest newPassengerRequest) {
        City passengerCity = cityService.findCityById(newPassengerRequest.cityID);
        if (passengerCity == null) {
            System.out.println("City not found with ID: " + newPassengerRequest.cityID);
        }

        // Create the Passenger object with data from the request and the retrieved city
        Passenger modifiedPassenger = new Passenger(
                newPassengerRequest.firstName,
                newPassengerRequest.lastName,
                newPassengerRequest.phoneNumber,
                passengerCity
        );

        // Save the new passenger and return the saved passenger object
        return passengerService.createPassenger(modifiedPassenger);
    }

    @PutMapping("/passenger/{id}")
    public Passenger updatePassenger(@PathVariable("id") long id, @RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(id, updatedPassenger);
    }

    public static class PassengerRequest {
        public String firstName;
        public String lastName;
        public int phoneNumber;
        public long cityID;

    }
}
