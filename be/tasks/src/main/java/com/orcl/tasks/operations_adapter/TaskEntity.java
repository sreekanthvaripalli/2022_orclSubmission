package com.orcl.tasks.operations_adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
@Entity
@NoArgsConstructor
@Table(name = "TASKS")
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    @Id
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Override
    public String toString(){
        return String.format("Task : [ name: %s, date: %s]", name, date);
    }
}
