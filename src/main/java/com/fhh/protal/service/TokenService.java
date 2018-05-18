package com.fhh.protal.service;

import com.fhh.pojo.User;
import com.fhh.utils.YZResult;

public interface TokenService {
    //根据token查询用户信息
    YZResult getUserByToken(String token);
}
