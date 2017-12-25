/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: TestSend
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/25 14:58
 * Description: 测试消息队列
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.rabbitmq.simple;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试消息队列发送消息〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/25 14:58
 * @since 1.0.0
 */
public class TestSend {

    @Test
    public void send() throws IOException {
        /**
         * 步骤：
         * 1、创建链接，链接虚拟机
         * 2、创建通道channel
         * 3、声明或者绑定队列
         * 4、创建发送者，发送消息到消息队列
         */
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
       //设置rabbitmq的ip地址
        factory.setHost("192.168.161.130");
        //设置rabbitmq的端口
        factory.setPort(5672);
        //设置rabbitmq的虚拟主机
        factory.setVirtualHost("/jt");
        //用户名
        factory.setUsername("ouzhenye");
        //密码
        factory.setPassword("123456");
        
        //2.通过工厂获取连接
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare("test_simple",false,false,false,null);

        for (int i=0;i<50;i++){
            //发送消息
            String msg = "simple"+i;
            channel.basicPublish("","test_simple",null,msg.getBytes());

        }

    }
}
