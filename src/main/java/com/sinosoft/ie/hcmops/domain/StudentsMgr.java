package com.sinosoft.ie.hcmops.domain;

import java.util.List;

import com.sinosoft.ie.hcmops.model.StudentsStu;
import com.sinosoft.ie.mpiws.model.PersonInfo;

/**
 * 登陆接口
 * @author thinkpad
 *
 */
public interface StudentsMgr {
	//验证登陆接口
	public List verifyPerson(String id,String loginPw,String type);//用户名，密码，用户类型
}
