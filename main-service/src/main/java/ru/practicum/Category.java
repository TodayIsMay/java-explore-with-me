package ru.practicum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    private long id;
    private String name;
}
