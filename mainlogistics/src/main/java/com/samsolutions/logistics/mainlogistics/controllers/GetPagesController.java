package com.samsolutions.logistics.mainlogistics.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Get secondary pages
 */
@Controller
public class GetPagesController {

    /**
     * Get home page
     * @param model for adding attributes
     * @return view
     */
    @RequestMapping(path = {"/", "index"}, method = RequestMethod.GET)
    public String getHome(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

    /**
     * Get contacts page
     * @return view
     */
    @RequestMapping(path = "/contacts", method = RequestMethod.GET)
    public String contactsPage() {
        return "contacts";
    }

    /**
     * Get "getdirection" page
     * @return view
     */
    @RequestMapping(path = "/direction", method = RequestMethod.GET)
    public String getDirection() {
        return "getdirection";
    }

    /**
     * Get search page
     * @return view
     */
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String getSearch() {
        return "search";
    }
}
