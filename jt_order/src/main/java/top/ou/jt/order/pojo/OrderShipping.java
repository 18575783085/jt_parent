/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: OrderShipping
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 15:38
 * Description: 订单物流
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.order.pojo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈订单物流〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 15:38
 * @since 1.0.0
 */
public class OrderShipping {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 收货人名称
     */
    private String receiverName;
    /**
     * 固定电话
     */
    private String receiverPhone;
    /**
     * 手机号码
     */
    private String receiverMobile;
    /**
     * 省份
     */
    private String receiverState ;
    /**
     * 城市
     */
    private String receiverCity ;
    /**
     * 区/县
     */
    private String receiverDistrict;
    /**
     * 收货地址（详细地址）
     */
    private String receiverAddress;
    /**
     * 邮政编码
     */
    private String receiverZip ;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }
}
