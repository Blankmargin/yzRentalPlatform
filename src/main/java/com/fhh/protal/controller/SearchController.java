package com.fhh.protal.controller;

import com.fhh.config.ConfigConstants;
import com.fhh.domain.SearchResult;
import com.fhh.protal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品搜索Controller
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @Autowired
    private ConfigConstants configConstants;

    @RequestMapping("/search")
    public String searchItemList(String keyword,@RequestParam(defaultValue = "1") Integer page,Model model) throws Exception {
        //查询商品列表
        SearchResult searchResult = searchService.search(keyword, page, configConstants.getSearchRows());
        //将结果传递给页面
        model.addAttribute("query",keyword);
        model.addAttribute("totalPages",searchResult.getTotalPages());
        model.addAttribute("page",page);
        model.addAttribute("recordCount",searchResult.getRecordCount());
        model.addAttribute("itemList",searchResult.getItemList());
        return "search";
    }
}
