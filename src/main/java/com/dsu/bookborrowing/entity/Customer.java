package com.dsu.bookborrowing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Customer {
    @Id
    private Integer customer_id;

    private String username;

    private String name;

    private Integer rol;

    private Integer cellphone;
}
