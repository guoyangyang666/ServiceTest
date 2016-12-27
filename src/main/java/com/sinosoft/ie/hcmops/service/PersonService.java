package com.sinosoft.ie.hcmops.service;

import com.sinosoft.ie.hcmops.domain.PersonMgrImpl;
import com.sinosoft.ie.hcmops.webUtil.WebUtil;
import com.sinosoft.ie.mpiws.model.PersonInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.UUID;

//import com.sinosoft.ie.dhow.core.dao.CommonBaseDao;

@Controller
@RequestMapping("/Person")
public class PersonService {

	@Resource(name = "personMgr")
	private PersonMgrImpl personMgr;

	@RequestMapping(value = "/addPerson.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String addPerson(HttpServletRequest request,
			String name, String certId, String loginName, String phone,
			String sex, String hospital, String department, String job,
			String password, ModelMap modelMap, HttpServletResponse resp) {
		 	resp.setHeader("Pragma", "no-cache");
		 	resp.setHeader("Cache-Control", "no-cache");
		 	resp.setHeader("Access-Control-Allow-Origin", "*");
		 	resp.setDateHeader("Expires", 0); System.out.println(name);
		 
		JSONObject callback = new JSONObject();
		PersonInfo person = new PersonInfo();
		String id = UUID.randomUUID().toString();
		person.setId(id);
		person.setCertId(certId);
		person.setLoginName(loginName);
		person.setName(name);
		person.setPhone(phone);
		person.setSex(sex);
		person.setJob(job);
		person.setHospital(hospital);
		person.setPassword(password);
		personMgr.addPerson(person);
		List list = personMgr.getAllPerson();
		String json = JSONArray.fromObject(list).toString();
		System.out.println(json);
		return "{ \"user\":" + json + "}";
		
	}

	@RequestMapping(value = "/queryAllPerson.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody String queryAllPerson(HttpServletRequest request,
			HttpServletResponse resp) {
		 resp.setHeader("Pragma", "no-cache");
		 resp.setHeader("Cache-Control", "no-cache");
		 //下面那句是核心
		 resp.setHeader("Access-Control-Allow-Origin", "*");
		 resp.setDateHeader("Expires", 0);
		 List list = this.personMgr.getAllPerson();
		 String temp = list.toString();
		  String json = JSONArray.fromObject(list).toString();
		// System.out.println(json);
		return "{ \"users\":" + json + "}";
	}
	@RequestMapping(value = "/cancelPerson.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody String cancelPerson(HttpServletRequest request,String operId,
			HttpServletResponse resp) {
		 resp.setHeader("Pragma", "no-cache");
		 resp.setHeader("Cache-Control", "no-cache");
		 //下面那句是核心
		 resp.setHeader("Access-Control-Allow-Origin", "*");
		 resp.setDateHeader("Expires", 0);
		 personMgr.cancelPerson(operId);
//		 String temp = list.toString();
//		  String json = JSONArray.fromObject(list).toString();
		// System.out.println(json);
		//return "{ \"users\":" + json + "}";
		 return "1";
	}
	@RequestMapping(value = "/showHospital.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody String showHospital(HttpServletRequest request,
			HttpServletResponse resp){
		WebUtil wu = new WebUtil();
		String IP = wu.getIpAddr(request);
		 List list = personMgr.showHospital(IP);
		 String json = JSONArray.fromObject(list).toString();
		 return "{ \"hospital\":" + json + "}";
		
	}
}
