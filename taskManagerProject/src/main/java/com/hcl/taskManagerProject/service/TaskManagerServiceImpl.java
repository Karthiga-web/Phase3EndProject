package com.hcl.taskManagerProject.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.taskManagerProject.entity.Role;
import com.hcl.taskManagerProject.entity.TaskEntity;
import com.hcl.taskManagerProject.entity.UserEntity;
import com.hcl.taskManagerProject.repository.RoleRepository;
import com.hcl.taskManagerProject.repository.TaskRepository;
import com.hcl.taskManagerProject.repository.UserRepository;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	TaskRepository taskRepository;

	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Configuration
    static class BCryptPasswordEncoderContextConfiguration {
 
        @Bean
        public BCryptPasswordEncoder employeeService() {
            return new BCryptPasswordEncoder();
        }
    }
	
	 @Autowired
	    public TaskManagerServiceImpl(UserRepository userRepository,
	                       RoleRepository roleRepository,
	                       BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }

	@Override
	public boolean existsByUserNameAndPassword(String userName, String password) {
		return userRepository.existsByUserNameAndPassword(userName, password);
	}

	@Override
	public Optional<UserEntity> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public UserEntity update(UserEntity user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(user.getActive());
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}

	@Override
	public TaskEntity update(TaskEntity task) {
		return taskRepository.save(task);
	}

//	@Override
//	public Iterable<TaskEntity> findAllById(Iterable<Integer> userId) {
//		return taskRepository.findAllById(userId);
//	}

	@Override
	public List<TaskEntity> findAllMethod(int userId) {
		List<TaskEntity> list = taskRepository.findAll();
		List<TaskEntity> newList = null;
		for (int i = 0; i < list.size(); i++) {
//			list.get(i).getUserId()
//			if(list.get(i).getUserId() == userId) {
//				newList.add(list.get(i));
//			}
		}
//		list = list.stream().filter(c -> list.contains(c.getUserId())).collect(Collectors.toList());
		return list;
	}

	@Override
	public Optional<TaskEntity> findTaskById(int id) {
		Optional<TaskEntity> entity = taskRepository.findById(id);
		return entity;
	}

	@Override
	public void saveTask(TaskEntity taskEntity) {
		taskRepository.save(taskEntity);
	}

	@Override
	public void deleteTask(TaskEntity newTaskEntity) {
		taskRepository.deleteById(newTaskEntity.getTaskId());
	}

	@Override
	public Optional<TaskEntity> findTaskByTaskName(String taskName) {
		return taskRepository.findByTaskName(taskName);
	};

}