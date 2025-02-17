package com.keyin.aircraft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
    public Airport findByName(String name);
    public Airport findByCode(String code);
}
