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

import com.sinosoft.ie.hcmops.domain.ContactMeMgrImpl;
import com.sinosoft.ie.hcmops.domain.EquipMgrImpl;

import net.sf.json.JSONArray;
/**
 * 联系我们
 * @author thinkpad
 *
 */
@Controller
@RequestMapping("/contact")
public class ContactMeService {
	@Resource(name = "contactMgr")
	private ContactMeMgrImpl contactMgr;
	
	//查询最新的两条公告，前台展示
	@RequestMapping(value = "/quryAllContact.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryAllContact(HttpServletRequest request,
			int current, int pageSize,ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = contactMgr.quryAllContact(current, pageSize);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
}
