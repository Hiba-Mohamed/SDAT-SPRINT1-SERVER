package com.keyin.aircraft;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
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

    @Autowired
    private AirportService airportService;


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


    @PutMapping("/updateAircraftAirportList/{id}")
    public List<AirportDisplay> updateAircraftAirportsOnly(@PathVariable("id") Long aircraftId, @RequestBody List<Long> airportIdList) {
        Aircraft aircraft = aircraftService.findById(aircraftId);
        if (aircraft == null) {
            throw new RuntimeException("Aircraft not found");
        }

        List<Airport> airports = new ArrayList<>();

        for (Long airportId : airportIdList) {
            Airport airport = airportService.findAirportById(airportId);
            if (airport == null) {
                throw new RuntimeException("Airport with ID " + airportId + " not found");
            }
            airports.add(airport);
        }

        aircraft.setAirports(airports);

        for (Airport airport : airports) {
            if (!airport.getAircraftList().contains(aircraft)) {
                airport.getAircraftList().add(aircraft);
                airportService.updateAirport(airport.getId(), airport);
            }
        }

        aircraftService.updateAircraft(aircraftId, aircraft);

        List<AirportDisplay> airportDisplays = new ArrayList<>();
        for (Airport airport : airports) {
            AirportDisplay airportDisplay = new AirportDisplay();
            airportDisplay.setAirportId(airport.getId());
            airportDisplay.setAirportCity(airport.getCity().getName());
            airportDisplay.setAirportName(airport.getName());
            airportDisplay.setAirportCode(airport.getCode());
            airportDisplays.add(airportDisplay);
        }

        return airportDisplays;
    }

    @GetMapping("/{aircraftId}/aircraftsAirportsList")
    public List<AirportDisplay> getAirportsByAircraft(@PathVariable long aircraftId) {
        Aircraft aircraft = aircraftService.findById(aircraftId);

        if (aircraft == null) {
            throw new RuntimeException("Aircraft not found");
        }

        List<Airport> airports = aircraft.getAirports();

        List<AirportDisplay> airportsForDisplay = new ArrayList<>();
        for (Airport airport : airports) {
            AirportDisplay display = new AirportDisplay();
            display.setAirportId(airport.getId());
            display.setAirportName(airport.getName());
            display.setAirportCity(airport.getCity().getName());
            display.setAirportCode(airport.getCode());
            airportsForDisplay.add(display);
        }

        return airportsForDisplay;
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

    public static class AirportDisplay{
        public long airportId;
        public String airportName;
        public String airportCode;
        public String airportCity;

        public void setAirportId(long airportId) {
            this.airportId = airportId;
        }

        public void setAirportCity(String airportCity) {
            this.airportCity = airportCity;
        }

        public void setAirportName(String airportName) {
            this.airportName = airportName;
        }

        public void setAirportCode(String airportCode) {
            this.airportCode = airportCode;
        }
    }
    }
