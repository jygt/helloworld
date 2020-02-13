package com.example.urlclient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.urlapi.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class UrlClient {
    @Reference(version = "0.2.0")
    private IndexService objIndexService;

    @RequestMapping("/Hello")
    public String Hello(Model model){
        String message =objIndexService.index("Good Day! ");
        System.out.println(message);

        model.addAttribute("content",message);
        return "hello";
    }
}
