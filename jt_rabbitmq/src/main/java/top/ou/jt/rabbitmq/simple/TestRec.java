/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: TestRec
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/25 15:31
 * Description: 消息队列接受者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消息队列接收者〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/25 15:31
 * @since 1.0.0
 */
public class TestRec {

    //接收消息
    @Test
    public void recvice() throws IOException, InterruptedException {
        //创建队列工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置rabbitmq的ip地址
        factory.setHost("192.168.161.130");
        //设置端口（程序访问，所以使用5672）
        factory.setPort(5672);
        //设置虚拟主机
        factory.setVirtualHost("/jt");
        //设置用户名和密码
        factory.setUsername("ouzhenye");
        factory.setPassword("123456");

        //2.创建通道
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //绑定队列
        channel.queueDeclare("test_simple",false,false,false,null);

        //创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //监听队列,绑定消费者消息队列，消费消息，true自动确认(一旦获取，消息就从队列中消失)
        channel.basicConsume("test_simple",true,consumer);

        while (true){//利用死循坏模拟监听

            //从消费者对象中获取下一条消息
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            //获取消息
            String msg = new String(delivery.getBody());
            System.out.println("消息："+msg);
        }


    }
}
