package com.sinosoft.ie.hcmops.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.ie.hcmops.model.Equip;
import com.sinosoft.ie.hcmops.model.Notice;
@Service("NoticeMgr")
public class NoticeMgrImpl implements NoticeMgr {
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
	
	//查询最新的两条公告，前台展示
	@Override
	public List<Map<String, String>> quryTwoNotice() {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
		String sql= "select id,notice_title,notice_desc,notice_date from t_notice where  del_status = 1 order by order_num desc limit 0,2"; 
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	
//	//查询所有的公告
//	@Override
//	public List<Map<String, String>> quryAllNotice() {
//		List list = null;
//		List<Map<String , String>> listMap = new ArrayList();
//		String sql= "select id,notice_title,notice_desc,notice_date,image_url from t_notice where  del_status = 1 order by order_num desc"; 
//		try {
//			list = jdbcTemplate.queryForList(sql);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.err.println(list);
//		return list;
//	}
	//查询所有的公告
	@Override
	public List<Map<String, String>> quryAllNotice(int current,int pageSize) {
		int totalRecord;//总条数
		List list = null;
		List list1 = null;
		List<Map<String , String>> listMap = new ArrayList();
		String sql= "select * from t_notice where  del_status = 1 order by order_num desc"; 
		try {
			list = jdbcTemplate.queryForList(sql);
			totalRecord = list.size();//计算出总条数							
			current = (current-1)*pageSize;//当前索引值	
			System.out.println(current);
			System.out.println("-----------------------------------------");
			System.out.println(pageSize);
			String sql1= "select id,notice_title,notice_desc,notice_date,image_url from t_notice where del_status = 1 order by order_num desc limit "+current+","+pageSize+""; 
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
		System.err.println(list1);
		return list1;
	}
	
	
	//根据公告的id查询所有内容
	@Override
	public List<Notice> quryNotice(String id) {
		String sql = "select * from t_notice where id = '"+id+"' and del_status = 1";
		List list = (List<Notice>)jdbcTemplate.query(sql, new BeanPropertyRowMapper(Notice.class));
		
		return list;
	}

}
