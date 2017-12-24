/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: OrderService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 15:49
 * Description: 订单业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.order.mapper.OrderMapper;
import top.ou.jt.order.pojo.Order;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈订单业务层〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 15:49
 * @since 1.0.0
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 新增订单
     * @param order
     * @return
     */
    public String saveOrder(Order order){
        /*
            前台只负责传递订单的部分字段内容
            需要业务层封装一部分
         */
        String orderId = order.getUserId()+""+System.currentTimeMillis();
        //设置订单id
        order.setOrderId(orderId);
        //设置订单创建时间
        order.setCreated(new Date());
        //设置订单修改时间
        order.setUpdated(order.getCreated());
        //创建订单
        orderMapper.orderCreate(order);
        return orderId;
    }

    /**
     * 根据订单id查询该订单信息
     * @param orderId
     * @return
     */
    public Order queryByOrderId(String orderId){

        Order order = orderMapper.queryByOrderId(orderId);
        return order;
    }
}
