package com.milena.sensorcontroller.common.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Configuration
public class BaseController {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<String> handleException(EntityNotFoundException ex) {
        return new ResponseEntity<>("Sensor not found", HttpStatus.NOT_FOUND);
    }
}
