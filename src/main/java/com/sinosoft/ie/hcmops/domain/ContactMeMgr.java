package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

/**
 * 联系我
 * @author thinkpad
 *
 */
public interface ContactMeMgr {
	
	//查出所有的实验室及负责人的联系方式,current当前页，pageSize一页显示的条数
	public List<Map<String, String>> quryAllContact(int current,int pageSize);
}
