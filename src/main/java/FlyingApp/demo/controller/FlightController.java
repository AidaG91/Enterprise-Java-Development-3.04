package FlyingApp.demo.controller;

import FlyingApp.demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")

public class FlightController {

    @Autowired
    private FlightService flightService;

}
