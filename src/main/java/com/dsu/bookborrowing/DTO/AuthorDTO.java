package com.dsu.bookborrowing.DTO;

import com.dsu.bookborrowing.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private String name;
    private String lastName;
    private String direction;
    private Integer cellphone;


}
