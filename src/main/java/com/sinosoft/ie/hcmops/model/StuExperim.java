package com.sinosoft.ie.hcmops.model;
/**
 * 学生预约记录表
 * @author thinkpad
 *
 */
public class StuExperim {
	private String id;//
	private String stu_id;//学生学号
	private String course_time_id;//预约的id(实验室时间表)t_course_time
	private String laboratory_id;//实验室编号
	private String status;//（1为已预约，2为取消，3为删除）
	private String cancel_reason;//取消原因
	private String staff_id;//教师id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getCourse_time_id() {
		return course_time_id;
	}
	public void setCourse_time_id(String course_time_id) {
		this.course_time_id = course_time_id;
	}
	public String getLaboratory_id() {
		return laboratory_id;
	}
	public void setLaboratory_id(String laboratory_id) {
		this.laboratory_id = laboratory_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCancel_reason() {
		return cancel_reason;
	}
	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	
}
