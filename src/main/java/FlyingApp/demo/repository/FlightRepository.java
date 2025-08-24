package FlyingApp.demo.repository;

import FlyingApp.demo.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFlightNumber(String flightNumber);

    List<Flight> findByFlightMileage(Integer flightMileage);

    List<Flight> findByAircraft(String aircraft);

    List<Flight> findByAircraftContainingIgnoreCase(String keyword);

    List<Flight> findByFlightMileageBetween(Integer start, Integer end);

    @Query("SELECT f from Flight f WHERE f.flightMileage > :minMileage")
    List<Flight> findFlightsWithMileageGreaterThan(@Param("minMileage") Integer minMileage);
}
