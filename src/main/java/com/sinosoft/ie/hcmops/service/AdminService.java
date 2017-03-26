package com.sinosoft.ie.hcmops.service;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.sinosoft.ie.hcmops.domain.AdminMgrImpl;
import com.sinosoft.ie.hcmops.domain.PersonMgrImpl;
import com.sinosoft.ie.hcmops.model.Equip;
import com.sinosoft.ie.hcmops.model.ExperimBatch;
import com.sinosoft.ie.hcmops.model.Experimbatchs;
import com.sinosoft.ie.hcmops.model.LabInfo;
import com.sinosoft.ie.hcmops.model.NoClassTimes;
import com.sinosoft.ie.mpiws.model.PersonInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//实验室管理员服务
@Controller
@RequestMapping("/labAdmin")
public class AdminService {

	@Resource(name = "adminMgr")
	private AdminMgrImpl adminMgr;
	
	@RequestMapping(value = "/updatePassword.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody String login(HttpServletRequest request,
			String loginName, String loginPw, ModelMap modelMap,
			HttpServletResponse resp) {
		List list = adminMgr.updatePassword(loginName, loginPw);
		//把list列表格式化
		String json = JSONArray.fromObject(list).toString();
		System.out.print(json);
		return json;
	}
	
//	@RequestMapping(value = "/addLabInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
//	public @ResponseBody String addPerson(HttpServletRequest request,
//			String id, String laboratory_name, String category_id, String laboratory_adress,
//			String laboratory_adressnum, String staff_id, String laboratory_renshu, String laboratory_desc,
//			ModelMap modelMap, HttpServletResponse resp) {
//		 
//		JSONObject callback = new JSONObject();
//		LabInfo labInfo = new LabInfo();
//		labInfo.setId(id);
//		labInfo.setLaboratory_name(laboratory_name);
//		labInfo.setCategory_id(category_id);
//		labInfo.setLaboratory_adress(laboratory_adress);
//		labInfo.setLaboratory_adressnum(laboratory_adressnum);
//		labInfo.setStaff_id(staff_id);
//		labInfo.setLaboratory_renshu(laboratory_renshu);
//		labInfo.setLaboratory_desc(laboratory_desc);
//		adminMgr.addLabInfo(labInfo);
//		List list = adminMgr.addLabInfo(labInfo);
//		String json = JSONArray.fromObject(list).toString();
//		System.out.println(json);
//		return json;
//		
//	}
	
