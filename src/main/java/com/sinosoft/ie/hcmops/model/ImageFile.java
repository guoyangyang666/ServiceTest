package com.sinosoft.ie.hcmops.model;

import java.util.Date;

/**
 * 图片存放表
 * @author guoyangyang
 *
 */
public class ImageFile {
	private String id;//文件id
	private String image_name;//文件名称
	private String image_url;//文件路径
	private Date create_time;//操作时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
}
