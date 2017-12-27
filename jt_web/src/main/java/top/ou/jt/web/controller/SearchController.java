/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: SearchController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/27 8:57
 * Description: 搜索控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ou.jt.web.pojo.Item;
import top.ou.jt.web.service.SearchService;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈搜索控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/27 8:57
 * @since 1.0.0
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 跳转到搜索结果页面
     * @return
     */
    @RequestMapping("/search")
    public String search(String q, Integer page, Integer rows, Model model) throws Exception {

        //解决乱码
        String keyWords = new String(q.getBytes("ISO-8859-1"),"utf-8");
        model.addAttribute("query",keyWords);//get请求有中文乱码

        List<Item> itemList = searchService.search(keyWords,page,rows);
        model.addAttribute("itemList",itemList);

        return "search";

    }
}
