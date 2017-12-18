package top.ou.jt.manage.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.common.service.RedisService;
import top.ou.jt.manage.mapper.ItemCatMapper;
import top.ou.jt.manage.pojo.ItemCat;

import java.util.List;

@Service
public class ItemCatService extends BaseService<ItemCat> {


    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<ItemCat> queryItemCatList(Integer parentId) {

        //1.定义key值
        String ITEM_CAT_KEY = "ITEM_CAT"+parentId;

        //2.读取缓存调用get方法拿到字符串，需要json字符格式，json格式可以转化成java对象
        String jsonData = redisService.get(ITEM_CAT_KEY);

        //3.判断是否有值
        List<ItemCat> itemCatList = null;

        //4.判断缓存值json串为空
        if (StringUtils.isNullOrEmpty(jsonData)){
            try{
                //5.缓存有数据json字符串转化成对象
                JsonNode jsonNode = MAPPER.readTree(jsonData);

                itemCatList = MAPPER.readValue(jsonNode.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class,ItemCat.class));

            }catch (Exception e){
                //写错误日志
                e.printStackTrace();
            }
        }else{
            //6.缓存没有值，取数据库的数据
            itemCatList = itemCatMapper.queryItemCatList(parentId);

            //7.保存到缓存中，对象变json
            String json = null;
            try{
                json = MAPPER.writeValueAsString(itemCatList);
                //以当前生成的key存入redis
                redisService.set(ITEM_CAT_KEY,json);

            }catch (Exception e){
                //写错误日志
                e.printStackTrace();
            }
        }
        return itemCatList;
    }
}
