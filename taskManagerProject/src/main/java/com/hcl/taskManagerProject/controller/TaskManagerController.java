package com.hcl.taskManagerProject.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcl.taskManagerProject.entity.TaskEntity;
import com.hcl.taskManagerProject.entity.UserEntity;
import com.hcl.taskManagerProject.service.TaskManagerService;

@Controller
//@RequestMapping("/taskManager")
public class TaskManagerController {
	Iterable<Integer> userId;
	int user;
	int taskId;
	@Autowired
	TaskManagerService service;

	Logger logger = LoggerFactory.getLogger(TaskManagerController.class);

	@GetMapping("/")
	String hello() {
		logger.info("Mapping to index");
		return "index";
	}
	
	@PostMapping("/decide")
	public String find(@RequestParam(name = "button") String buttonValue) {
		logger.info("Finding user clicked which button");
		if (buttonValue.equals("Login")) {
			return "login";
		} else {
			return "register";
		}
	}

	@RequestMapping(value="/login")
	String login() {
		logger.info("Mapping to index");
		return "welcome";
	}
	
	@RequestMapping(value="/welcome",method = RequestMethod.GET)
	String welcome1() {
		logger.info("Mapping to index");
		return "welcome";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") UserEntity user, ModelMap model) {
		logger.info("registration page entered");
		Optional<UserEntity> usercheck = service.findByUserName(user.getUserName());
		if (usercheck.isPresent()) {
			model.addAttribute("message", "User Already Exists");
			return "/login";
		} else {
			String save = saveMethod(user);
			if (save.equals("Saved")) {
				model.addAttribute("message", "User Information Updated");
				return "/login";
			} else {
				model.addAttribute("message", "Error Occured on Registration. Please Try again!");
				return "/register";
			}
		}
	}

	public String saveMethod(UserEntity user) {
		try {
			service.update(user);
			return "Saved";
		} catch (Exception e) {
			return "Error";
		}
	}

//	@RequestMapping("/welcome")
	@RequestMapping(value="/welcome", method = RequestMethod.POST)
	public String welcome(@RequestParam(name = "button") String buttonValue, ModelMap model) {
		logger.info("welcome page entered");
		if (buttonValue.equals("CreateTask")) {
			return "/createTask";
		} else {
//			Iterable<TaskEntity> usercheck = service.findAllById(userId);
//			model.addAttribute("entity", usercheck);
			List<TaskEntity> tasks = service.findAllMethod(user);
			model.addAttribute("listTasks", tasks);
			return "/viewTasks";
		}
	}

	@PostMapping("/createTask")
	public String createTask(@ModelAttribute("createTask") TaskEntity task, ModelMap model) {
		logger.info("create task page entered");
		task.setUserId(user);
		String save = saveTaskMethod(task);
		if (save.equals("Saved")) {
			model.addAttribute("message", "Task saved");
			return "/welcome";
		} else {
			model.addAttribute("message", "Error Occured on Task Save. Please Try again!");
			return "/createTask";
		}
	}

	public String saveTaskMethod(TaskEntity user) {
		try {
			service.update(user);
			return "Saved";
		} catch (Exception e) {
			return "Error";
		}
	}

//	@RequestMapping("/viewTasks")
//	public String viewTasks(@ModelAttribute("createTask") TaskEntity task, ModelMap model) {
//		logger.info("view task page entered");
//		return null;
//	}
	
	@GetMapping("/updateTask/{id}")
//	@ResponseBody
	public String updateTask(@PathVariable String id, ModelMap model) {
		logger.info("update task page entered");
		taskId = Integer.parseInt(id);
		Optional<TaskEntity> taskEntity = service.findTaskById(taskId);
		TaskEntity newTaskEntity = taskEntity.get();
		if(taskEntity.isPresent()) {
			model.addAttribute("taskId", newTaskEntity.getTaskId());
			model.addAttribute("taskName", newTaskEntity.getTaskName());
			model.addAttribute("startDate", newTaskEntity.getStartDate());
			model.addAttribute("endDate", newTaskEntity.getEndDate());
			model.addAttribute("description", newTaskEntity.getDescription());
			model.addAttribute("email",newTaskEntity.getEmail());
			model.addAttribute("severity", newTaskEntity.getSeverity());
			model.addAttribute("taskId", newTaskEntity.getTaskId());
			return "/updateTask";
		}else {
			List<TaskEntity> tasks = service.findAllMethod(user);
			model.addAttribute("listTasks", tasks);
			return "/viewTasks";
		}
	}
	
