package com.sinosoft.ie.hcmops.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.ie.hcmops.domain.StudentsMgrImpl;
import com.sinosoft.ie.hcmops.domain.TeacherMgrImpl;

import net.sf.json.JSONArray;

/**
 * 教师
 * @author thinkpad
 *
 */
@Controller
@RequestMapping("/teacher")
public class TeacherService {
	@Resource(name = "teacherMgr")
	private TeacherMgrImpl teacherMgr;
	
	
	//查看个人基本信息,入参是管理员工号
	@RequestMapping(value = "/queryTeacherInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String queryTeacherInfo(HttpServletRequest request,
			String staff_id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = teacherMgr.queryTeacherInfo(staff_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
		//教师查看预约批次
		@RequestMapping(value = "/queryApplyBach.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String queryApplyBach(HttpServletRequest request, HttpServletResponse resp,
				 ModelMap modelMap) {
			 List list = teacherMgr.queryApplyBatch();
			 String json = JSONArray.fromObject(list).toString();		 
			return json;		
		}
		//教师预约实验批次
		@RequestMapping(value = "/applyBach.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String applyBach(HttpServletRequest request, HttpServletResponse resp) {
			 Map<String, String[]> para = request.getParameterMap();
			 for (String key : para.keySet()) {
				   System.out.println("key= "+ key + " and value= " + para.get(key).toString());
				  }
			return null;		
		}
}
