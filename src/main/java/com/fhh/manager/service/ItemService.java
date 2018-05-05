package com.fhh.manager.service;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.pojo.Item;
import com.fhh.pojo.ItemDesc;
import com.fhh.utils.YZResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemService {
//    根据id查询商品
    Item getItemById(String id);
//    获得商品列表
    EasyUIDataGridResult getItemList(int page,int rows);
//    新增商品
    YZResult addItem(Item item,String desc);
//    编辑商品
    YZResult editItem(@RequestParam("ids") String id,Item item);
//    删除选中的商品
    YZResult deleteItemById(@RequestParam("ids") String id,Item item);
//    上架商品
    YZResult resheltItem(@RequestParam("ids") String id,Item item);
//    下架商品
    YZResult instockItem(@RequestParam("ids") String id, Item item);
}
