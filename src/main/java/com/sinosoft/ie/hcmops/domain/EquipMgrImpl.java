package com.sinosoft.ie.hcmops.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.ie.hcmops.model.Equip;
import com.sinosoft.ie.hcmops.model.News;
/**
 * 实现设备首页展示
 * @author guoyangyang
 *
 */
@Service("EquipMgr")
public class EquipMgrImpl implements EquipMgr {
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

	//查询最新的5个设备，前台展示
	@Override
	public List<Map<String, String>> quryFiveEquip() {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
		String sql= "select e.id,e.equip_name,e.equip_image_one,e.equip_desc,e.laboratory_id,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum from t_equip e,t_jiaoshiinfor j"
				+ " where e.laboratory_id = j.id order by e.order_num desc limit 0,5"; 
	
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	//查询所有的设备实现分页,current当前页，pageSize一页显示的条数
	@Override
	public List<Map<String, String>> quryAllEquip(int current, int pageSize) {
		int totalRecord;//总条数
		List list = null;
		List list1 = null;
		List<Map<String, String>> listMap = new ArrayList();
		String sql= "select * from t_equip order by order_num desc"; 
		try {
			list = jdbcTemplate.queryForList(sql);
			totalRecord = list.size();//计算出总条数							
			current = (current-1)*pageSize;//当前索引值	
			String sql1= "select e.id,e.equip_name,e.equip_image_one,e.equip_desc,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum from t_equip e,t_jiaoshiinfor j"
					+ " where e.laboratory_id = j.id order by e.order_num desc limit "+current+","+pageSize+""; 
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
	//根据设备的id查询所有内容
	@Override
	public List<Equip> quryEquip(String id) {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
//		String sql = "select * from t_equip"
//				+ " where id = '"+id+"'";
		String sql = "select e.id,e.equip_name,e.equip_image_one,e.equip_desc,e.application,e.laboratory_id,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum from t_equip e,t_jiaoshiinfor j "
				+ " where e.id = '"+id+"' and e.laboratory_id = j.id";
		//List list = (List<Equip>)jdbcTemplate.query(sql, new BeanPropertyRowMapper(Equip.class));	
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//查询出所有的实验室名称，在实验室设备页面左侧展示
	@Override
	public List<Map<String, String>> quryAllLab() {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
		String sql= "select id,laboratory_name from t_jiaoshiinfor"; 	
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	//根据实验室id查询出设备及其详细信息
	@Override
	public List<Map<String, String>> quryLab(String laboratory_id) {
		List list = null;
		List<Map<String,String>> listMap = new ArrayList();
		String sql = "select e.id,e.equip_name,e.equip_image_one,e.equip_desc,e.laboratory_id,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum from t_equip e,t_jiaoshiinfor j"
				+ " where e.laboratory_id = '"+laboratory_id+"' and  e.laboratory_id = j.id";
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(list);
		return list;
	}
	
	@Override
	public List<Map<String, String>> quryAllLabEquip(int current, int pageSize, String laboratory_id) {
		int totalRecord;//总条数
		List list = null;
		List list1 = null;
		List<Map<String, String>> listMap = new ArrayList();
		if(laboratory_id == null){
			String sql= "select * from t_equip order by order_num desc"; 
			try {
				list = jdbcTemplate.queryForList(sql);
				totalRecord = list.size();//计算出总条数							
				current = (current-1)*pageSize;//当前索引值	
				String sql1= "select e.id,e.equip_name,e.equip_image_one,e.equip_desc,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum from t_equip e,t_jiaoshiinfor j"
						+ " where e.laboratory_id = j.id order by e.order_num desc limit "+current+","+pageSize+""; 
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
		}else{
			String sql= "select * from t_equip where laboratory_id = '"+laboratory_id+"' order by order_num desc"; 
			try {
				list = jdbcTemplate.queryForList(sql);
				totalRecord = list.size();//计算出总条数							
				current = (current-1)*pageSize;//当前索引值
				String sql1 = "select e.id,e.equip_name,e.equip_image_one,e.equip_desc,e.laboratory_id,j.laboratory_name,j.laboratory_adress,j.laboratory_adressnum from t_equip e,t_jiaoshiinfor j"
						+ " where e.laboratory_id = '"+laboratory_id+"' and  e.laboratory_id = j.id order by e.order_num desc limit "+current+","+pageSize+"";
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
		}
		
		System.err.println(list1);
		return list1;
	}
	
	

}
