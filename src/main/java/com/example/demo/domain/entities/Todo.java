package com.example.demo.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@Builder
public class Todo {

    private Integer userId;
    private Integer id;
    private String title;
    private Boolean completed;
}
