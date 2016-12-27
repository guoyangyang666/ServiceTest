package com.sinosoft.ie.hcmops.domain;

import java.util.List;

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

}
