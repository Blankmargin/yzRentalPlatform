package com.fhh.manager.controller;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.pojo.Item;
import com.fhh.manager.service.ItemService;
import com.fhh.pojo.ItemDesc;
import com.fhh.utils.JsonUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理Controller
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    //显示商品列表
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    //保存商品信息
    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public YZResult addItem(Item item, String desc) {
        YZResult yzResult = itemService.addItem(item, desc);
        return yzResult;
    }

    //删除选中商品

    @RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
    @ResponseBody
    public YZResult deleteItemById(@RequestParam("ids") String id, Item item) {
        YZResult yzResult = itemService.deleteItemById(id, item);
        return yzResult;
    }
    //上架

    @RequestMapping(value = "/rest/item/reshelf", method = RequestMethod.POST)
    @ResponseBody
    public YZResult resheltItem(@RequestParam("ids") String id, Item item) {
        YZResult yzResult = itemService.resheltItem(id, item);
        return yzResult;
    }
    //下架

    @RequestMapping(value = "/rest/item/instock", method = RequestMethod.POST)
    @ResponseBody
    public YZResult instockItem(@RequestParam("ids") String id, Item item) {
        YZResult yzResult = itemService.instockItem(id, item);
        return yzResult;
    }

    //获得商品描述信息
    @RequestMapping(value = "/rest/item/desc/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getItemDesc(@PathVariable String id){
        String itemDesc = itemService.getItemDesc(id);
        return itemDesc;
    }

    //    编辑商品信息
    @RequestMapping(value = "/rest/item/update", method = RequestMethod.POST)
    @ResponseBody
    public YZResult editItem(String id,String desc, Item item) {
        YZResult yzResult = itemService.editItem(id,desc,item);
        return yzResult;
    }
}
