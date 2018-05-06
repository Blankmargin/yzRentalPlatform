package com.fhh.manager.service.impl;

import com.fhh.domain.EasyUITreeNode;
import com.fhh.manager.service.ItemCatService;
import com.fhh.mapper.ItemCatMapper;
import com.fhh.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(String parentId) {
//        根据商品parentid查询子节点列表
        List<ItemCat> itemCatList = itemCatMapper.selectByParentId(parentId);
        List<EasyUITreeNode> resultList = new ArrayList<>();
//        把列表转换成EasyUITreeNode列表返回结果
        for (ItemCat list : itemCatList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(list.getId());
            node.setText(list.getName());
            node.setState(list.getIsParent() ? "closed" : "open");
            //添加到结果列表
            resultList.add(node);
        }
        return resultList;
    }
}
