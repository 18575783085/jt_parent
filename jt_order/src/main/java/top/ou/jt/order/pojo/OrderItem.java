/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: OrderItem
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 15:38
 * Description: 订单列表实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.order.pojo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈订单列表实体〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 15:38
 * @since 1.0.0
 */
public class OrderItem {
    /**
     * 商品id
     */
    private String itemId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 商品购买数量
     */
    private Integer num;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品单价
     */
    private Long price;
    /**
     * s商品总金额
     */
    private String totalFee;
    /**
     * 商品图片地址
     */
    private String picPath;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
