package com.keyin.aircraft;
import com.keyin.aircraft.Aircraft;
import com.keyin.passengers.Passenger;
import com.keyin.passengers.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private PassengerService passengerService;
    public List<Aircraft> findAllAircrafts() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    public Aircraft findById(long id) {
        Optional<Aircraft> optionalAircraft = aircraftRepository.findById(id);
        return optionalAircraft.orElse(null); // Return null if not found
    }

    public Aircraft createAircraft(Aircraft newAircraft) {
        return aircraftRepository.save(newAircraft);
    }

    public Aircraft updateAircraft(long id, Aircraft updatedAircraft) {
        Aircraft aircraftToUpdate = findById(id);
        if (aircraftToUpdate != null) {
            aircraftToUpdate.setType(updatedAircraft.getType());
            aircraftToUpdate.setAirlineName(updatedAircraft.getAirlineName());
            aircraftToUpdate.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());
            aircraftToUpdate.setPassengers(updatedAircraft.getPassengers());
            return aircraftRepository.save(aircraftToUpdate);
        }

        return null;
    }




}
