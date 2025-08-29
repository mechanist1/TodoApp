package com.example.jwttodoapp.Repositories;

import com.example.jwttodoapp.Module.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByName(String name);
    Optional<Task> deleteTaskById(Long id);}
