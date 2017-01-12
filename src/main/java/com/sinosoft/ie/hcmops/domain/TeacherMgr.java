package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

import com.sinosoft.ie.hcmops.model.ExperimBatch;

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
	
	//查询实验室课表及添加的实验批次
	public List<Map<String, String>> quryCourseExperim(String laboratory_id, String current_week);
	//查询实验室管理员添加的实验名
	public List<Map<String, String>> quryExperimName(String laboratory_id);
	//通过实验名查询所有的实验批次,并且有当前周的限制
	public List<Map<String, String>> quryExperims(String laboratory_id,String experim_id,String current_week);
	//预约实验室，确认批次
	public List<Map<String, String>> addLabExperim(ExperimBatch experimBatch);
	//查看教师自己的所有预约记录
	public List<Map<String, String>> quryAppointList(String staff_id);
	//教师取消预约记录
	public List<Map<String, String>> cancelAppoint(String staff_id,String id,String cancel_reason);
	//查看教师自己所有取消的记录
	public List<Map<String, String>> cancelAppointList(String staff_id);
	//删除已预约的记录
//	public List<Map<String, String>> cancelAppointList(String staff_id);
}
