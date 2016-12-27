package com.sinosoft.ie.hcmops.model;
/**
 * 实验室实体类
 * @author thinkpad
 *
 */
public class LabInfo {
	private String id;//实验室编号
	private String laboratory_name;//教室名称
	private String laboratory_adress;//实验室地址，如北实验楼
	private String laboratory_adressnum;//教室号，如201
	private String category_id;//实验室类别（物理实验室、生物实验室等）
	private String staff_id;//实验室负责人id
	private String laboratory_desc;//实验室简介
	private String laboratory_renshu;//容量
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLaboratory_name() {
		return laboratory_name;
	}
	public void setLaboratory_name(String laboratory_name) {
		this.laboratory_name = laboratory_name;
	}
	public String getLaboratory_adress() {
		return laboratory_adress;
	}
	public void setLaboratory_adress(String laboratory_adress) {
		this.laboratory_adress = laboratory_adress;
	}
	public String getLaboratory_adressnum() {
		return laboratory_adressnum;
	}
	public void setLaboratory_adressnum(String laboratory_adressnum) {
		this.laboratory_adressnum = laboratory_adressnum;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getLaboratory_desc() {
		return laboratory_desc;
	}
	public void setLaboratory_desc(String laboratory_desc) {
		this.laboratory_desc = laboratory_desc;
	}
	public String getLaboratory_renshu() {
		return laboratory_renshu;
	}
	public void setLaboratory_renshu(String laboratory_renshu) {
		this.laboratory_renshu = laboratory_renshu;
	}
	
	
	
}
