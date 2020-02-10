package com.example.helloWorld.controller;

import com.example.helloWorld.error.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.helloWorld.error.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exp")

public class ExpController {

    private static Integer iRetry = 0;

    @RequestMapping("/err1")
    public String FindALl(Model model){
        String res = "err1";


        if (iRetry++ >= 3) {
            iRetry = 0;
            res = "success";
        }

        if (res.startsWith("e"))
            throw new BusinessException("Business Exception. 业务异常！"+ res);

        model.addAttribute("content",res);

        return  "err";
    }
    @RequestMapping("/err2")
    public String FindALl2(Model model){
        String res = "err2";

        if (res.startsWith("e"))
            throw new BusinessException("Business Exception. 业务异常！" + res);

        model.addAttribute("content",res);
        return  "err";
    }

}
