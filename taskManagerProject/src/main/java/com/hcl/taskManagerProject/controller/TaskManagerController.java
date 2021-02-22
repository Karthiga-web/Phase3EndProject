package com.hcl.taskManagerProject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

import com.hcl.taskManagerProject.entity.TaskEntity;
import com.hcl.taskManagerProject.entity.UserEntity;
import com.hcl.taskManagerProject.exceptions.SaveFailedException;
import com.hcl.taskManagerProject.service.TaskManagerService;

@Controller
public class TaskManagerController {
	String currentUserName;
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

	@RequestMapping(value = "/login")
	public String getLoginPage(@RequestParam(name = "error", required = false) String error, Model model,
			@RequestParam(name = "logout", required = false) String logout) {
		if (error != null) {
			model.addAttribute("error", "Invalid Username or Password");
		}
		if (logout != null) {
			model.addAttribute("logout", "You have Successfully Logged Out");
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "logout";
	}

	public int getUserIdMethod() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserEntity> usercheck = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			usercheck = service.findByUserName(currentUserName);
		}
		return usercheck.get().getUserId();
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	String welcome1(ModelMap model) {
		logger.info("Mapping to index");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().iterator().next().getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
			Iterable<UserEntity> users = service.findAllUsers();
			model.addAttribute("listUsers", users);
			return "adminWelcome";
		}
		return "welcome";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") UserEntity user, ModelMap model) {
		logger.info("registration page entered");
		Optional<UserEntity> usercheck = service.findByUserName(user.getUserName());
		if (usercheck.isPresent()) {
			model.addAttribute("message", "Username Already Exists!Try a different one!");
			return "/register";
		} else {
			String save = saveMethod(user);
			if (save.equals("Saved")) {
				model.addAttribute("message", "User Information Saved");
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

	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String welcome(@RequestParam(name = "button") String buttonValue, ModelMap model) {
		logger.info("welcome page entered");
		if (buttonValue.equals("CreateTask")) {
			return "/createTask";
		} else {
			List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
			model.addAttribute("listTasks", tasks);
			return "/viewTasks";
		}
	}

	@PostMapping("/createTask")
	public String createTask(@ModelAttribute("createTask") TaskEntity task, ModelMap model) {
		logger.info("create task page entered");
		task.setUserId(getUserIdMethod());
		String save = saveTaskMethod(task);
		if (save.equals("Saved")) {
			model.addAttribute("message", "Task created");
			return "/welcome";
		} else {
			model.addAttribute("message", "Error Occured on Task Save. Please Try again!");
			return "/createTask";
		}
	}

	public String saveTaskMethod(TaskEntity user) throws SaveFailedException {
		try {
			service.update(user);
			return "Saved";
		} catch (Exception e) {
			throw new SaveFailedException();
		}
	}

	@RequestMapping("/viewTasks")
	public String viewTasks() {
		logger.info("Welcome Page Entered");
		return "welcome";
	}

	@RequestMapping("/deleteAnotherUser")
	public String deleteAnotherUser(ModelMap model) {
		logger.info("Admin Welcome Page Entered");
		Iterable<UserEntity> users = service.findAllUsers();
		model.addAttribute("listUsers", users);
		return "adminWelcome";
	}

	@GetMapping("/updateTask/{id}")
	public String updateTask(@PathVariable String id, ModelMap model) {
		logger.info("update task page entered");
		taskId = Integer.parseInt(id);
		Optional<TaskEntity> taskEntity = service.findTaskById(taskId);
		TaskEntity newTaskEntity = taskEntity.get();
		if (taskEntity.isPresent()) {
			model.addAttribute("taskId", newTaskEntity.getTaskId());
			model.addAttribute("taskName", newTaskEntity.getTaskName());
			model.addAttribute("startDate", newTaskEntity.getStartDate());
			model.addAttribute("endDate", newTaskEntity.getEndDate());
			model.addAttribute("description", newTaskEntity.getDescription());
			model.addAttribute("email", newTaskEntity.getEmail());
			model.addAttribute("severity", newTaskEntity.getSeverity());
			model.addAttribute("taskId", newTaskEntity.getTaskId());
			return "/updateTask";
		} else {
			List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
			model.addAttribute("listTasks", tasks);
			model.addAttribute("message", "Task Not found! Error!");
			return "/viewTasks";
		}
	}

	@PostMapping("/updateDone")
	public String updateDone(@ModelAttribute("updateDone") TaskEntity task, ModelMap model) {
		logger.info("updateDone entered");
		TaskEntity newTaskEntity;
		Optional<TaskEntity> taskEntity = service.findTaskById(task.getTaskId());
		newTaskEntity = taskEntity.get();
		if (taskEntity.isPresent()) {
			newTaskEntity.setDescription(task.getDescription());
			newTaskEntity.setEmail(task.getEmail());
			newTaskEntity.setEndDate(task.getEndDate());
			newTaskEntity.setSeverity(task.getSeverity());
			newTaskEntity.setStartDate(task.getStartDate());
			newTaskEntity.setTaskId(taskId);
			newTaskEntity.setUserId(getUserIdMethod());
			try {
				service.saveTask(newTaskEntity);
				List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
				model.addAttribute("listTasks", tasks);
				model.addAttribute("message", "Task Information Updated");
				return "/viewTasks";
			} catch (Exception e) {
				List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
				model.addAttribute("listTasks", tasks);
				model.addAttribute("message", "Error occured on Updation!");
				return "/viewTasks";
			}
		} else {
			List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
			model.addAttribute("listTasks", tasks);
			model.addAttribute("message", "Task Not found! Error!");
			return "/viewTasks";
		}
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable String id, ModelMap model) {
		logger.info("DeleteUserDone entered");
		int userId;
		userId = Integer.parseInt(id);
		try {
			service.findUserById(userId);
			Iterable<UserEntity> users = service.findAllUsers();
			model.addAttribute("listUsers", users);
			model.addAttribute("message", "User Deleted");
			return "deleteAnotherUser";
		} catch (Exception e) {
			Iterable<UserEntity> users = service.findAllUsers();
			model.addAttribute("listUsers", users);
			model.addAttribute("message", "Error occured on Deletion of User!");
			return "deleteAnotherUser";
		}
	}

	@GetMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable String id, ModelMap model) {
		taskId = Integer.parseInt(id);
		Optional<TaskEntity> taskEntity = service.findTaskById(taskId);
		TaskEntity newTaskEntity = taskEntity.get();
		if (taskEntity.isPresent()) {
			model.addAttribute("taskId", newTaskEntity.getTaskId());
			model.addAttribute("taskName", newTaskEntity.getTaskName());
			model.addAttribute("startDate", newTaskEntity.getStartDate());
			model.addAttribute("endDate", newTaskEntity.getEndDate());
			model.addAttribute("description", newTaskEntity.getDescription());
			model.addAttribute("email", newTaskEntity.getEmail());
			model.addAttribute("severity", newTaskEntity.getSeverity());
			model.addAttribute("taskId", newTaskEntity.getTaskId());
			return "/deleteTask";
		} else {
			List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
			model.addAttribute("listTasks", tasks);
			model.addAttribute("message", "Task Not found! Error!");
			return "/viewTasks";
		}
	}

	@PostMapping("/deleteDone")
	public String deleteDone(@ModelAttribute("deleteDone") TaskEntity task, ModelMap model) {
		logger.info("updateDone entered");
		TaskEntity newTaskEntity;
		Optional<TaskEntity> taskEntity = service.findTaskById(task.getTaskId());
		newTaskEntity = taskEntity.get();
		if (taskEntity.isPresent()) {
			newTaskEntity.setDescription(task.getDescription());
			newTaskEntity.setEmail(task.getEmail());
			newTaskEntity.setEndDate(task.getEndDate());
			newTaskEntity.setSeverity(task.getSeverity());
			newTaskEntity.setStartDate(task.getStartDate());
			newTaskEntity.setTaskId(taskId);
			newTaskEntity.setUserId(getUserIdMethod());
			try {
				service.deleteTask(newTaskEntity);
				List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
				model.addAttribute("message", "Task Deleted");
				model.addAttribute("listTasks", tasks);
				return "/viewTasks";
			} catch (Exception e) {
				List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
				model.addAttribute("listTasks", tasks);
				model.addAttribute("message", "Error occured on Deletion!");
				return "/viewTasks";
			}
		} else {
			List<TaskEntity> tasks = service.findAllMethod(getUserIdMethod());
			model.addAttribute("listTasks", tasks);
			model.addAttribute("message", "Task Not found! Error!");
			return "/viewTasks";
		}
	}

	@RequestMapping("/403")
	public String accessdenied() {
		return "403";
	}
}