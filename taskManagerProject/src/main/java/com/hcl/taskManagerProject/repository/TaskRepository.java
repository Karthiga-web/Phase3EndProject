package com.hcl.taskManagerProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.taskManagerProject.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
	Optional<TaskEntity> findByTaskName(String taskName);

	void deleteAllByUserId(int userId);
}
