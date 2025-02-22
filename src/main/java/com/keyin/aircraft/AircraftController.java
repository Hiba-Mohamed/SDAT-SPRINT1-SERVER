package com.keyin.aircraft;

import com.keyin.passengers.Passenger;
import com.keyin.passengers.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private PassengerService passengerService;

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

    @PutMapping("/updateAircraftPassengersList/{id}")
    public List<PassengerDisplay> updateAircraftPassengersOnly(@PathVariable("id") Long aircraftId, @RequestBody List<Long> passengerIdList) {
        Aircraft aircraft = aircraftService.findById(aircraftId);
        if (aircraft == null) {
            throw new RuntimeException("Aircraft not found");
        }

        List<Passenger> passengers = new ArrayList<>();

        for (Long passengerId : passengerIdList) {
            Passenger passenger = passengerService.findPassengerById(passengerId);
            if (passenger == null) {
                throw new RuntimeException("Passenger with ID " + passengerId + " not found");
            }
            passengers.add(passenger);
        }

        aircraft.setPassengers(passengers);

        for (Passenger passenger : passengers) {
            if (!passenger.getAircraft().contains(aircraft)) {
                passenger.getAircraft().add(aircraft);
                passengerService.updatePassenger(passenger.getId(), passenger); // Save updated passenger
            }
        }

        aircraftService.updateAircraft(aircraftId, aircraft);

        List<PassengerDisplay> passengerDisplays = new ArrayList<>();
        for (Passenger passenger : passengers) {
            PassengerDisplay passengerDisplay = new PassengerDisplay();
            passengerDisplay.setPassengerId(passenger.getId());
            passengerDisplay.setFirstName(passenger.getFirstName());
            passengerDisplay.setLastName(passenger.getLastName());
            passengerDisplay.setPhoneNumber(passenger.getPhoneNumber());

            passengerDisplays.add(passengerDisplay);
        }

        return passengerDisplays;
    }

    @GetMapping("/{passengerId}/aircraftsPassengerTravelledOn")
    public List<AircraftDisplay> getAircraftsByPassenger(@PathVariable long passengerId) {
        Passenger passenger = passengerService.findPassengerById(passengerId);

        if (passenger == null) {
            throw new RuntimeException("Passenger not found with ID " + passengerId);
        }

        List<Aircraft> passengerAircraft = passenger.getAircraft();

        List<AircraftDisplay> aircraftForDisplay = new ArrayList<>();
        for (Aircraft aircraft : passengerAircraft) {
            AircraftDisplay display = new AircraftDisplay();
            display.setCraftId(aircraft.getId());
            display.setType(aircraft.getType());
            display.setAirlineName(aircraft.getAirlineName());

            aircraftForDisplay.add(display);
        }

        return aircraftForDisplay;
    }

    public static class AircraftDisplay{
        public long craftId;
        public String type;
        public String airlineName;

        public void setCraftId(long craftId) {
            this.craftId = craftId;
        }
        public void setType(String type) {
            this.type = type;
        }
        public void setAirlineName(String airlineName) {
            this.airlineName = airlineName;
        }
    }
    public static class PassengerDisplay{
        public long passengerId;
        public String firstName;
        public String lastName;
        public int phoneNumber;

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setPassengerId(long passengerId) {
            this.passengerId = passengerId;
        }

        public void setPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }



}
