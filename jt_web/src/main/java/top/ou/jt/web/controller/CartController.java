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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.web.pojo.Cart;
import top.ou.jt.web.service.CartService;
import top.ou.jt.web.threadlocal.UserThreadLocal;

import java.util.List;

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

    @Autowired
    private CartService cartService;

    /**
     * 向购物车页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/show")
    public String show(Model model) throws Exception {
        /*
        自定义用户id
        Long userId = 531L;
        Long userId = UserThreadLocal.getUserId();
         */
        Long userId = UserThreadLocal.getUserId();
        List<Cart> cartList = cartService.show(userId);
        //返回购物车信息到前台购物车
        model.addAttribute("cartList",cartList);
        return "cart";
    }

    /**
     * 保存商品到购物车：/cart/add/562379.html
     * @param itemId 商品id
     * @param num 商品数量
     * @return
     */
    @RequestMapping("/add/{itemId}")
    public String addCart(@PathVariable Long itemId,Integer num) throws Exception {
        //需要当前用户id
        Long userId = UserThreadLocal.getUserId();
        cartService.saveCart(userId,itemId,num);
        return "redirect:/cart/show.html";//重定向
    }

    /**
     * 更新商品数量：//service/cart/update/{num}/{itemId}
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public String updateNum(Long itemId,Integer num) throws Exception {
        Long userId = UserThreadLocal.getUserId();
        cartService.updateNum(userId,itemId,num);

        //如果这里直接返回，springmvc会不会拼接
        return "";
    }

    /**
     * 删除购物车商品：http://www.jt.com/cart/delete/1474391955.html
     * @param itemId
     * @return
     */
    @RequestMapping("/delete/{itemId}")
    public String delete(Long itemId) throws Exception {
        Long userId = UserThreadLocal.getUserId();
        cartService.delete(userId,itemId);

        return "redirect:/cart/show.html";
    }
}
