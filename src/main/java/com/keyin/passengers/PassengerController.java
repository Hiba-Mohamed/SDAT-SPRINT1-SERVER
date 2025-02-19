package com.keyin.passengers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.findAllPassengers();
    }

    @GetMapping("/passenger/{id}")
    public Passenger getPassengerByID(@PathVariable("id") long id) {
        return passengerService.findPassengerById(id);
    }

//    @GetMapping("/passenger_search")
//    public List<Passenger> searchPassengers(@RequestParam(value = "name", required = false) String name) {
//        List<Passenger> results = new ArrayList<Passenger>();
//
//        Passenger passenger = passengerService.findByName(name);
//
//        if (passenger != null) {
//            results.add(passenger);
//        }
//
//        return results;
//    }

    @PostMapping("/passenger")
    public Passenger createPassenger(@RequestBody Passenger newPassenger) {
        return passengerService.createPassenger(newPassenger);
    }

    @PutMapping("/passenger/{id}")
    public Passenger updatePassenger(@PathVariable("id") long id, @RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(id, updatedPassenger);
    }
}
