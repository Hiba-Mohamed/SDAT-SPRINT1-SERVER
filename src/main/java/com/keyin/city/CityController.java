package com.keyin.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.findAllCities();
    }

    @GetMapping("/city/{id}")
    public City getCityByID(@PathVariable("id") long id) {
        return cityService.findCityById(id);
    }

    @GetMapping("/city_search")
    public List<City> searchCities(@RequestParam(value = "name", required = false) String name) {
        List<City> results = new ArrayList<City>();

        City city = cityService.findByName(name);

        if (city != null) {
            results.add(city);
        }

        return results;
    }

    @PostMapping("/city")
    public City createCity(@RequestBody City newCity) {
        return cityService.createCity(newCity);
    }

    @PutMapping("/city/{id}")
    public City updateCity(@PathVariable("id") long id, @RequestBody City updatedCity) {
        return cityService.updateCity(id, updatedCity);
    }
}
