package com.fhh.protal.controller;

import com.fhh.pojo.User;
import com.fhh.protal.service.RegisterService;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/page/register")
    public String showRegister(){
        return "register";
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public YZResult checkData(@PathVariable String param,@PathVariable Integer type){
        YZResult yzResult = registerService.checkData(param, type);
        return yzResult;
    }

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public YZResult register(User user){
        YZResult yzResult = registerService.register(user);
        return yzResult;
    }
}
