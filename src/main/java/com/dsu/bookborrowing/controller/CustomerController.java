package com.dsu.bookborrowing.controller;


import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/customer")
@Api(value = "API Rest Customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping()
    public ArrayList<Customer> getCustomer(){
        return  customerService.getCustomer();
    }

    @PostMapping
    public  Customer setCustomer(@RequestBody Customer customer){
        return  customerService.setCustomer(customer);
    }


}
