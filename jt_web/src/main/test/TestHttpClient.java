/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: TestHttpClient
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/21 15:40
 * Description: 模拟爬虫页面测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈模拟爬虫页面测试〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/21 15:40
 * @since 1.0.0
 */
public class TestHttpClient {
    /**
     * 最简单的爬虫：模拟get请求
     */
    @Test
    public void httpClient() throws IOException {
        //1.目标地址
        String webUrl = "http://toutiao.jxnews.com.cn/p/20171221/730696.html?m=178f637e2ec9ef329957799af84472f7";

        //2.创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //3.模拟get请求,创建get对象
        HttpGet httpGet = new HttpGet(webUrl);

        //4.获取get请求，返回HttpClient response对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        //5.利用工具类解析出需要的结果html页面
        //获取html页面
        String html = EntityUtils.toString(httpResponse.getEntity());

        //打印
        System.out.println(html);

        //关闭资源
        httpClient.close();

    }
}
