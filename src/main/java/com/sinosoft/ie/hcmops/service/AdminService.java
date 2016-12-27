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

import com.sinosoft.ie.hcmops.domain.AdminMgrImpl;
import com.sinosoft.ie.hcmops.domain.PersonMgrImpl;
import com.sinosoft.ie.hcmops.model.Equip;
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
	
	@RequestMapping(value = "/addLabInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String addPerson(HttpServletRequest request,
			String id, String laboratory_name, String category_id, String laboratory_adress,
			String laboratory_adressnum, String staff_id, String laboratory_renshu, String laboratory_desc,
			ModelMap modelMap, HttpServletResponse resp) {
		 
		JSONObject callback = new JSONObject();
		LabInfo labInfo = new LabInfo();
		labInfo.setId(id);
		labInfo.setLaboratory_name(laboratory_name);
		labInfo.setCategory_id(category_id);
		labInfo.setLaboratory_adress(laboratory_adress);
		labInfo.setLaboratory_adressnum(laboratory_adressnum);
		labInfo.setStaff_id(staff_id);
		labInfo.setLaboratory_renshu(laboratory_renshu);
		labInfo.setLaboratory_desc(laboratory_desc);
		adminMgr.addLabInfo(labInfo);
		List list = adminMgr.addLabInfo(labInfo);
		String json = JSONArray.fromObject(list).toString();
		System.out.println(json);
		return json;
		
	}
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
		List list = adminMgr.queryLabInfo(id);
		if(list.size()>0){
			s = "已有该设备编号";
		}else{
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
			boolean bl = adminMgr.addEquip(equip);
			if(bl){
				s = "添加成功";
			}else{
				s = "添加失败";
			}
		}	
//		String json = JSONArray.fromObject(s).toString();
//		System.out.println(json);
		return s;
		//System.out.println(json);
		///return "{ 'test' : 'OK'}";		
	}
	//搜索本实验室的所有设备列表
		@RequestMapping(value = "/deleteLabInfo.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String getAllEquip(HttpServletRequest request,
				String laboratory_id, String id,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			adminMgr.deleteLabInfo(id, laboratory_id);
			
			return "1";		
		}
	
}
