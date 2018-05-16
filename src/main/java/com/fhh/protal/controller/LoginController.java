package com.fhh.protal.controller;

import com.fhh.config.ConfigConstants;
import com.fhh.protal.service.LoginService;
import com.fhh.utils.CookieUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录处理
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ConfigConstants configConstants;

    @RequestMapping("/page/login")
    public String showLogin(){
        return "login";
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public YZResult login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        YZResult yzResult = loginService.login(username, password);
        //判断是否登录成功
        if (yzResult.getStatus()==200){
            //登录成功将token写入cookie
            String token = yzResult.getData().toString();
            CookieUtils.setCookie(request,response,configConstants.getTokenKey(),token);
        }
        return yzResult;
    }
}
