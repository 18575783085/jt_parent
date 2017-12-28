/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/28 9:25
 * Description: 商品业务层（抓取某商场的商品）
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.jsoup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.common.service.BaseService;
import top.ou.jt.common.service.RedisService;
import top.ou.jt.jsoup.mapper.ItemDescMapper;
import top.ou.jt.jsoup.mapper.ItemMapper;
import top.ou.jt.jsoup.pojo.Item;
import top.ou.jt.jsoup.pojo.ItemDesc;
import top.ou.jt.jsoup.util.JDUtil;

import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品业务层（抓取某商场的商品）〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/28 9:25
 * @since 1.0.0
 */
@Service
public class ItemService extends BaseService<Item>{

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private RedisService redisService;

    public static final String ITEM_URL_LIST = "ITEM_URL_LIST";
    /**
     * 备份队列
     */
    public static final String ITEM_URL_LIST_BAK = "ITEM_URL_LIST_BAK";

    /**
     * 初始化
     */
    public void init() throws IOException {
        //保存数据之前先清除当前存储在redis中的ITEM_URL_LIST对象数据
        redisService.del(ITEM_URL_LIST);
        //1.抓取所有商品链接
        List<String> itemUrls = JDUtil.getItemUrlAll();

        for(String url : itemUrls){
            redisService.lpush(ITEM_URL_LIST,url);
        }
    }

    /**
     * 保存商品详细信息
     */
    public void save(String itemUrl){

        //从抓取商品地址的商品对象
        Item item = JDUtil.getItem(itemUrl);
        //插入商品数据库
        itemMapper.insert(item);

        ItemDesc itemDesc = JDUtil.getItemDesc(item.getId(), JDUtil.getItemId(itemUrl));
        //插入商品数据库中的商品详情信息
        itemDescMapper.insert(itemDesc);
    }
}
