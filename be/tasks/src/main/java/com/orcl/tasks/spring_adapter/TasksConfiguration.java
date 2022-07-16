package com.orcl.tasks.spring_adapter;

import com.orcl.tasks.operations.TasksCommandRepository;
import com.orcl.tasks.operations.TasksCommandService;
import com.orcl.tasks.operations.TasksCommandServiceAdapter;
import com.orcl.tasks.operations.TasksQueryRepository;
import com.orcl.tasks.operations.TasksQueryService;
import com.orcl.tasks.operations.TasksQueryServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TasksConfiguration {

    @Bean
    public TasksCommandService tasksCommandService (TasksCommandRepository tasksCommandRepository) {
        return new TasksCommandServiceAdapter(tasksCommandRepository);
    }

    @Bean
    public TasksQueryService tasksQueryService (TasksQueryRepository tasksQueryRepository) {
        return new TasksQueryServiceAdapter(tasksQueryRepository);
    }
}
