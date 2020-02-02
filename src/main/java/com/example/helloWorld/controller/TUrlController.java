package com.example.helloWorld.controller;

import com.example.helloWorld.TUrl;
import com.example.helloWorld.TUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

// 标准
@Controller
@RequestMapping("/url")
public class TUrlController {
    @Resource
    private TUrlService objTUrlService;

    @RequestMapping("/test")
    public String test(Model model){
        // 查询数据库素有记录
        List<TUrl> turlList = objTUrlService.findAll();

        model.addAttribute("urllist",turlList);

        return "turl";
    }
}
