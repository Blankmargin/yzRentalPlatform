package com.fhh.manager.controller;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.manager.service.ContentService;
import com.fhh.pojo.Content;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows){
        EasyUIDataGridResult result = contentService.getContentList(categoryId, page, rows);
        return result;
    }

    @RequestMapping(value = "/content/save",method = RequestMethod.POST)
    @ResponseBody
    public YZResult insertContent(Content content){
        YZResult yzResult = contentService.insertContent(content);
        return yzResult;
    }

    @RequestMapping(value = "/content/delete",method = RequestMethod.POST)
    @ResponseBody
    public YZResult deleteContent(@RequestParam("ids") Long id,Content content){
        YZResult yzResult = contentService.deleteContent(id, content);
        return yzResult;
    }

    @RequestMapping(value = "/rest/content/edit",method = RequestMethod.POST)
    @ResponseBody
    public YZResult editContent(Long id,Content content){
        YZResult yzResult = contentService.editContent(id, content);
        return yzResult;
    }
}
