package com.fhh.protal.service.impl;

import com.fhh.mapper.UserMapper;
import com.fhh.pojo.User;
import com.fhh.protal.service.RegisterService;
import com.fhh.utils.IDUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户注册处理Service
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public YZResult checkData(String param, int type) {
        //根据不同的type生成不同的查询条件,1：用户名，2：手机号，3：邮箱
        if(type==1){
            //执行查询
            List<User> userList = userMapper.selectUserByUsername(param);
            //判断结果中是否包含数据
            if(userList!=null&&userList.size()>0){
                //如果有数据返回false,没有则返回true
                return YZResult.ok(false);
            }
            return YZResult.ok(true);
        }else if (type==2){
            List<User> userList = userMapper.selectUserByPhone(param);
            if(userList!=null&&userList.size()>0){
                return YZResult.ok(false);
            }
            return YZResult.ok(true);
        }else if (type==3){
            List<User> userList = userMapper.selectUserByEmail(param);
            if(userList!=null&&userList.size()>0){
                return YZResult.ok(false);
            }
            return YZResult.ok(true);
        }else{
            return YZResult.build(400,"数据类型错误！");
        }
    }

    @Override
    public YZResult register(User user) {
//        校验数据
        if(StringUtils.isEmpty(user.getUsername())||StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(user.getPhone())){
            return YZResult.build(400,"用户数据不完整,注册失败！");
        }
        YZResult result = checkData(user.getUsername(), 1);
        if(!(boolean)result.getData()){
            return YZResult.build(400,"此用户名已被占用！");
        }
        result=checkData(user.getPhone(),2);
        if(!(boolean)result.getData()){
            return YZResult.build(400,"此手机号已被占用！");
        }
//        补全user属性
        user.setId(IDUtils.getUserId());
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
//        password进行MD5加密
        String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5password);
//        插入数据库
        userMapper.insert(user);
        return YZResult.ok();
    }
}
