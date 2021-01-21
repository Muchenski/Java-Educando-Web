package com.muchenski.course.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.muchenski.course.services.exceptions.DbIntegrityException;
import com.muchenski.course.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		Integer status = HttpStatus.NOT_FOUND.value();

		StandardError error = new StandardError(Instant.now(), status, "Resource not found", e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(DbIntegrityException.class)
	public ResponseEntity<StandardError> databaseIntegrityViolation(DbIntegrityException e,
			HttpServletRequest request) {
		Integer status = HttpStatus.BAD_REQUEST.value();

		StandardError error = new StandardError(Instant.now(), status, "Database error", e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}
}
