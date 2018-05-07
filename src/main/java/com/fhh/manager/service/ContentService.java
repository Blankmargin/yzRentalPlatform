package com.fhh.manager.service;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.pojo.Content;
import com.fhh.utils.YZResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ContentService {
    //内容管理列表查询
    EasyUIDataGridResult getContentList(Long categoryId,int page,int rows);

    YZResult insertContent(Content content);

    YZResult deleteContent(@RequestParam("ids") Long id,Content content);

    YZResult editContent(Long id,Content content);

    List<Content> getContentListByCid(Long cid);
}
