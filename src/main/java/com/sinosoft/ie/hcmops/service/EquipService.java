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

import com.sinosoft.ie.hcmops.domain.EquipMgrImpl;
import com.sinosoft.ie.hcmops.domain.NewsMgrImpl;

import net.sf.json.JSONArray;

/**
 * 首页设备展示
 * @author guoyangyang
 *
 */
@Controller
@RequestMapping("/equip")
public class EquipService {
	@Resource(name = "equipMgr")
	private EquipMgrImpl equipMgr;
	
	//查询最新的两条公告，前台展示
		@RequestMapping(value = "/quryFiveEquip.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryFiveEquip(HttpServletRequest request,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = equipMgr.quryFiveEquip();
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
	//查询所有的新闻，分页
	@RequestMapping(value = "/quryAllEquip.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryAllEquip(HttpServletRequest request,
			int current,int pageSize,
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = equipMgr.quryAllEquip(current, pageSize);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
		
	//根据设备的id所有内容
	@RequestMapping(value = "/quryEquip.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryEquip(HttpServletRequest request,
			String id, ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = equipMgr.quryEquip(id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//查询最新的两条公告，前台展示
	@RequestMapping(value = "/quryAllLab.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryAllLab(HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = equipMgr.quryAllLab();
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	
	//根据实验室id查询出设备及其详细信息
	@RequestMapping(value = "/quryLab.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String quryLab(HttpServletRequest request,
			String laboratory_id,ModelMap modelMap, HttpServletResponse resp) {
		String s = null;
		 List list = equipMgr.quryLab(laboratory_id);
		 String json = JSONArray.fromObject(list).toString();
		 System.out.println("-----------------------------------------");
		 System.out.println(json);
		 System.out.println("-----------------------------------------");
		return json;		
	}
	//查询所有的新闻，分页
		@RequestMapping(value = "/quryAllLabEquip.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String quryAllLabEquip(HttpServletRequest request,
				int current,int pageSize,String laboratory_id,
				ModelMap modelMap, HttpServletResponse resp) {
			String s = null;
			 List list = equipMgr.quryAllLabEquip(current, pageSize, laboratory_id);
			 String json = JSONArray.fromObject(list).toString();
			 System.out.println("-----------------------------------------");
			 System.out.println(json);
			 System.out.println("-----------------------------------------");
			return json;		
		}
}
