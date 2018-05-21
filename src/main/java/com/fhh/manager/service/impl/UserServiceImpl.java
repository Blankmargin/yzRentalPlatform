package com.fhh.manager.service.impl;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.manager.service.UserService;
import com.fhh.mapper.UserMapper;
import com.fhh.pojo.User;
import com.fhh.utils.IDUtils;
import com.fhh.utils.YZResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public YZResult addUser(User user) {
        //生成用户id
        String userId = IDUtils.getUserId();
        user.setId(userId);
        //MD5加密密码
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        userMapper.insert(user);
        return YZResult.ok();
    }

    @Override
    public User getUserById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public EasyUIDataGridResult getUserList(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<User> userList = userMapper.getAllUser();
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(userList);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public YZResult deleteItemById(String id, User user) {
        userMapper.deleteByPrimaryKey(id);
        return YZResult.ok();
    }

    @Override
    public YZResult editUser(@RequestParam("ids") String id,User user) {
        User nowUser = userMapper.selectByPrimaryKey(id);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreatetime(nowUser.getCreatetime());
        user.setUpdatetime(new Date());
        userMapper.updateByPrimaryKey(user);
        return YZResult.ok();
    }
}
