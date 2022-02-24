package com.dsu.bookborrowing.entity;

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
public class Author_book {

    @EmbeddedId
    Author_book_keys id;

    @ManyToOne
    @MapsId( "book_id")
    @JoinColumn( name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId( "author_id")
    @JoinColumn( name = "author_id")
    private Book author;

}
