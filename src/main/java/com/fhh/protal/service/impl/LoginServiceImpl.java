package com.fhh.protal.service.impl;

import com.fhh.config.ConfigConstants;
import com.fhh.mapper.UserMapper;
import com.fhh.pojo.User;
import com.fhh.protal.service.LoginService;
import com.fhh.utils.JedisClient;
import com.fhh.utils.JsonUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private ConfigConstants configConstants;

    @Override
    public YZResult login(String username, String password) {
        //判断用户名密码是否正确
        if("admin".equals(username)&&DigestUtils.md5DigestAsHex(password.getBytes()).equals(getManagerPassword())){
            return YZResult.build(100,"管理员登录");
        }
        List<User> list = userMapper.selectUserByUsername(username);
        if(list==null||list.size()==0){
            //如果不正确返回登录失败
            return YZResult.build(400,"用户名或密码错误！");
        }
        //取用户信息
        User user = list.get(0);
        //判断密码是否正确
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            return YZResult.build(400,"用户名或密码错误！");
        }
        //如果正确生成token
        String token = UUID.randomUUID().toString();
        //把用户信息写入redis
        user.setPassword(null);
        jedisClient.set("SESSION:"+token, JsonUtils.objectToJson(user));
        //设置Session过期时间
        jedisClient.expire("SESSION:"+token,configConstants.getSessionExpire());
        //返回token
        return YZResult.ok(token);
    }

    public String getManagerPassword(){
        String username="admin";
        List<User> list = userMapper.selectUserByUsername(username);
        return list.get(0).getPassword();
    }
}
