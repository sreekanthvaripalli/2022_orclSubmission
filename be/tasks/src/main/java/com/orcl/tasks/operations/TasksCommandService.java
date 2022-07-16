package com.orcl.tasks.operations;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TasksCommandService {
    ResponseEntity<Task> saveTask(Task task);

    ResponseEntity<List<Task>> deleteTask(String name);

    ResponseEntity<Task> updateTask(Task task);
}
