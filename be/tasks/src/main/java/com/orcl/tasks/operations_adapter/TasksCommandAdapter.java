package com.orcl.tasks.operations_adapter;

import com.orcl.tasks.operations.Task;
import com.orcl.tasks.operations.TasksCommandRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class TasksCommandAdapter implements TasksCommandRepository {

    private TaskJpaClient taskJpaClient;
    @Override
    public ResponseEntity<Task> saveTask(Task task) {

        if (isNonExistingTaskName(task.getName())) {
            TaskEntity save = taskJpaClient.save(getTaskEntity(task));
            if (save != null) {
                return new ResponseEntity<>(Task.builder().name(save.getName()).date(save.getDate()).build(), HttpStatus.OK);
            }
        }
        log.error("Task already exist, try to update");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean isNonExistingTaskName(String name) {
        List<TaskEntity> result = taskJpaClient.findTaskByName(name);
        log.info("size: {}", result.size());
        return result.isEmpty();
    }

    @Override
    public ResponseEntity<List<Task>> deleteTask(String name) {

        log.info("take name: {}", name);
        try {
            int deletedRecords = taskJpaClient.deleteTaskByName(name);
            return (deletedRecords > 0) ? getTasks() : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("exception so failed - return failure");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Task>> getTasks() {
        try {
            Optional<List<Task>> tasksOptional = getAllTasks();
            return tasksOptional.map(tasks -> new ResponseEntity<>(tasks, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            log.error("Exception occurred while fetching all tasks", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Optional<List<Task>> getAllTasks() {
        List<TaskEntity> allTasks = (List<TaskEntity>) taskJpaClient.findAll();
        if (CollectionUtils.isEmpty(allTasks)) {
            log.error("returned empty result from DB");
            return Optional.empty();
        } else {
            log.info("number of tasks fetched : {}", allTasks.size());
            return Optional.of(getMappedTasks(allTasks));
        }
    }

    private List<Task> getMappedTasks(List<TaskEntity> allTasks) {
        return allTasks.stream().map(this::getBuildTask)
                .collect(Collectors.toList());
    }

    private Task getBuildTask(TaskEntity taskEntity) {
        return Task.builder()
                .name(taskEntity.getName())
                .date(taskEntity.getDate())
                .build();
    }

    @Override
    public ResponseEntity<Task> updateTask(Task task) {
        if (!isNonExistingTaskName(task.getName())) {
            TaskEntity save = taskJpaClient.save(getTaskEntity(task));
            if (save != null) {
                log.debug("returning updated date of record {}", save.getDate());
                return new ResponseEntity<>(Task.builder()
                        .name(save.getName())
                        .date(save.getDate())
                        .build(), HttpStatus.OK);
            }
            log.error("unable to update the record");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.error("Task not found in DB");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private TaskEntity getTaskEntity(Task task) {
        return TaskEntity.builder()
                .name(task.getName())
                .date(task.getDate())
                .build();
    }
}
