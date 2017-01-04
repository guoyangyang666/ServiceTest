package com.sinosoft.ie.hcmops.domain;

import java.util.List;

/*
 * 教师服务
 */
public interface TeacherMgr {
	//查找教师的个人信息，入参是教师的工号
	public List queryTeacherInfo(String staff_id);
}
