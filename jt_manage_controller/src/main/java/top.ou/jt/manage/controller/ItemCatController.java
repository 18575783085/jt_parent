package top.ou.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.ou.jt.manage.pojo.ItemCat;
import top.ou.jt.manage.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/queryall")
	@ResponseBody//springmvc会将返回的对象利用jackson json 转换成对应格式的json字符串
	public List<ItemCat> quertAll(){
		List<ItemCat> itemCatList = itemCatService.queryAll();
		return itemCatList;
	}
}
