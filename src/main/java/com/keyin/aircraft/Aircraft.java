package com.keyin.aircraft;

import jakarta.persistence.*;
import com.keyin.passengers.Passenger;
import java.util.List;
import com.keyin.airport.Airport;


@Entity
public class Aircraft {
    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "aircraft_sequence")
    private long id;
    private String type;
    private String airlineName;
    private int numberOfPassengers;

    @ManyToMany
    @JoinTable(
            name = "aircraft_airport",
            joinColumns = @JoinColumn(name = "aircraft_id"),
            inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    private List<Airport> airports;

    @ManyToMany(mappedBy = "aircraft")
    private List<Passenger> passengers;

    public Aircraft(){}

    public Aircraft(String type, String airlineName, int numberOfPassengers, List<Airport> airports, List<Passenger> passengers) {
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
        this.airports = airports;
        this.passengers = passengers;
    }

    //getters
    public long getId() {
        return id;
    }
    public String getType() {
        return this.type;
    }
    public String getAirlineName(){return  this.airlineName;}
    public int getNumberOfPassengers(){return this.numberOfPassengers;}
    public List<Passenger> getPassengers(){
        return this.passengers;
    }
    public List<Airport> getAirports(){
        return this.airports;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    public void setNumberOfPassengers(int numberOfPassengers){
        this.numberOfPassengers = numberOfPassengers;
    }
    public void setAirports(List<Airport> airports) { this.airports = airports; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
}
