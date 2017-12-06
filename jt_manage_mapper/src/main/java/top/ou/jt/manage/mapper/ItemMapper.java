/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: ItemMapper
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/6 20:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.mapper;

import top.ou.jt.common.mapper.SysMapper;
import top.ou.jt.manage.pojo.Item;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/6 20:45
 * @since 1.0.0
 */
public interface ItemMapper extends SysMapper<Item> {

    public List<Item> queryItemList();
}
