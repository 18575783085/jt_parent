package top.ou.jt.jsoup.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tb_item_other")
public class Item extends BasePojo{
	@Id
	private Long id;
	private String cwhere;
	public String getCwhere() {
		return cwhere;
	}
	public void setCwhere(String cwhere) {
		this.cwhere = cwhere;
	}
	private String title;
	private String sellPoint;
	private Integer price;
	private Integer num;
	private String barcode;
	private String image;
	private Long cid;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
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
	@Override
	public String toString() {
		return "Item [id=" + id + ", cwhere=" + cwhere + ", title=" + title + ", sellPoint=" + sellPoint + ", price="
				+ price + ", num=" + num + ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status="
				+ status + "]";
	}
	
}
