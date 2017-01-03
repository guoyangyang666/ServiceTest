package com.sinosoft.ie.hcmops.domain;

import java.util.ArrayList;
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
	public List<Map<String, String>> quryAllContact() {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
		String sql= "select j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum,s.staff_name,s.staff_phone from t_jiaoshiinfor j,t_staff s "
				+ "where j.staff_id = s.id"; 	
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}

}
