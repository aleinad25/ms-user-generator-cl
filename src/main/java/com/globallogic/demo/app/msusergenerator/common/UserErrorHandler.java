package com.globallogic.demo.app.msusergenerator.common;

import com.globallogic.demo.app.msusergenerator.domain.entity.UserApiResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserErrorHandler {
	
	@ExceptionHandler(PasswordException.class)
	public ResponseEntity<UserApiResponse> invalidPassword(HttpServletRequest request, MethodArgumentNotValidException e) {
		UserApiResponse userApiResponse = new  UserApiResponse(false, "Invalid password");
		return new ResponseEntity<>(userApiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmailFormatException.class)
	public ResponseEntity<UserApiResponse> invalidEmail(HttpServletRequest request, MethodArgumentNotValidException e) {
		UserApiResponse userApiResponse = new  UserApiResponse(false, e.getMessage());
		return new ResponseEntity<>(userApiResponse, HttpStatus.BAD_REQUEST);
	}
}
