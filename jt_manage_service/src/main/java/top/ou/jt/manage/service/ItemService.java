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
import top.ou.jt.manage.mapper.ItemMapper;
import top.ou.jt.manage.pojo.Item;

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

    public List<Item> queryItemList() {
        List<Item> itemList = itemMapper.queryItemList();
        return itemList;
    }


    /**
     * 新增商品
     * @param item
     */
    public void saveItem(Item item) {
        //设置商品状态 1-上架 2-删除
        item.setStatus(1);
        //设置商品上架时间
        item.setCreated(new Date());
        //设置商品更新时间
        item.setUpdated(new Date());
        itemMapper.insertSelective(item);
    }

    /**
     * 修改商品信息
     * @param item
     */
    public void updateItem(Item item) {
        //修改商品更新时间
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);
    }

    /**
     * 单个或批量删除商品
     * @param ids
     */
    public void deleteItems(Long[] ids) {
        itemMapper.deleteByIDS(ids);
    }
}
