package com.keyin.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAllCities() {
        return (List<City>) cityRepository.findAll();
    }

    public City findCityById(long id) {
        Optional<City> optionalCity = cityRepository.findById(id);

        return optionalCity.orElse(null);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name);
    }

    public City createCity(City newCity) {
        return cityRepository.save(newCity);
    }

    public City updateCity(long id, City updatedCity) {
        City cityToUpdate = findCityById(id);

        if (cityToUpdate != null) {new ArrayList<City>();
            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setState((updatedCity.getState()));
            cityToUpdate.setPopulation(updatedCity.getPopulation());

            return cityRepository.save(cityToUpdate);
        }

        return null;
    }
}
