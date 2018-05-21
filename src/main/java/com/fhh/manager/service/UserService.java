package com.fhh.manager.service;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.pojo.User;
import com.fhh.utils.YZResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {
    YZResult addUser(User user);

    User getUserById(String id);

    EasyUIDataGridResult getUserList(int page, int rows);

    YZResult deleteItemById(@RequestParam("ids") String id, User user);

    YZResult editUser(String id,User user);
}
