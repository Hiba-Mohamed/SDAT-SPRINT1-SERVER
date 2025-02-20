package com.keyin.airport;

import jakarta.persistence.*;
import com.keyin.city.City;

import com.keyin.aircraft.Aircraft;
import java.util.List;

@Entity
public class Airport {
    @Id
    @SequenceGenerator(name = "airport_sequence", sequenceName = "airport_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "airport_sequence")
    private long id;

    private String code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false) // FK to City
    private City city;

    @ManyToMany(mappedBy = "airports")
    private List<Aircraft> aircraftList;

    public Airport(){}

    public Airport(String code, String name, City city, List<Aircraft> aircraftList)
    {
        this.code = code;
        this.name = name;
        this.city = city;
        this.aircraftList = aircraftList;
    }
    public long getId() {
        return id;
    }

    public void setId(long newId) {
        this.id = newId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String newCode) {
        this.code = newCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public City getCity(){
        return this.city;
    }
    public void setCity(City newCity){
        this.city = newCity;
    }

    public List<Aircraft> getAircraftList(){
        return this.aircraftList;
    }

    public void setAircraftList(List<Aircraft> newAircraftlist){
        this.aircraftList = newAircraftlist;
    }
}
