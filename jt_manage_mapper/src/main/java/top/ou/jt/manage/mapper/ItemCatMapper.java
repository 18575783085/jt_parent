package top.ou.jt.manage.mapper;

import top.ou.jt.common.mapper.SysMapper;
import top.ou.jt.manage.pojo.ItemCat;

import java.util.List;

public interface ItemCatMapper extends SysMapper<ItemCat>{


    public List<ItemCat> queryItemCatList(Integer parentId);
}
