package com.fhh.protal.controller;

import com.fhh.protal.service.TokenService;
import com.fhh.utils.JsonUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/user/token/{token}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserByToken(@PathVariable String token,String callback){
        YZResult yzResult = tokenService.getUserByToken(token);
        //响应结果之前判断是否为jsonp请求
        if(!StringUtils.isEmpty(callback)){
            //把结果封装成js语句响应
            return callback+"("+ JsonUtils.objectToJson(yzResult)+");";
        }
        return JsonUtils.objectToJson(yzResult);
    }
}
