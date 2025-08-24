package FlyingApp.demo.controller;

import FlyingApp.demo.model.Flight;
import FlyingApp.demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")

public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight){
        return flightService.createFlight(flight);
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightId (@PathVariable Long flightId) {
        Optional<Flight> flight = flightService.getFlightById(flightId);
        return flight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> putFlightById (@PathVariable Long flightId, @RequestBody Flight flight) {
        Optional<Flight> updateFlight = flightService.updateFlight(flightId, flight);
        return updateFlight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/by-number/{flightNumber}")
    public ResponseEntity<List<Flight>> getFlightNumber (@PathVariable String flightNumber){
        List<Flight> flights = flightService.findByFlightNumber(flightNumber);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/find/by-aircraft/exact/{aircraft}")
    public ResponseEntity<List<Flight>> getAircraft (@PathVariable String aircraft){
        List<Flight> aircrafts = flightService.findByAircraft(aircraft);
        return ResponseEntity.ok(aircrafts);
    }

    @GetMapping("/find/by-aircraft/contains/{keyword}")
    public ResponseEntity<List<Flight>> getFlightsByAircraft(@PathVariable String keyword) {
        return ResponseEntity.ok(flightService.findByAircraftContaining(keyword));
    }

    @GetMapping("/find/by-mileage/{flightMileage}")
    public ResponseEntity<List<Flight>> getFlightMileage (@PathVariable Integer flightMileage) {
        List<Flight> mileage = flightService.findByFlightMileage(flightMileage);
        return ResponseEntity.ok(mileage);
    }

    @GetMapping("/find/mileage")
    public ResponseEntity<List<Flight>> findByMileage(
            @RequestParam("startMileage") Integer startMileage,
            @RequestParam("endMileage") Integer endMileage
    ){
        List<Flight> flightMileage = flightService.findByMileageRange(startMileage, endMileage);
        return ResponseEntity.ok(flightMileage);
    }

    @GetMapping("/find/mileage/min")
    public ResponseEntity<List<Flight>> findByMinMileage(@RequestParam("minMileage") Integer minMileage) {
        List<Flight> flights = flightService.findFlightsWithMileageGreaterThan(minMileage);
        return ResponseEntity.ok(flights);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight (@PathVariable Long flightId) {
        if(flightService.deleteFlight((flightId))){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
