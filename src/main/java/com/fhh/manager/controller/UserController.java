package com.fhh.manager.controller;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.manager.service.UserService;
import com.fhh.pojo.User;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/save",method = RequestMethod.POST)
    @ResponseBody
    public YZResult saveUser(User user){
        YZResult yzResult = userService.addUser(user);
        return yzResult;
    }

    @RequestMapping("/user/list")
    @ResponseBody
    public EasyUIDataGridResult getUserList(Integer page, Integer rows){
        EasyUIDataGridResult result = userService.getUserList(page, rows);
        return result;
    }

    @RequestMapping(value = "/rest/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public YZResult deleteUserById(@RequestParam("ids") String id, User user) {
        YZResult yzResult = userService.deleteItemById(id, user);
        return yzResult;
    }

    @RequestMapping(value = "/rest/user/update", method = RequestMethod.POST)
    @ResponseBody
    public YZResult editUser(String id,User user) {
        YZResult yzResult = userService.editUser(id,user);
        return yzResult;
    }
}
