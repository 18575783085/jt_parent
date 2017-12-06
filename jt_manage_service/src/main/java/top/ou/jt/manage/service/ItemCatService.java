package top.ou.jt.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ou.jt.manage.mapper.ItemCatMapper;
import top.ou.jt.manage.pojo.ItemCat;

import java.util.List;

@Service
public class ItemCatService extends BaseService<ItemCat> {


    @Autowired
    private ItemCatMapper itemCatMapper;

    public List<ItemCat> queryItemCatList(Integer parentId) {
        List<ItemCat> itemCatList = itemCatMapper.queryItemCatList(parentId);
        return itemCatList;
    }
}
