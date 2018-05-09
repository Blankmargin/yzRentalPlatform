package com.fhh.manager.controller;

import com.fhh.domain.EasyUITreeNode;
import com.fhh.manager.service.ContentCategoryService;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类管理Controller
 */
@Controller
public class ContentCatController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> contentCatList = contentCategoryService.getContentCatList(parentId);
        return contentCatList;
    }

    //添加分类节点
    @RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public YZResult createContentCategory(Long parentId, String name) {
        YZResult yzResult = contentCategoryService.createContentCategory(parentId, name);
        return yzResult;
    }

    //修改节点信息
    @RequestMapping("/content/category/update")
    @ResponseBody
    public YZResult updateContentCategory(Long id, String name) {
        YZResult yzResult = contentCategoryService.updateContentCategory(id, name);
        return yzResult;
    }

    //删除节点
    @RequestMapping("/content/category/delete")
    @ResponseBody
    public YZResult deleteContentCategory(Long id) {
        YZResult yzResult = contentCategoryService.deleteContentCategory(id);
        return yzResult;
    }
}
