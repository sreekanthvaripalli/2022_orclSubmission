package com.orcl.tasks.operations_adapter;

import com.orcl.tasks.operations.Task;
import com.orcl.tasks.operations.TasksCommandRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class TasksCommandAdapter implements TasksCommandRepository {

    private TaskJpaClient taskJpaClient;
    @Override
    public ResponseEntity<String> saveTask(Task task) {

        if (isNonExistingTaskName(task.getName())) {
            TaskEntity save = taskJpaClient.save(getTaskEntity(task));
            if (save != null) {
                return ResponseEntity.of(Optional.of("Success"));
            }
        }
        log.error("Task already exist, try to update");
        return ResponseEntity.of(Optional.of("Task already exist, try to update or add new task"));
    }

    private boolean isNonExistingTaskName(String name) {
        List<TaskEntity> result = taskJpaClient.findTaskByName(name);
        log.info("size: {}", result.size());
        return result.isEmpty();
    }

    @Override
    public ResponseEntity<String> deleteTask(String name) {

        log.info("take name: {}", name);
        try {
            int deletedRecords = taskJpaClient.deleteTaskByName(name);
            return (deletedRecords > 0) ? new ResponseEntity<>("Success", HttpStatus.OK)
                    : new ResponseEntity<>("Record not available", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("exception so failed - return failure");
            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> updateTask(Task task) {
        if (!isNonExistingTaskName(task.getName())) {
            TaskEntity save = taskJpaClient.save(getTaskEntity(task));
            if (save != null) {
                return ResponseEntity.of(Optional.of("Success"));
            }
            return new ResponseEntity<>("Unable to update the task", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Task not found in list", HttpStatus.NOT_FOUND);
    }

    private TaskEntity getTaskEntity(Task task) {
        return TaskEntity.builder()
                .name(task.getName())
                .date(task.getDate())
                .build();
    }
}
