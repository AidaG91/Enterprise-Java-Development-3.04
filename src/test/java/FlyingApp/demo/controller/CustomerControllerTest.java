package FlyingApp.demo.controller;

import FlyingApp.demo.enums.CustomerStatus;
import FlyingApp.demo.model.Customer;
import FlyingApp.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)

public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer juan, helena;

    @BeforeEach
    public void setUp(){
        juan = new Customer("Juan", CustomerStatus.SILVER, 1200);
        helena = new Customer("Helena", CustomerStatus.NONE, 350);
    }

    @Test
    public void testCreateCustomer() throws Exception{
        when(customerService.createCustomer(juan)).thenReturn(juan);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(juan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Juan"))
                .andExpect(jsonPath("$.customerStatus").value("SILVER"))
                .andExpect(jsonPath("$.totalCustomerMileage").value(1200));
    }

    @Test
    public void testGetCustomerByName() throws Exception {
        String customerName = "Helena";

        when(customerService.findByCustomerName(customerName)).thenReturn(List.of(helena));

        mockMvc.perform(get("/customers/find/by-name/{customerName}", customerName).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName").value("Helena"));

    }

    @Test
    public void testGetCustomerByStatus() throws Exception{
        CustomerStatus customerStatus = CustomerStatus.NONE;

        when(customerService.findByCustomerStatus(customerStatus)).thenReturn(List.of(helena));

        mockMvc.perform(get("/customers/find/by-status/" + customerStatus.name()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerStatus").value("NONE"));
    }

}
