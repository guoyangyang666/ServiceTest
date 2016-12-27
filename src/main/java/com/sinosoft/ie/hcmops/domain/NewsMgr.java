package com.sinosoft.ie.hcmops.domain;

import java.util.List;
import java.util.Map;

import com.sinosoft.ie.hcmops.model.News;

/**
 * 新闻
 * @author thinkpad
 *
 */
public interface NewsMgr {
	//查询所有的新闻实现分页,current当前页，pageSize一页显示的条数
	public List<Map<String, String>> quryAllNews(int current,int pageSize);
	//根据新闻的id查询所有内容
	public List<News> quryNews(String id);
}
