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

    @RequestMapping("/archives")
    public String archives(Model model) {
        return "archives";
    }

    @RequestMapping("/guestbook")
    public String guestbook(Model model) {
        return "guestbook";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping("/links")
    public String links(Model model) {
        return "links";
    }
}
