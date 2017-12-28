/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/28 10:11
 * Description: 抓取商品的控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.jsoup.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.common.service.RedisService;
import top.ou.jt.jsoup.service.ItemService;

import java.io.IOException;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抓取商品的控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/28 10:11
 * @since 1.0.0
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("init")
    @ResponseBody
    public String init() throws IOException {
        itemService.init();
        return "init Successful"+new Date();
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(){
        int i = 1;

        String itemUrl = redisService.rpop(ItemService.ITEM_URL_LIST);
        //写入另外一个队列
        redisService.lpush(ItemService.ITEM_URL_LIST_BAK,itemUrl);

        while (StringUtils.isNotEmpty(itemUrl)){
            i++;
            if(i > 10){
                break;
            }

            try{
                itemService.save(itemUrl);
                itemUrl = redisService.rpop(ItemService.ITEM_URL_LIST);
                //如果操作正常，移除备份数据
                redisService.lpop(ItemService.ITEM_URL_LIST_BAK);
            } catch (Exception e){
                itemUrl = redisService.lpop(ItemService.ITEM_URL_LIST_BAK);
                redisService.lpush(ItemService.ITEM_URL_LIST,itemUrl);
            }

        }
        return "save Successful"+new Date();
    }
}
