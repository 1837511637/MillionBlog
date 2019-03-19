package com.kcy.system.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MillionController {

    @RequestMapping("/")
    public String beetl(Model model) {
        return "index";
    }

    @RequestMapping("/{id}.html")
    public String goBlog(@PathVariable long id) {
        return "blog";
    }

}
