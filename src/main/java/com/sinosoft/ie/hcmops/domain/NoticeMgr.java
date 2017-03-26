package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

import com.sinosoft.ie.hcmops.model.Notice;

//通知公告
public interface NoticeMgr {
	//查询最新的两条公告，前台展示
	public List<Map<String, String>> quryTwoNotice();
	//查询所有的公告实现分页,current当前页，pageSize一页显示的条数
//	public List<Map<String, String>> quryAllNotice();
	public List<Map<String, String>> quryAllNotice(int current,int pageSize);
	//根据公告的id查询所有内容
	public List<Notice> quryNotice(String id);
	//管理员取消某一个课，添加公告
	public List<Map<String, String>> addNotice(Notice notice,String dataId);
}
