/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/19 11:57
 * Description: 商品控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ou.jt.web.pojo.Item;
import top.ou.jt.web.service.ItemService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/19 11:57
 * @since 1.0.0
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("item/{itemId}")
    public String getItem(@PathVariable Long itemId,Model model){
        //从service中获取数据
        Item item = itemService.getItem(itemId);
        model.addAttribute("item",item);

        return "item";
    }
}
