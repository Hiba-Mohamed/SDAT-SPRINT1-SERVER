package com.keyin.passengers;

import jakarta.persistence.*;
import com.keyin.aircraft.Aircraft;
import com.keyin.city.City;
import java.util.List;

@Entity
public class Passenger {
    @Id
    @SequenceGenerator(name = "passenger_sequence", sequenceName = "passenger_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "passenger_sequence")
    private long id;
    private String firstName;
    private String lastName;
    private int phoneNumber;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false) // FK to City
    private City city;

    @ManyToMany
    @JoinTable(
            name = "passenger_aircraft",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "aircraft_id")
    )
    private List<Aircraft> aircraft;

    public Passenger(String firstName, String lastName, int phoneNumber, City city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public Passenger(){}
    public City getCity(){
        return this.city;
    }
    public void setCity(City newCity){
        this.city = newCity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
