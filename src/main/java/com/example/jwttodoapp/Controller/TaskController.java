package com.example.jwttodoapp.Controller;

import com.example.jwttodoapp.Module.Task;
import com.example.jwttodoapp.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;


    @QueryMapping

    public Task getTask(@Argument Long id) {
        return taskService.getTask(id);
    }

    @QueryMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks(); // 🚀 this is cached in Redis
    }

    @MutationMapping
    public Task createTask(@Argument String title,
                           @Argument String description,
                           @Argument String name) {
        Task t = new Task();
        t.setTitle(title);
        t.setDescription(description);
        t.setName(name);
        taskService.SaveTask(t);
        return t;
    }

    @MutationMapping
    public String deleteTask(@Argument Long id) {
        taskService.DeleteTask(id);
        return "Task deleted";
    }
}
