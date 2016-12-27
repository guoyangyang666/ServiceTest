package com.sinosoft.ie.mpiws.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.ie.hcmops.md5.Md5;

//import com.sinosoft.ie.mpiws.domain.MedicalPractitionerMgr;
//import com.sinosoft.ie.mpiws.domain.MedicalPractitionerMgrImpl;

/*@Controller
@RequestMapping("/mpiAuthenticateService")*/
public class MpiAuthenticateService {
	private static Logger logger = LoggerFactory
			.getLogger(MpiAuthenticateService.class);

	// @Resource(name = "MedicalPractitionerMgr")
	// private MedicalPractitionerMgr medicalPractitionerMgr;
	public String pmsUrl;

	public MpiAuthenticateService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPmsUrl() {
		return pmsUrl;
	}

	public void setPmsUrl(String pmsUrl) {
		this.pmsUrl = pmsUrl;
	}

	/**
	 * 获取权限系统信息（认证）
	 * 
	 * @throws IOException
	 */
//	@RequestMapping(value = "/authenticate.do", method = { RequestMethod.GET,
//			RequestMethod.POST })
	// public @ResponseBody JSONObject authenticate(HttpServletRequest
	// request,ModelMap modelMap) throws IOException
	//public @ResponseBody JSONObject authenticate(HttpServletRequest request,
	public JSONObject authenticate(HttpServletRequest request,
		String userName, String password) throws IOException {
		HttpURLConnection connection = null;
		JSONObject callback = new JSONObject();
		try {
			StringBuilder sbr = new StringBuilder();
			// 用户名
			sbr.append("userName=" + userName + "");
			// sbr.append("userName = pms.user");
			sbr.append('&');
			// 密码
			String md5Pwd = Md5.md5(password);

			sbr.append("password=" + md5Pwd + "");
			// sbr.append("password=ab9b5a2bbe7e2f5d083892b263d9c340");
			byte[] data = sbr.toString().getBytes("UTF-8");
			// Create connection
			String pathUrl = pmsUrl + "/service/AccessProviderService/authenticate.ws";
			URL url = new URL(pathUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			connection.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			connection.setRequestProperty("userName", "pms.user");
			connection.setRequestProperty("password",
					"ab9b5a2bbe7e2f5d083892b263d9c340");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// Send request
			OutputStream os = connection.getOutputStream();
			os.write(data);
			os.flush();
			// Get Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			callback = JSONObject.fromObject(response.toString());
			Map<String, String> map = ReadProperties
					.readProperties("/place.properties");
			callback.put("placeName", map.get("name"));
			rd.close();
			os.close();
		} catch (Throwable e) {
			logger.error("authenticate", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return callback;
	}

	// public String getAuthorisedMenuTree(String userName , String menuId)
	// throws Exception {
	// String menuTree = "";
	// try {
	// String pathUrl = "http://192.168.10.87:8082/pms" +
	// "/service/AccessProviderService/getAuthorityMenuTree.ws?userName=" +
	// userName + "&menuId=" + menuId;
	// // 建立连接
	// URL url = new URL(pathUrl);
	// HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	//
	// // //设置连接属性
	// httpConn.setDoOutput(true);
	// httpConn.setDoInput(true);
	// httpConn.setUseCaches(false);
	// httpConn.setRequestMethod("GET");
	//
	// // 获得响应状态
	// int responseCode = httpConn.getResponseCode();
	//
	// if (HttpURLConnection.HTTP_OK == responseCode) {
	// StringBuffer sb = new StringBuffer();
	// String readLine;
	// BufferedReader responseReader;
	// responseReader = new BufferedReader(new
	// InputStreamReader(httpConn.getInputStream(), "UTF-8"));
	// while ((readLine = responseReader.readLine()) != null) {
	// sb.append(readLine);
	// }
	// responseReader.close();
	//
	// JSONObject menuData = new JSONObject();
	// menuData = JSONObject.fromObject(sb.toString());
	// if(menuData.has("result")){
	// if(menuData.getBoolean("result")) menuTree = menuData.getString("data");
	// }
	// }
	// } catch (Exception ex) {
	// throw ex;
	// }
	// return menuTree;
	// }

	public  JSONObject getToken(String ticketId)throws IOException {
		HttpURLConnection connection = null;
		JSONObject callback = new JSONObject();
		try {
			StringBuilder sbr = new StringBuilder();
			// 用户名
			sbr.append("ticketId=" + ticketId + "");
			// sbr.append("userName = pms.user");
			// sbr.append("password=ab9b5a2bbe7e2f5d083892b263d9c340");
			byte[] data = sbr.toString().getBytes("UTF-8");
			// Create connection
			String pathUrl = pmsUrl + "/service/AccessProviderService/getToken.ws";
			URL url = new URL(pathUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			connection.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			connection.setRequestProperty("userName", "pms.user");
			connection.setRequestProperty("password",
					"ab9b5a2bbe7e2f5d083892b263d9c340");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// Send request
			OutputStream os = connection.getOutputStream();
			os.write(data);
			os.flush();
			// Get Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			callback = JSONObject.fromObject(response.toString());
			Map<String, String> map = ReadProperties
					.readProperties("/place.properties");
			callback.put("placeName", map.get("name"));
			rd.close();
			os.close();
		} catch (Throwable e) {
			logger.error("authenticate", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return callback;
	}

	public  JSONObject getAuthorityMenuTree(String tokenId,String userName, String menuId) throws IOException {
		HttpURLConnection connection = null;
		JSONObject callback = new JSONObject();
		try {
			StringBuilder sbr = new StringBuilder();
			// 用户名
			sbr.append("tokenId=" + tokenId + "");
			sbr.append('&');
			sbr.append("userName=" + userName + "");
			sbr.append('&');
			sbr.append("menuId=" + menuId + "");
			byte[] data = sbr.toString().getBytes("UTF-8");
			String pathUrl = pmsUrl + "/service/AccessProviderService/getAuthorityMenuTree.ws";
			// Create connection
			URL url = new URL(pathUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			connection.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			connection.setRequestProperty("userName", "pms.user");
			connection.setRequestProperty("password",
					"ab9b5a2bbe7e2f5d083892b263d9c340");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// Send request
			OutputStream os = connection.getOutputStream();
			os.write(data);
			os.flush();
			// Get Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			callback = JSONObject.fromObject(response.toString());
			Map<String, String> map = ReadProperties
					.readProperties("/place.properties");
			callback.put("placeName", map.get("name"));
			rd.close();
			os.close();
		} catch (Throwable e) {
			logger.error("authenticate", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return callback;
	}

}
