package com.fhh.protal.service;

import com.fhh.pojo.Item;
import com.fhh.utils.YZResult;

import java.util.List;

public interface CartService {
    YZResult addCart(String userId,String itemId,int num);

    YZResult mergeCart(String userId,List<Item> list);

    List<Item> getCartList(String userId);

    YZResult updateCartNum(String userId,String itemId,int num);

    YZResult deleteCartItem(String userId,String itemId);
}
