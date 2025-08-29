package com.example.jwttodoapp.Services;

import com.example.jwttodoapp.Module.Task;
import com.example.jwttodoapp.Repositories.TaskRepository;
import org.springframework.cache.annotation.Cacheable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;


    public Task getTask(Long id) {
        Optional<Task> task=taskRepository.findById(id);
        return task.orElse(null);
    }

    @Cacheable(value = "Tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public void SaveTask(Task task){
        taskRepository.save(task);
    }
    public void DeleteTask(Long id){
        taskRepository.deleteTaskById(id);
    }
}
