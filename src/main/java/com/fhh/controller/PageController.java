package com.fhh.controller;

import com.fhh.config.ConfigConstants;
import com.fhh.manager.service.ContentService;
import com.fhh.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 页面跳转Controller
 */
@Controller
public class PageController {
    @Autowired
    private ContentService contentService;

    @Autowired
    private ConfigConstants configConstants;

    @RequestMapping("/manager")
    public String showManagerIndex() {
        return "index-manager";
    }

    @RequestMapping("/")
    public String showIndex(Model model) {
        //查询内容列表
        List<Content> adList = contentService.getContentListByCid(configConstants.getContentId());
        //把结果传给页面
        model.addAttribute("adList", adList);
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
