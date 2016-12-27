package com.sinosoft.ie.hcmops.model;
/**
 * 学生实体类
 * @author thinkpad
 *
 */
public class StudentsStu {
	private String id;//学生学号
	private String stuName;//学生名称
	private String stuSex;//学生性别
	private String stuBirth;//出生年月
	private String stuCardnum;//身份证号
	private String schoolId;//学校id
	private String collegeId;//学院id
	private String deptId;//专业id
	private String classId;//班级id
	private String stuDesc;//学生简介
	private String loginPw;//登陆密码（默认6个0）
	private String type;//类型，登陆时用（管理员1，教师2，学生3）
	private Integer orderNum;//排列顺序
	private Integer delStatus=1;//删除状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuBirth() {
		return stuBirth;
	}
	public void setStuBirth(String stuBirth) {
		this.stuBirth = stuBirth;
	}
	public String getStuCardnum() {
		return stuCardnum;
	}
	public void setStuCardnum(String stuCardnum) {
		this.stuCardnum = stuCardnum;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getStuDesc() {
		return stuDesc;
	}
	public void setStuDesc(String stuDesc) {
		this.stuDesc = stuDesc;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
