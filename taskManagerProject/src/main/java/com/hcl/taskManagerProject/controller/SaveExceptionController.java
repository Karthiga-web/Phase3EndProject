package com.hcl.taskManagerProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hcl.taskManagerProject.exceptions.SaveFailedException;

@ControllerAdvice
public class SaveExceptionController {
	@ExceptionHandler(value = SaveFailedException.class)
	   public ResponseEntity<Object> exception(SaveFailedException exception) {
	      return new ResponseEntity<>("Product not found", HttpStatus.METHOD_FAILURE);
	   }
}
