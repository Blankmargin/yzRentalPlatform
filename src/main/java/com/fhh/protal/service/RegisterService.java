package com.fhh.protal.service;

import com.fhh.pojo.User;
import com.fhh.utils.YZResult;

public interface RegisterService {
    //数据检测
    YZResult checkData(String param,int type);

    //注册
    YZResult register(User user);
}
