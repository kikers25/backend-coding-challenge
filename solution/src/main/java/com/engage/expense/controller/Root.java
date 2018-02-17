package com.engage.expense.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class Root {

    @RequestMapping(method= RequestMethod.GET)
    public String toFront() {
        return "redirect:/index.html";
    }

}
