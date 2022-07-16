package com.orcl.tasks.operations;

import org.springframework.http.ResponseEntity;

public interface TasksCommandService {
    ResponseEntity<String> saveTask(Task task);

    ResponseEntity<String> deleteTask(String name);

    ResponseEntity<String> updateTask(Task task);
}
