package top.ou.jt.order.mapper;

import top.ou.jt.common.mapper.SysMapper;
import top.ou.jt.order.pojo.Order;

/**
 * 〈一句话功能简述〉<br>
 * 〈${DESCRIPTION}〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 15:49
 * @since 1.0.0
 */
public interface OrderMapper {
    /**
     * 新建订单
     * @param order
     */
    public void orderCreate(Order order);

    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    public Order queryByOrderId(String orderId);
}
