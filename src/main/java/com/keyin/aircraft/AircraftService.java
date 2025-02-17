package com.keyin.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository airportRepository;

    public List<Aircraft> findAllAirports() {
        return (List<Aircraft>) airportRepository.findAll();
    }

    public Aircraft findAirportById(long id) {
        Optional<Aircraft> optionalAirport = airportRepository.findById(id);

        return optionalAirport.orElse(null);
    }

    public Aircraft findByName(String name) {
        return airportRepository.findByName(name);
    }

    public Aircraft createAirport(Aircraft newAirport) {
        return airportRepository.save(newAirport);
    }

    public Aircraft updateAirport(long id, Aircraft updatedAirport) {
        Aircraft airportToUpdate = findAirportById(id);

        if (airportToUpdate != null) {
            airportToUpdate.setCode(updatedAirport.getCode());
            airportToUpdate.setName(updatedAirport.getName());

            return airportRepository.save(airportToUpdate);
        }

        return null;
    }
}
