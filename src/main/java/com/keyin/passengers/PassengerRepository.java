package com.keyin.passengers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    public Passenger findByFirstName(String firstName);
    public Passenger findByLastName(String lastName);
    public Passenger findByPhoneNumber(int phoneNumber);
}
