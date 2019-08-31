package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_Gra;

public class IndexSelect_GraDaoImpl extends BaseDaoImpl<IndexSelect_Gra> implements
		IndexSelect_GraDao {

	public List<IndexSelect_Gra> findIndexSelect_GraListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from IndexSelect_Gra where syllabus_Graid=?",syllabusId_Copy);
		
	}

}
