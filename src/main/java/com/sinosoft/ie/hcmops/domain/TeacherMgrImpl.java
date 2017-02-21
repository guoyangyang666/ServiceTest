package com.sinosoft.ie.hcmops.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.ie.hcmops.model.ExperimBatch;
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
	//查询实验室课表及添加的实验批次
	@Override
	public List<Map<String, String>> quryCourseExperim(String laboratory_id, String current_week) {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
		String sql= "select ct.id,ct.week,ct.start_times,ct.stop_times,ct.start_week,ct.last_week,ct.type,tc.courses_name "
				+ "from t_course_time ct,t_course tc "
				+ "where  ct.laboratory_id = '"+laboratory_id+"' and '"+current_week+"'>=ct.start_week and "+current_week+"<=ct.last_week and ct.course_id=tc.id and ct.status=1"; 
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//查询实验室管理员添加的实验名
	@Override
	public List<Map<String, String>> quryExperimName(String laboratory_id) {
		List list = null;
		String sql="select * from t_experimbatch_name where laboratory_id = '"+laboratory_id+"'";
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	//通过实验名查询所有的实验批次,当前周
	@Override
	public List<Map<String, String>> quryExperims(String laboratory_id, String experim_id, String current_week) {
		List list = null;
		String sql="select * from t_experimbatch where experim_id = '"+experim_id+"' and laboratory_id = '"+laboratory_id+"' and '"+current_week+"'>=start_week and '"+current_week+"'<=last_week";
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	//教师根据课表添加实验批次
	@Override
	public List<Map<String, String>> addLabExperim(ExperimBatch experimBatch) {
		List list = null;
		List<Map<String , String>> listMap = new ArrayList();
		Map mapTemp = new HashMap();
		
		String id = experimBatch.getId();
		String experim_id = experimBatch.getExperim_id();
		String course_id = experimBatch.getCourse_id();//教师已预约
		String batch = experimBatch.getBatch();
		String week = experimBatch.getWeek();
		String start_times = experimBatch.getStart_times();
		String stop_times = experimBatch.getStop_times();
		String appoint_week = experimBatch.getAppoint_week();//预约的周（教师）
		String start_week = experimBatch.getStart_week();//开始周数
		String last_week = experimBatch.getLast_week();//结束周数
		String staff_id = experimBatch.getStaff_id();
		String laboratory_id = experimBatch.getLaboratory_id();//
		String type = experimBatch.getType();//类型，1为实验室的课，2为教师确认的批次，其他教师不可选
		String status = experimBatch.getStatus();//1为普通的课程。教师确认批次的状态，（1为已预约，2为取消，3为删除）
		Integer experim_num = experimBatch.getExperim_num();////预约的人数，与实验室容量对比
		String sql = "insert into t_course_time(id,experim_id,batch,week,start_times,stop_times,appoint_week,staff_id,laboratory_id,type,status,experim_num,course_id,start_week,last_week)value('"+id+"','"+experim_id+"','"+batch+"','"+week+"','"+start_times+"','"+stop_times+"','"+appoint_week+"','"+staff_id+"','"+laboratory_id+"','"+type+"','"+status+"','"+experim_num+"','"+course_id+"','"+start_week+"','"+last_week+"')";
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
	//查看教师自己的所有预约记录
	@Override
	public List<Map<String, String>> quryAppointList(String staff_id) {
		List list = null;
		String sql="select ct.id,ct.week,ct.batch,ct.start_times,ct.stop_times,ct.appoint_week,en.experim_name,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum "
				+ "from t_course_time ct,t_experimbatch_name en,t_jiaoshiinfor j "
				+ "where ct.staff_id = '"+staff_id+"' and ct.type = 2 and ct.status = 1 and ct.experim_id = en.id and ct.laboratory_id = j.id";
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	//教师取消预约记录
	@Override
	public List<Map<String, String>> cancelAppoint(String staff_id, String id,String cancel_reason) {
		List list = null;
		List<Map<String , String>> listMap = new ArrayList();
		Map mapTemp = new HashMap();
		String sql="update t_course_time set status ='2',cancel_reason = '"+cancel_reason+"' where staff_id = '"+staff_id+"' and id = '"+id+"' and type = 2 ";
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
	
	//查看教师自己所有取消的记录
	@Override
	public List<Map<String, String>> cancelAppointList(String staff_id) {
		List list = null;
		String sql="select ct.id,ct.week,ct.batch,ct.start_times,ct.stop_times,ct.appoint_week,ct.cancel_reason,en.experim_name,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum "
				+ "from t_course_time ct,t_experimbatch_name en,t_jiaoshiinfor j "
				+ "where ct.staff_id = '"+staff_id+"' and ct.type = 2 and ct.status = 2 and ct.experim_id = en.id and ct.laboratory_id = j.id";
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	//教师查看学生预约的记录
	@Override
	public List<Map<String, String>> quryStuAppoint(String staff_id) {
		List list = null;
		String sql="select se.id,se.stu_id,se.course_time_id,se.laboratory_id,j.laboratory_name,j.laboratory_renshu,s.stu_name,en.experim_name,ct.batch,ct.experim_num,se.status "
				+ "from t_stu_experim se,t_jiaoshiinfor j,t_course_time ct,t_stu s,t_experimbatch_name en "
				+ "where se.staff_id='"+staff_id+"' and se.status<>3 and se.laboratory_id=j.id and se.stu_id=s.id and ct.experim_id=en.id and se.course_time_id=ct.id";
				
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//教师根据选择的实验室id选择相应的实验项目
	@Override
	public List<Map<String, String>> quryExperim(String laboratory_id) {
		List list = null;
		String sql="select id,experim_name "
				+ "from t_experimbatch_name "
				+ "where laboratory_id='"+laboratory_id+"'";
				
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	//教师根据选择的实验项目选择相应的批次
	@Override
	public List<Map<String, String>> quryBatch(String experim_id,String laboratory_id) {
		List list = null;
		String sql="select id,batch "
				+ "from t_experimbatch "
				+ "where experim_id='"+experim_id+"' and laboratory_id='"+laboratory_id+"'";
				
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//首页，预约信息，显示所有教师预约的信息,只显示最新的6条
	@Override
	public List<Map<String, String>> quryTeacherAppoint() {
		List list = null;
		String sql="select ct.id,en.experim_name,ct.appoint_week,ct.week,ct.start_times,ct.stop_times,ct.status,s.staff_name "
				+ "from t_course_time ct,t_experimbatch_name en,t_staff s "
				+ "where ct.experim_id=en.id and ct.staff_id=s.id and ct.type='2' and ct.status='1' limit 0,6";
				
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//所有的教师预约信息
	@Override
	public List<Map<String, String>> quryAllTeacherAppoint(int current, int pageSize) {
		int totalRecord;//总条数
		List list = null;
		List list1 = null;
		List<Map<String, String>> listMap = new ArrayList();
		String sql= "select * from t_course_time where  type=2 and status=1"; 
		try {
			list = jdbcTemplate.queryForList(sql);
			totalRecord = list.size();//计算出总条数							
			current = (current-1)*pageSize;//当前索引值	
			String sql1= "select ct.id,en.experim_name,ct.appoint_week,ct.week,ct.start_times,ct.stop_times,ct.status,s.staff_name,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum "
					+ "from t_course_time ct,t_experimbatch_name en,t_staff s,t_jiaoshiinfor j "
					+ "where ct.experim_id=en.id and ct.staff_id=s.id and ct.type=2 and ct.status=1 and ct.laboratory_id=j.id limit "+current+","+pageSize+""; 
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
	
	//查询周，1-18周
	@Override
	public List<Map<String, String>> quryWeek() {
		List list = null;
		String sql="select * "
				+ "from t_week_number ";
				
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//查询持续的小节
	@Override
	public List<Map<String, String>> quryLastHours() {
		List list = null;
		String sql="select * "
				+ "from t_last_hours ";
				
		System.out.println(sql);
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	
	
	
}
