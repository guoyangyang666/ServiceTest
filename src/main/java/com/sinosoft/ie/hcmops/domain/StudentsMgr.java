package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

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
}
