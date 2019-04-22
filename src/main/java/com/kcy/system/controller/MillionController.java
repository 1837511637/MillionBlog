package com.kcy.system.controller;

import com.kcy.common.base.BaseController;
import com.kcy.system.dao.MillionBlogMapper;
import com.kcy.system.model.MillionBlog;
import com.kcy.system.service.MillionBlogService;
import com.kcy.system.service.MillionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Api(value = "跳转页面")
public class MillionController extends BaseController {

    @Autowired
    private MillionBlogService millionBlogService;
    @Autowired
    private MillionService millionService;


    @ApiOperation(value = "跳转首页", notes = "首页")
    @RequestMapping("/")
    public String beetl(Model model) {
        model.addAttribute("result", millionService.getIndexData());
        return "index";
    }

    @ApiOperation(value = "跳转博客页", notes = "内容页")
    @RequestMapping("/blog/{id}.html")
    public String goBlog(@PathVariable Integer id, HttpServletRequest request, Model model) {
        model.addAttribute("result", millionService.getBlogDetails(request, id));
        return "blog";
    }

    @ApiOperation(value = "跳转档案页", notes = "档案页")
    @RequestMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("result", millionService.getArchives());
        return "archives";
    }

    @ApiOperation(value = "跳转留言页", notes = "留言页")
    @RequestMapping("/guestbook")
    public String guestbook(@RequestParam(defaultValue = "1") Integer page, Model model) {
        model.addAttribute("result", millionService.getGuestbooks(page));
        return "guestbook";
    }

    @ApiOperation(value = "跳转关于页", notes = "关于页")
    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    /*@ApiOperation(value = "跳转链接页", notes = "链接页")
    @RequestMapping("/links")
    public String links(Model model) {
        return "links";
    }*/

    @ApiOperation(value = "跳转编写博客页", notes = "写作页")
    @RequestMapping("/read")
    public String read(Model model) {
        model.addAttribute("result", millionBlogService.getBlogPageData());
        return "read";
    }

    @ApiOperation(value = "跳转登录页", notes = "登录页")
    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @ApiOperation(value = "跳转轻语页", notes = "轻语")
    @RequestMapping("/whisper")
    public String whisper(@RequestParam(defaultValue = "1") Integer page, Model model) {
        model.addAttribute("result", millionService.getWhisperDatas(page));
        return "whisper";
    }

    @ApiOperation(value = "条件查询", notes = "条件页")
    @GetMapping("/query")
    public String query(@RequestParam Map<String, Object> map, Model model) {
        model.addAttribute("result", millionService.query(map));
        return "query";
    }

}