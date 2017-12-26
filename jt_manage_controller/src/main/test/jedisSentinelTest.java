/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: jedisSentinelTest
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/18 12:11
 * Description: redis哨兵模式
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈redis哨兵模式测试〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/18 12:11
 * @since 1.0.0
 */
public class jedisSentinelTest {

    @Test
    public void sentinelTest1(){
        Set<String> sentinels = new HashSet<String>();

        sentinels.add(new HostAndPort("192.168.161.131",26379).toString());
        //sentinels.add(new HostAndPort("192.168.161.131",26379).toString());

        /*
        mymaster 是在sentinel.conf配置文件的名称
        sentinel monitor mymaster 192.168.161.131 6379 1
         */
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster",sentinels);
        System.out.println("当前的master是："+sentinelPool.getCurrentHostMaster());

        //从哨兵获取redis主支
        Jedis jedis = sentinelPool.getResource();

        /*
            如果在sentinel.conf配置文件设置了密码
            jedis.auth("123456");
         */

        String num = jedis.get("num");
        System.out.println(num);
        sentinelPool.returnResource(jedis);

        sentinelPool.destroy();
        System.out.println("ok");
    }
}
