package com.keyin.aircraft;
import com.keyin.aircraft.Aircraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Aircraft> findAllAircrafts() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    public Aircraft findById(long id) {
        Optional<Aircraft> optionalAircraft = Optional.ofNullable(aircraftRepository.findById(id));

        return optionalAircraft.orElse(null);
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
            return aircraftRepository.save(aircraftToUpdate);
        }

        return null;
    }


}
