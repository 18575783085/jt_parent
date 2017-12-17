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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        //插入商品详情
        itemDescMapper.insert(itemDesc);
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
