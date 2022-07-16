package com.orcl.tasks.operations;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TasksQueryService {
    ResponseEntity<List<Task>> getTasks();
}
