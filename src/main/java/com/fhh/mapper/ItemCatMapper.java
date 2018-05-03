package com.fhh.mapper;

import com.fhh.pojo.ItemCat;

import java.util.List;

public interface ItemCatMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemCat record);

    int insertSelective(ItemCat record);

    ItemCat selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemCat record);

    int updateByPrimaryKey(ItemCat record);

    List<ItemCat> selectByParentId(String parentId);
}