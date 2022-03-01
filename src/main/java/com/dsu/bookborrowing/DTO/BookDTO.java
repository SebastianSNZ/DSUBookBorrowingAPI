package com.dsu.bookborrowing.DTO;

import com.dsu.bookborrowing.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String name;
    private String category;
    private Integer quantity;


}
