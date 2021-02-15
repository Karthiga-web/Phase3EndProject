package com.hcl.taskManagerProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.taskManagerProject.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
//	List<TaskEntity> findByOwnerOrderByDateDesc(UserEntity user);
	 Optional<TaskEntity> findByTaskName(String taskName);
}