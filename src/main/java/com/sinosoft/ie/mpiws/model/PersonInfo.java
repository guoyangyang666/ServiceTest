package com.sinosoft.ie.mpiws.model;

public class PersonInfo {
private String id;//唯一标识
private String name;//姓名
private String loginName;//登录名
private String sex;//性别
private String certId;//身份证号
private String hospital;//所属医院
private String phone;//联系方式
private String job;//职务
private String password;//密码
private String operId;//操作员id
private String status;//用户状态，0可登陆，1不可登陆
private String HOS_ORG_CODE;//医院编号
public String getHOS_ORG_CODE() {
	return HOS_ORG_CODE;
}
public void setHOS_ORG_CODE(String hOS_ORG_CODE) {
	HOS_ORG_CODE = hOS_ORG_CODE;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getOperId() {
	return operId;
}
public void setOperId(String operId) {
	this.operId = operId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getCertId() {
	return certId;
}
public void setCertId(String certId) {
	this.certId = certId;
}
public String getHospital() {
	return hospital;
}
public void setHospital(String hospital) {
	this.hospital = hospital;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getJob() {
	return job;
}
public void setJob(String job) {
	this.job = job;
}
}
