package com.sinosoft.ie.hcmops.model;
/**
 * 实验室管理员添加实验批次大概范围
 * @author thinkpad
 *
 */
public class Experimbatchs {
	private String id;//实验室管理员添加实验批次，id
	private String experim_id;//实验批次名id（什么实验）
	private String start_week;//开始周数
	private String last_week;//持续周数
	private String laboratory_id;//对应的实验室
	private String batch;//实验批次
	private String status;//状态（1为无教师预约，2为教师预约，）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExperim_id() {
		return experim_id;
	}
	public void setExperim_id(String experim_id) {
		this.experim_id = experim_id;
	}
	public String getStart_week() {
		return start_week;
	}
	public void setStart_week(String start_week) {
		this.start_week = start_week;
	}
	public String getLast_week() {
		return last_week;
	}
	public void setLast_week(String last_week) {
		this.last_week = last_week;
	}
	public String getLaboratory_id() {
		return laboratory_id;
	}
	public void setLaboratory_id(String laboratory_id) {
		this.laboratory_id = laboratory_id;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
