package com.sinosoft.ie.hcmops.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.sinosoft.ie.hcmops.model.ExperimBatch;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		
		//查询实验室课表及添加的实验批次
		@RequestMapping(value = "/quryCourseExperim.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryCourseExperim(HttpServletRequest request,
				String laboratory_id,String current_week, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryCourseExperim(laboratory_id, current_week);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//查询实验室课表及添加的实验批次
		@RequestMapping(value = "/quryExperimName.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryExperimName(HttpServletRequest request,
				String laboratory_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryExperimName(laboratory_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//通过实验名查询所有的实验批次,当前周
		@RequestMapping(value = "/quryExperims.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryExperims(HttpServletRequest request,
				String laboratory_id,String experim_id,String current_week, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryExperims(laboratory_id, experim_id, current_week);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//教师根据课表添加实验批次
		@RequestMapping(value = "/addLabExperim.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String addLabExperim(HttpServletRequest request,
				String experim_id,String batch,String week, String start_times, String stop_times,
				String appoint_week, String staff_id, String laboratory_id,			
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			JSONObject callback = new JSONObject();
			ExperimBatch experimBatch = new ExperimBatch();
			String id = UUID.randomUUID().toString();
			experimBatch.setId(id);
			experimBatch.setExperim_id(experim_id);
			experimBatch.setBatch(batch);
			experimBatch.setWeek(week);
			experimBatch.setStart_times(start_times);
			experimBatch.setStop_times(stop_times);
			experimBatch.setAppoint_week(appoint_week);
			experimBatch.setLaboratory_id(laboratory_id);
			experimBatch.setStart_week(appoint_week);
			experimBatch.setLast_week(appoint_week);
			experimBatch.setStaff_id(staff_id);
			String course_id ="000000";
			experimBatch.setCourse_id(course_id);
			String status = "1";
			experimBatch.setStatus(status);//教师确认批次的状态，（1为已预约，2为取消，3为删除）
			String type = "2";
			experimBatch.setType(type);//类型，1为实验室的课，2为教师确认的批次，其他教师不可选			
			Integer experim_num = 0;//预约的人数，与实验室容量对比
			experimBatch.setExperim_num(experim_num);
			List list = teacherMgr.addLabExperim(experimBatch);		
			String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//查看教师自己的所有预约记录
		@RequestMapping(value = "/quryAppointList.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryAppointList(HttpServletRequest request,
				String staff_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryAppointList(staff_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		//教师取消预约记录
		@RequestMapping(value = "/cancelAppoint.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String cancelAppoint(HttpServletRequest request,
				String staff_id,String id,String cancel_reason, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.cancelAppoint(staff_id, id, cancel_reason);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
				
		//查看教师自己所有取消的记录
		@RequestMapping(value = "/cancelAppointList.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String cancelAppointList(HttpServletRequest request,
				String staff_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.cancelAppointList(staff_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		//教师查看学生预约的记录
		@RequestMapping(value = "/quryStuAppoint.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryStuAppoint(HttpServletRequest request,
				String staff_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryStuAppoint(staff_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		//教师根据选择的实验室id选择相应的实验项目
		@RequestMapping(value = "/quryExperim.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryExperim(HttpServletRequest request,
				String laboratory_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryExperim(laboratory_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		//教师根据选择的实验项目选择相应的批次
		@RequestMapping(value = "/quryBatch.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryBatch(HttpServletRequest request,
				String experim_id,String laboratory_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryBatch(experim_id, laboratory_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//首页，预约信息，显示所有教师预约的信息,只显示最新的6条
		@RequestMapping(value = "/quryTeacherAppoint.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryTeacherAppoint(HttpServletRequest request,
				 ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryTeacherAppoint();
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//所有的教师预约信息
		@RequestMapping(value = "/quryAllTeacherAppoint.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryAllTeacherAppoint(HttpServletRequest request,
				int current, int pageSize, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryAllTeacherAppoint(current, pageSize);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//查询周，1-18周
		@RequestMapping(value = "/quryWeek.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryWeek(HttpServletRequest request,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryWeek();
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//查询持续的小节
		@RequestMapping(value = "/quryLastHours.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryLastHours(HttpServletRequest request,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = teacherMgr.quryLastHours();
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
}
