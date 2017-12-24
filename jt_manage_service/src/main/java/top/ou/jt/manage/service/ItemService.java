/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/6 20:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.common.service.RedisService;
import top.ou.jt.manage.mapper.ItemDescMapper;
import top.ou.jt.manage.mapper.ItemMapper;
import top.ou.jt.manage.pojo.Item;
import top.ou.jt.manage.pojo.ItemDesc;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/6 20:42
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

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger log = Logger.getLogger(ItemService.class);

    public List<Item> queryItemList() {
        List<Item> itemList = itemMapper.queryItemList();
        return itemList;
    }


    /**
     * 新增商品和商品详情
     * @param item 商品实体
     * @param desc 商品详情
     */
    public void saveItem(Item item,String desc) {
        //设置商品状态 1-上架 2-删除
        item.setStatus(1);
        //设置商品上架时间
        item.setCreated(new Date());
        //设置商品更新时间
        item.setUpdated(new Date());
        itemMapper.insertSelective(item);

        //设置商品详情
        //创建商品详情实体
        ItemDesc itemDesc = new ItemDesc();
        //设置商品id
        itemDesc.setItemId(item.getId());
        //设置商品详情信息
        itemDesc.setItemDesc(desc);
        //设置创建时间
        itemDesc.setCreated(new Date());
        //设置修改时间
        itemDesc.setUpdated(new Date());
        //插入商品详情（插入的方法不能写在service两个方法中）
        itemDescMapper.insert(itemDesc);

        /**
         * 需要事务维护
         * 把后台新增页面传递过来的数据封装成json字符串
         * 设置key值，将json存入redis ITEM_+item.getId();
         * MAPPER.writerValueAsString(item)
         */
        String ITEM_KEY = "ITEM_"+item.getId();
        try{
            redisService.set(ITEM_KEY,MAPPER.writeValueAsString(item),60*660*24*10);

        }catch (JsonProcessingException e){
            //写错误日志
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 修改商品和商品详情信息
     * @param item 商品实体
     * @param desc 商品详情信息
     */
    public void updateItem(Item item,String desc) {
        //修改商品更新时间
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);

        //修改商品详情
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        //设置修改详情信息
        itemDesc.setItemDesc(desc);
        //设置修改时间
        itemDesc.setUpdated(new Date());
        itemDescMapper.updateByPrimaryKey(itemDesc);

        //从redis缓存数据库删除商品数据
        String ITEM_KEY = "ITEM_"+item.getId();
        redisService.del(ITEM_KEY);
    }

    /**
     * 单个或批量删除商品和详情信息
     * @param ids
     */
    public void deleteItems(Long[] ids) {
        itemMapper.deleteByIDS(ids);
        itemDescMapper.deleteByIDS(ids);
    }

    /**
     * 单个或批量获取商品详情信息
     * @param itemId 商品id
     * @return
     */
    public ItemDesc getItemDesc(Long itemId) {
        //主外键一致
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        return  itemDesc;
    }
}
