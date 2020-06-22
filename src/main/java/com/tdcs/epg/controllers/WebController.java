package com.tdcs.epg.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("title", appName);
        return "views/index";
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("title", "Users");
        return "views/users";
    }

    @GetMapping("/videogames")
    public String videoGames(Model model){
        model.addAttribute("title", "Video Games");
        return "views/videogames";
    }

    @GetMapping("/rent")
    public String rents(Model model){
        model.addAttribute("title", "Rent");
        return "views/rent";
    }

    @GetMapping("/returns")
    public String returns(Model model){
        model.addAttribute("title", "Returns");
        return "views/returns";
    }
}
