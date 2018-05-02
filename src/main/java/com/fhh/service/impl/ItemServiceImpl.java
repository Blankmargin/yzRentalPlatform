package com.fhh.service.impl;

import com.fhh.mapper.ItemMapper;
import com.fhh.pojo.Item;
import com.fhh.service.ItemService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;

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
}
