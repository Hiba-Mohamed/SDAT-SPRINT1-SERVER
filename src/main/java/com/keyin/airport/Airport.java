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
    private List<Aircraft> aircraft;

    public Airport(){}

    public Airport(String code, String name)
    {
        this.code = code;
        this.name = name;
    }    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
