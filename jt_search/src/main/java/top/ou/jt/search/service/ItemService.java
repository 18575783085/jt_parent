/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/26 23:00
 * Description: 商品搜索
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.search.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.common.vo.SysResult;
import top.ou.jt.search.pojo.Item;

import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品搜索〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/26 23:00
 * @since 1.0.0
 */
@Service
public class ItemService {

    //查询，通过solr来查询
    @Autowired
    private HttpSolrServer httpSolrServer;

    public SysResult search(String q,Integer page,Integer rows){
        /**
         * 步骤：
         * 1、链接solr，配置参数，page当前页，rows一页条数，q查询条件
         * 2、进行查询，调用solr提供RESTFul，返回时一个资深返回对象，从这个对象中解析出来List和numFount
         * 3、放置到SysResult
         * 4、对搜索的关键字加亮
         */

        //1.创建搜索对象
        SolrQuery solrQuery = new SolrQuery();

        //分页数据，哪一页第一条记录位置
        solrQuery.setStart((Math.max(page,1)-1)*rows);

        //设置查询条件
        solrQuery.setQuery(q);

        //设置索引高亮
        //打开高亮开关
        solrQuery.setHighlight(true);
        //设置样式
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");
        //设置标题进行高亮
        solrQuery.addHighlightField("title");

        try {
            QueryResponse queryResponse = httpSolrServer.query(solrQuery);
            //获取到当前的页记录
            List<Item> itemList = queryResponse.getBeans(Item.class);
            //获取到记录总数
            String numFont = queryResponse.getResults().getNumFound()+"";
            //利用msg属性
            return SysResult.build(200,numFont,itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SysResult.build(201,"solr查询异常");

    }

}
