package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_FieldWork;

public class IndexSelect_FieldWorkDaoImpl extends BaseDaoImpl<IndexSelect_FieldWork> implements
		IndexSelect_FieldWorkDao {

	public List<IndexSelect_FieldWork> findIndexSelect_FieldWorkListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from IndexSelect_FieldWork where syllabus_FieldWorkid=?",syllabusId_Copy);
	}

}
