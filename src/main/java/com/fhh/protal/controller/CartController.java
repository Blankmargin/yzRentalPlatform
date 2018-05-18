package com.fhh.protal.controller;

import com.fhh.config.ConfigConstants;
import com.fhh.manager.service.ItemService;
import com.fhh.pojo.Item;
import com.fhh.pojo.User;
import com.fhh.protal.service.CartService;
import com.fhh.utils.CookieUtils;
import com.fhh.utils.JsonUtils;
import com.fhh.utils.YZResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车管理Controller
 */
@Controller
public class CartController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ConfigConstants configConstants;

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable String itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getAttribute("user");
        //判断用户是否登录,如果登录,把购物车写入redis
        if(user!=null){
            //保存到服务端
            cartService.addCart(user.getId(),itemId,num);
            //返回逻辑视图
            return "cartSuccess";
        }
        //如果是未登录,把购物车写入cookie
        //从Cookie中取购物车列表
        List<Item> cartList = getCartListFromCookie(request);
        //判断商品是否存在
        boolean flag=false;
        for (Item item:cartList){
            //如果存在数量增加
            if(item.getId().equals(itemId)){
                flag=true;
                item.setNum(item.getNum()+num);
                break;
            }
        }
        if (!flag){
            //如果不存在根据商品id查询商品信息
            Item item = itemService.getItemById(itemId);
            //设置商品数量取代库存
            item.setNum(num);
            //取一张图片
            String image = item.getImage();
            if(StringUtils.isEmpty(image)){
                item.setImage(image.split(",")[0]);
            }
            //把商品添加到商品列表
            cartList.add(item);
        }
        //把列表写入Cookie
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartList),configConstants.getCookieCartExpire(),true);
        //返回添加成功页面
        return "cartSuccess";
    }

    //从Cookie中取购物车列表
    private List<Item> getCartListFromCookie(HttpServletRequest request){
        String json = CookieUtils.getCookieValue(request, "cart", true);
        //判断json是否为空
        if (StringUtils.isEmpty(json)){
            return new ArrayList<>();
        }
        //不为空把json转换成商品列表
        List<Item> list = JsonUtils.jsonToList(json, Item.class);
        return list;
    }

    /**
     * 展示购物车列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request,HttpServletResponse response){
        //从Cookie中取购物车列表
        List<Item> cartList = getCartListFromCookie(request);
        //判断用户是否为登录状态
        User user = (User) request.getAttribute("user");
        //若为登录状态
        if (user!=null){
            //如果不为空把cookie中的购物车商品和服务端的合并
            cartService.mergeCart(user.getId(),cartList);
            //把Cookie中的购物车删除
            CookieUtils.deleteCookie(request,response,"cart");
            //从服务端取购物车列表
            cartList= cartService.getCartList(user.getId());
        }
        //若是未登录状态
        //把列表传回页面
        request.setAttribute("cartList",cartList);
        return "cart";
    }

    /**
     * 更新购物车商品数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public YZResult updateCartNum(@PathVariable String itemId,@PathVariable Integer num,HttpServletRequest request,HttpServletResponse response){
        //判断用户是否为登录状态
        User user = (User) request.getAttribute("user");
        if (user!=null){
            cartService.updateCartNum(user.getId(),itemId,num);
            return YZResult.ok();
        }
        //取购物车列表
        List<Item> list = getCartListFromCookie(request);
        //遍历列表找到对应的商品
        for (Item item:list){
            if (item.getId().equals(itemId)){
                //更新数量
                item.setNum(num);
                break;
            }
        }
        //购物车列表写入Cookie
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(list),configConstants.getCookieCartExpire(),true);
        //返回成功
        return YZResult.ok();
    }

    /**
     * 删除购物车中的商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("cart/delete/{itemId}")
    public String deleteCart(@PathVariable String itemId,HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getAttribute("user");
        if (user!=null){
            cartService.deleteCartItem(user.getId(),itemId);
            return "redirect:/cart/cart";
        }
        //从Cookie中取购物车列表
        List<Item> list = getCartListFromCookie(request);
        //遍历列表找到要删除的商品
        for (Item item:list){
            if (item.getId().equals(itemId)){
                //找到后删除商品
                list.remove(item);
                break;
            }
        }
        //将购物车列表重新写入Cookie
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(list),configConstants.getCookieCartExpire(),true);
        //返回结果
        return "redirect:/cart/cart";
    }

}
