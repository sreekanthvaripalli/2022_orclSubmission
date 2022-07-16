package com.orcl.tasks.operations;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@AllArgsConstructor
public class TasksCommandServiceAdapter implements TasksCommandService {
    private TasksCommandRepository tasksCommandRepository;
    @Override
    public ResponseEntity<Task> saveTask(Task task) {
        return tasksCommandRepository.saveTask(task);
    }

    @Override
    public ResponseEntity<List<Task>> deleteTask(String name) {
        return tasksCommandRepository.deleteTask(name);
    }

    @Override
    public ResponseEntity<Task> updateTask(Task task) {
        return tasksCommandRepository.updateTask(task);
    }
}
