package com.sinosoft.ie.hcmops.model;
/**
 * 新闻
 * @author thinkpad
 *
 */
public class News {
	private String id;//新闻id
	private String news_title;//新闻标题
	private String news_desc;//新闻简介
	private String news_detail;//新闻详情
	private String news_date;//新闻发布时间
	private String news_source;//新闻来源
	private String image_url;//新闻缩略图地址
	private Integer pv_num;//浏览量
	private String order_num;//排序
	private String del_status;//是否删除（0删，1为保存）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_desc() {
		return news_desc;
	}
	public void setNews_desc(String news_desc) {
		this.news_desc = news_desc;
	}
	public String getNews_detail() {
		return news_detail;
	}
	public void setNews_detail(String news_detail) {
		this.news_detail = news_detail;
	}
	public String getNews_date() {
		return news_date;
	}
	public void setNews_date(String news_date) {
		this.news_date = news_date;
	}
	public String getNews_source() {
		return news_source;
	}
	public void setNews_source(String news_source) {
		this.news_source = news_source;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
	public Integer getPv_num() {
		return pv_num;
	}
	public void setPv_num(Integer pv_num) {
		this.pv_num = pv_num;
	}
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	
	
}
