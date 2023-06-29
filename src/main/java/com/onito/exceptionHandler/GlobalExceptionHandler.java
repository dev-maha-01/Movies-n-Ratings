package com.onito.exceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.onito.exception.MoviesException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MoviesException.class)
	public ResponseEntity<String> moviesExceptionHandler(MoviesException me){
		return new ResponseEntity<String>(me.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	//for invalid data
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> invalidDataExceptionHandler(MethodArgumentNotValidException ma){
		return new ResponseEntity<>(ma.getBindingResult().getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
	}
	
	//file not found
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<String> fileNotFoundHandler(FileNotFoundException fe){
		return new ResponseEntity<String>("invalid file path !!!",HttpStatus.BAD_REQUEST);
	}
	
	//error while reading the file
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> fileNotFoundHandler(IOException ie){
		return new ResponseEntity<String>("Error while reading the file !!!",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> lastExceptionHandler(Exception e){
		e.printStackTrace();
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
}
