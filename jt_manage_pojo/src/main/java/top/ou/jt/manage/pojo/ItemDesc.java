/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemDesc
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/17 17:27
 * Description: 商品详情
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.pojo;

import top.ou.jt.common.po.BasePojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品详情实体〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/1717:27
 * @since 1.0.0
 */
@Table(name = "tb_item_desc")
public class ItemDesc extends BasePojo {

    @Id
    private Long itemId;

    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
