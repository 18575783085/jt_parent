/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: CartService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 21:33
 * Description: 购物车业务层
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
import top.ou.jt.web.pojo.Item;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈购物车业务层〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 21:33
 * @since 1.0.0
 */
@Service
public class CartService {

    @Autowired
    private HttpClientService httpClientService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 前台展示加入购物车列表
     * @param userId
     * @return
     */
    public List<Cart> show(Long userId) throws Exception {
        //http://cart.jt.com/cart/query/{userId}
        String url = "http://cart.jt.com/cart/query/"+userId;
        String jsonData = httpClientService.doGet(url,"utf-8");

        //json字符串转化成对象
        JsonNode jsonNode = MAPPER.readTree(jsonData);

        //返回的json字符串包含SysResult的其他内容，截取data
        JsonNode data = jsonNode.get("data");
        Object obj = null;
        if (data.isArray()&&data.size()>0){
            obj = MAPPER.readValue(data.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class,Cart.class));
        }
        return (List<Cart>) obj;
    }

    /**
     * 保存商品到购物车
     * @param userId
     * @param itemId
     * @param num
     */
    public void saveCart(Long userId, Long itemId, Integer num) throws Exception {
        //1.调用后台，获取3个冗余字段
        String url = "http://manage.jt.com/items/"+itemId;
        String jsonItem = httpClientService.doGet(url,"utf-8");
        Item item = MAPPER.readValue(jsonItem,Item.class);

        //2.调用购物车接口，传递数据
        url = "http://cart.jt.com/cart/save";
        Map<String,String> params = new HashMap<String, String>();
        params.put("userId",userId+"");
        params.put("itemId",itemId+"");
        params.put("num",num+"");

        //3.从后台获取的数据封装
        params.put("itemTitle",item.getTitle());
        params.put("jtemImage",item.getImage().split(",")[0]);
        params.put("itemPrice",item.getPrice()+"");

        httpClientService.doPost(url,params,"utf-8");

    }

    /**
     * 更新商品数量
     * @param userId
     * @param itemId
     * @param num
     */
    public void updateNum(Long userId, Long itemId, Integer num) throws Exception {
        String url = "http://cart.jt.com/"+"cart/update/num/"+userId+"/"+itemId+"/"+num;
        httpClientService.doGet(url,"utf-8");
    }

    /**
     * 删除购物车的商品
     * @param userId
     * @param itemId
     */
    public void delete(Long userId, Long itemId) throws Exception {
        String url = "http://cart.jt.com/cart"+"/delete/"+userId+"/"+itemId;
        httpClientService.doGet(url,"utf-8");
    }
}
