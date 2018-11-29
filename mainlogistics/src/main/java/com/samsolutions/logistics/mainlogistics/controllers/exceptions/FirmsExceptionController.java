package com.samsolutions.logistics.mainlogistics.controllers.exceptions;

import com.samsolutions.logistics.mainlogistics.validation.exceptions.FirmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FirmsExceptionController {
    @ExceptionHandler(value = FirmNotFoundException.class)
    public ResponseEntity<Object> firmNotFoundException(FirmNotFoundException firmNotFoundException){
        return new ResponseEntity<>("Firm not found", HttpStatus.NOT_FOUND);
    }
}
