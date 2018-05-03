package com.fhh.service;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.pojo.Item;

public interface ItemService {
//    根据id查询商品
    Item getItemById(String id);
//    获得商品列表
    EasyUIDataGridResult getItemList(int page,int rows);
}
