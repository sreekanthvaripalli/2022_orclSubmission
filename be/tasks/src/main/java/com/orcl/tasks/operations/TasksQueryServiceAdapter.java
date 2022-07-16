package com.orcl.tasks.operations;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@AllArgsConstructor
public class TasksQueryServiceAdapter implements TasksQueryService {

    private TasksQueryRepository tasksQueryRepository;
    @Override
    public ResponseEntity<List<Task>> getTasks() {
        return tasksQueryRepository.getTasks();
    }
}
