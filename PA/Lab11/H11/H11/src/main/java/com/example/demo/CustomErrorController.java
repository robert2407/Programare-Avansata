package com.example.demo;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error"); // daca am o eroare
        modelAndView.addObject("errorMessage", "Error!");
        return modelAndView;
    }
}

