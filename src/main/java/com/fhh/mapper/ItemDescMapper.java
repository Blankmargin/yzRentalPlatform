package com.fhh.mapper;

import com.fhh.pojo.ItemDesc;

public interface ItemDescMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(ItemDesc record);

    int insertSelective(ItemDesc record);

    ItemDesc selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(ItemDesc record);

    int updateByPrimaryKeyWithBLOBs(ItemDesc record);

    int updateByPrimaryKey(ItemDesc record);
}