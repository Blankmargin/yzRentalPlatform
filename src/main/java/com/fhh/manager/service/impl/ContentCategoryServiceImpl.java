package com.fhh.manager.service.impl;

import com.fhh.domain.EasyUITreeNode;
import com.fhh.manager.service.ContentCategoryService;
import com.fhh.mapper.ContentCategoryMapper;
import com.fhh.pojo.ContentCategory;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    //根据parentID查询子节点列表
    @Override
    public List<EasyUITreeNode> getContentCatList(Long parentId) {
        List<ContentCategory> contentCategoryList = contentCategoryMapper.selectByParentId(parentId);
        //转换成EasyUITreeNode列表
        List<EasyUITreeNode> nodeList = new ArrayList<>();
        for (ContentCategory contentCategory : contentCategoryList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(contentCategory.getId() + "");
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent() ? "closed" : "open");
            nodeList.add(node);
        }
        return nodeList;
    }

    @Override
    public YZResult createContentCategory(Long parentId, String name) {
        //创建ContentCategory的pojo
        ContentCategory contentCategory = new ContentCategory();
        //设置pojo属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //1-正常 2-删除
        contentCategory.setStatus(1);
        //默认排序为1
        contentCategory.setSortOrder(1);
        //新增节点一定是叶子结点
        contentCategory.setIsParent(false);
        contentCategory.setCreatetime(new Date());
        contentCategory.setUpdatetime(new Date());
        //插入到数据库
        contentCategoryMapper.insert(contentCategory);
        //根据parentid查询父节点
        ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断父节点的isparent属性,不是true则修改为true
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            //更新到数据库
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        //返回结果
        return YZResult.ok(contentCategory);
    }

    @Override
    public YZResult updateContentCategory(Long id, String name) {
        ContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return YZResult.ok();
    }

    @Override
    public YZResult deleteContentCategory(Long id) {
        //获得当前节点
        ContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //获得父节点id
        Long parentId = contentCategory.getParentId();
        //获得父节点
        ContentCategory contentCategoryParent = contentCategoryMapper.selectByPrimaryKey(parentId);
        //删除当前节点及其子节点
        deleteNode(id);
        //获得兄弟节点数目
        int count = contentCategoryMapper.countContentCategoryByParentId(parentId);
        if (count == 0) {
            contentCategoryParent.setIsParent(false);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(contentCategoryParent);
        }
        return YZResult.ok();
    }

    //删除当前节点的子节点
    public void deleteNode(Long id) {
        ContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //删除当前节点
        contentCategoryMapper.deleteByPrimaryKey(id);
        //如果当前节点是父节点,进入递归
        while (contentCategory.getIsParent()) {
            //获得该父节点下的所有子节点
            List<ContentCategory> contentCategories = contentCategoryMapper.selectByParentId(id);
            if (contentCategories == null)
                break;
            //遍历删除子节点
            for (ContentCategory category : contentCategories) {
                contentCategoryMapper.deleteByPrimaryKey(category.getId());
            }
        }
    }

}
