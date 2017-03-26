package com.sinosoft.ie.hcmops.model;

import org.joda.time.DateTime;

/**
 * 实验室设备
 * @author thinkpad
 *
 */
public class Equip {
	@Override
	public String toString() {
		return "Equip [id=" + id + ", equip_name=" + equip_name + ", equip_model=" + equip_model + ", unit_price="
				+ unit_price + ", equip_number=" + equip_number + ", storage_time=" + storage_time + ", producer="
				+ producer + ", application=" + application + ", equip_image_one=" + equip_image_one
				+ ", equip_image_two=" + equip_image_two + ", laboratory_id=" + laboratory_id + ", operation_time="
				+ operation_time + ", staff_id=" + staff_id + ", type=" + type + ", equip_desc=" + equip_desc
				+ ", order_num=" + order_num + "]";
	}
	private String id;//设备编号，id
	private String equip_name;//设备名称
	private String equip_model;//设备型号
	private String unit_price;//设备单价
	private String equip_number;//设备数量
	private String storage_time;//入库时间
	private String producer;//生产产商
	private String application;//用途简介
	private String equip_image_one;//设备图片1
	private String equip_image_two;//设备图片2
	private String laboratory_id;//实验室编号id
	private String operation_time;//操作时间
	private String staff_id;//管理员工号
	private String type;//类型（1是管理员，2是教师，3是学生）
	private String equip_desc;//备注（可填可不填）
	private String order_num;//排序
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEquip_name() {
		return equip_name;
	}
	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}
	public String getEquip_model() {
		return equip_model;
	}
	public void setEquip_model(String equip_model) {
		this.equip_model = equip_model;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getEquip_number() {
		return equip_number;
	}
	public void setEquip_number(String equip_number) {
		this.equip_number = equip_number;
	}
	public String getStorage_time() {
		return storage_time;
	}
	public void setStorage_time(String storage_time) {
		this.storage_time = storage_time;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getEquip_image_one() {
		return equip_image_one;
	}
	public void setEquip_image_one(String equip_image_one) {
		this.equip_image_one = equip_image_one;
	}
	public String getEquip_image_two() {
		return equip_image_two;
	}
	public void setEquip_image_two(String equip_image_two) {
		this.equip_image_two = equip_image_two;
	}
	public String getLaboratory_id() {
		return laboratory_id;
	}
	public void setLaboratory_id(String laboratory_id) {
		this.laboratory_id = laboratory_id;
	}
	
	public String getOperation_time() {
		return operation_time;
	}
	public void setOperation_time(String operation_time) {
		this.operation_time = operation_time;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEquip_desc() {
		return equip_desc;
	}
	public void setEquip_desc(String equip_desc) {
		this.equip_desc = equip_desc;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
}
