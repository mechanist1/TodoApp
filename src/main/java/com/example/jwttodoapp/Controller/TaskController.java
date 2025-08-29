package com.example.jwttodoapp.Controller;

import com.example.jwttodoapp.Module.Task;
import com.example.jwttodoapp.Services.TaskService;
import org.springframework.cache.annotation.Cacheable;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;


    @GetMapping("/get/{id}")

    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }
    @GetMapping("/getAll")
    public List<Task> getAllTasks() {
        System.out.println("Fetching from DB..."+taskService.getAllTasks());
        return taskService.getAllTasks();
    }

    @PostMapping("/post")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        taskService.SaveTask(task);
        return ResponseEntity.ok("Task created");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestBody Long id) {
        taskService.DeleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }
}
