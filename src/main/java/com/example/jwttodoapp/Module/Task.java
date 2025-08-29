package com.example.jwttodoapp.Module;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique = false, nullable = false,name = "title",updatable = false)
    String title;
    @Column(unique = false, nullable = false,name = "description")
    String description;
    @Column(unique = false, nullable = false,name = "name")
    String name;

}
