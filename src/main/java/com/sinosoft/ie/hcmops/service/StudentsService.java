package com.sinosoft.ie.hcmops.service;

import java.util.List;

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
}
