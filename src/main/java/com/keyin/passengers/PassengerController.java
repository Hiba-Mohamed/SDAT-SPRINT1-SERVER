package com.keyin.passengers;

import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
    public Passenger createPassenger(@RequestBody PassengerRequest newPassengerRequest) {
        System.out.println(newPassengerRequest);
        City city = cityService.findCityById(newPassengerRequest.cityId);
        System.out.println(city);
        if (city == null) {
            throw new ResourceNotFoundException("City not found with ID: " + newPassengerRequest.cityId);
        }

        Passenger newPassenger = new Passenger(newPassengerRequest.firstName, newPassengerRequest.lastName, newPassengerRequest.phoneNumber,city);


        return passengerService.createPassenger(newPassenger);
    }


    public static class PassengerRequest {
        public String firstName;
        public String lastName;
        public int phoneNumber;
        public int cityId;
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
