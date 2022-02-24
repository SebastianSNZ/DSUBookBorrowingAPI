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

    public AuthorDTO(Author au){
        name = au.getName();
    }
}
