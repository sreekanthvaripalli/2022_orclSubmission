package com.orcl.tasks.operations;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TasksQueryRepository {
    ResponseEntity<List<Task>> getTasks();
}
