package com.orcl.tasks.operations_adapter;

import com.orcl.tasks.operations.Task;
import com.orcl.tasks.operations.TasksQueryRepository;
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
public class TasksQueryAdapter implements TasksQueryRepository {

    private TaskJpaClient taskJpaClient;

    @Override
    public ResponseEntity<List<Task>> getTasks() {
        try {
            Optional<List<Task>> tasksOptional = getAllTasksFromDB();
            return tasksOptional.map(users -> new ResponseEntity<>(users, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            log.error("Exception occurred while fetching all users", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Optional<List<Task>> getAllTasksFromDB() {
        List<TaskEntity> allTasks = (List<TaskEntity>) taskJpaClient.findAll();
        if (CollectionUtils.isEmpty(allTasks)) {
            log.error("returned empty result from DB");
            return Optional.empty();
        } else {
            log.info("number of users fetched : {}", allTasks.size());
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
}
