package com.example.thymeleaf.demo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(PersonForm1 personForm1) {

        return "index";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm1 personForm1, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "index";
        }

        return "redirect:/results";
    }
}