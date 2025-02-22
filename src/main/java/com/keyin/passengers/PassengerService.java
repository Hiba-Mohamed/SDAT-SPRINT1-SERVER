package com.keyin.passengers;

import com.keyin.aircraft.Aircraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> findAllPassengers() {
        return (List<Passenger>) passengerRepository.findAll();
    }

    public Passenger findPassengerById(long id) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(id);

        return optionalPassenger.orElse(null);
    }

    public Passenger findByFirstName(String firstName) {
        return passengerRepository.findByFirstName(firstName);
    }

    public Passenger findByLastName(String lastName) {
        return passengerRepository.findByLastName(lastName);
    }

    public Passenger findByPhoneNumber(int phoneNumber) {
        return passengerRepository.findByPhoneNumber(phoneNumber);
    }

    public Passenger createPassenger(Passenger newPassenger) {
        return passengerRepository.save(newPassenger);
    }

    public Passenger updatePassenger(long id, Passenger updatedPassenger) {
        Passenger passengerToUpdate = findPassengerById(id);

        if (passengerToUpdate != null) {new ArrayList<Passenger>();
            passengerToUpdate.setFirstName(updatedPassenger.getFirstName());
            passengerToUpdate.setLastName((updatedPassenger.getLastName()));
            passengerToUpdate.setPhoneNumber((updatedPassenger.getPhoneNumber()));

            return passengerRepository.save(passengerToUpdate);
        }

        return null;
    }

    public Passenger addToPassengerAircraftList(long id, Aircraft aircraft) {
        Passenger passengerToUpdate = findPassengerById(id);

        if (passengerToUpdate != null) {new ArrayList<Passenger>();
            List<Aircraft> passengerAircraftList = passengerToUpdate.getAircraft();
            passengerAircraftList.add(aircraft);
            return passengerRepository.save(passengerToUpdate);
        }

        return null;
    }

}
