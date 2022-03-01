package com.dsu.bookborrowing.controller;


import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customer")
@Api(value = "API Rest Customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<Customer> getCustomer(){
        return customerService.getCustomer();
    }

    @PostMapping
    public Customer setCustomer(@RequestBody @Valid Customer customer){
        return  customerService.setCustomer(customer);
    }

    @GetMapping(path = "/{id}")
    public Optional<Customer> getById(@PathVariable("id") Integer id){
        return  customerService.getById(id);
    }

    @GetMapping("/query")
    ArrayList<Customer> getByRol(@RequestParam("rol") Integer id){
        return customerService.getByRol(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        boolean ok = customerService.deleteCustomer(id);
        if(ok){
            return "Se elimino usuario con id " + id;
        }else{
            return "No se ha podido eliminar usuario con id " + id;
        }
    }

    @PutMapping
    public  Customer updateCustomer(@RequestBody Customer customer){
        return  customerService.updateCustomer(customer);
    }

}
