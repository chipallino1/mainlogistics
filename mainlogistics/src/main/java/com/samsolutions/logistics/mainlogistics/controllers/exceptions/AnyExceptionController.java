package com.samsolutions.logistics.mainlogistics.controllers.exceptions;

import com.samsolutions.logistics.mainlogistics.dto.ErrorDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for handle exceptions
 */
@RestControllerAdvice
public class AnyExceptionController implements ExceptionInfoCreator {
    @ExceptionHandler(value = Exception.class)
    public ErrorDTO anyException(Exception exception, RedirectAttributes redirectAttributes){
        ErrorDTO errorDTO=new ErrorDTO();
        errorDTO.setHasError(true);
        addExceptionInfo(exception,errorDTO);
        return errorDTO;
    }
}
