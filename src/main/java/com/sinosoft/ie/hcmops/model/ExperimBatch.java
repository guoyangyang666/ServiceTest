package com.sinosoft.ie.hcmops.model;
/**
 * 实验批次及实验室课表
 * @author thinkpad
 *
 */
public class ExperimBatch {
	private String id;//实验室管理员添加实验批次，id
	private String course_id;//课程id
	private String experim_id;//实验批次名（什么实验）
	private String batch;//实验批次
	private String week;//星期数，数组j的值
	private String start_times;//开始课时，数组i的值
	private String stop_times;//持续课时，
	private String start_week;//开始周数
	private String last_week;//持续周数
	private String laboratory_id;//对应的实验室
	private String appoint_week;//预约的周（教师）
	private String staff_id;//教师工号
	private String type;//类型，1为实验室的课，2为教师确认的批次，其他教师不可选
	private String status;//1为普通的课程。教师确认批次的状态，（1为已预约，2为取消，3为删除）
	private Integer experim_num;//预约的人数，与实验室容量对比
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getStart_times() {
		return start_times;
	}
	public void setStart_times(String start_times) {
		this.start_times = start_times;
	}
	public String getStop_times() {
		return stop_times;
	}
	public void setStop_times(String stop_times) {
		this.stop_times = stop_times;
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
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getExperim_id() {
		return experim_id;
	}
	public void setExperim_id(String experim_id) {
		this.experim_id = experim_id;
	}
	public String getAppoint_week() {
		return appoint_week;
	}
	public void setAppoint_week(String appoint_week) {
		this.appoint_week = appoint_week;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getExperim_num() {
		return experim_num;
	}
	public void setExperim_num(Integer experim_num) {
		this.experim_num = experim_num;
	}
	
	
	

}