package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;

public interface CollegeDao extends BaseDao<College>{

	College getbyname(String collegename);

	List<College> getone(String collegeid);

}
