/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: OrderController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 22:21
 * Description: 前台订单控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.common.vo.SysResult;
import top.ou.jt.web.pojo.Cart;
import top.ou.jt.web.pojo.Order;
import top.ou.jt.web.service.OrderService;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈前台订单控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 22:21
 * @since 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 转向订单创建页面；http://www.jt.com/order/create.html
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String orderCreate(Model model) throws Exception {

        //1.需要userId（模拟一个用户id）
        Long userId = 531L;
        List<Cart> cartList = orderService.queryCartList(userId);
        model.addAttribute("carts",cartList);

        return "order-cart";
    }

    /**
     * 订单提交：/service/order/submit
     * @param order
     * @return
     */
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult orderSubmit(Order order) throws Exception {
        //模拟一个用户id
        Long userId = 531L;
        //设置订单的用户id
        order.setUserId(userId);
        String orderId = orderService.saveOrder(order);

        return SysResult.oK(orderId);
    }

    /**
     * 转向成功页面："/order/sucess.html?id="+result.data
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping("/success")
    public String sucess(@RequestParam("id") String orderId, Model model) throws Exception {

        Order order = orderService.queryByOrderId(orderId);
        model.addAttribute("order",order);

        return "success";
    }

}
