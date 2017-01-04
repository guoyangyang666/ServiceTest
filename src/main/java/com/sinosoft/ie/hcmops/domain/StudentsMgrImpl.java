package com.sinosoft.ie.hcmops.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sinosoft.ie.hcmops.model.StudentsStu;
import com.sinosoft.ie.mpiws.model.PersonInfo;
/**
 * 实现登陆
 * @author thinkpad
 *
 */
@Service("StudentsMgr")
public class StudentsMgrImpl implements StudentsMgr {
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
	@Override
	public List verifyPerson(String id, String loginPw, String type) {
		List list = null;
		if(type.equals("1")){
			String sql = "SELECT user_name,user_pwd,staff_id,type,laboratory_id FROM  s_user where user_name = '"+id+"' and user_pwd = '"+loginPw+"'";
			//查出来的数据转换成list
			 list = jdbcTemplate.queryForList(sql);
		}else if(type.equals("2")){
			String sql = "SELECT id,login_pw,staff_name,type FROM  t_staff where id = '"+id+"' and login_pw = '"+loginPw+"'";
			//查出来的数据转换成list
			 list = jdbcTemplate.queryForList(sql);
		}else if(type.equals("3")){
			String sql2 = "SELECT id,login_pw,stu_name,type FROM  t_stu where id = '"+id+"' and login_pw = '"+loginPw+"'";
			//查出来的数据转换成list
			 list = jdbcTemplate.queryForList(sql2);
		}else{
			
		}
		return list;
	}
	
	//修改密码,用户的学号工号，要修改的密码，登陆类型，3为学生，2为教师
	@Override
	public List<Map<String, String>> updatePassword(String number, String loginPw, String logintype) {
		String sql =null;	
		Map mapTemp = new HashMap();
		List<Map<String,String>> listMap = new ArrayList();
		if(logintype.equals("3")){
			//学生
			sql = "update t_stu set login_pw = '"+loginPw+"' where id = '"+number+"'";
		}else if(logintype.equals("2")){
			//教师
			sql = "update t_staff set login_pw = '"+loginPw+"' where id = '"+number+"'";
		}else{
			
		}
		try {
			jdbcTemplate.execute(sql);			
			//把需要的数据放到map里
			mapTemp.put("code", "1");	
			mapTemp.put("msg", "修改成功");			
		} catch (Exception e) {			
			e.printStackTrace();
			//把需要的数据放到map里
			mapTemp.put("code", "0");
			mapTemp.put("msg", "修改失败");
		}
		//把map放到list里的最后
		listMap.add(mapTemp);
		System.out.println(listMap);
		return listMap;
	}
	
	//查找学生的个人信息，入参是学生的学号
	@Override
	public List queryStudentInfo(String stu_id) {
		List list = null;
		//String sql = "SELECT * FROM t_stu where id = '"+stu_id+"'";
		String sql = "SELECT t.id,t.stu_name,t.stu_sex,t.stu_birth,t.stu_cardnum,t.stu_desc,c.college_name,d.dept_name,a.class_name,g.grade_name "
				+ "FROM t_stu t,t_college c,t_dept d,t_class a,t_grade g "
				+ "where t.id = '"+stu_id+"' and t.college_id = c.id and t.dept_id = d.id and t.class_id = a.id and a.grade_id = g.id";
		list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}

}
