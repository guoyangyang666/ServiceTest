package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

import com.sinosoft.ie.hcmops.model.Equip;
import com.sinosoft.ie.hcmops.model.ExperimBatch;
import com.sinosoft.ie.hcmops.model.Experimbatchs;
import com.sinosoft.ie.hcmops.model.LabInfo;
import com.sinosoft.ie.hcmops.model.NoClassTimes;
import com.sinosoft.ie.mpiws.model.PersonInfo;

/**
 * 实验室管理员
 * @author thinkpad
 *
 */
public interface AdminMgr {
	//修改密码
	public List updatePassword(String loginName, String loginPw);
	//查询实验室基本信息
	public List<Map<String, String>> quryLabInfo(String laboratory_id);
	//修改实验室基本信息
	public List<Map<String, String>> changeLabInfo(String laboratory_id,LabInfo labInfo);
//	public List addLabInfo(LabInfo LabInfo);
	//实验室管理员添加实验室无课课表
	public boolean addLabNoClass(NoClassTimes noClassTimes);
	//搜索本实验室的所有设备列表,入参是登陆的管理员工号，类型，实验室的id
	public List<Equip> getAllEquip(String type, String staff_id, String laboratory_id);
	//添加本实验室的设备
	public List<Map<String, String>> addEquip(Equip equip);
	//根据设备id和实验室id查数据
	public List queryLabInfo(String laboratory_id, String id);
	//根据设备id和实验室id删除数据
	public void deleteLabInfo(String id,String laboratory_id);
	//修改实验室设备基本信息
	public List<Map<String, String>> changeLabEquip(String laboratory_id,Equip equip,String id);
	//查看个人基本信息,入参是管理员工号
	public List queryLabAdminInfo(String staff_id);
	//查看实验室课表
	public List<Map<String, String>> quryAllCourse(String laboratory_id);	
	//添加的实验批次，新
	public List<Map<String, String>> addExperimbatchs(Experimbatchs experimbatchs);
	//查询的实验批次，新
	public List<Map<String, String>> quryExperimbatchs(String laboratory_id);
	//删除的实验批次，新
	public List<Map<String, String>> deleteExperimbatchs(String id);
	//查找所有的实验批次名
	public List<Map<String, String>> quryExperimbatchsName(String laboratory_id);
	//审核学生申请
	public List<Map<String, String>> changeStuAppoint(String id,String stu_id,String status,String fail_reason);
	//查看所有预约的学生（未审核的2）
	public List<Map<String, String>> quryStuAppoint(String laboratory_id);
	//根据开始课时（如第1节）数组i的值，星期（1,2,3,4,5）星期数，数组j的值，laboratory_id，week（当前周）查到id
	public List<Map<String, String>> quryTeacherAppointId(String week,String start_times,String laboratory_id,String appoint_week);
	
}
