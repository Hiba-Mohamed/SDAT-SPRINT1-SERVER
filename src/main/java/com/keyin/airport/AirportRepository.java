package com.keyin.airport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
    public Airport findByName(String name);
    public Airport findByCode(String code);
    List<Airport> findByCityId(Long cityId);

}
