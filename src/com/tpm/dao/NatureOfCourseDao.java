package com.tpm.dao;

import java.util.List;

import com.tpm.entity.NatureOfCourse;

public interface NatureOfCourseDao extends BaseDao<NatureOfCourse>{

	NatureOfCourse getbyname(String natureOfCoursename);

	List<NatureOfCourse> getDepartment();
	
	List<NatureOfCourse> getAll();

}
