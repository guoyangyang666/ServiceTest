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

import com.sinosoft.ie.hcmops.domain.NewsMgrImpl;
import com.sinosoft.ie.hcmops.domain.NoticeMgrImpl;

import net.sf.json.JSONArray;

/**
 * 新闻
 * @author thinkpad
 *
 */
@Controller
@RequestMapping("/news")
public class NewsService {
	@Resource(name = "newsMgr")
	private NewsMgrImpl newsMgr;
	
	//查询所有的新闻，分页
	@RequestMapping(value = "/quryAllNews.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryAllNews(HttpServletRequest request,
			int current,int pageSize,
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = newsMgr.quryAllNews(current, pageSize);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//根据新闻的id查询所有内容
	@RequestMapping(value = "/quryNews.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryNews(HttpServletRequest request,
			String id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = newsMgr.quryNews(id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
}
