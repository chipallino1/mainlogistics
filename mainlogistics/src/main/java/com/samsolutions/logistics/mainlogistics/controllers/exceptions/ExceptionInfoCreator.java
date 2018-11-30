package com.samsolutions.logistics.mainlogistics.controllers.exceptions;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Interface for adding exception info
 */
public interface ExceptionInfoCreator {
    default public void addExceptionInfo(Exception exception,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorMsg",exception.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("errorCause",exception.getCause());
    }
}
