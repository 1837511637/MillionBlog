package com.kcy.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hi")
public class IndexController {

    @RequestMapping("/beetl")
    public String beetl(Model model) {
        model.addAttribute("beetl","kcy is very goods!");
        return "helloBeetl";
    }
}
