/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: JDUtil
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/27 18:20
 * Description: 抓取商品分类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.jsoup.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import top.ou.jt.jsoup.pojo.Item;
import top.ou.jt.jsoup.pojo.ItemDesc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抓取商品分类〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/27 18:20
 * @since 1.0.0
 */
public class JDUtil {

    private static final Logger log = Logger.getLogger(JDUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 获取所有三级分类链接，三级分类总数：1259，有效三级分类总数：1138
     * @param url
     * @return
     */
    public static List<String> getItemCatLevel3(String url) throws IOException {
        List<String> catList = new ArrayList<String>();

        Elements elements = Jsoup.connect(url).get().select(".clearfix dd a");

        for(Element element : elements){
            url = "http:"+element.attr("href");
            if (url.startsWith("http://list.jd.com/list.html?cat=")){
                catList.add("http:"+element.attr("href"));
            }
            log.info(element.attr("href"));
        }
        return catList;
    }

    /**
     * 抓取某个分类下的总页数
     */
    public static Integer getCatPages(String url){
        try {
           return Integer.valueOf(Jsoup.connect(url).get().select("#J_topPage .fp-text i").get(0).text());
        } catch (Exception e) {
            //发错误日志消息
            log.error(e.getMessage());
            return 0;
        }
    }

    /**
     * 抓取某个分类下的所有的分页链接
     * @param url
     * @return
     */
    public static List<String> getAllPageUrl(String url){
        List<String> pageUrls = new ArrayList<String>();

        Integer pages = getCatPages(url);
        for(int i = 1;i <= pages;i++){
            pageUrls.add(url+"&page="+i);
            log.info(url+"&page="+i);
        }
        return pageUrls;
    }

    /**
     * 获取某个三级分类列表页面的所有商品链接
     * @param pageUrl
     * @return
     */
    public static List<String> getItemUrl(String pageUrl){
        List<String> itemUrls = new ArrayList<String>();

        try {
            Elements elements = Jsoup.connect(pageUrl).get().select(".gl-i-wrap").select(".j-sku-item .p-img a");

            //遍历抓取到链接
            for (Element element:elements){
                String itemUrl = "http:"+element.attr("href");
                log.info(itemUrl);
                //将抓取的链接存进集合
                itemUrls.add(itemUrl);
            }
            //返回数据
            return  itemUrls;
        } catch (IOException e) {
            //写错误日志
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 抓取商品的标题
     * @param itemUrl
     * @return
     */
    public static String getTitle(String itemUrl){
        String title = null;

        try {
             title = Jsoup.connect(itemUrl).get().select(".itemInfo-wrap .sku-name").get(0).text();
             return title;
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return  null;
    }

    /**
     * 获取商品的卖点
     * @param itemId
     * @return
     */
    public static String getSellPoint(String itemId){
        String url = "http://ad.3.cn/ads/mgets?skuids=AD_"+itemId;
        try {
            //ignoreContentType=true：忽略类型
            String jsonData = Jsoup.connect(url).ignoreContentType(true).execute().body();

            String sellPoint = MAPPER.readTree(jsonData).get(0).get("ad").asText();
            return sellPoint;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 抓取商品的价格
     * @param itemId
     * @return
     */
    public static Integer getPrice(String itemId){
        String url = "http://p.3.cn/prices/mgets?skuIds=J_"+itemId;
        try {
            String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
            String price = MAPPER.readTree(json).get(0).get("p").asText().replaceFirst("\\.","");
            return Integer.valueOf(price);
        } catch (IOException e) {
            //写错误日志
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 抓取商品的图片
     */
    public static String getImage(String itemUrl){

        try {
            String image = "";
            Elements elements = Jsoup.connect(itemUrl).get().select(".lh li img");
            for(Element element : elements){
                image += "http:"+element.attr("src")+",";
            }
            image = image.substring(0,image.length() - 1);
            return image;
        } catch (IOException e) {
            //写错误日志
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 抓取商品的详情信息
     */
    public static String getDesc(String itemId){
        String url = "http://d.3.cn/desc/" + itemId;
        try {
            String jsonp = Jsoup.connect(url).ignoreContentType(true).execute().body();
            String json = jsonp.substring(jsonp.indexOf("(") + 1, jsonp.lastIndexOf(")"));
            //获取Content字段，把它转变成字符串
            String desc = MAPPER.readTree(json).get("content").asText();
            return desc;
        } catch (IOException e) {
            //写错误日志
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getItemId(String itemUrl){
        return itemUrl.substring(itemUrl.lastIndexOf("/")+1,itemUrl.lastIndexOf("."));
    }

    /**
     * 获取某个商品的详细内容
     */
    public static Item getItem(String itemUrl){
        Item item = new Item();
        //给定前缀，例如：10JD、20GM、30SN
        String itemId = getItemId(itemUrl);
        String id = "10"+itemId;
        //商品id
        item.setId(Long.valueOf(id));
        //设置标识(出处)
        item.setCwhere("京东");
        //商品标题
        item.setTitle(getTitle(itemUrl));
        //商品卖点
        item.setSellPoint(getSellPoint(itemId));
        //商品价格
        item.setPrice(getPrice(itemId));
        //商品图片
        item.setImage(getImage(itemUrl));
        //设置商品状态，默认状态：0
        item.setStatus(0);

        item.setCreated(new Date());
        item.setUpdated(item.getCreated());

        log.info(item);
        return item;
    }

    /**
     * 获取某个商品的介绍
     * @param id 当前商品的id（带企业标识）
     * @param itemId 是网站上的id
     * @return
     */
    public static ItemDesc getItemDesc(Long id,String itemId){
        //构造商品详情对象
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        //设置抓取商品介绍存进到商品详情对象中
        itemDesc.setItemDesc(getDesc(itemId));
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());

        //返回商品介绍数据
        log.info(itemDesc);
        return itemDesc;
    }

    @Test
    public void run() throws IOException {
        //抓取商品三级分类链接
        String url="https://www.jd.com/allSort.aspx";
//        JDUtil.getItemCatLevel3(url);

        //抓取商品页数
        url="https://list.jd.com/list.html?cat=1315,1343,1355";

//        Integer pages = JDUtil.getCatPages(url);
//        log.info(pages);

        //抓取某个分类下的所有的分页链接
        //JDUtil.getAllPageUrl(url);

        url="https://list.jd.com/list.html?cat=1315,1343,1355&page=3";
        //JDUtil.getItemUrl(url);

        url = "https://item.jd.com/3995645.html";
        //打印商品详情
        Item item = JDUtil.getItem(url);
        String itemId = getItemId(url);

        //打印商品介绍
        JDUtil.getItemDesc(item.getId(),itemId);
    }


}
