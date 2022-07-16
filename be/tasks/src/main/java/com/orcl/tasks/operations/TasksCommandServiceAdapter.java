package com.orcl.tasks.operations;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class TasksCommandServiceAdapter implements TasksCommandService {
    private TasksCommandRepository tasksCommandRepository;
    @Override
    public ResponseEntity<String> saveTask(Task task) {
        return tasksCommandRepository.saveTask(task);
    }

    @Override
    public ResponseEntity<String> deleteTask(String name) {
        return tasksCommandRepository.deleteTask(name);
    }

    @Override
    public ResponseEntity<String> updateTask(Task task) {
        return tasksCommandRepository.updateTask(task);
    }
}
