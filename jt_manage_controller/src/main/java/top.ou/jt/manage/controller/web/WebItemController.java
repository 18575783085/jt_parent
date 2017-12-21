/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: WebItemController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/21 12:21
 * Description: 商品前台控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.manage.pojo.Item;
import top.ou.jt.manage.pojo.ItemDesc;
import top.ou.jt.manage.service.ItemService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品前台控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 12:21
 * @since 1.0.0
 */
@Controller
public class WebItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 根据商品id获取当前的商品
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody //httpclient请求返回值都是json串
    public Item getItemById(@PathVariable Long itemId){
        Item item = itemService.queryById(itemId);
        return item;
    }

    /**
     * 获取商品详情
     * @param itemId
     * @return
     */
    @RequestMapping("/item/desc/{itemId}")
    @ResponseBody
    public ItemDesc getItemDescById(@PathVariable Long itemId){
        //根据商品id获取该商品的详情信息
        ItemDesc itemDesc = itemService.getItemDesc(itemId);
        return itemDesc;
    }
}
