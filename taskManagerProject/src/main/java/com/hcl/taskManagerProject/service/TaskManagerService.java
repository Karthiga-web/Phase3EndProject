package com.hcl.taskManagerProject.service;

import java.util.List;
import java.util.Optional;

import com.hcl.taskManagerProject.entity.TaskEntity;
import com.hcl.taskManagerProject.entity.UserEntity;

public interface TaskManagerService {

	boolean existsByUserNameAndPassword(String userName, String password);

	Optional<UserEntity> findByUserName(String userName);

	UserEntity update(UserEntity user);

	TaskEntity update(TaskEntity user);

//	Iterable<TaskEntity> findAllById(Iterable<Integer> userId);

	List<TaskEntity> findAllMethod(int userId);

	Optional<TaskEntity> findTaskById(int taskId);

	void saveTask(TaskEntity newTaskEntity);

	void deleteTask(TaskEntity newTaskEntity);

	Optional<TaskEntity> findTaskByTaskName(String taskName);

}
