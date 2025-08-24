package FlyingApp.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class FlightBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
   // @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customerId;

    @ManyToOne
  //  @JoinColumn(name = "flight_id", referencedColumnName = "flightId")
    private Flight flightId;

    // Constructor
    public FlightBooking() {
    }

    public FlightBooking(Customer customerId, Flight flightId) {
        this.customerId = customerId;
        this.flightId = flightId;
    }

    // Getters and setters
    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Flight getFlightId() {
        return flightId;
    }

    public void setFlightId(Flight flightId) {
        this.flightId = flightId;
    }

}
