package com.hcl.taskManagerProject.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskId")
	int taskId;
	@Column(name = "taskName", nullable = false)
	String taskName;
//	@NotNull(message = "{task.date.not.null}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "startDate")
	LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "endDate")
    LocalDate endDate;
	@Column(name = "description", nullable = false, length = 3200)
	String description;
	@Column(name = "email", nullable = false)
	String email;
	@Column(name = "severity", nullable = false)
	String severity;
	@Column(name = "userId", nullable = false)
	int userId;
}
