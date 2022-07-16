package com.orcl.tasks.operations_resource;

import com.orcl.tasks.operations.Task;
import com.orcl.tasks.operations.TasksCommandService;
import com.orcl.tasks.operations.TasksQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TasksHandler {

    private TasksCommandService tasksCommandService;
    private TasksQueryService tasksQueryService;
    public ResponseEntity<Task> saveTask(Task task) {
        return tasksCommandService.saveTask(task);
    }

    public ResponseEntity<List<Task>> getTasks() {
        return tasksQueryService.getTasks();
    }

    public ResponseEntity<List<Task>> deleteTask(String name) {
        return tasksCommandService.deleteTask(name);
    }

    public ResponseEntity<Task> administerTask(Task task) {
        return tasksCommandService.updateTask(task);
    }
}
