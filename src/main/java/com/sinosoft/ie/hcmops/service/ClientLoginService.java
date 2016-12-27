package com.sinosoft.ie.hcmops.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.ie.hcmops.domain.PersonMgrImpl;

@Controller
@RequestMapping("/clientLogin")
public class ClientLoginService {
	private String zeroSupport = null;

	@Resource(name = "personMgr")
	private PersonMgrImpl personMgr;

	@RequestMapping(value = "/login.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody String login(HttpServletRequest request,
			String loginName, String password, ModelMap modelMap,
			HttpServletResponse resp) {
		String LoginName = null;
		String name = null;
		String hospital = null;
		String orgCode = null;
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		// 下面那句是核心
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setDateHeader("Expires", 0);
		String result = null;
		String operId = null;
		List list = personMgr.verifyPerson(loginName, password);
		if (list.size() == 0) {
			result = "0";
		} else {
			String json = JSONArray.fromObject(list).toString();
			//result =  "{ \"data\":" + json + "}";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String LoginTime = df.format(new Date());
			JSONArray js = JSONArray.fromObject(list);
			
			String  operIdTemp = JSONArray.fromObject(list).getString(0);
			JSONObject myJsonObject = (JSONObject) new JSONObject().parse(operIdTemp);
			operId = myJsonObject.getString("operId");
			
		    String nameTemp = JSONArray.fromObject(list).getString(0);
		    JSONObject myJsonObject1 = (JSONObject) new JSONObject().parse(nameTemp);
		    name = myJsonObject1.getString("name");
		    
		    String orgCodeTemp = JSONArray.fromObject(list).getString(0);
		    JSONObject myJsonObject3 = (JSONObject) new JSONObject().parse(orgCodeTemp);
		    orgCode = myJsonObject3.getString("HOS_ORG_CODE");
		    
		    String  hospitalTemp = JSONArray.fromObject(list).getString(0);
			JSONObject myJsonObject2 = (JSONObject) new JSONObject().parse(hospitalTemp);
			hospital = myJsonObject2.getString("hospital");
			
			String LoginnameTemp = JSONArray.fromObject(list).getString(0);
			JSONObject myJsonObject4 = (JSONObject) new JSONObject().parse(LoginnameTemp);
			loginName = myJsonObject4.getString("loginName");
		    personMgr.addLoginLog(operId, loginName, LoginTime);
			//result = JSONArray.fromObject(list).toString();
			String temp = "{\"hospital\":\""+hospital+"\",\"name\":\""+name+"\",\"LoginTime\":\""+LoginTime+"\",\"LoginName\":\""+loginName+"\",\"orgCode\":\""+orgCode+"\"}";
			result = "{ \"data\":" +"["+ temp +"]"+ "}";
		}
		System.out.println(result);
		return result;
	}
		@RequestMapping(value = "/resetPassword.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String resetPassword(HttpServletRequest request,String certId, String name, String password ,ModelMap modelMap,HttpServletResponse resp) {
			String result = null;
			List list = personMgr.verifyPersonExist(name, certId);
			if(list.size()==0){                    //用户不存在
				result = "0";
			}else{
				personMgr.resetPassword(password, certId);
				result = "1";
			}
			return result;
	}
}

