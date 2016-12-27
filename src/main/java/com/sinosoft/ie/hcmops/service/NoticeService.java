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

import com.sinosoft.ie.hcmops.domain.AdminMgrImpl;
import com.sinosoft.ie.hcmops.domain.NoticeMgrImpl;

import net.sf.json.JSONArray;

/**
 * 新闻
 * @author thinkpad
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeService {
	@Resource(name = "noticeMgr")
	private NoticeMgrImpl noticeMgr;
	
	//查询最新的两条公告，前台展示
	@RequestMapping(value = "/quryTwoNotice.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String getAllEquip(HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = noticeMgr.quryTwoNotice();
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
//	//查询所有的公告
//		@RequestMapping(value = "/quryAllNotice.do", method = { RequestMethod.GET,RequestMethod.POST })
//		public @ResponseBody String quryAllNotice(HttpServletRequest request,
//				ModelMap modelMap, HttpServletResponse resp) {
//			String s = null;
//			 List list = noticeMgr.quryAllNotice();
//			 String json = JSONArray.fromObject(list).toString();
//			 System.out.println("-----------------------------------------");
//			 System.out.println(json);
//			 System.out.println("-----------------------------------------");
//			return json;		
//		}
	//查询所有的公告
			@RequestMapping(value = "/quryAllNotice.do", method = { RequestMethod.GET,RequestMethod.POST })
			public @ResponseBody String quryAllNotice(HttpServletRequest request,
					int current,int pageSize,
					ModelMap modelMap, HttpServletResponse resp) {
				String s = null;
				 List list = noticeMgr.quryAllNotice(current, pageSize);
				 String json = JSONArray.fromObject(list).toString();
				 System.out.println("-----------------------------------------");
				 System.out.println(json);
				 System.out.println("-----------------------------------------");
				return json;		
			}
		
		//根据公告的id查询所有内容
		@RequestMapping(value = "/quryNotice.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryNotice(HttpServletRequest request,
				String id, ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = noticeMgr.quryNotice(id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
}
