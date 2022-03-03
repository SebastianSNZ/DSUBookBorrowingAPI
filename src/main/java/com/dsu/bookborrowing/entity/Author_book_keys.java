package com.dsu.bookborrowing.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
public class Author_book_keys implements Serializable {
    private Integer book;
    private Integer author;


}
