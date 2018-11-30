package com.samsolutions.logistics.mainlogistics.controllers.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for handle exceptions
 */
@ControllerAdvice
public class AnyExceptionController implements ExceptionInfoCreator {
    @ExceptionHandler(value = Exception.class)
    public String anyException(Exception exception, RedirectAttributes redirectAttributes){
        addExceptionInfo(exception,redirectAttributes);
        return "redirect:/error";
    }
}
