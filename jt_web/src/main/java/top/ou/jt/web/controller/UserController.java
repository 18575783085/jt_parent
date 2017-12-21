/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: UserController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/21 23:25
 * Description: 前台用户控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.common.util.CookieUtils;
import top.ou.jt.common.vo.SysResult;
import top.ou.jt.web.pojo.User;
import top.ou.jt.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br> 
 * 〈前台用户控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 23:25
 * @since 1.0.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 拦截用户注册请求
     * @return
     */
    @RequestMapping("/user/register")
    public String registerIndex(){
        return "register";
    }

    /**
     * 用户注册：/user/doRegister.html
     * @return
     */
    @RequestMapping("/user/doRegister")
    @ResponseBody
    public SysResult doRegister(User user){

        String username = null;
        try{
            username = userService.doRegister(user);
            //判断是否为空
            if(StringUtils.isNotEmpty(username)){
                //存在
                return SysResult.oK();
            }else{
                //不存在
                return SysResult.build(201,"注册失败",user.getUsername());
            }
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"注册失败",user.getUsername());
        }
    }

    /**
     * 转向登录页面：/user/login.html
     * @return
     */
    @RequestMapping("/user/login")
    public String loginIndex(){
        return "login";
    }

    /**
     * 实现用户登录
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/user/doLogin")
    @ResponseBody
    public SysResult doLogin(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
       //1.进行用户登录，获取票
        String ticket = userService.doLogin(user);

        //2.判断票是否为空
        if(StringUtils.isNotEmpty(ticket)){
            //3.如果有值则登录成功，封装ticket到cookie中
            String cookieName = "JT_TICKET";
            CookieUtils.setCookie(request,response,cookieName,ticket);
            return SysResult.oK();
        }else{
            return SysResult.build(201,"用户登录失败");
        }
    }

    /**
     * 用户登出操作
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        //1.删除cookie
        String cookieName = "JT_TICKET";
        CookieUtils.deleteCookie(request,response,cookieName);

        /*
            2.还需要清除UserThreadLocal
            缓存可以清除，redis除了删除，过期，LUR计算，最近最久未使用删除
         */
        return "index";

    }
}
