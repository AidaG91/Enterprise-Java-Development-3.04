package FlyingApp.demo.repository;

import FlyingApp.demo.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
}
