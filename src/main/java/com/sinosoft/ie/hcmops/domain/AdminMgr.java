package com.sinosoft.ie.hcmops.domain;

import java.util.List;

import com.sinosoft.ie.hcmops.model.Equip;
import com.sinosoft.ie.hcmops.model.LabInfo;
import com.sinosoft.ie.hcmops.model.NoClassTimes;
import com.sinosoft.ie.mpiws.model.PersonInfo;

/**
 * 实验室管理员
 * @author thinkpad
 *
 */
public interface AdminMgr {
	//修改密码
	public List updatePassword(String loginName, String loginPw);
	//添加实验室基本信息
	public List addLabInfo(LabInfo labInfo);
	//实验室管理员添加实验室无课课表
	public boolean addLabNoClass(NoClassTimes noClassTimes);
	//搜索本实验室的所有设备列表,入参是登陆的管理员工号，类型，实验室的id
	public List<Equip> getAllEquip(String type, String staff_id, String laboratory_id);
	//添加本实验室的设备
	public boolean addEquip(Equip equip);
	//根据设备id查数据
	public List queryLabInfo(String id);
	//根据设备id和实验室id删除数据
	public void deleteLabInfo(String id,String laboratory_id);
}
