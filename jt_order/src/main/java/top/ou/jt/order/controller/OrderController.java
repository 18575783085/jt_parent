/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: OrderController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 15:55
 * Description: 订单控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.order.pojo.Order;
import top.ou.jt.order.service.OrderService;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈订单控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 15:55
 * @since 1.0.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 新增订单：http://order.jt.com/order/create
     * @param json
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public String orderCreate(@RequestBody String json) throws IOException {

        Order order = MAPPER.readValue(json,Order.class);

        String orderId = orderService.saveOrder(order);

        return orderId;
    }


    /**
     * 查询订单http://order.jt.com/order/query/71487577654381
     * @return
     */
    @RequestMapping("/query/{orderId}")
    @ResponseBody
    public Order queryByOrderId(@PathVariable String orderId){
        Order order = orderService.queryByOrderId(orderId);
        return order;
    }

}
