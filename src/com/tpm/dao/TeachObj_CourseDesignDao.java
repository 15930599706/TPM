package com.tpm.dao;

import java.util.List;

import com.tpm.entity.TeachObj_CourseDesign;

public interface TeachObj_CourseDesignDao extends BaseDao<TeachObj_CourseDesign> {

	String findAllByCDP_CourseDesign(String syllabusId);

	List<TeachObj_CourseDesign> findTeachObj_CourseDesignListBySyllabusid(
			String syllabusId_Copy);

}
