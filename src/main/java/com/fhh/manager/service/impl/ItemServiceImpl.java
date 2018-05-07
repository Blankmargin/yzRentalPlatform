package com.fhh.manager.service.impl;

import com.fhh.domain.EasyUIDataGridResult;
import com.fhh.manager.service.ItemService;
import com.fhh.mapper.ItemDescMapper;
import com.fhh.mapper.ItemMapper;
import com.fhh.pojo.Item;
import com.fhh.pojo.ItemDesc;
import com.fhh.utils.IDUtils;
import com.fhh.utils.YZResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public Item getItemById(String id) {
        //根据主键查询
        Item item = itemMapper.selectByPrimaryKey(id);
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
//        设置分页信息
        PageHelper.startPage(page, rows);
//        执行查询
        List<Item> itemList = itemMapper.getAllItem();
//        创建返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(itemList);
        PageInfo<Item> pageInfo = new PageInfo<>(itemList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public YZResult addItem(Item item, String desc) {
//        生成商品id
        String itemId = IDUtils.getItemId();
//        补全item属性
        item.setId(itemId);
//        1:正常 2：下架 3：删除
        item.setStatus(1);
        item.setCreatetime(new Date());
        item.setUpdatetime(new Date());
//        创建商品描述表对象
        ItemDesc itemDesc = new ItemDesc();
//        补全商品描述表属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreatetime(new Date());
        itemDesc.setUpdatetime(new Date());
//        向商品表插入信息
        itemMapper.insert(item);
//        向商品描述表插入信息
        itemDescMapper.insert(itemDesc);
//        返回成功信息
        return YZResult.ok();
    }

    @Override
    public YZResult editItem(String id, Item item) {
        Item nowItem = itemMapper.selectByPrimaryKey(id);
        item.setCreatetime(nowItem.getCreatetime());
        item.setUpdatetime(new Date());
        return YZResult.ok();
    }

    @Override
    public YZResult deleteItemById(@RequestParam("ids") String id, Item item) {
        itemMapper.deleteByPrimaryKey(id);
        return YZResult.ok();
    }

    @Override
    public YZResult resheltItem(@RequestParam("ids") String id, Item item) {
        item = itemMapper.selectByPrimaryKey(id);
        item.setStatus(1);
        item.setCreatetime(item.getCreatetime());
        item.setUpdatetime(new Date());
        itemMapper.updateByPrimaryKeySelective(item);
        return YZResult.ok();
    }

    @Override
    public YZResult instockItem(@RequestParam("ids") String id, Item item) {
        item = itemMapper.selectByPrimaryKey(id);
        item.setStatus(2);
        item.setCreatetime(item.getCreatetime());
        item.setUpdatetime(new Date());
        itemMapper.updateByPrimaryKeySelective(item);
        return YZResult.ok();
    }

}
