/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: CartController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/24 13:03
 * Description: 购物车控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ou.jt.cart.pojo.Cart;
import top.ou.jt.cart.service.CartService;
import top.ou.jt.common.vo.SysResult;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈购物车控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/24 13:03
 * @since 1.0.0
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 查询我的购物车
     * @return
     */
    @RequestMapping("/query/{userId}")
    @ResponseBody
    public SysResult queryMyCart(@PathVariable Long userId){
        List<Cart> cartList = cartService.queryMyCart(userId);
        //返回数据
        return SysResult.oK(cartList);
    }

    /**
     * 保存商品到购物车
     * @param cart 封装后的商品实体
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveCart(Cart cart){
        Integer codeStatus = cartService.saveCart(cart);
        if (codeStatus == 200){
            return SysResult.oK();
        }else if(codeStatus == 202){
            return  SysResult.build(202,"此商品已经存在购物车中，数量累加");
        }
        return SysResult.build(201,"新增商品到购物车出错！");

    }

    /**
     * 修改某个用户的某个商品的数量
     */
    @RequestMapping("/update/num/{userId}/{itemId}/{num}")
    @ResponseBody
    public SysResult updateNum(@PathVariable Long userId,@PathVariable Long itemId,@PathVariable Integer num){
        Cart param = new Cart();
        //设置用户id
        param.setUserId(userId);
        //设置商品id
        param.setItemId(itemId);
        //设置商品数量
        param.setNum(num);
        cartService.updateNum(param);
        return SysResult.oK();
    }


    /**
     * 删除购物车：/delete/{userId}/{itemId}
     * @param userId
     * @param itemId
     * @return
     */
    @RequestMapping("/delete/{userId}/{itemId}")
    @ResponseBody
    public SysResult deleteCart(@PathVariable Long userId,@PathVariable Long itemId){

        Cart cart = new Cart();
        //设置用户id
        cart.setUserId(userId);
        //设置商品id
        cart.setItemId(itemId);
        //根据用户id和商品id进行对购物车的商品删除
        cartService.deleteByWhere(cart);
        return SysResult.oK();
    }

}
