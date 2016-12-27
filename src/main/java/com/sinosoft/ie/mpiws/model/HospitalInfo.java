package com.sinosoft.ie.mpiws.model;

public class HospitalInfo {
private String HOS_ORG_CODE;//医疗机构代码
private String ORG_NIZATION_NAME;//医院名称
private String IP;//所属ip
public String getIP() {
	return IP;
}
public void setIP(String iP) {
	IP = iP;
}
public String getHOS_ORG_CODE() {
	return HOS_ORG_CODE;
}
public void setHOS_ORG_CODE(String hOS_ORG_CODE) {
	HOS_ORG_CODE = hOS_ORG_CODE;
}
public String getORG_NIZATION_NAME() {
	return ORG_NIZATION_NAME;
}
public void setORG_NIZATION_NAME(String oRG_NIZATION_NAME) {
	ORG_NIZATION_NAME = oRG_NIZATION_NAME;
}
}
