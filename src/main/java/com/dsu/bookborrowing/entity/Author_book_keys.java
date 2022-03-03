package com.dsu.bookborrowing.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Author_book_keys implements Serializable {
    private Integer book;
    private Integer author;


}
