package com.fhh.mapper;

import com.fhh.pojo.Renter;

public interface RenterMapper {
    int deleteByPrimaryKey(String renterId);

    int insert(Renter record);

    int insertSelective(Renter record);

    Renter selectByPrimaryKey(String renterId);

    int updateByPrimaryKeySelective(Renter record);

    int updateByPrimaryKey(Renter record);
}