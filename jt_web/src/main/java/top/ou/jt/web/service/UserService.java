/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: UserService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/22 0:00
 * Description:
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
import top.ou.jt.web.pojo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/22 0:00
 * @since 1.0.0
 */
@Service
public class UserService {

    @Autowired
    private HttpClientService httpClientService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 用户注册
     * @param user
     * @return
     */
    public String doRegister(User user) throws Exception {
        //1.发起http请求的链接地址
        String url = "http://sso.jt.com/user/register";

        //2.封装http请求的数据
        Map<String,String> params = new HashMap<String, String>();

        //3.准备用户参数
        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        params.put("phone",user.getPhone());
        params.put("email",user.getEmail());

        //转换为json串
        String jsonData = httpClientService.doPost(url,params,"utf-8");

        //截取username
        JsonNode jsonNode = MAPPER.readTree(jsonData);

        //获取data
        String username = jsonNode.get("data").asText();

        return username;

    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    public String doLogin(User user) throws Exception {
        //1.调用httpclient
        String url = "http://sso.jt.com/user/login";

        //2.封装传递的数据，一个是u，一个是p
        Map<String,String> params = new HashMap<String, String>();
        //2.1准备参数
        params.put("u",user.getUsername());
        params.put("p",user.getPassword());

        //调用SSO系统的方法
        String jsonData = httpClientService.doPost(url,params,"utf-8");

        //从SysResult结构中截取ticket
        JsonNode jsonNode = MAPPER.readTree(jsonData);

        String ticket = jsonNode.get("data").asText();
        return ticket;

    }
}
