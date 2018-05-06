package com.fhh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 */
@Controller
public class PageController {

//    @RequestMapping("/manager")
//    public String showManagerIndex(){
//        return "index-manager";
//    }

    @RequestMapping("/")
    public String showManagerIndex() {
        return "index-manager";
    }

//    @RequestMapping("/")
//    public String showIndex(){
//        return "index";
//    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
