package com.fhh.protal.service.impl;

import com.fhh.domain.SearchResult;
import com.fhh.protal.dao.SearchDao;
import com.fhh.protal.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String keyword, int page, int rows) throws Exception {
        //创建SolrQuery对象
        SolrQuery solrQuery=new SolrQuery();
        //设置查询条件
        solrQuery.setQuery(keyword);
        //设置分页条件
        if(page<=0)
            page=1;
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        //设置默认搜索域
        solrQuery.set("df","item_title");
        //开启高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        //调用dao执行查询
        SearchResult searchResult = searchDao.search(solrQuery);
        //计算总页数
        int totalPage =(int)searchResult.getRecordCount() / rows;
        if(searchResult.getRecordCount()%rows>0)
            totalPage++;
        //添加到返回结果
        searchResult.setTotalPages(totalPage);
        return searchResult;
    }
}
