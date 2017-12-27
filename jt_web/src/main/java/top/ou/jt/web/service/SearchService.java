/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: SearchService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/27 9:16
 * Description: 搜索业务层
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
import top.ou.jt.web.pojo.Item;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈搜索业务层〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/27 9:16
 * @since 1.0.0
 */
@Service
public class SearchService {

    @Autowired
    private HttpClientService httpClientService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 发起httpClient请求，访问search服务，返回数据
     * @param keyWords
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    public List<Item> search(String keyWords,Integer page,Integer rows) throws Exception {
        if(page==null){
            page=1;
        }
        if (rows==null){
            rows=20;
        }

        //http请求地址
        String url = "http://search.jt.com/"+keyWords+"/"+page+"/"+rows;

        String json = httpClientService.doGet(url);

        JsonNode jsonNode = MAPPER.readTree(json);
        JsonNode data = jsonNode.get("data");
        ObjectMapper obj = null;
        if (data.isArray() && data.size() > 0){
            obj = MAPPER.readValue(data.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class,Item.class));

        }
        return (List<Item>) obj;
    }


}
