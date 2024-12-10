package com.nhlstenden.netflix.exception;

import jakarta.validation.ValidationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponseDTO<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return new ResponseEntity<>(ApiResponseDTO.error(ex.getMessage()), HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<ApiResponseDTO<Void>> handleValidationException(ValidationException ex) {
//        return new ResponseEntity<>(ApiResponseDTO.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<ApiResponseDTO<Void>> handleUnauthorizedException(UnauthorizedException ex) {
//        return new ResponseEntity<>(ApiResponseDTO.error(ex.getMessage()), HttpStatus.UNAUTHORIZED);
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponseDTO<Void>> handleGenericException(Exception ex) {
//        return new ResponseEntity<>(
//                ApiResponseDTO.error("An unexpected error occurred"),
//                HttpStatus.INTERNAL_SERVER_ERROR
//        );
//    }
}