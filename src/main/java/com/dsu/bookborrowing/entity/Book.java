package com.dsu.bookborrowing.entity;

import com.dsu.bookborrowing.DTO.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    private Integer book_id;
    private String name;
    private String category;
    private Integer quantity;

    public Book(BookDTO bookDTO) {
        name = bookDTO.getName();
        category = bookDTO.getCategory();
        quantity = bookDTO.getQuantity();
    }

//    @OneToMany(mappedBy = "book" , cascade = CascadeType.REMOVE)
//    private List<Author_book> author_books;


}
