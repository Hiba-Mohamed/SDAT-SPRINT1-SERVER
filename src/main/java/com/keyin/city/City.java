package com.keyin.city;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class City {
    @Id
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "city_sequence")
    private long id;
    private String name;
    private String state;
    private long population;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public long getPopulation() { return population; }

    public void setPopulation(long population) { this.population = population; }
}


