/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/19 8:44
 * Description: 商品业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.common.service.HttpClientService;
import top.ou.jt.web.pojo.Item;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品业务层〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/19 8:44
 * @since 1.0.0
 */
@Service
public class ItemService {

    @Autowired
    private HttpClientService httpClientService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Item getItem(Long itemId){
        //1.模拟发起http请求，需要一个url
        String url = "http://manage.jt.com/items/"+itemId;

        /*
            2.引入redis的缓存，如果有数据就直接返回，没有数据继续执行业务
            key值要计算，和后台需要一致
         */
        String ITEM_KEY = "ITEM_" + itemId;
        try{
            /**
             * 异常抛出疑问：
             * redis遇到异常走不通，它还能走业务，走数据库去执行
             * 而这里不通，整个程序就走不下去
             * 注：redis自己错了，直接写日志；
             * 将来这报错，会自动跳转到一个错误页面
             */
            String jsonData = httpClientService.doGet(url,"utf-8");
            Item item = MAPPER.readValue(jsonData,Item.class);
            return item;
        } catch (Exception e){
            e.printStackTrace();
        }

        return  null;
    }

}
