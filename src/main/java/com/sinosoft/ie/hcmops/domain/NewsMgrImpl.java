package com.sinosoft.ie.hcmops.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.ie.hcmops.model.News;
import com.sinosoft.ie.hcmops.model.Notice;
/**
 * 实现新闻接口
 * @author thinkpad
 *
 */
@Service("NewsMgr")
public class NewsMgrImpl implements NewsMgr {
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

	//查询所有的新闻实现分页,current当前页，pageSize一页显示的条数
	@Override
	public List<Map<String, String>> quryAllNews(int current, int pageSize) {
		int totalRecord;//总条数
		List list = null;
		List list1 = null;
		List<Map<String, String>> listMap = new ArrayList();
		String sql= "select * from t_news where  del_status = 1 order by order_num desc"; 
		try {
			list = jdbcTemplate.queryForList(sql);
			totalRecord = list.size();//计算出总条数							
			current = (current-1)*pageSize;//当前索引值	
			String sql1= "select id,news_title,news_date from t_news where del_status = 1 order by order_num desc limit "+current+","+pageSize+""; 
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

	
	//根据新闻的id查询所有内容
	@Override
	public List<News> quryNews(String id) {
//		Interger pv_num=0;
//		Integer pv_num = pv_num+1;
//		String sql1 = "update t_news set pv_num = '"+pv_num+"' where id = '"+id+"'";
//		jdbcTemplate.execute(sql1);
		String sql = "select * from t_news where id = '"+id+"' and del_status = 1";
		List list = (List<News>)jdbcTemplate.query(sql, new BeanPropertyRowMapper(News.class));	
		return list;
	}

}
