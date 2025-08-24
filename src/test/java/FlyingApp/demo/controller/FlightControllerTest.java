package FlyingApp.demo.controller;

import FlyingApp.demo.model.Flight;
import FlyingApp.demo.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)

public class FlightControllerTest {
   @Autowired
   private MockMvc mockMvc;

   @MockitoBean
    private FlightService flightService;

   @Autowired
    private ObjectMapper objectMapper;

   private Flight boeing, airbus;

   @BeforeEach
    public void setUp() {
       boeing = new Flight("DL143", "Boeing 747", 400, 135);
       airbus = new Flight("DL122", "Airbus A330", 236, 4370);
   }

   @Test
    public void testCreateFlight() throws Exception{
       when(flightService.createFlight(boeing)).thenReturn(boeing);

       mockMvc.perform(post("/flights").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(boeing)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.flightNumber").value("DL143"))
               .andExpect(jsonPath("$.aircraft").value("Boeing 747"))
               .andExpect(jsonPath("$.totalAircraftSeats").value(400))
               .andExpect(jsonPath("$.flightMileage").value(135));
   }

   @Test
    public void testGetFlightByFlightNumber() throws Exception {
       String flightNumber = "DL143";

       when(flightService.findByFlightNumber(flightNumber)).thenReturn(List.of(boeing));

       mockMvc.perform(get("/flights/find/by-number/{flightNumber}", flightNumber).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].flightNumber").value("DL143"));
   }

    @Test
    public void testGetFlightsByAircraftContainingKeyword() throws Exception {
        String keyword = "Boeing";

        when(flightService.findByAircraftContaining(keyword)).thenReturn(List.of(boeing));

        mockMvc.perform(get("/flights/find/by-aircraft/contains/{keyword}", keyword)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aircraft").value("Boeing 747"));
    }

    @Test
    void shouldFindFlightsWithMileageGreaterThan500() throws Exception {
        when(flightService.findFlightsWithMileageGreaterThan(500)).thenReturn(List.of(airbus));


        mockMvc.perform(get("/flights/find/mileage/min")
                        .param("minMileage", "500")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].flightMileage").value(Matchers.greaterThan(500)));
    }

}
