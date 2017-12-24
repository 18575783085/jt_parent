/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: CartService
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 13:02
 * Description: 购物车业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.cart.mapper.CartMapper;
import top.ou.jt.cart.pojo.Cart;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈购物车业务层〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 13:02
 * @since 1.0.0
 */
@Service
public class CartService extends BaseService<Cart>{

    @Autowired
    private CartMapper cartMapper;

    /**
     * 查询我的购物车
     * @param userId 用户id
     * @return
     */
    public List<Cart> queryMyCart(Long userId){
        //根据用户id查询该用户的购物车
        List<Cart> cartList = cartMapper.queryMyCart(userId);
        return cartList;
    }

    /**
     * 保存商品到购物车
     * @param cart
     */
    public Integer saveCart(Cart cart){
        /**
         * 1、判断商品是否存在于购物车
         * 2、如果不存在，新增
         * 3、如果存在，修改其数量，在旧的商品数量（旧数据）上增加新的数量（新数据）
         */
        Cart param = new Cart();//保证where条件的正确，因为通用Mapper是按字段是否为null来拼接where条件的
        //设置用户id
        param.setUserId(cart.getUserId());
        //设置商品id
        param.setItemId(cart.getItemId());

        Cart oldCart = super.queryByWhere(param);
        if (oldCart == null){
            /*
                不存在对应用户和对应商品的相同购物车
                则新增
             */
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cartMapper.insertSelective(cart);

            return 200;
        }else{
            /*
                存在，新数量=旧数量+传递数量
             */
            oldCart.setNum(oldCart.getNum()+cart.getNum());
            oldCart.setUpdated(new Date());
            cartMapper.updateByPrimaryKeySelective(oldCart);
            return 202;
        }
    }

    /**
     * 修改购物车商品数量
     * @param cart
     */
    public void updateNum(Cart cart){

        //设置购物车修改时间
        cart.setUpdated(new Date());
        cartMapper.updateNum(cart);
    }
}
