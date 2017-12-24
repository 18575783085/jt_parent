package top.ou.jt.cart.mapper;

import top.ou.jt.cart.pojo.Cart;
import top.ou.jt.common.mapper.SysMapper;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈${DESCRIPTION}〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 13:01
 * @since 1.0.0
 */
public interface CartMapper extends SysMapper<Cart>{
    /**
     * 查询我的购物车
     * @param userId
     * @return
     */
    public List<Cart> queryMyCart(Long userId);

    /**
     * 修改购物车的商品数量
     * @param cart
     */
    public void updateNum(Cart cart);
}
