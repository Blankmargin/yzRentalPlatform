package com.fhh.protal.message;

import com.fhh.domain.SearchItem;
import com.fhh.mapper.ItemMapper;
import com.fhh.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 监听商品添加事件，接收消息后将商品信息同步到索引库
 */
public class ItemAddMessageListener implements MessageListener {
    @Autowired
    private SearchItemMapper itemMapper;

    @Autowired
    private SolrServer cloudSolrServer;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage= (TextMessage) message;
            //从消息中取商品id
            String itemId = textMessage.getText();
            //等待事务提交
            Thread.sleep(1000);
            //根据商品id查询商品信息
            SearchItem item = itemMapper.getItemById(itemId);
            //创建文档对象，向文档对象中添加域
            SolrInputDocument document=new SolrInputDocument();
            //将文档写入索引库
            document.addField("id",item.getId());
            document.addField("item_title",item.getTitle());
            document.addField("item_sell_point",item.getSellPoint());
            document.addField("item_price",item.getPrice());
            document.addField("item_image",item.getImage());
            document.addField("item_category_name",item.getCategoryName());
            cloudSolrServer.add(document);
            //提交
            cloudSolrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
