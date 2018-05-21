package com.fhh.mapper;

import com.fhh.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserByUsername(String username);

    List<User> selectUserByPhone(String phone);

    List<User> selectUserByEmail(String email);

    List<User> getAllUser();
}