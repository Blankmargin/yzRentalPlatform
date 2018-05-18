package com.fhh.protal.controller;

import com.fhh.domain.BoxItem;
import com.fhh.manager.service.ItemService;
import com.fhh.pojo.Item;
import com.fhh.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情页面展示Controller
 */
@Controller
public class ItemInfoController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable String itemId, Model model){
        Item item = itemService.getItemById(itemId);
        BoxItem protalItem=new BoxItem(item);
        ItemDesc itemDesc = itemService.getItemDescById(itemId);
        model.addAttribute("item",protalItem);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }

}
