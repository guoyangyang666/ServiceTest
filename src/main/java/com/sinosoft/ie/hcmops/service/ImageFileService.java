package com.sinosoft.ie.hcmops.service;

/*import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.ie.hcmops.domain.ImageFileMgr;
import com.sinosoft.ie.hcmops.model.ImageFile;
import com.sinosoft.ie.mpiws.model.PersonInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

*//**
 * 上传图片
 * @author guoyangyang
 *
 *//*
@Controller
@RequestMapping("/image")
public class ImageFileService {
	@Resource(name = "imageFileMgr")
	private ImageFileMgr imageFileMgr;
	
	@RequestMapping(value = "/addImageFile.do", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody String addImageFile(HttpServletRequest request,
			String image_id, String image_name, String image_url,
			ModelMap modelMap, HttpServletResponse resp) {		 			 
		ImageFile image = new ImageFile();
		
		
		return "1";
		
	}

	
}
package com.rookie.image.controller;
*/
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//import com.rookie.framework.util.RandomUtil;
import com.sinosoft.ie.hcmops.domain.ImageFileMgr;
//import com.rookie.image.po.ImageFile;
//import com.rookie.image.service.ImageUploadService;
import com.sinosoft.ie.hcmops.model.ImageFile;

/**
 * 文件上传
 * @introduction 
 * @author rookie
 * 创建日期：2016年1月7日
 */
@Controller
@RequestMapping("/image")
public class ImageFileService {
	
//	@Resource
//	private ImageUploadService imageUploadService;
	@Resource(name = "imageFileMgr")
	private ImageFileMgr imageFileMgr;
	
	private String allowSuffix = "jpg,png,gif,jpeg";//允许文件格式
    private long allowSize = 10000000L;//允许文件大小
    
    
	public String getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public long getAllowSize() {
		return allowSize;
	}

	public void setAllowSize(long allowSize) {
		this.allowSize = allowSize;
	}

	/*
	 * 图片存放的基本目录
	 */
	private static final String IMAGE_BASE="/upload";
	
	@RequestMapping("/multiupload")
	@ResponseBody
	public Map<String, String> upload(@RequestParam("file")MultipartFile[] files,String modelName,HttpServletRequest request){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			List<ImageFile> imageFiles = uploadimages(files,request,modelName);
			StringBuilder str = new StringBuilder("");
			if(imageFiles!=null||imageFiles.size()>0){
				int size = imageFiles.size();
				for(int i=0;i<size;i++){
					if(i==(size-1)){
						str.append(imageFiles.get(i).getId());
					}else{
						str.append(imageFiles.get(i).getId()+",");
					}
				}
			}
			resultMap.put("resultcode", "200");
			resultMap.put("imageId", str.toString());
			resultMap.put("resultmsg", "上传成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("resultcode", "400");
			resultMap.put("resultmsg", "上传失败");
		}
		return resultMap;
		
	}
	
	/**
	 * 单图片上传
	 * 返回图片的id
	 * @param file
	 * @param modelName
	 * @param request
	 * @return
	 */
//	@RequestMapping("/singleupload")
	@RequestMapping(value = "/singleupload.do", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map<String, String> upload(@RequestParam("file")MultipartFile file,String modelName,HttpServletRequest request){
		Map<String, String> resultMap = new HashMap<String, String>();
		String path = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath("/");
		try {
			ImageFile imageFile = uploadimage(file,path,realPath,modelName);
			resultMap.put("resultcode", "200");
			resultMap.put("imageId", imageFile.getId());
			resultMap.put("resultmsg", "上传成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("resultcode", "400");
			resultMap.put("resultmsg", "上传失败");
		}
		return resultMap;
		
	}
	
	/**
	 * 返回的是图片的存放地址
	 * @param file
	 * @param modelName
	 * @param request
	 * @return
	 */
	//@RequestMapping("/singleuploadurl")
	@RequestMapping(value = "/singleuploadurl.do", method = { RequestMethod.GET,RequestMethod.POST })

	@ResponseBody
	public Map<String, String> uploadUrl(@RequestParam("file")MultipartFile file,String modelName,HttpServletRequest request){
		Map<String, String> resultMap = new HashMap<String, String>();
		String path = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath("/");
		try {
			ImageFile imageFile = uploadimage(file,path,realPath,modelName);
			resultMap.put("resultcode", "200");
			resultMap.put("imageUrl", imageFile.getImage_url());
			resultMap.put("resultmsg", "上传成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("resultcode", "400");
			resultMap.put("resultmsg", "上传失败");
		}
		return resultMap;
		
	}
	
	/**
	 * 单文件上传逻辑
	 * 创建日期：2016年1月7日
	 * @author rookie
	 * @param modelName   图片所属模块，将这个名称当作存放目录的一部分
	 * @param path
	 * @param realPath
	 * @throws Exception 
	 */
	private ImageFile uploadimage(MultipartFile file,String path,String realPath,String modelName) throws Exception{
//        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        try {
        	ImageFile imageFile = new ImageFile();
        	//imageFile.setId(RandomUtil.getUUID());
        	String id = UUID.randomUUID().toString();
        	imageFile.setId(id);
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            int length = getAllowSuffix().indexOf(suffix);
            if(length == -1){
                throw new Exception("请上传允许格式的文件");
            }
            if(file.getSize() > getAllowSize()){
                throw new Exception("您上传的文件大小已经超出范围");
            }
            
            File destFile = new File(realPath+IMAGE_BASE+"/"+modelName);
            if(!destFile.exists()){
                destFile.mkdirs();
            }
            String fileNameNew = getCurrentDateSequen()+"."+suffix;
            imageFile.setImage_name(fileNameNew);
            imageFile.setCreate_time(new Date());
            imageFile.setImage_url(IMAGE_BASE+"/"+modelName+"/"+fileNameNew);
            File f = new File(destFile.getAbsoluteFile()+"/"+fileNameNew);
            file.transferTo(f);
            f.createNewFile();
            imageFileMgr.addImageFile(imageFile);
            return imageFile;
        } catch (Exception e) {
	        throw e;
	    }
	}
	/**
	 * 多文件处理逻辑
	 * 创建日期：2016年1月7日
	 * @author rookie
	 * explain：
	 */
	private List<ImageFile> uploadimages(MultipartFile[] files,HttpServletRequest request,String modelName) throws Exception{
		String path = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath("/");
		List<ImageFile> imageFiles = new ArrayList<ImageFile>();
		int size = files.length;
		for(int i=0;i<size;i++){
			ImageFile imageFile = uploadimage(files[i],path,realPath,modelName);
			imageFiles.add(imageFile);
		}
		return imageFiles;
	}
	public static String getCurrentDateSequen()
    {
   	 //格式
   	 SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
   	 //生成时间字符串
		 String cdtSequen = tempDate.format(new java.util.Date());
		 //生成结果字符串
		 String rtnStr = cdtSequen;
   	 return rtnStr;
    }

}

