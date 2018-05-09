package com.fhh.manager.controller;

import com.fhh.manager.service.SearchItemService;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//导入商品到索引库
@Controller
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public YZResult importAllItems(){
        YZResult yzResult = searchItemService.importAllItems();
        return yzResult;
    }
}
