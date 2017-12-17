/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: redisJedisTest2
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/17 22:44
 * Description: redis的分片案例
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */

import org.junit.Test;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈jedis的分片案例〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/17 22:45
 * @since 1.0.0
 */
public class redisJedisTest2 {

    /**
     * jedis分片
     *    如何将分开独立的redis节点整合到一起来执行分布式的内存数据库功能？
     *    从redis的角度三个节点实例还是分开的，jjedis提供分片的功能，利用底层代码
     *    来维护一个分布式的使用
     */

    /**
     * 分片是什么意思
     * 将key对应的存储到唯一的redis节点中，分开存储，分开计算
     */

    @Test
    public void jedisShardTest1(){
        //1.构造一个集合存储redis所有节点的信息
        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>();

        //2.存储节点信息的内容需要对象jedisShardInfo对象
        JedisShardInfo info1 = new JedisShardInfo("192.168.161.130",6379);
        JedisShardInfo info2 = new JedisShardInfo("192.168.161.130",6380);
        JedisShardInfo info3 = new JedisShardInfo("192.168.161.130",6381);

        //3.将jedisShardInfo对象存进到list集合中（添加节点）
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);
        jedisShardInfoList.add(info3);

        ShardedJedis jedis = new ShardedJedis(jedisShardInfoList);

        //疑问：什么样的key会对应哪个redis？name是放到6379还是6380
        //获取参数
        String name = jedis.get("name");
        System.out.println(name);
        //设置参数
        jedis.set("address","gaoming");

        //key值的取值和redis节点的映射-----hash一致性
        //关闭连接
        jedis.close();
    }

    @Test
    public void jedisShardTest2(){
        //1.创建jedisShardInfo集合
        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>();

        //2.设置JedisShardInfo对象
        JedisShardInfo info1 = new JedisShardInfo("192.168.161.130",6379);
        JedisShardInfo info2 = new JedisShardInfo("192.168.161.130",6380);
        JedisShardInfo info3 = new JedisShardInfo("192.168.161.130",6381);

        //3.添加redis节点
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);
        jedisShardInfoList.add(info3);

        //4.Jedis就不是new出来的，需要调用jedis池来获取jedis对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();//初始化配置
        //4.1设置jedis池的最大条数
        poolConfig.setMaxTotal(200);

        //5.将池对象创建出来
        ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig,jedisShardInfoList);
        //6.获取jedis池连接
        ShardedJedis jedis = jedisPool.getResource();

        //7.从redis中获取参数
        String name = jedis.get("name");
        System.out.println(name);

        //7.1设置参数存储到jedis池中
        jedis.set("city","foshan");

        //8.关闭jedis池
        jedisPool.close();
    }

}
