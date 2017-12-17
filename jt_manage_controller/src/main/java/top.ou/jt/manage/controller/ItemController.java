/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/6 20:34
 * Description: 商品控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.common.vo.EasyUIResult;
import top.ou.jt.common.vo.SysResult;
import top.ou.jt.manage.pojo.Item;
import top.ou.jt.manage.service.ItemService;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/6 20:34
 * @since 1.0.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private static final Logger log = Logger.getLogger(ItemController.class);

    /**
     * 使用分页查询插件实现单页查询商品
     * @param page 当前页数
     * @param rows 需要查询的每页数量（EasyUI封装了参数的过程）
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult queryItemList(Integer page,Integer rows){
        //只开启当前startPage方法下的第一条查询语句的拦截
        PageHelper.startPage(page,rows);
        List<Item> itemList = itemService.queryItemList();
        //用pageInfo来封装结果，记录总数和当前页的记录条商品
        PageInfo<Item> pageInfo = new PageInfo<Item>(itemList);
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 新增商品
     * @param item 商品实体
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveItem(Item item){
        try {
            itemService.saveItem(item);
            return SysResult.oK();
        }catch (Exception e){
            //写出错误日志
            String message = e.getMessage();
            log.error(message);
            //返回错误信息
            return  SysResult.build(201,"新增商品："+message);
        }
    }

    /**
     * 修改商品
     * @param item 商品实体
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateItem(Item item){
        try{
            itemService.updateItem(item);
            return SysResult.oK();
        }catch (Exception e){
            //写出错误日志
            String msg = e.getMessage();
            log.error(msg);
            return SysResult.build(201,msg);
        }
    }

    /**
     * 单个或批量删除商品
     * @param ids 商品id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult deleteItems(Long[] ids){
        try{
            itemService.deleteItems(ids);
            return SysResult.oK();
        }catch (Exception e){
            //写出错误日志
            log.error(e.getMessage());
            return SysResult.build(201,e.getMessage());
        }
    }
}
