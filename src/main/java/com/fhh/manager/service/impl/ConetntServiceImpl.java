package com.fhh.manager.service.impl;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.manager.service.ContentService;
import com.fhh.mapper.ContentMapper;
import com.fhh.pojo.Content;
import com.fhh.utils.YZResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class ConetntServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
        //分页管理
        PageHelper.startPage(page,rows);
        //根据categoryId查询
        List<Content> contents = contentMapper.getContentByCategoryId(categoryId);
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        result.setRows(contents);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public YZResult insertContent(Content content) {
        content.setCreatetime(new Date());
        content.setUpdatetime(new Date());
        contentMapper.insert(content);
        return YZResult.ok();
    }

    @Override
    public YZResult deleteContent(@RequestParam("ids")Long id, Content content) {
        contentMapper.deleteByPrimaryKey(id);
        return YZResult.ok();
    }

    @Override
    public YZResult editContent(Long id, Content content) {
//        content.setCreatetime(content.getCreatetime());
        Content nowContent = contentMapper.selectByPrimaryKey(id);
        content.setCreatetime(nowContent.getCreatetime());
        content.setUpdatetime(new Date());
        contentMapper.updateByPrimaryKey(content);
        return YZResult.ok();
    }

    @Override
    public List<Content> getContentListByCid(Long cid) {
        List<Content> contents = contentMapper.getContentByCategoryId(cid);
        return contents;
    }

}
