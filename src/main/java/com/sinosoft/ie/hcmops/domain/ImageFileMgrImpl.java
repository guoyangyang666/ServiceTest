package com.sinosoft.ie.hcmops.domain;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.ie.hcmops.model.ImageFile;
@Service("ImageFileMgr")
public class ImageFileMgrImpl implements ImageFileMgr {
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
	//添加图片
	@Override
	public void addImageFile(ImageFile imageFile) {
		String id = imageFile.getId();
		String image_name = imageFile.getImage_name();
		String image_url = imageFile.getImage_url();
		Date date = new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_time=format.format(date);
		String sql = "insert into plat_image_file(id,image_name,image_url,date)value('"+id+"','"+image_name+"','"+image_url+"','"+date+"')";		

	}

}
