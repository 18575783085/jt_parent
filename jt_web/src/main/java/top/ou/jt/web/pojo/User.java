/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: User
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/21 23:53
 * Description: 用户实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.web.pojo;

import top.ou.jt.common.po.BasePojo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户实体类〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 23:53
 * @since 1.0.0
 */
public class User extends BasePojo {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
