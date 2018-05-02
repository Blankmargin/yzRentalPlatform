package com.fhh.controller;

import com.fhh.pojo.Item;
import com.fhh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public Item getItemById(@PathVariable String itemId){
        Item item = itemService.getItemById(itemId);
        return item;
    }
}
