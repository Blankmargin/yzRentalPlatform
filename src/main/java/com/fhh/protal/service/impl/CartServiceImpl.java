package com.fhh.protal.service.impl;

import com.fhh.mapper.ItemMapper;
import com.fhh.pojo.Item;
import com.fhh.protal.service.CartService;
import com.fhh.utils.JedisClient;
import com.fhh.utils.JsonUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车处理服务
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 添加购物车
     * @param userId
     * @param itemId
     * @param num
     * @return
     */
    @Override
    public YZResult addCart(String userId, String itemId,int num) {
        //向redis中添加购物车
        Boolean exist = jedisClient.hexists("CART:" + userId, itemId);
        //判断商品是否存在
        if (exist){
            //如果存在商品数量相加
            String json = jedisClient.hget("CART:" + userId, itemId);
            Item item = JsonUtils.jsonToPojo(json, Item.class);
            item.setNum(item.getNum()+num);
            jedisClient.hset("CART:" + userId, itemId,JsonUtils.objectToJson(item));
            return YZResult.ok();
        }
        //如果不存在根据商品id去商品信息
        Item item = itemMapper.selectByPrimaryKey(itemId);
        //设置购物车数量
        item.setNum(num);
        //取一张图片
        String image = item.getImage();
        if(StringUtils.isEmpty(image)){
            item.setImage(image.split(",")[0]);
        }
        //添加购物车列表
        jedisClient.hset("CART:" + userId, itemId,JsonUtils.objectToJson(item));
        return YZResult.ok();
    }

    /**
     * 合并购物车
     * @param userId
     * @param list
     * @return
     */
    @Override
    public YZResult mergeCart(String userId, List<Item> list) {
        //遍历商品列表
        //把列表添加到购物车
        //判断购物车中是否有此商品
        //如果有，数量相加
        //如果没有添加一个新的商品
        //返回结果
        for (Item item:list){
            addCart(userId,item.getId(),item.getNum());
        }
        return YZResult.ok();
    }

    /**
     * 获得购物车列表
     * @param userId
     * @return
     */
    @Override
    public List<Item> getCartList(String userId) {
        //根据用户id查询购物车列表
        List<String> jsonList = jedisClient.hvals("CART:" + userId);
        List<Item> itemList=new ArrayList<>();
        for (String string:jsonList){
            Item item=JsonUtils.jsonToPojo(string,Item.class);
            itemList.add(item);
        }
        return itemList;
    }

    /**
     * 更新购物车数量
     * @param userId
     * @param itemId
     * @param num
     * @return
     */
    @Override
    public YZResult updateCartNum(String userId, String itemId, int num) {
        //从redis中取商品信息
        String json = jedisClient.hget("CART:" + userId, itemId);
        //更新商品数量
        Item item = JsonUtils.jsonToPojo(json, Item.class);
        item.setNum(num);
        //写入redis
        jedisClient.hset("CART:" + userId,itemId, JsonUtils.objectToJson(item));
        return YZResult.ok();
    }

    /**
     * 删除购物车商品
     * @param userId
     * @param itemId
     * @return
     */
    @Override
    public YZResult deleteCartItem(String userId, String itemId) {
        //删除购物车商品
        jedisClient.hdel("CART:" + userId, itemId);
        return YZResult.ok();
    }

}
