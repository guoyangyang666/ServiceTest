package com.sinosoft.ie.hcmops.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.ie.hcmops.domain.PersonMgrImpl;
import com.sinosoft.ie.hcmops.domain.StudentsMgrImpl;
import com.sinosoft.ie.hcmops.model.ExperimBatch;
import com.sinosoft.ie.hcmops.model.StuExperim;

import net.sf.json.JSONArray;
//验证登陆
@Controller
@RequestMapping("/students")
public class StudentsService {
	
	@Resource(name = "studentsMgr")
	private StudentsMgrImpl studentsMgr;
	
	@RequestMapping(value = "/login.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody String login(HttpServletRequest request,
			String id, String loginPw,String type, ModelMap modelMap,
			HttpServletResponse resp) {
		List list = studentsMgr.verifyPerson(id,loginPw,type);
		String json = JSONArray.fromObject(list).toString();
		System.out.print(json);
		return json;
	}
	
	//修改密码,用户的学号工号，要修改的密码，登陆类型，3为学生，2为教师
	@RequestMapping(value = "/updatePassword.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String getAllEquip(HttpServletRequest request,
			String number, String loginPw, String logintype,ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = studentsMgr.updatePassword(number, loginPw, logintype);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//查看个人基本信息,入参是管理员工号
	@RequestMapping(value = "/queryStudentInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String queryStudentInfo(HttpServletRequest request,
			String stu_id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = studentsMgr.queryStudentInfo(stu_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//学生预约之前先先查询是否已预约，
	@RequestMapping(value = "/quryStu.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryStu(HttpServletRequest request,
			String course_time_id,String stu_id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = studentsMgr.quryStu(course_time_id, stu_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//学生预约实验室
	@RequestMapping(value = "/addStuExperim.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String addStuExperim(HttpServletRequest request,
			String stu_id,String course_time_id,String laboratory_id,String staff_id,Integer experim_num,			
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		JSONObject callback = new JSONObject();
		StuExperim stuExperim = new StuExperim();
		String id = UUID.randomUUID().toString();
		stuExperim.setId(id);
		stuExperim.setStu_id(stu_id);
		stuExperim.setCourse_time_id(course_time_id);
		stuExperim.setLaboratory_id(laboratory_id);
		stuExperim.setStaff_id(staff_id);
		String status = "2";
		stuExperim.setStatus(status);//（1为已预约，2为取消，3为删除）		
		List list = studentsMgr.addStuExperim(stuExperim, experim_num);
		String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}

	//学生查询所有的能预约的实验室，并可查询：实验室名，教师名，实验名，上课时间，
	@RequestMapping(value = "/quryAllExperim.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryAllExperim(HttpServletRequest request,
			String current_week, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = studentsMgr.quryAllExperim(current_week);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//学生查询自己预约的实验室,包括已预约的和未审核的。
	@RequestMapping(value = "/quryStuExperim.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryStuExperim(HttpServletRequest request,
			String stu_id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = studentsMgr.quryStuExperim(stu_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//学生取消预约记录
	@RequestMapping(value = "/cancelAppoint.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String cancelAppoint(HttpServletRequest request,
			String stu_id,String id,String cancel_reason,Integer experim_num,String course_time_id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = studentsMgr.cancelAppoint(stu_id, stu_id, cancel_reason, experim_num, course_time_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//查看教师自己所有取消的记录
	@RequestMapping(value = "/cancelAppointList.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String cancelAppointList(HttpServletRequest request,
			String stu_id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = studentsMgr.cancelAppointList(stu_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
}
