package com.keyin.airport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
    public Airport findByName(String name);
    public Airport findByCode(String code);
    List<Airport> findByCityId(Long cityId);
    @Query("SELECT DISTINCT a FROM Airport a " +
            "JOIN a.aircraftList ac " +
            "JOIN ac.passengers p " +
            "WHERE p.id = :passengerId")
    List<Airport> findAirportsByPassengerId(@Param("passengerId") long passengerId);
}
