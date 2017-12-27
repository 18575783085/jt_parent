/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/27 0:09
 * Description: 商品搜索控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.common.vo.SysResult;
import top.ou.jt.search.service.ItemService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品搜索控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/27 0:09
 * @since 1.0.0
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 访问solr查询
     */
    @RequestMapping("/search/{keyWords}/{page}/{rows}")
    @ResponseBody//返回json串
    public SysResult search(@PathVariable String keyWords,@PathVariable Integer page,@PathVariable Integer rows){
        return itemService.search(keyWords, page, rows);
    }
}
