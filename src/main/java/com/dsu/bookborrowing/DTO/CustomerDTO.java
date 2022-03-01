package com.dsu.bookborrowing.DTO;


import com.dsu.bookborrowing.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String username;
    private String name;
    private String rol;
    private Integer cellphone;

    public CustomerDTO(Customer customer){
        username = customer.getUsername();
        name = customer.getName();
        rol = customer.getRol().getRol();
        cellphone = customer.getCellphone();
    }
}
