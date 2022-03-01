package com.dsu.bookborrowing;

import com.dsu.bookborrowing.controller.CustomerController;
import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.entity.RolModel;
import com.dsu.bookborrowing.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private CustomerService customerServicer;

    RolModel rol = new RolModel(1, "maestro");
    Customer customer1 = new Customer(1, "reyesdiego90", "Juan Diego Reyes Zepeda", rol, 56089144);
    Customer customer2 = new Customer(2, "jreyesz778", "Diego Reyes", rol, 56089144);
    Customer customer3 = new Customer(3, "poky", "andres solorzano", rol, 56089144);

    @Test
    public void getCustomerController() throws Exception{
        List<Customer> customers = new ArrayList<>(Arrays.asList(customer1, customer2, customer3));

        Mockito.when(customerServicer.getCustomer()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)))
                        .andExpect(jsonPath("$[2].name", is("andres solorzano")));
    }

    @Test
    public void getCustomerById() throws Exception{
        Mockito.when(customerServicer.getById(customer2.getId())).thenReturn(java.util.Optional.of(customer2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/customer/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Diego Reyes")));
    }

    @Test
    public void createCustomer_success() throws Exception{
        Customer customer4 = new Customer(4, "ggggg", "ffff fffffdsf", rol, 56089144);

        Mockito.when(customerServicer.setCustomer(Mockito.any())).thenReturn(customer4);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(customer4).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isNotEmpty();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(customer4));
    }


    @Test
    public void updateCustomer() throws Exception{
        Customer customer = new Customer(1, "reyesdiego90", "Jose Diego Perez Zepeda", rol, 56089144);
        Mockito.when(customerServicer.getById(customer1.getId())).thenReturn(Optional.of(customer1));
        Mockito.when(customerServicer.setCustomer(Mockito.any())).thenReturn(customer);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(customer).getBytes(StandardCharsets.UTF_8))
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isNotEmpty();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(customer));
    }

    @Test
    public void deleteCustomer() throws Exception {
        Mockito.when(customerServicer.getById(customer2.getId())).thenReturn(Optional.of(customer2));
        customerServicer.deleteCustomer(customer2.getId());
        Mockito.verify(customerServicer).deleteCustomer(customer2.getId());
    }
}
