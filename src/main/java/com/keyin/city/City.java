package com.keyin.city;
import com.keyin.airport.Airport;
import com.keyin.passengers.Passenger;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class City {
    @Id
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "city_sequence")
    private long id;
    private String name;
    private String state;
    private long population;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Airport> airports;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Passenger> passengers;

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


