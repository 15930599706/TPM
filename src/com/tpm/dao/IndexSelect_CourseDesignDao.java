package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_CourseDesign;

public interface IndexSelect_CourseDesignDao extends BaseDao<IndexSelect_CourseDesign> {

	List<IndexSelect_CourseDesign> findIndexSelect_CourseDesignListBySyllabusid(
			String syllabusId_Copy);

}
