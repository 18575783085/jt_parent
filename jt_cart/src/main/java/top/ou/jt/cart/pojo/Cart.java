/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: Cart
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 12:57
 * Description: 购物车实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.cart.pojo;

import top.ou.jt.common.po.BasePojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 〈一句话功能简述〉<br> 
 * 〈购物车实体类〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 12:57
 * @since 1.0.0
 */
@Table(name = "tb_cart")
public class Cart extends BasePojo{

    /**
     * 购物车id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商品id
     */
    private Long itemId;
    /**
     * 商品名称
     */
    private String itemTitle;
    /**
     * 商品图片
     */
    private String itemImage;
    /**
     * 商品图片
     */
    private Long itemPrice;
    /**
     * 商品数量
     */
    private Integer num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
