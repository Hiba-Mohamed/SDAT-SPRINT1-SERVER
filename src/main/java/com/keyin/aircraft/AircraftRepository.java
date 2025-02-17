package com.keyin.aircraft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
    public Aircraft findByName(String name);
    public Aircraft findByCode(String code);
}