	@PostMapping("/updateDone")
	public String updateDone(@ModelAttribute("updateDone") TaskEntity task, ModelMap model) {
		logger.info("updateDone entered");
		TaskEntity newTaskEntity;
		Optional<TaskEntity> taskEntity = service.findTaskById(task.getTaskId());
		newTaskEntity = taskEntity.get();
		if(taskEntity.isPresent()) {
			
			newTaskEntity.setDescription(task.getDescription());
			newTaskEntity.setEmail(task.getEmail());
			newTaskEntity.setEndDate(task.getEndDate());
			newTaskEntity.setSeverity(task.getSeverity());
			newTaskEntity.setStartDate(task.getStartDate());
			newTaskEntity.setTaskId(taskId);
			newTaskEntity.setUserId(user);
			service.saveTask(newTaskEntity);
			List<TaskEntity> tasks = service.findAllMethod(user);
			model.addAttribute("listTasks", tasks);
			return "/viewTasks";
		}else {
			List<TaskEntity> tasks = service.findAllMethod(user);
			model.addAttribute("listTasks", tasks);
			return "/viewTasks";
		}
	}
	
	@GetMapping("/deleteTask/{id}")
//	@ResponseBody
	public String deleteTask(@PathVariable String id, ModelMap model) {
		taskId = Integer.parseInt(id);
		Optional<TaskEntity> taskEntity = service.findTaskById(taskId);
		TaskEntity newTaskEntity = taskEntity.get();
		if(taskEntity.isPresent()) {
			model.addAttribute("taskId", newTaskEntity.getTaskId());
			model.addAttribute("taskName", newTaskEntity.getTaskName());
			model.addAttribute("startDate", newTaskEntity.getStartDate());
			model.addAttribute("endDate", newTaskEntity.getEndDate());
			model.addAttribute("description", newTaskEntity.getDescription());
			model.addAttribute("email",newTaskEntity.getEmail());
			model.addAttribute("severity", newTaskEntity.getSeverity());
			model.addAttribute("taskId", newTaskEntity.getTaskId());
			return "/deleteTask";
		}else {
			List<TaskEntity> tasks = service.findAllMethod(user);
			model.addAttribute("listTasks", tasks);
			return "/viewTasks";
		}
	}
	
	@PostMapping("/deleteDone")
	public String deleteDone(@ModelAttribute("deleteDone") TaskEntity task, ModelMap model) {
		logger.info("updateDone entered");
		TaskEntity newTaskEntity;
		Optional<TaskEntity> taskEntity = service.findTaskById(task.getTaskId());
		newTaskEntity = taskEntity.get();
		if(taskEntity.isPresent()) {
			newTaskEntity.setDescription(task.getDescription());
			newTaskEntity.setEmail(task.getEmail());
			newTaskEntity.setEndDate(task.getEndDate());
			newTaskEntity.setSeverity(task.getSeverity());
			newTaskEntity.setStartDate(task.getStartDate());
			newTaskEntity.setTaskId(taskId);
			newTaskEntity.setUserId(user);
			service.deleteTask(newTaskEntity);
			List<TaskEntity> tasks = service.findAllMethod(user);
			model.addAttribute("listTasks", tasks);
			return "/viewTasks";
		}else {
			List<TaskEntity> tasks = service.findAllMethod(user);
			model.addAttribute("listTasks", tasks);
			return "/viewTasks";
		}
	}
}
