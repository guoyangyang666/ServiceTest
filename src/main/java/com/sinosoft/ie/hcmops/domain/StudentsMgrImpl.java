package com.sinosoft.ie.hcmops.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sinosoft.ie.hcmops.model.ExperimBatch;
import com.sinosoft.ie.hcmops.model.StuExperim;
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
	//学生预约之前先先查询是否已预约，
	@Override
	public List<Map<String, String>> quryStu(String course_time_id,String stu_id) {
		List list = null;	
		Map mapTemp = new HashMap();
		List<Map<String , String>> listMap = new ArrayList();
		String sql = "SELECT * from t_stu_experim where course_time_id ='"+course_time_id+"' and state=1 and stu_id ='"+stu_id+"'";
				
		
		try {
			list = jdbcTemplate.queryForList(sql);
			System.out.println(list);
			if(list.size()==0){
				mapTemp.put("code", "1");
				mapTemp.put("res", "可以添加");
			}else{
				mapTemp.put("code", "2");
				mapTemp.put("res", "您已添加");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mapTemp.put("code", "0");
			mapTemp.put("res", "添加成功");
		}
		listMap.add(mapTemp);
		 return listMap;
	}
	
	//学生预约实验室
	@Override
	public List<Map<String, String>> addStuExperim(StuExperim stuExperim,Integer experim_num) {
		List list = null;
		String sql1;
		sql1 = "update t_course_time set experim_num = '"+experim_num+"' where id = '"+stuExperim.getCourse_time_id()+"'";
		jdbcTemplate.update(sql1);
		
		List<Map<String , String>> listMap = new ArrayList();
		Map mapTemp = new HashMap();		
		String id = stuExperim.getId();
		String stu_id = stuExperim.getStu_id();
		String course_time_id = stuExperim.getCourse_time_id();
		String laboratory_id = stuExperim.getLaboratory_id();
		String staff_id = stuExperim.getStaff_id();
		String status = stuExperim.getStatus();
		String state = stuExperim.getState();
		String sql = "insert into t_stu_experim(id,stu_id,course_time_id,laboratory_id,status,staff_id,state)value('"+id+"','"+stu_id+"','"+course_time_id+"','"+laboratory_id+"','"+status+"','"+staff_id+"','"+state+"')";
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
	
	
	
	//学生查询所有的能预约的实验室，并可查询：实验室名，教师名，实验名，上课时间，
	@Override
	public List<Map<String, String>> quryAllExperim(String current_week) {
		List list = null;
		
		String sql="select ct.id,en.experim_name,ct.batch,ct.laboratory_id,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum,ct.staff_id,s.staff_name,ct.experim_num,ct.appoint_week,"
				+ "ct.week,ct.start_times,ct.stop_times,j.laboratory_renshu "
				+ "from t_course_time ct,t_jiaoshiinfor j,t_staff s,t_experimbatch_name en "
				+ "where ct.experim_id = en.id and ct.laboratory_id = j.id and ct.staff_id = s.id and ct.type=2 and ct.status=1 and ct.appoint_week = '"+current_week+"'";
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	
	//学生查询自己预约的实验室,包括已预约的和未审核的。
	@Override
	public List<Map<String, String>> quryStuExperim(String stu_id) {
		List list = null;
		
		String sql="select se.id,se.course_time_id,se.laboratory_id,se.staff_id,se.status,en.experim_name,ct.batch,ct.laboratory_id,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum,ct.staff_id,s.staff_name,ct.appoint_week,"
				+ "ct.week,ct.start_times,ct.stop_times,ct.experim_num,st.stu_name,se.stu_id,se.status "
				+ "from t_course_time ct,t_jiaoshiinfor j,t_staff s,t_experimbatch_name en,t_stu_experim se,t_stu st "
				+ "where se.stu_id = '"+stu_id+"' and se.course_time_id = ct.id and ct.experim_id = en.id and se.laboratory_id = j.id and se.staff_id = s.id and se.status<>'3' and se.state=1 and se.stu_id=st.id";
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	//学生取消预约记录
	@Override
	public List<Map<String, String>> cancelAppoint(String stu_id, String id,String cancel_reason,Integer experim_num,String course_time_id) {
		List list = null;
		String sql1 = "update t_course_time set experim_num = '"+experim_num+"' where id = '"+course_time_id+"'";
		System.out.println(sql1);
		jdbcTemplate.update(sql1);
		List<Map<String , String>> listMap = new ArrayList();
		Map mapTemp = new HashMap();
		String sql="update t_stu_experim set status ='3',cancel_reason = '"+cancel_reason+"' where stu_id = '"+stu_id+"' and id = '"+id+"'";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql);
			mapTemp.put("code", "1");
			mapTemp.put("res", "取消成功");
		} catch (Exception e) {
			e.printStackTrace();
			mapTemp.put("code", "1");
			mapTemp.put("res", "取消失败");
		}
		listMap.add(mapTemp);
		System.err.println(list);
	    return listMap;
	}
		
	//查询自己所有取消的记录
	@Override
	public List<Map<String, String>> cancelAppointList(String stu_id) {
		List list = null;
		String sql="select se.id,se.course_time_id,se.laboratory_id,se.staff_id,se.status,en.experim_name,ct.batch,ct.laboratory_id,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum,ct.staff_id,s.staff_name,ct.appoint_week,"
				+ "ct.week,ct.start_times,ct.stop_times,st.stu_name,se.stu_id,se.status "
				+ "from t_course_time ct,t_jiaoshiinfor j,t_staff s,t_experimbatch_name en,t_stu_experim se,t_stu st "
				+ "where se.stu_id = '"+stu_id+"' and se.course_time_id = ct.id and ct.experim_id = en.id and se.laboratory_id = j.id and se.staff_id = s.id and se.status='3' and se.state=1 and se.stu_id=st.id";
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//学生删除自己取消的记录
	@Override
	public List<Map<String, String>> deleteAppointList(String id) {	
		List<Map<String , String>> listMap = new ArrayList();
		Map mapTemp = new HashMap();
		String sql="update t_stu_experim set state=0 where id = '"+id+"'";
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql);
			mapTemp.put("code", "1");
			mapTemp.put("res", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			mapTemp.put("code", "1");
			mapTemp.put("res", "删除失败");
		}
		listMap.add(mapTemp);
	    return listMap;
	}
	
	
}
