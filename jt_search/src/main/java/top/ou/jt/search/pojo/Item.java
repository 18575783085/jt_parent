/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: Item
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/27 0:10
 * Description: 商品
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.search.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.solr.client.solrj.beans.Field;
import top.ou.jt.common.po.BasePojo;


/**
 * 〈一句话功能简述〉<br> 
 * 〈商品〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/27 0:10
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)//当转换时，属性不匹配，忽略
public class Item extends BasePojo{
    /**
     * 商品id
     */
    @Field //solr搜索的结果和实体对象属性映射
    private Long id;
    /**
     * 商品标题
     */
    @Field
    private String title;
    /**
     * 商品卖点
     */
    @Field
    private String sellPoint;
    /**
     * 商品价格
     */
    @Field
    private Long price;
    /**
     * 库存数量
     */
    @Field
    private Integer num;
    /**
     * 商品条形码
     */
    @Field
    private String barcode;
    /**
     * 商品图片
     */
    @Field
    private String image;
    /**
     * 所属类目，叶子类目
     */
    @Field
    private Long cid;
    /**
     * 商品状态，1-正常，2-下架
     */
    @Field
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //拆分图片
    public String[] getImages(){
        return this.image.split(",");
    }
}
