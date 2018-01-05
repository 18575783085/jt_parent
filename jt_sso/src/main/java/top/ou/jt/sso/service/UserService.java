/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: UserService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/21 20:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.sso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.common.service.RedisService;
import top.ou.jt.sso.mapper.UserMapper;
import top.ou.jt.sso.pojo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 20:25
 * @since 1.0.0
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 检查数据的接口方法
     * @param param
     * @param type
     * @return
     */
    public Boolean check(String param,Integer type){
        //1.构建一个传递给userMapper的map对象，将param传递给它
        Map<String,String> map = new HashMap<String, String>();

        //2.根据type的数值完成map参数的封装
        if (type == 1){
            //username
            map.put("name","username");
        }else if (type == 2){
            //phone
            map.put("name","phone");
        }else if (type == 3){
            //email
            map.put("name","email");
        }
        map.put("val",param);

        //3.调用check方法查询
        Integer i = userMapper.check(map);
        if (i == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 注册的接口方法
     * 用户注册
     * @param user 用户信息对象
     * @return
     */
    public String register(User user) {
        //对传递过来的数据进行处理，密码保存在数据库中不能是明文
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));//对用户密码进行MD5加密
        user.setCreated(new Date());//用户创建的时间
        user.setUpdated(user.getCreated());//用户更新时间

        /**
         * 由于前台的注册邮箱和手机号没有做同时处理，这里只有一个唯一性校验，
         * email是null，不可以是null，数据库对三个字段做了唯一性校验
         * username,phone,email,不能默认插入email = null
         * 拿一个前台做过的唯一性校验来做email
         */
        if(StringUtils.isEmpty(user.getEmail())){
            //如果邮箱为空
            user.setEmail(user.getPhone());
        }

        userMapper.insertSelective(user);
        return user.getUsername();
    }

    /**
     * 用户登录
     * @param username 账号
     * @param passowrd 密码
     * @return
     */
    public String login(String username, String passowrd) {
        /*
            这里有个问题，用哪个方法查询数据
            byWhere 会对不是null的数据进行查询条件的拼接
         */
        User _user = new User();
        //1.1设置用户账号
        _user.setUsername(username);
        //1.2设置用户密码
        //_user.setPassword(passowrd);

        //2.按照用户名查询
        User user = super.queryByWhere(_user);

        //3.逻辑判断是否有返回的user
        if(user != null){
            //4.进行密码匹配
            String newPassword = DigestUtils.md5Hex(passowrd);
            if (newPassword.equals(user.getPassword())){
                //是系统用户登录成功，写redis
                try{
                    String userJson = MAPPER.writeValueAsString(user);
                    //生成key ticket=cookie名+时间戳+用户名
                    String ticket = DigestUtils.md5Hex("JT_TICKET"+System.currentTimeMillis()+username);
                    //对redis数据库设置keyvalue值和存储的时间
                    redisService.set(ticket,userJson,60*60*24*7);
                    return ticket;

                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    /**
     * 查询用户
     * @param ticket
     * @return
     */
    public String queryByTicket(String ticket) {
        String userJson = redisService.get(ticket);
        return userJson;
    }
}
