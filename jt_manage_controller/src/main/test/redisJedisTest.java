/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: redisJedisTest
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/17 21:52
 * Description: redis测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */


import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 〈一句话功能简述〉<br> 
 * 〈redis测试〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/17 21:52
 * @since 1.0.0
 */
public class redisJedisTest {

    @Test
    public void jedisTest1(){
        //1.创建host链接虚拟主机的外网地址，第一个redis节点，设置外网ip和端口
        Jedis jedis = new Jedis("192.168.161.130",6390);

        //2.获取存储在redis中的key（参数）
        String name = jedis.get("name");
        System.out.println(name);

        //3.设置参数存储到redis中
        jedis.set("age","22");

        //4.关闭redis连接
        jedis.close();
    }

    @Test
    public void jedisTest2(){
        //1.创建host链接虚拟主机的外网地址，第二个redis节点
        Jedis jedis = new Jedis("192.168.161.130",6380);

        //2.获取存储在redis中的参数值
        String name = jedis.get("name");
        System.out.println(name);

        //3.设置参数存储到redis中
        jedis.set("age ","66");

        //4.关闭redis连接
        jedis.close();

    }

    @Test
    public void jedisTest3(){
        //1.创建host链接虚拟主机的外网地址，第三个redis节点，设置ip和端口
        Jedis jedis = new Jedis("192.168.163.130",6381);

        //2.获取存储在redis中的参数值
        String name = jedis.get("name");
        System.out.println(name);

        //3.设置参数存储到redis中
        jedis.set("age","99");

        //4.关闭redis连接
        jedis.close();


    }
}
