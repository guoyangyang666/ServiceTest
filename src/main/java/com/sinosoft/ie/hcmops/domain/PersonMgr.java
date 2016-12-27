package com.sinosoft.ie.hcmops.domain;

import java.util.List;

import com.sinosoft.ie.mpiws.model.HospitalInfo;
import com.sinosoft.ie.mpiws.model.PersonInfo;

public interface PersonMgr {
public void addPerson(PersonInfo person);
public List<PersonInfo> getAllPerson();
public List<PersonInfo> verifyPerson(String loginName,String password);
public void addLoginLog(String operId ,String loginNme ,String LoginTime);
public void cancelPerson(String operId);
public List<HospitalInfo> showHospital(String IP);
public List<PersonInfo> verifyPersonExist(String name,String certId);
public void resetPassword(String password,String certId);

}
