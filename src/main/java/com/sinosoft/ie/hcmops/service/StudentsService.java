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
}
