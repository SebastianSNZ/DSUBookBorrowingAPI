package com.dsu.bookborrowing.entity;

import lombok.Data;

import javax.persistence.*;


@Table
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer customer_id;

    private String username;

    private String name;

    private Integer rol;

    private Integer cellphone;
}
