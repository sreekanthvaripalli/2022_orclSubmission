package com.orcl.tasks.operations_adapter;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskJpaClient extends CrudRepository<TaskEntity, Long> {

    @Query(
            value = "SELECT * FROM TASKS WHERE TASKS.NAME = ?1",
            nativeQuery = true
    )
    List<TaskEntity> findTaskByName(String name);

    @Modifying
    @Query(
            value = "DELETE FROM TASKS WHERE TASKS.NAME = :name",
            nativeQuery = true
    )
    int deleteTaskByName(@Param("name") String name);
}
