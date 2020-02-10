package com.example.helloWorld.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义统一的异常处理类，basePackage用来定义扫描那些包，默认可不设置
 */
@ControllerAdvice(basePackages = "com.example.helloWorld")
public class GlobalDefaultExceptionHandler {
    /**
     * 用来定义函数针对的异常类型，可闯入多个可捕获的异常类
     */
    @ExceptionHandler({BusinessException.class})
    /**
     * 如果返回的是json数据或者其他对象，就添加此注解
     */
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest req,Exception exp) throws Exception{
        ErrorInfo errInfo = new ErrorInfo();
        errInfo.setMsg(exp.getMessage());
        errInfo.setUrl(req.getRequestURI());
        errInfo.setCode(ErrorInfo.SUCCESS);
        return errInfo;
    }
}
