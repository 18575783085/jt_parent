/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: CartController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 15:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈购物车前台控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 15:07
 * @since 1.0.0
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    /**
     * 向购物车页面调准
     * @param model
     * @return
     */
    @RequestMapping("/show")
    public String show(Model model){
        return "cart";
    }
}
