package com.sinosoft.ie.mpiws.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/login")
public class LoginService {
    private static Logger logger = LoggerFactory.getLogger(LoginService.class);
    
    private HttpSession session ;
    
    
    @Resource(name = "mpiAuthenticateService")
    private MpiAuthenticateService mpiAuthenticateService;
    
    public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
    
    //private JSONObject menuTree = null;
    
 
	private  Logger log = LoggerFactory.getLogger(LoginService.class);
 
    
    /**
     * 登录系统
     * @throws IOException 
     */
	@RequestMapping(value="/login.do", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody JSONObject login(HttpServletRequest request,HttpServletResponse response,String userName,String password,ModelMap modelMap) throws IOException{
		JSONObject js = new JSONObject();
		 JSONObject menuTree = new JSONObject();
		 response.reset();
		 response.setContentType("String");
        try {
        	//response.sendRedirect("/hc-mops/index.html");
        	//MpiAuthenticateService mpiAuthenticateService  = new MpiAuthenticateService();
        	System.out.println(mpiAuthenticateService.getPmsUrl());
        	js = mpiAuthenticateService.authenticate(request, userName, password);
        	String desc = (String) js.get("desc");
        	JSONObject data = js.getJSONObject("data");
        	String ticketId = data.getString("ticketId");
        	if(desc.equals("success")){
        		String tokenId = mpiAuthenticateService.getToken(ticketId).getJSONObject("data").getString("tokenId");
        		 menuTree = mpiAuthenticateService.getAuthorityMenuTree(tokenId, userName,"lfsms");
        		 session = request.getSession();
        		 session.setAttribute("menuTree", menuTree);
        		response.sendRedirect("/hc-mops/index.html");
        		return menuTree;
        	}else{
        		response.sendRedirect("/hc-mops/error.jsp");
        		return null ;
        	}
        } catch (Throwable e) {
            modelMap.put("error", "系统错误,请联系管理员!");
            response.sendRedirect("/hc-mops/error.jsp");
        }
		return menuTree;

    }
    /**
     * 动态获取菜单
     * @throws IOException 
     */
	@RequestMapping(value="/getMenuTree.do", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody JSONObject getMenuTree(HttpServletRequest request,HttpServletResponse resp) throws IOException{
	
		//String menuTree = "1";
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		// 下面那句是核心
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setDateHeader("Expires", 0);
		JSONObject menuTree = (JSONObject) request.getSession().getAttribute("menuTree");

		return menuTree;
	//	String jo = "";
//		try {
////			MedicalPractitioner practitioner = new MedicalPractitioner();
////			practitioner.setHealtherName("付国霖");
////			practitioner.setOrganizationCode("123123");
////			practitioner.setIdCardNo("152302199306290018");
////			ResponseMessage msg = ResponseMessage.createResponseMessage(true,"东城",practitioner);
//        	MedicalPractitioner practitioner = (MedicalPractitioner) request.getSession(true).getAttribute("practitioner");
//            if (practitioner == null || practitioner.equals("")) throw new Exception("userInfo is empty");
//            Map<String,String> map = ReadProperties.readProperties("/place.properties");
//            ResponseMessage msg = ResponseMessage.createResponseMessage(true,map.get("name"),practitioner);
//            jo =JSONObject.getString(msg);
//        } catch (Throwable e) {
//        	logger.error("gainLoginInfo",e);
//        	ResponseMessage msg = ResponseMessage.createResponseMessage(false,"查询失败",e);
//        	jo =JSONObject.getString(msg);
//        }
//		return jo;
    }

}
