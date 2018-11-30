package com.samsolutions.logistics.mainlogistics.controllers.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for custom error page
 */
@Controller
public class ExceptionController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}