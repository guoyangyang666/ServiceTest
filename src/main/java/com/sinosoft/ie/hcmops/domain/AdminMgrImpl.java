package com.sinosoft.ie.hcmops.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.ie.hcmops.model.Equip;
import com.sinosoft.ie.hcmops.model.ExperimBatch;
import com.sinosoft.ie.hcmops.model.Experimbatchs;
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
	public List queryLabInfo(String laboratory_id, String id) {
		List list = null;
		String sql = "SELECT * FROM  t_equip where id = '"+id+"' and laboratory_id = '"+laboratory_id+"'";
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
	
	//查看个人基本信息,入参是管理员工号
	@Override
	public List queryLabAdminInfo(String staff_id) {
		List list = null;
		String sql = "SELECT * FROM t_staff where id = '"+staff_id+"'";
		list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}
	
	//查看实验室课表
	@Override
	public List<Map<String, String>> quryAllCourse(String laboratory_id) {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
		String sql= "select ct.id,ct.week,c.courses_name,start_times,ct.stop_times,ct.start_week,ct.last_week,ct.start_time,ct.stop_time,ct.type,co.courses_name "
				+ "from t_course_time ct,t_course co,t_course c "
				+ "where  ct.laboratory_id = '"+laboratory_id+"' and ct.course_id = co.id and ct.course_id = c.id and type = 1"; 
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	
	
	
	
	//添加的实验批次
	@Override
	public List<Map<String, String>> addExperimbatchs(Experimbatchs experimbatchs) {
		List list = null;
		List<Map<String , String>> listMap = new ArrayList();
		Map mapTemp = new HashMap();
		
		String id = experimbatchs.getId();
		String experim_name = experimbatchs.getExperim_name();
		String batch = experimbatchs.getBatch();
		String start_week = experimbatchs.getStart_week();
		String last_week = experimbatchs.getLast_week();
		String laboratory_id = experimbatchs.getLaboratory_id();	
		String status = experimbatchs.getStatus();
		String sql = "insert into t_experimbatch(id,experim_name,batch,start_week,last_week,laboratory_id,status)value('"+id+"','"+experim_name+"','"+batch+"','"+start_week+"','"+last_week+"','"+laboratory_id+"','"+status+"')";
		try {
			jdbcTemplate.execute(sql);
			mapTemp.put("code", "1");
			mapTemp.put("res", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			mapTemp.put("code", "0");
			mapTemp.put("res", "添加成功");
		}
		listMap.add(mapTemp);
		 return listMap;
	}
	
	//查询的实验批次，新
	@Override
	public List<Map<String, String>> quryExperimbatchs(String laboratory_id) {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
		String sql= "select e.id,en.experim_name,e.batch,e.start_week,e.last_week,e.laboratory_id,j.laboratory_name "
				+ "from t_experimbatch e,t_jiaoshiinfor j,t_experimbatch_name en "
				+ "where  e.laboratory_id = '"+laboratory_id+"' and e.laboratory_id = j.id and e.experim_id = en.id"; 
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//删除的实验批次，新
	@Override
	public List<Map<String, String>> deleteExperimbatchs(String id) {
		List list = null;
		List<Map<String , String>> listMap = new ArrayList();
		Map mapTemp = new HashMap();
		String sql = "DELETE FROM  t_experimbatch where id = '"+id+"'";
		
		try {
			jdbcTemplate.execute(sql);
			mapTemp.put("code", "1");
			mapTemp.put("res", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			mapTemp.put("code", "0");
			mapTemp.put("res", "删除失败");
		}
		listMap.add(mapTemp);
		System.err.println(listMap);
		 return listMap;
	}
	
	
	

}
