package com.dsu.bookborrowing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    private Integer customer_id;

    @Column(name="username", unique = true, nullable = false)
    private String username;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RolModel rol;

    @Column(name="cellphone")
    private Integer cellphone;
}
