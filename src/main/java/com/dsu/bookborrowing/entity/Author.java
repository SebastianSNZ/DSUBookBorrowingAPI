package com.dsu.bookborrowing.entity;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Author {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )

    private Integer author_id;
    private String name;
    private String lastName;
    private String direction;
    private Integer cellphone;


    public Author(AuthorDTO authorDTO){
        name = authorDTO.getName();
        lastName = authorDTO.getLastName();
        direction = authorDTO.getDirection();
        cellphone = authorDTO.getCellphone();
    }


//    @OneToMany(mappedBy = "author" , cascade = CascadeType.REMOVE)
//    private List<Author_book> author_books;

}
