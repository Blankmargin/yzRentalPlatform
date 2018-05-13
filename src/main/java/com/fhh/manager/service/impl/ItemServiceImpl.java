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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource
    private Destination topicDestination;

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
        final String itemId = IDUtils.getItemId();
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
        //发送商品添加消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(itemId);
            }
        });
//        返回成功信息
        return YZResult.ok();
    }

    @Override
    public YZResult editItem(String id,String desc, Item item) {
        Date updatetime=new Date();
        Item nowItem = itemMapper.selectByPrimaryKey(id);
//        ItemDesc nowItemDesc=itemDescMapper.selectByPrimaryKey(id);
        item.setCreatetime(nowItem.getCreatetime());
        item.setStatus(nowItem.getStatus());
        item.setUpdatetime(updatetime);
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(nowItem.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreatetime(nowItem.getCreatetime());
        itemDesc.setUpdatetime(updatetime);
        itemMapper.updateByPrimaryKey(item);
        itemDescMapper.updateByPrimaryKeyWithBLOBs(itemDesc);
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

    @Override
    public String getItemDesc(String id) {
        String itemDesc = itemDescMapper.getItemDescById(id);
        return itemDesc;
    }

    @Override
    public ItemDesc getItemDescById(String id) {
        return itemDescMapper.selectByPrimaryKey(id);
    }

}
