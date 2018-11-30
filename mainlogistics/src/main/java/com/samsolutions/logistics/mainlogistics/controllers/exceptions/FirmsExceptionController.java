package com.samsolutions.logistics.mainlogistics.controllers.exceptions;

import com.samsolutions.logistics.mainlogistics.validation.exceptions.FirmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for handle firms exceptions
 */
@ControllerAdvice
public class FirmsExceptionController implements ExceptionInfoCreator {
    /**
     * Set attributes to view
     * @param firmNotFoundException exception
     * @param redirectAttributes attributes
     * @return view
     */
    @ExceptionHandler(value = FirmNotFoundException.class)
    public String firmNotFoundException(FirmNotFoundException firmNotFoundException, RedirectAttributes redirectAttributes){
        addMainExceptionInfo(firmNotFoundException,redirectAttributes);
        return "redirect:/error";
    }
}
