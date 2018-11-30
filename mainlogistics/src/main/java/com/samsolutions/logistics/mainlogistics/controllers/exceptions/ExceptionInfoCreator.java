package com.samsolutions.logistics.mainlogistics.controllers.exceptions;

import com.samsolutions.logistics.mainlogistics.validation.exceptions.MainException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Interface for adding exception info
 */
public interface ExceptionInfoCreator {
    default public void addMainExceptionInfo(MainException mainException, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorMsg",mainException.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("errorCause",mainException.getLocalizedCauseMessage());
    }
    default public void addExceptionInfo(Exception exception, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorMsg",exception.getLocalizedMessage());
        if(exception.getCause()!=null)
             redirectAttributes.addFlashAttribute("errorCause",exception.getCause().toString());
    }
}
