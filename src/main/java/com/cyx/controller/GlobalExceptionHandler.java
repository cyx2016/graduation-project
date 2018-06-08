package com.cyx.controller;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 所有异常报错
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)
    public ModelAndView allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception
    {
        exception.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("/error");
        System.out.println("系统出错，请开发人员处理："+exception.getLocalizedMessage());
        HashMap <String,String> retMsg = new HashMap <String,String>();
        retMsg.put("Code","FFFFF");
        retMsg.put("Msg","亲，系统错误，请耐心等待...");
        modelAndView.addObject("retMsg",retMsg);
        return modelAndView;
    }
}
