package com.sinosoft.ie.hcmops.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class EquipService extends HttpServlet {
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
	//上传图片
		@RequestMapping(value = "/imageUp.do", method = { RequestMethod.GET,RequestMethod.POST })
		public @ResponseBody String imageUp(HttpServletRequest request,
				ModelMap modelMap, HttpServletResponse response) throws Exception {
			File tempPathFile = null;
			String relaFilePath = null;
//			String s = null;
//			String realFile = "";//真实路径
//			String relaFile = "";//相对路径
			String upPath = "d:";
			//ModelAndView model = new ModelAndView("jackson");
			//ServletFileUpload.isMultipartContent(request)
			 DiskFileItemFactory factory = new DiskFileItemFactory();
			 factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
	         factory.setRepository(tempPathFile);// 设置缓冲区目录
	         ServletFileUpload upload = new ServletFileUpload(factory);
	         upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
	         List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
	         Iterator<FileItem> i = items.iterator();
	         while (i.hasNext()) {
	              FileItem fi = (FileItem) i.next();
	              String fileName = fi.getName();
	              if (fileName != null) {
	                  File fullFile = new File(fi.getName());
	                  File savedFile = new File( upPath, fullFile.getName());
	                  fi.write(savedFile);
	                  relaFilePath = savedFile.getPath();
	              }
	           }
	        
			response.setContentType("text/html; charset=UTF-8");
			System.err.println("------------------");
			System.err.println(response);
			System.err.println("------------------");
			return "{"+"relaFilePath"+":"+relaFilePath+"}";		
		}
     
}