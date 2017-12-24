/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: OrderService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 22:22
 * Description: 订单业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.common.service.HttpClientService;
import top.ou.jt.web.pojo.Cart;
import top.ou.jt.web.pojo.Order;

import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈订单业务层〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 22:22
 * @since 1.0.0
 */
@Service
public class OrderService {

    @Autowired
    private HttpClientService httpClientService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 根据用户id查询订单列表
     * @param userId
     * @return
     */
    public List<Cart> queryCartList(Long userId) throws Exception {
        //获取请求地址
        String url = "http://cart.jt.com/cart/query/"+userId;
        String jsonData = httpClientService.doGet(url,"utf-8");
        JsonNode jsonNode = MAPPER.readTree(jsonData);
        JsonNode data = jsonNode.get("data");
        Object obj = null;
        if (data.isArray()&&data.size()>0){
            obj = MAPPER.readValue(data.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class,Cart.class));

        }
        return (List<Cart>) obj;

    }

    /**
     * 创建订单
     * @param order
     * @return
     */
    public String saveOrder(Order order) throws Exception {
        String url = "http://order.jt.com/order/create";
        String json = MAPPER.writeValueAsString(order);
        String orderId = httpClientService.doPostJson(url,json);

        return orderId;
    }

    /**
     * 查询某个订单
     * @param orderId
     * @return
     */
    public Order queryByOrderId(String orderId) throws Exception {

        String url = "http://order.jt.com/order/query"+orderId;
        String jsonData = httpClientService.doGet(url,"utf-8");
        Order order = MAPPER.readValue(jsonData,Order.class);

        return order;
    }
}
