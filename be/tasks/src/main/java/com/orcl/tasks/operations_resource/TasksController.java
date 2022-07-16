package com.orcl.tasks.operations_resource;

import com.orcl.tasks.operations.Task;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "To perform CRUD operations on tasks")
@AllArgsConstructor
public class TasksController {
    private TasksHandler tasksHandler;

    @PostMapping(value = "/saveTask")
    public ResponseEntity<Task> saveTask(
            @RequestBody Task tasksRequest
    ){
        return tasksHandler.saveTask(tasksRequest);
    }

    @GetMapping(value = "/getTasks")
    public ResponseEntity<List<Task>> getTasks(
    ) {
        return tasksHandler.getTasks();
    }

    @DeleteMapping(value = "/deleteTask")
    public ResponseEntity<List<Task>> deleteTask(
            @RequestParam String name
    ) {
        return tasksHandler.deleteTask(name);
    }
    @PutMapping(value = "/administerTask")
    public ResponseEntity<Task> administerTask(
            @RequestBody Task taskRequest
    ) {
        return tasksHandler.administerTask(taskRequest);
    }
}
