package com.sinosoft.ie.hcmops.model;
/**
 * 通知公告
 * @author thinkpad
 *
 */
public class Notice {
	private String id;//公告id
	private String notice_title;//公告标题
	private String notice_desc;//公告简介
	private String notice_detail;//公告详情
	private String notice_date;//公告发布时间
	private String notice_source;//公告来源
	private String image_url;//首页图片展示一张
	private String order_num;//排序
	private String del_status;//是否删除（0删，1为保存）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_desc() {
		return notice_desc;
	}
	public void setNotice_desc(String notice_desc) {
		this.notice_desc = notice_desc;
	}
	public String getNotice_detail() {
		return notice_detail;
	}
	public void setNotice_detail(String notice_detail) {
		this.notice_detail = notice_detail;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_source() {
		return notice_source;
	}
	public void setNotice_source(String notice_source) {
		this.notice_source = notice_source;
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
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	
}
