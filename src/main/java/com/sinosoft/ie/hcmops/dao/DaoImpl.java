package com.sinosoft.ie.hcmops.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sinosoft.ie.mpiws.model.PersonInfo;

public class DaoImpl implements Dao {
	private DataSource datasource;  
    private JdbcTemplate jdbcTemplateObject;
    private PersonInfo person;

	public PersonInfo getPerson() {
		return person;
	}

	public void setPerson(PersonInfo person) {
		this.person = person;
	}

	public void addPerson(PersonInfo person) {
		// TODO Auto-generated method stub
		String sql = "insert into person(id,name,loginName,sex,certId,hospital,department,phone,job)value(?,?,?,?,?,?,?,?,?)";
		jdbcTemplateObject.update(sql);
		
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public JdbcTemplate getJdbcTemplateObject() {
		return jdbcTemplateObject;
	}

	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}

}
