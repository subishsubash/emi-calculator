package com.subash.emi.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for user interface navigate to index.html
 *
 * @author subash s
 */
@Controller
public class UIController {

    /**
     * API call with context root as "/" will navigate to index.html
     *
     * @return
     */
    @RequestMapping("/emi-calculator")
    public String index() {
        return "index";
    }
}
