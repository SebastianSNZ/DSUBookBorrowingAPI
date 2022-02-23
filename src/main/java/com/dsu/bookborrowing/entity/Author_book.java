package com.dsu.bookborrowing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn( name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId( "author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn( name = "author_id")
    private Author author;

}
