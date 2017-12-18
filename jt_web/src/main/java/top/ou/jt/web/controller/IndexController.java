/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: IndexController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/18 22:59
 * Description: 前台页面跳转控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈前台页面跳转控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/18 22:59
 * @since 1.0.0
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String toIndex(){
        return "index";
    }
}
