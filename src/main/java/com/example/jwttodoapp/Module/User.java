package com.example.jwttodoapp.Module;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "todouser")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false,unique = true)
    String email;
    @Column(nullable = false,length = 255)
    String password;
}
