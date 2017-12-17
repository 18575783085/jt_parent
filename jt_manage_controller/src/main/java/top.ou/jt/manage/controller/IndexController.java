/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: IndexController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/6 19:28
 * Description: 地址跳转控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈地址跳转控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/6 19:28
 * @since 1.0.0
 */
@Controller
public class IndexController {

    /**
     * 根据：page/{跳转的资源}----->跳转到 views文件下的网页
     * @param pageName 要跳转的资源
     * @return
     */
    @RequestMapping("/page/{pageName}")
    public String goHome(@PathVariable String pageName){
        return pageName;
    }

//    @RequestMapping("/index")
//    public String indexPath(){
//        return "/login";
//    }
}
