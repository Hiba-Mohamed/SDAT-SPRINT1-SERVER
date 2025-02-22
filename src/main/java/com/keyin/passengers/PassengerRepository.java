package com.keyin.passengers;

import com.keyin.aircraft.Aircraft;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    public Passenger findByFirstName(String firstName);
    public Passenger findByLastName(String lastName);
    public Passenger findByPhoneNumber(int phoneNumber);
}
