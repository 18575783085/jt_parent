package top.ou.jt.sso.mapper;

import top.ou.jt.common.mapper.SysMapper;
import top.ou.jt.sso.pojo.User;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈${DESCRIPTION}〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 20:37
 * @since 1.0.0
 */
public interface UserMapper extends SysMapper<User>{

    /**
     * Ajax校验用户注册信息
     * @param map
     * @return
     */
    Integer check(Map<String, String> map);
}
