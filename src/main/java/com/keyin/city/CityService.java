package com.keyin.city;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    // Retrieve all cities
    public List<City> findAllCities() {
        return (List<City>) cityRepository.findAll();
    }

    public List<City> saveCities(List<City> cities) {
        return (List<City>) cityRepository.saveAll(cities); // Save all cities in bulk
    }

    @PostConstruct
    public void initializeCities() {
        // Only add cities if they don't already exist
        if (cityRepository.count() == 0) {
            List<City> cities = Arrays.asList(
                    new City("Toronto", "Ontario", 2950000),
                    new City("Vancouver", "British Columbia", 675218),
                    new City("Montreal", "Quebec", 1780000),
                    new City("Calgary", "Alberta", 1239220),
                    new City("Edmonton", "Alberta", 1050000),
                    new City("Ottawa", "Ontario", 1010000),
                    new City("Winnipeg", "Manitoba", 749534),
                    new City("Quebec City", "Quebec", 542298),
                    new City("Hamilton", "Ontario", 579200),
                    new City("Halifax", "Nova Scotia", 480582)
            );
            for (City city : cities) {
                if (cityRepository.findByName(city.getName()) == null) {
                    cityRepository.save(city);
                }
            }
        }
    }

    // Retrieve city by ID
    public City findCityById(long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        return optionalCity.orElse(null); // Return null if not found
    }

    // Search for a city by name
    public City findByName(String name) {
        return cityRepository.findByName(name); // Assuming `findByName` is defined in repository
    }

    // Create a new city
    public City createCity(City newCity) {
        return cityRepository.save(newCity); // Save new city
    }

    // Update an existing city
    public City updateCity(long id, City updatedCity) {
        City cityToUpdate = findCityById(id);

        if (cityToUpdate != null) {
            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setState(updatedCity.getState());
            return cityRepository.save(cityToUpdate);
        }

        return null; // Return null if the city is not found
    }
}
