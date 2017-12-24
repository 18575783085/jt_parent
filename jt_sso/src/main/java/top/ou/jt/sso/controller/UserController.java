/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: UserController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/21 20:17
 * Description: 用户控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.common.vo.SysResult;
import top.ou.jt.sso.pojo.User;
import top.ou.jt.sso.service.UserService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 20:17
 * @since 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 检查的接收和返回的方法（用户注册信息的校验）
     * @param param
     * @param type
     * @return
     */
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public SysResult check(@PathVariable String param,@PathVariable Integer type){
        try{
            Boolean result = userService.check(param,type);
            return SysResult.oK(result);
        } catch (Exception e){
            return  SysResult.build(201,"失败");
        }

    }

    /**
     * 请求地址：http://sso.jt.com/user/register
     * 用户注册
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public SysResult register(User user){
        //获取前台封装好的用户信息，进行用户注册
        String username = userService.register(user);
        //判断是否插入成功
        if (StringUtils.isNotEmpty(username)){
            //注册成功
            return SysResult.oK(username);
        }else{
            //注册失败
            return SysResult.build(201,"注册失败");
        }
    }

    /**
     * 登录请求地址：http://sso.jt.com/user/login
     * 用户登录
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public SysResult login(String u,String p){

        //1.获取业务层返回的结果
        String ticket = userService.login(u,p);
        //判断ticket是否为空
        if(StringUtils.isNotEmpty(ticket)){
            //登录成功
            return SysResult.oK(ticket);
        }else{
            //登录失败
            return SysResult.build(201,"用户登录失败");
        }

    }

    /**
     * 请求地址：http://sso.jt.com/user/query/{ticket}
     * 查询用户信息
     * @param ticket
     * @return
     */
    @RequestMapping("/query/{ticket}")
    @ResponseBody
    public SysResult queryByTicket(@PathVariable String ticket){
        String userJson = userService.queryByTicket(ticket);
        //判断是否为空
        if (StringUtils.isNotEmpty(userJson)){
            //存在
            return SysResult.oK(userJson);
        }else{
            //不存在
            return SysResult.build(201,"查无此人");
        }
    }
}
