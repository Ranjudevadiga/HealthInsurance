package com.cg.onlineinsurance.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,HttpHeaders headers,HttpStatus status,WebRequest request)
	{	Map<String,String> errors=new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError) error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName,message);
		});
		//return super.handleMethodArgumentNotValid(e,headers,status, request);
	return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
	}
	
}
