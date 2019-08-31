package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_CourseDesign;

public class IndexSelect_CourseDesignDaoImpl extends BaseDaoImpl<IndexSelect_CourseDesign> implements
		IndexSelect_CourseDesignDao {

	public List<IndexSelect_CourseDesign> findIndexSelect_CourseDesignListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from IndexSelect_CourseDesign where syllabus_CourseDesignid=?",syllabusId_Copy);
	}

}
