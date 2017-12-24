/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: UserThreadLocal
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 20:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.threadlocal;

import top.ou.jt.web.pojo.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈拦截器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 20:24
 * @since 1.0.0
 */
public class UserThreadLocal {

    private static ThreadLocal<User> CURTHREAD = new ThreadLocal<User>();

    /**
     * 获取用户对象
     * @return
     */
    public static User get(){
        return CURTHREAD.get();
    }

    public static void set(User user){
        CURTHREAD.set(user);
    }

    /**
     * 获取用户的id
     * @return
     */
    public static Long getUserId(){
        User user = CURTHREAD.get();
        if(user != null){
            return user.getId();
        }else{
            return null;
        }
    }
}
