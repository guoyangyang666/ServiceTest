package com.sinosoft.ie.hcmops.domain;

import java.util.List;

/*
 * 教师服务
 */
public interface TeacherMgr {
	//查找教师的个人信息，入参是教师的工号
	public List queryTeacherInfo(String staff_id);
	//教师查看实验室预约批次，判定条件是管理员添加的批次
	public List queryApplyBatch();
	//教师预约该批次
	public void applyBatch();
}
