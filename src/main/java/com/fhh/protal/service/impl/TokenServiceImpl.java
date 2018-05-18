package com.fhh.protal.service.impl;

import com.fhh.config.ConfigConstants;
import com.fhh.pojo.User;
import com.fhh.protal.service.TokenService;
import com.fhh.utils.JedisClient;
import com.fhh.utils.JsonUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private ConfigConstants configConstants;

    @Override
    public YZResult getUserByToken(String token) {
        //根据token到redis中取用户信息
        String json = jedisClient.get("SESSION:" + token);
        //取不到用户信息说明登录已经过期,返回登录过期
        if(StringUtils.isEmpty(json)){
            return YZResult.build(201,"用户登录已过期！");
        }
        //取到用户信息更新token的过期时间
        jedisClient.expire("SESSION:" + token,configConstants.getSessionExpire());
        //返回结果
        User user= JsonUtils.jsonToPojo(json, User.class);
        return YZResult.ok(user);
    }
}
