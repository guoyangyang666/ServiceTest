package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service("TeacherMgr")
public class TeacherMgrImpl implements TeacherMgr {
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
	
	//查找教师的个人信息，入参是教师的工号
	@Override
	public List queryTeacherInfo(String staff_id) {
		List list = null;
		String sql = "SELECT s.id,s.staff_name,s.staff_sex,s.staff_phone,s.staff_adress,s.staff_cardnumber,s.staff_desc,r.ranks_name "
				+ "FROM t_staff s,t_ranks r "
				+ "where s.id = '"+staff_id+"' and s.staff_ranks = r.id";
		list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}
	//教师查看实验室预约批次，判定条件是管理员添加的批次
	public List queryApplyBatch() {
		List<Map<String, Object>> list = null;
		String sql ="select c.week,c.laboratory_id,c.course_name,c.batch,"
					+"(select j.laboratory_name from t_jiaoshiinfor j where j.id=c.laboratory_id) as laboratory_name,"
					+"(select e.status from t_teacher_experim e where e.course_name=c.course_name and e.batch=c.batch) as status "
				+"from t_course_time c "
				+"where c.type='2'";
		list = jdbcTemplate.queryForList(sql);
		return list;
	}
	@Override
	public void applyBatch() {
		// TODO Auto-generated method stub
		
	}


}
