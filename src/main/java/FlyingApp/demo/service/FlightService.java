package FlyingApp.demo.service;

import FlyingApp.demo.model.Flight;
import FlyingApp.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long flightId) {
        return flightRepository.findById(flightId);
    }

    public Optional<Flight> updateFlight(Long flightId, Flight flight){
        if (flightRepository.existsById(flightId)) {
            flight.setFlightId(flightId);
            return Optional.of(flightRepository.save(flight));
        }
        return Optional.empty();
    }

    public List<Flight> findByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    public List<Flight> findByAircraft(String aircraft) {
        return flightRepository.findByAircraft(aircraft);
    }

    public List<Flight> findByFlightMileage(Integer flightMileage) {
        return flightRepository.findByFlightMileage(flightMileage);
    }

    public List<Flight> findByMileageRange (Integer startMileage, Integer endMileage) {
        return flightRepository.findByFlightMileageBetween(startMileage, endMileage);
    }

    public List<Flight> findFlightsWithMileageGreaterThan(Integer minMileage) {
        return flightRepository.findFlightsWithMileageGreaterThan(minMileage);
    }


    public boolean deleteFlight(Long flightId) {
        if (flightRepository.existsById(flightId)) {
            flightRepository.deleteById(flightId);
            return true;
        }
        return false;
    }

}
