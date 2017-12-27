/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: TestJsoup
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/27 12:17
 * Description: jsoup爬虫测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.jsoup.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈jsoup爬虫测试〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/27 12:17
 * @since 1.0.0
 */
public class TestJsoup {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 抓整站，给一个url，从这个url页面中获取所有的 a 标签
     */
    @Test
    public void site() throws IOException {
        //1.赋值一个url地址参数
        String url = "https://item.jd.com/4838696.html";
        //2.获取一个连接
        Connection connect = Jsoup.connect(url);

        //3.抓取整个页面
        String html = connect.execute().body();

        System.out.println(html);
    }

    /**
     * 通过选择器抓取页面的a标签
     */
    @Test
    public void href() throws IOException {
        //1.目标地址
        String url = "https://item.jd.com/4838696.html";
        //2.获取连接
        Connection connect = Jsoup.connect(url);
        //3.获取页面文档对象
        Document document = connect.get();
        //4.根据选择器条件筛选标签
        Elements elements = document.select("a");
        //5.遍历标签
        for (Element element:elements){
            //属性名
            System.out.println(element.attr("href"));
        }

    }

    /**
     * 抓商品标题
     */
    @Test
    public void title() throws IOException {
        String url = "https://item.jd.com/4838696.html";
        //获取连接
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements elements = document.select(".sku-name");
        Element element = elements.get(0);
        String title = element.text();
        System.out.println(title);
    }

    /**
     * 抓取商品价格
     */
    @Test
    public void price() throws IOException {
        String url = "https://p.3.cn/prices/mgets?callback=jQuery2004361&type=1&area=1_72_2799_0&pdtk=&pduid=2056746480&pdpin=&pin=null&pdbp=0&skuIds=J_4838696&ext=11000000&source=item-pc";
        String jsonp = Jsoup.connect(url).ignoreContentType(true).execute().body();
        String json = jsonp.substring(jsonp.indexOf("(")+2,jsonp.length()-4);

        String price = MAPPER.readTree(json).get("p").asText();

        System.out.println(price);
    }
}
