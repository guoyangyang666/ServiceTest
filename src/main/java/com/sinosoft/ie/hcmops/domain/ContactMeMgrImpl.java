package com.sinosoft.ie.hcmops.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service("ContactMeMgr")
public class ContactMeMgrImpl implements ContactMeMgr {
	/**
     * spring提供的jdbc操作辅助类
     */
	 private JdbcTemplate jdbcTemplate;
		public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//查出所有的实验室及负责人的联系方式
	@Override
	public List<Map<String, String>> quryAllContact(int current, int pageSize) {
		int totalRecord;//总条数
		List list = null;
		List list1 = null;
		List<Map<String, String>> listMap = new ArrayList();
		String sql= "select * from t_jiaoshiinfor"; 
			
		try {
			list = jdbcTemplate.queryForList(sql);
			totalRecord = list.size();//计算出总条数							
			current = (current-1)*pageSize;//当前索引值	
			String sql1= "select j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum,s.staff_name,s.staff_phone from t_jiaoshiinfor j,t_staff s "
					+ "where j.staff_id = s.id limit "+current+","+pageSize+""; 
			list1 = jdbcTemplate.queryForList(sql1);
			//定义一个map
			Map mapTemp = new HashMap();
			//把需要的数据放到map里
			mapTemp.put("totalRecord", totalRecord);
			//把map放到list里的最后
			list1.add(mapTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list1;
	}

}
