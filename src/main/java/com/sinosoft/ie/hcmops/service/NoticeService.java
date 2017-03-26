package com.sinosoft.ie.hcmops.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sinosoft.ie.hcmops.domain.NoticeMgrImpl;
import com.sinosoft.ie.hcmops.model.ExperimBatch;
import com.sinosoft.ie.hcmops.model.Notice;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		
		//管理员取消某一个课，添加公告
		@RequestMapping(value = "/addNotice.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String addNotice(HttpServletRequest request,
				String notice_title,String notice_desc,String notice_detail, String notice_source,String dataId,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			JSONObject callback = new JSONObject();
			Notice notice = new Notice();
			String id = UUID.randomUUID().toString();
			notice.setId(id);
			notice.setNotice_title(notice_title);
			notice.setNotice_desc(notice_desc);
			notice.setNotice_detail(notice_detail);
			notice.setNotice_source(notice_source);
			String image_url="/images/person.png";
			notice.setImage_url(image_url);
			String del_status="1";
			notice.setDel_status(del_status);
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String notice_date=sdf.format(date);
			notice.setNotice_date(notice_date);
			List list = noticeMgr.addNotice(notice, dataId);	
			String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
}
