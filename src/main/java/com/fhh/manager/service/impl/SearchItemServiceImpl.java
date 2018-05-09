package com.fhh.manager.service.impl;

import com.fhh.domain.SearchItem;
import com.fhh.manager.service.SearchItemService;
import com.fhh.mapper.ItemMapper;
import com.fhh.mapper.SearchItemMapper;
import com.fhh.pojo.Item;
import com.fhh.utils.YZResult;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public YZResult importAllItems() {
        try{
            //查询商品列表
            List<SearchItem> itemList = searchItemMapper.getItemList();
            //遍历商品列表
            for (SearchItem searchItem:itemList){
                //创建文档对象
                SolrInputDocument document=new SolrInputDocument();
                //向文档对象中添加域
                document.addField("id",searchItem.getId());
                document.addField("item_title",searchItem.getTitle());
                document.addField("item_sell_point",searchItem.getSellPoint());
                document.addField("item_price",searchItem.getPrice());
                document.addField("item_image",searchItem.getImage());
                document.addField("item_category_name",searchItem.getCategoryName());
                //把文档对象写入索引库
                solrServer.add(document);
            }
            //提交
            solrServer.commit();
            //返回结果
            return YZResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return YZResult.build(500,"数据导入失败！");
        }
    }
}
