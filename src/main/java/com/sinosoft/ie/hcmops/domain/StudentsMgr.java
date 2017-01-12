package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

import com.sinosoft.ie.hcmops.model.ExperimBatch;
import com.sinosoft.ie.hcmops.model.StuExperim;
import com.sinosoft.ie.hcmops.model.StudentsStu;
import com.sinosoft.ie.mpiws.model.PersonInfo;

/**
 * 学生接口
 * @author thinkpad
 *
 */
public interface StudentsMgr {
	//验证登陆接口
	public List verifyPerson(String id,String loginPw,String type);//用户名，密码，用户类型
	//修改密码,用户的学号工号，要修改的密码，登陆类型，3为学生，2为教师
	public List<Map<String, String>> updatePassword(String number, String loginPw, String logintype);
	//查找学生的个人信息，入参是学生的学号
	public List queryStudentInfo(String stu_id);
	//学生预约实验室
	public List<Map<String, String>> addStuExperim(StuExperim stuExperim,Integer experim_num);
	//学生查询所有的能预约的实验室，并可查询：实验室名，教师名，实验名，上课时间，
	public List<Map<String, String>> quryAllExperim(String current_week);
	//学生查询自己预约的实验室,包括已预约的和未审核的。
	public List<Map<String, String>> quryStuExperim(String stu_id);
}