	//查询实验室基本信息
	@RequestMapping(value = "/quryLabInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryLabInfo(HttpServletRequest request,
			String laboratory_id,
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = adminMgr.quryLabInfo(laboratory_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//修改实验室基本信息
	@RequestMapping(value = "/changeLabInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String changeLabInfo(HttpServletRequest request,
			String laboratory_id,String laboratory_adress,
			String laboratory_adressnum, String laboratory_renshu, String laboratory_desc,
			ModelMap modelMap, HttpServletResponse resp) {
		JSONObject callback = new JSONObject();
		LabInfo labInfo = new LabInfo();
		labInfo.setLaboratory_adress(laboratory_adress);
		labInfo.setLaboratory_adressnum(laboratory_adressnum);
		labInfo.setLaboratory_renshu(laboratory_renshu);
		labInfo.setLaboratory_desc(laboratory_desc);
		List list = adminMgr.changeLabInfo(laboratory_id, labInfo);
		String json = JSONArray.fromObject(list).toString();
		System.out.println(json);
		return json;	
	}
	
	//实验室管理员添加实验室无课课表
	@RequestMapping(value = "/addLabNoClass.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String addLabInfo(HttpServletRequest request,
			String laboratory_id, String times_index, String staff_id,
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		JSONObject callback = new JSONObject();
		NoClassTimes noClassTimes = new NoClassTimes();
		String id = UUID.randomUUID().toString();
		noClassTimes.setId(id);
		System.out.println(id);
		noClassTimes.setLaboratory_id(laboratory_id);
		noClassTimes.setTimes_index(times_index);
		noClassTimes.setStaff_id(staff_id);
		boolean bl = adminMgr.addLabNoClass(noClassTimes);
		if(bl){
			s = "课程添加成功";
		}else{
			s = "课程添加失败";
		}
		System.out.println(s);
		return s;		
	}
	//搜索本实验室的所有设备列表
	@RequestMapping(value = "/getAllEquip.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String getAllEquip(HttpServletRequest request,
			String laboratory_id, String type, String staff_id,
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = adminMgr.getAllEquip(type, staff_id, laboratory_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//添加本实验室的设备
	@RequestMapping(value = "/addEquip.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String addEquip(HttpServletRequest request,
			String id, String equip_name, String equip_model, String unit_price,
			String equip_number, String storage_time, String producer, String application,
			String equip_image_one, String equip_image_two, String laboratory_id, String staff_id,
			String type, String equip_desc,
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		JSONObject callback = new JSONObject();
		List list = adminMgr.queryLabInfo(laboratory_id, id);		
		Equip equip = new Equip();
		equip.setId(id);
		equip.setEquip_name(equip_name);
		equip.setEquip_model(equip_model);
		equip.setUnit_price(unit_price);
		equip.setEquip_number(equip_number);
		equip.setStorage_time(storage_time);
		equip.setProducer(producer);
		equip.setApplication(application);
		equip.setEquip_image_one(equip_image_one);
		equip.setEquip_image_two(equip_image_two);
		equip.setLaboratory_id(laboratory_id);
		equip.setStaff_id(staff_id);
		equip.setType(type);
		equip.setEquip_desc(equip_desc);
		List list1 = adminMgr.addEquip(equip);
		String json = JSONArray.fromObject(list1).toString();
		System.out.println(json);
		return json;	
		
	}
	//根据设备id查数据
	@RequestMapping(value = "/queryLabInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String queryLabInfo(HttpServletRequest request,
			String id, String laboratory_id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = adminMgr.queryLabInfo(laboratory_id, id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	//删除本实验室的设备列表
		@RequestMapping(value = "/deleteLabInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String getAllEquip(HttpServletRequest request,
				String laboratory_id, String id,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			adminMgr.deleteLabInfo(id, laboratory_id);
			
			return "1";		
		}
		
	//修改实验室设备基本信息
	@RequestMapping(value = "/changeLabEquip.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String changeLabEquip(HttpServletRequest request,
			String laboratory_id,String id,
			String equip_model, String unit_price, String equip_number,String producer, String equip_image_one, String application, String equip_desc,
			ModelMap modelMap, HttpServletResponse resp) {
		JSONObject callback = new JSONObject();
		Equip equip = new Equip();
		equip.setEquip_model(equip_model);
		equip.setUnit_price(unit_price);
		equip.setEquip_number(equip_number);
		equip.setProducer(producer);
		equip.setEquip_image_one(equip_image_one);
		equip.setApplication(application);
		equip.setEquip_desc(equip_desc);
		List list = adminMgr.changeLabEquip(laboratory_id, equip, id);
		String json = JSONArray.fromObject(list).toString();
		System.out.println(json);
		return json;	
	}	
		
		//查看个人基本信息,入参是管理员工号
		@RequestMapping(value = "/queryLabAdminInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String queryLabAdminInfo(HttpServletRequest request,
				String staff_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = adminMgr.queryLabAdminInfo(staff_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//查看实验室课表
		@RequestMapping(value = "/quryAllCourse.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryAllCourse(HttpServletRequest request,
				String laboratory_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = adminMgr.quryAllCourse(laboratory_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		
		
		//添加本实验批次
		@RequestMapping(value = "/addExperimbatchs.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String addExperimbatchs(HttpServletRequest request,
				String experim_id, String batch, String start_week,
				String last_week, String laboratory_id,			
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			JSONObject callback = new JSONObject();
			Experimbatchs experimbatchs = new Experimbatchs();
			String id = UUID.randomUUID().toString();
			experimbatchs.setId(id);
			experimbatchs.setExperim_id(experim_id);
			experimbatchs.setBatch(batch);
			experimbatchs.setStart_week(start_week);
			experimbatchs.setLast_week(last_week);
			experimbatchs.setLaboratory_id(laboratory_id);
			String status = "1";
			experimbatchs.setStatus(status);
			List list = adminMgr.addExperimbatchs(experimbatchs);		
			String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		//查询的实验批次，新
		@RequestMapping(value = "/quryExperimbatchs.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryExperimbatchs(HttpServletRequest request,
				String laboratory_id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = adminMgr.quryExperimbatchs(laboratory_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		//删除本实验室的设备列表
		@RequestMapping(value = "/deleteExperimbatchs.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String deleteExperimbatchs(HttpServletRequest request,
				String id,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			List list = adminMgr.deleteExperimbatchs(id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		//查找所有的实验批次名
		@RequestMapping(value = "/quryExperimbatchsName.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryExperimbatchsName(HttpServletRequest request,
				String laboratory_id,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			List list = adminMgr.quryExperimbatchsName(laboratory_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		//审核学生申请
		@RequestMapping(value = "/changeStuAppoint.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String changeStuAppoint(HttpServletRequest request,
				String stu_id,String id,
				String status, String fail_reason,
				ModelMap modelMap, HttpServletResponse resp) {
			JSONObject callback = new JSONObject();
			List list = adminMgr.changeStuAppoint(id, stu_id, status, fail_reason);
			String json = JSONArray.fromObject(list).toString();
			System.out.println(json);
			return json;	
		}	
		
		//查看所有预约的学生（未审核的2）
		@RequestMapping(value = "/quryStuAppoint.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryStuAppoint(HttpServletRequest request,
				String laboratory_id,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			List list = adminMgr.quryStuAppoint(laboratory_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
		
		//查看所有预约的学生（未审核的2）
		@RequestMapping(value = "/quryTeacherAppointId.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryTeacherAppointId(HttpServletRequest request,
				String laboratory_id,String week,String start_times,String appoint_week,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			List list = adminMgr.quryTeacherAppointId(week, start_times, laboratory_id, appoint_week);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
				
}
