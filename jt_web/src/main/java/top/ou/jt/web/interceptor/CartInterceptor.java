/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: CartInterceptor
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 20:34
 * Description: 购物车拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.ou.jt.common.service.HttpClientService;
import top.ou.jt.common.util.CookieUtils;
import top.ou.jt.web.pojo.User;
import top.ou.jt.web.threadlocal.UserThreadLocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br> 
 * 〈购物车拦截器〉
 *   拦截器，继承接口类，拦截符合扫描规则的controller请求
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 20:34
 * @since 1.0.0
 */
public class CartInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpClientService httpClientService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 在controller执行方法前调用
     *
     * @param request
     * @param response
     * @param handler
     * @return true 表示拦截器放行  false表示不放行，不放行的时候，刷新页面，重定向，转发
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 步骤：
         * 1、从cookie中获取ticket
         * 2、去访问sso系统获取redis的user的json
         * 3、json转化成对象user
         * 4、userId的共享数据，线程安全
         * 5、使用UserThreaLocal 自定义的ThreadLocal的子类
         * 专门控制user对象的线程安全
         */

        //1.去cookie中获取数据，ticket
        String cookieName = "JT_TICKET";
        String ticket = CookieUtils.getCookieValue(request,cookieName);

        //2.判断是否为空
        if(StringUtils.isNotEmpty(ticket)){
            //3.去redis中根据ticket查询
            String url = "http://sso.jt.com/user/query/"+ticket;
            String jsonData = httpClientService.doGet(url,"utf-8");
            if(StringUtils.isNotEmpty(jsonData)){
                try {
                    JsonNode userJsonNode = MAPPER.readTree(jsonData);
                    String curUserJson = userJsonNode.get("data").asText();

                    User user = MAPPER.readValue(curUserJson,User.class);
                    UserThreadLocal.set(user);

                    return true;
                }catch (Exception e){

                }

            }

        }
        //没有登录信息的时候
        UserThreadLocal.set(null);

        //当没有ticket时转向登录页面
        response.sendRedirect("/user/login.html");
        return false;
    }

    /**
     * 在controller执行方法后调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在controller执行方法后，再转向页面前
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
