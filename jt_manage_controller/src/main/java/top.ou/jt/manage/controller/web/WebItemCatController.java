/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: WebItemCatController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/21 12:28
 * Description: 商品分类查询控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.common.vo.ItemCatResult;
import top.ou.jt.manage.service.web.WebItemCatService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品分类查询控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 12:28
 * @since 1.0.0
 */
@Controller
public class WebItemCatController {

    @Autowired
    private WebItemCatService webItemCatService;

    /**
     * http://manage.jt.com/web/itemcat/all
     *
     * 查询商品类目的商品列表
     * @return
     */
    @RequestMapping("/all")
    @ResponseBody
    public ItemCatResult queryItemCatList(){
        ItemCatResult itemCatResult = webItemCatService.queryItemCat();
        return itemCatResult;
    }
}
