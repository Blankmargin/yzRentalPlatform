package com.fhh.pagehelper;

import com.fhh.mapper.ItemMapper;
import com.fhh.pojo.Item;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.util.List;

public class PageHelperTest {

    @Test
    public void testPageHelper()throws Exception{
        //初始化spring容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
        //从容器中获得Mapper代理对象
        ItemMapper itemMapper = ac.getBean(ItemMapper.class);
        //设置分页信息
        PageHelper.startPage(1,12);
        //执行查询
        List<Item> itemList = itemMapper.getAllItem();
//        for (Item item:itemList){
//            System.out.println(item);
//        }
        //取分页信息
        PageInfo<Item> pageInfo=new PageInfo<>(itemList);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(itemList.size());
    }
}
