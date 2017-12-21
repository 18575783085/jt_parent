/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: WebItemCatService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/21 12:38
 * Description: 前台商品分类业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.service.web;

import org.springframework.stereotype.Service;
import top.ou.jt.common.vo.ItemCatData;
import top.ou.jt.common.vo.ItemCatResult;
import top.ou.jt.manage.pojo.ItemCat;
import top.ou.jt.manage.service.BaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈前台商品分类业务层〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 12:38
 * @since 1.0.0
 */
@Service
public class WebItemCatService extends BaseService<ItemCat>{

    /**
     *
     * @return
     */
    public ItemCatResult queryItemCat() {
        //1.准备返回给controller的对象
        ItemCatResult itemCatResult = new ItemCatResult();

        /*
            2.获取所有的itemCat，封装到一个list中
            然后对这个list的数据进行加工处理
         */
        List<ItemCat> itemCatList = super.queryAll();

        /*
            3.这里继续引入一个map对象，配合一个list循环拼接的方式完成，
            这种3层结构的封装，ItemCatData
         */
        Map<Long,List<ItemCat>> map = new HashMap<Long, List<ItemCat>>();

        /*
            4.第一步的封装，将一个父类对应id作为key保存在map中，
            value保存了这个id对应的下一级分类的ItemCat
         */
        for(ItemCat itemCat:itemCatList){
            //5.从当前的itemCat中获取parentID
            Long parentId = itemCat.getParentId();
            //6.判断当前获取的parentId，在map中有没有
            if(!map.containsKey(parentId)){//不存在
                map.put(parentId,new ArrayList<ItemCat>(0));
            }
            //7.获取value值的集合，再添加商品
            map.get(parentId).add(itemCat);
        }
        //8.构建三级菜单结构（EasyUI.tree根节点父id=0）
        List<ItemCat> catList = map.get(0L);

        //9.为一级菜单构建所有子菜单
        List<ItemCatData> itemCatDataList = new ArrayList<ItemCatData>();
        for (ItemCat itemCat:catList){
            //10.添加数据，u、n、i
            ItemCatData itemCatData = new ItemCatData();
            itemCatData.setUrl("/products/"+itemCat.getId()+".html");
            itemCatData.setName("<a htef='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");

            //11.做i，遍历二级菜单，构建二级结构
            List<ItemCatData> itemCatDataList2 = new ArrayList<ItemCatData>();//保存二级菜单
            for (ItemCat itemCat2 : map.get(itemCat.getId())){  //当前的父节点id
                ItemCatData itemCatData2 = new ItemCatData();   //二级菜单
                itemCatData2.setUrl("/products/"+itemCat2.getId()+".html");
                itemCatData2.setName("<a href='/products/"+itemCat2.getId()+".html'>"+itemCat2.getName()+"</a>");

                //12.遍历三级菜单，构建三级结构
                List<String> itemCatDataLsit3 = new ArrayList<String>(); //保存三级菜单
                for (ItemCat itemCat3 : map.get(itemCat2.getId())){
                    itemCatDataLsit3.add("/products/"+itemCat3.getId()+".html|"+itemCat3.getName());
                }
                itemCatData2.setItems(itemCatDataLsit3);
                itemCatDataList2.add(itemCatData2);
            }
            itemCatData.setItems(itemCatDataList2);
            //首页的菜单只能返回14条数据
            itemCatDataList.add(itemCatData);
            if(itemCatDataList.size()>14){
                break;
            }
        }

        itemCatResult.setItemCats(itemCatDataList);
        return itemCatResult;
    }
}
