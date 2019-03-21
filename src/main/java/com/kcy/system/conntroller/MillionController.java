package com.kcy.system.conntroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(value = "跳转页面")
public class MillionController {

    @ApiOperation(value = "跳转首页", notes = "首页")
    @RequestMapping("/")
    public String beetl(Model model) {
        return "index";
    }

    @ApiOperation(value = "跳转博客页", notes = "内容页")
    @RequestMapping("/{id}.html")
    public String goBlog(@PathVariable long id) {
        return "blog";
    }

    @ApiOperation(value = "跳转档案页", notes = "档案页")
    @RequestMapping("/archives")
    public String archives(Model model) {
        return "archives";
    }

    @ApiOperation(value = "跳转留言页", notes = "留言页")
    @RequestMapping("/guestbook")
    public String guestbook(Model model) {
        return "guestbook";
    }

    @ApiOperation(value = "跳转关于页", notes = "关于页")
    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @ApiOperation(value = "跳转链接页", notes = "链接页")
    @RequestMapping("/links")
    public String links(Model model) {
        return "links";
    }
}
