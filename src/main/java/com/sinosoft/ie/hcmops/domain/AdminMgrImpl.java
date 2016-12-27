package com.sinosoft.ie.hcmops.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.ie.hcmops.model.Equip;
import com.sinosoft.ie.hcmops.model.LabInfo;
import com.sinosoft.ie.hcmops.model.NoClassTimes;
import com.sinosoft.ie.mpiws.model.PersonInfo;

@Service("AdminMgr")
public class AdminMgrImpl implements AdminMgr {
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
	
	//实现修改密码
	@Override
	public List updatePassword(String loginName, String loginPw) {
		List list = null;
		// TODO Auto-generated method stub
		String sql = "update s_user set user_pwd = '"+loginPw+"' where user_name = '"+loginName+"'";
		jdbcTemplate.execute(sql);
		String sql1 = "SELECT user_name,user_pwd FROM  s_user where user_name = '"+loginName+"' and user_pwd = '"+loginPw+"'";
		//查出来的数据转换成list
		 list = jdbcTemplate.queryForList(sql1);
		 return list;
	}
	@Override
	public List addLabInfo(LabInfo labInfo) {
		// TODO Auto-generated method stub
		List list = null;
		String id = labInfo.getId();
		String laboratory_name = labInfo.getLaboratory_name();
		String category_id = labInfo.getCategory_id();
		String laboratory_adress = labInfo.getLaboratory_adress();
		String laboratory_adressnum = labInfo.getLaboratory_adressnum();
		String staff_id = labInfo.getStaff_id();//从登陆信息里获取
		String laboratory_renshu = labInfo.getLaboratory_renshu();//从登陆信息里获取
		String laboratory_desc = labInfo.getLaboratory_desc();//从登陆信息里获取
		//String sql = "update t_jiaoshiinfor set user_pwd = '"+loginPw+"' where user_name = '"+loginName+"'";
		String sql = "insert into t_jiaoshiinfor(id,laboratory_name,category_id,laboratory_adress,laboratory_adressnum,staff_id,laboratory_renshu,laboratory_desc)value('"+id+"','"+laboratory_name+"','"+category_id+"','"+laboratory_adress+"','"+laboratory_adressnum+"','"+staff_id+"','"+laboratory_renshu+"','"+laboratory_desc+"')";
		jdbcTemplate.execute(sql);
		String sql1 = "SELECT id,staff_id FROM  s_user where id = '"+id+"' and staff_id = '"+staff_id+"'";
		//查出来的数据转换成list
		 list = jdbcTemplate.queryForList(sql1);
		 return list;
	}
	
	//实验室管理员添加实验室无课课表
	@Override
	public boolean addLabNoClass(NoClassTimes noClassTimes) {
		// TODO Auto-generated method stub
		boolean list = true;
		String id = noClassTimes.getId();
		String laboratory_id = noClassTimes.getLaboratory_id();
		String times_index = noClassTimes.getTimes_index();
		String staff_id = noClassTimes.getStaff_id();
		String sql = "insert into t_no_class_time(id,laboratory_id,times_index,staff_id)value('"+id+"','"+laboratory_id+"','"+times_index+"','"+staff_id+"')";
		try {
			jdbcTemplate.execute(sql);
			list = true;
		} catch (Exception e) {
			e.getMessage();
			list = false;
		}
		return list;
	}
	
	//搜索本实验室的所有设备列表
	@Override
	public List<Equip> getAllEquip(String type, String staff_id, String laboratory_id) {
		// TODO Auto-generated method stub
		String sql = "select * from t_equip where type = '"+type+"' and staff_id = '"+staff_id+"' and laboratory_id = '"+laboratory_id+"'";
		List list = (List<Equip>)jdbcTemplate.query(sql, new BeanPropertyRowMapper(Equip.class));
		return list;
	}
	//添加本实验室的设备
	@Override
	public boolean addEquip(Equip equip) {
		boolean list = true;
		String id = equip.getId();
		String equip_name = equip.getEquip_name();
		String equip_model = equip.getEquip_model();
		String unit_price = equip.getUnit_price();
		String equip_number = equip.getEquip_number();
		String storage_time = equip.getStorage_time();
		String producer = equip.getProducer();
		String application = equip.getApplication();
		String equip_image_one = equip.getEquip_image_one();
		String equip_image_two = equip.getEquip_image_two();
		String laboratory_id = equip.getLaboratory_id();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operation_time=format.format(date);
		//String operation_time = equip.getOperation_time();
		String staff_id = equip.getStaff_id();
		String type = equip.getType();
		String equip_desc = equip.getEquip_desc();
		String sql = "insert into t_equip(id,equip_name,equip_model,unit_price,equip_number,storage_time,producer,application,equip_image_one,equip_image_two,laboratory_id,operation_time,staff_id,type,equip_desc)value('"+id+"','"+equip_name+"','"+equip_model+"','"+unit_price+"','"+equip_number+"','"+storage_time+"','"+producer+"','"+application+"','"+equip_image_one+"','"+equip_image_two+"','"+laboratory_id+"','"+operation_time+"','"+staff_id+"','"+type+"','"+equip_desc+"')";		
		try {
			jdbcTemplate.execute(sql);
			list = true;
		} catch (Exception e) {
			e.getMessage();
			list = false;
		}
		return list;
	}
	
	//根据设备id查数据
	@Override
	public List queryLabInfo(String id) {
		List list = null;
		String sql = "SELECT * FROM  t_equip where id = '"+id+"'";
		//查出来的数据转换成list
		 list = jdbcTemplate.queryForList(sql);
		 System.out.println(list);
		return list;
	}
	
	//根据设备id删除数据
	@Override
	public void deleteLabInfo(String id,String laboratory_id) {
		String sql = "DELETE FROM  t_equip where id = '"+id+"' and laboratory_id = '"+laboratory_id+"'";
		jdbcTemplate.execute(sql);
	}
	
	

}
