package top.ou.jt.manage.pojo;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import top.ou.jt.common.po.BasePojo;

/**
 * 商品分类实体类
 */
@Table(name="tb_item_cat")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCat extends BasePojo {
	/**
	 * 类目id
	 */
	@Id//代表这是一个主键，自增字段
	@GeneratedValue(strategy=GenerationType.IDENTITY)//自增主键
	private Long id;//如何知道parent_id=parentId
	/**
	 * 父类目id
	 */
	@Column(name="parent_id")//为什么只写了一个，这里做讲解实际上框架也给我们搞定了
	private Long parentId;//性能最高不要面向对象
	/**
	 * 类目名称
	 */
	private String name;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 排列序号
	 */
	private int sortOrder;
	/**
	 * 该类目是否为父类目
	 */
	private Boolean isParent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	/**
	 * easyUI通过ajax获取商品栏目的名称
	 * @return
	 */
	public String getText(){
		return this.getName();
	}

	/**
	 * 获取树枝状态
	 * @return
	 */
	public String getState(){
		return this.getIsParent() ? "closed":"open";//默认树枝是关闭的
	}
	
	
	
}
