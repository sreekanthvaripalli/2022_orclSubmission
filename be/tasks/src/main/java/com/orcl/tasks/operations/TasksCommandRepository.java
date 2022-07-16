package com.orcl.tasks.operations;

import org.springframework.http.ResponseEntity;

public interface TasksCommandRepository {
    ResponseEntity<String> saveTask(Task task);

    ResponseEntity<String> deleteTask(String name);

    ResponseEntity<String> updateTask(Task task);
}
