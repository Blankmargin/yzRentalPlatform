package com.fhh.service.impl;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.mapper.ItemMapper;
import com.fhh.pojo.Item;
import com.fhh.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Item getItemById(String id) {
        //根据主键查询
        Item item = itemMapper.selectByPrimaryKey(id);
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
//        设置分页信息
        PageHelper.startPage(page, rows);
//        执行查询
        List<Item> itemList = itemMapper.getAllItem();
//        创建返回值对象
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        result.setRows(itemList);
        PageInfo<Item> pageInfo=new PageInfo<>(itemList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
