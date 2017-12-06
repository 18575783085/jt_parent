/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/6 20:34
 * Description: 商品控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.common.vo.EasyUIResult;
import top.ou.jt.manage.pojo.Item;
import top.ou.jt.manage.service.ItemService;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/6 20:34
 * @since 1.0.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult queryItemList(){
        List<Item> itemList = itemService.queryItemList();
        return new EasyUIResult(itemList.size(),itemList);
    }
}
