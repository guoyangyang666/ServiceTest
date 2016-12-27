package com.sinosoft.ie.hcmops.model;
/**
 * 实验室无课
 * @author thinkpad
 *
 */
public class NoClassTimes {
	private String id;//无课表id
	private String laboratory_id;//实验室编号
	private String staff_id;
	private String times_index;//实验室管理员选择的索引值，取的时候对照select_class_index
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLaboratory_id() {
		return laboratory_id;
	}
	public void setLaboratory_id(String laboratory_id) {
		this.laboratory_id = laboratory_id;
	}
	public String getTimes_index() {
		return times_index;
	}
	public void setTimes_index(String times_index) {
		this.times_index = times_index;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	
	
}
