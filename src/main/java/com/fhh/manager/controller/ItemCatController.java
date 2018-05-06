package com.fhh.manager.controller;

import com.fhh.domain.EasyUITreeNode;
import com.fhh.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类管理Controller
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(name = "id", defaultValue = "0") String parentId) {
        List<EasyUITreeNode> itemCatList = itemCatService.getItemCatList(parentId);
        return itemCatList;
    }

}