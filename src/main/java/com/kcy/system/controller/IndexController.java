package com.kcy.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hi")
public class IndexController {

    /*@RequestMapping("/beetl")
    public String beetl(Model model) {
        model.addAttribute("beetl","kcy is very goods!");
        List<String> arrayList = new ArrayList();
        arrayList.add("1");
        model.addAttribute("list", arrayList);
        return "helloBeetl";
    }*/
}
