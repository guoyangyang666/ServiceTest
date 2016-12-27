package com.sinosoft.ie.hcmops.domain;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;
import com.sinosoft.ie.mpiws.model.HospitalInfo;
//import com.sinosoft.ie.dhow.core.dao.CommonBaseDao;
//import com.sinosoft.ie.dhow.core.dao.DataAccessException;
import com.sinosoft.ie.mpiws.model.PersonInfo;

@Service("PersonMgr")
public class PersonMgrImpl implements PersonMgr {

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
	public PersonMgrImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 添加用户
	 * @author liwei
	 */

	public void addPerson(PersonInfo person) {

		String id = person.getId();
		String name = person.getName();
		String loginName = person.getLoginName();
		String sex = person.getSex();
		String certId = person.getCertId();
		String hospital = person.getHospital();
		String phone = person.getPhone();
		String job = person.getJob();
		String password = person.getPassword();
		String sql = "insert into person(id,name,loginName,sex,certId,hospital,phone,job,password)value('"+id+"','"+name+"','"+loginName+"','"+sex+"','"+certId+"','"+hospital+"','"+phone+"','"+job+"','"+password+"')";
		jdbcTemplate.execute(sql);
		
	}
	@Override
	/**
	 * 查询所有用户
	 * @author liwei
	 */
	public List<PersonInfo> getAllPerson() {

		String sql = "select * from person";

		return (List<PersonInfo>)jdbcTemplate.query(sql, new BeanPropertyRowMapper(PersonInfo.class));
		}
	/**
	 * 验证客户端登录用户
	 * @author liwei
	 */
	public List<PersonInfo> verifyPerson(String loginName,String password){
		int result = 0;
		String sql = "SELECT HOS_ORG_CODE,loginName,name,hospital FROM  t_hos_info JOIN person ON ( t_hos_info.ORG_NIZATION_NAME = person.hospital) where loginName = '"+loginName+"' and password = '"+password+"'";
		List list = (List<PersonInfo>)jdbcTemplate.query(sql, new BeanPropertyRowMapper(PersonInfo.class));
		if(list.size()==0){
			result = 0;//无此用户，验证未通过
		}else{
			result = 1;//验证通过
		}
		return list;
		
	}
	public void addLoginLog(String operId ,String loginNme ,String LoginTime){
		String sql = "insert into LoginLog(OPERID,LOGINNAME,LOGINTIME) value ('"+operId+"','"+loginNme+"','"+LoginTime+"')";
		jdbcTemplate.execute(sql);
	}
	public void cancelPerson(String operId){
		String sql = "update person set status = '0' where operId = '"+operId+"' ";
		jdbcTemplate.execute(sql);
	}
	public List<HospitalInfo> showHospital(String IP){
		String sql = "selsect ORG_NIZATION_NAME from ORG_NIZATION_NAME where IP = '"+IP+"'";
		return (List<HospitalInfo>)jdbcTemplate.query(sql, new BeanPropertyRowMapper(HospitalInfo.class));
		}
	public List<PersonInfo> verifyPersonExist(String name,String certId){
		String sql = "select * from person where name = '"+name+"' and certId = '"+certId+"'";
		List list = (List<PersonInfo>)jdbcTemplate.query(sql, new BeanPropertyRowMapper(PersonInfo.class));
		return list;
	}
	public void resetPassword(String password ,String certId){
		String sql = "update person set password = '"+password+"' where name = '"+certId+"'";
		jdbcTemplate.execute(sql);
	}
}
