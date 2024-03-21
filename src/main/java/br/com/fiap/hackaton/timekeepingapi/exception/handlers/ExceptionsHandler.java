package br.com.fiap.hackaton.timekeepingapi.exception.handlers;

import br.com.fiap.hackaton.timekeepingapi.exception.BadRequestException;
import br.com.fiap.hackaton.timekeepingapi.exception.EntityAlreadyExistException;
import br.com.fiap.hackaton.timekeepingapi.exception.EntityNotFoundException;
import br.com.fiap.hackaton.timekeepingapi.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsHandler {
    Logger logger = LogManager.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        var errorDetails = new ErrorDetails.Builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getAllErrors().get(0).getDefaultMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        var errorDetails = new ErrorDetails.Builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(Objects.requireNonNull(e.getRootCause()).getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> handlerBadRequestException(BadRequestException e, HttpServletRequest request) {
        var errorDetails = new ErrorDetails.Builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDetails> handlerEntityAlreadyExistException(EntityAlreadyExistException e, HttpServletRequest request) {
        var errorDetails = new ErrorDetails.Builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handlerEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        return buildNotFoundException(e);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handlerNotFoundException(NotFoundException e, HttpServletRequest request) {
        return buildNotFoundException(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDetails> handlerException(Exception e, HttpServletRequest request) {
        var errorDetails = new ErrorDetails.Builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();
        logger.error(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> handlerConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        var message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));

        var errorDetails = new ErrorDetails.Builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    private ResponseEntity<ErrorDetails> buildNotFoundException(Exception e) {
        var errorDetails = new ErrorDetails.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

}
