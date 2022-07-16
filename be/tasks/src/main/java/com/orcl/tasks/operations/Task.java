package com.orcl.tasks.operations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class Task {
    private String name;
    private LocalDate date;
}
