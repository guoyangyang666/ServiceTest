package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

import com.sinosoft.ie.hcmops.model.Equip;
import com.sinosoft.ie.hcmops.model.News;

/**
 * 网站首页设备展示及详细
 * @author guoyangyang
 *
 */
public interface EquipMgr {
	//查询最新的5个设备，前台展示
	public List<Map<String, String>> quryFiveEquip();
	//查询所有的设备实现分页,current当前页，pageSize一页显示的条数
	public List<Map<String, String>> quryAllEquip(int current,int pageSize);
	public List<Map<String, String>> quryAllLabEquip(int current,int pageSize,String laboratory_id);
	//根据设备的id查询所有内容
	public List<Equip> quryEquip(String id,String laboratory_id);
	//查询出所有的实验室名称，在实验室设备页面左侧展示
	public List<Map<String, String>> quryAllLab();
	//根据实验室id查询出设备及其详细信息
	public List<Map<String, String>> quryLab(String laboratory_id);
}
