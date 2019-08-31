package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect;

public class IndexSelectDaoImpl extends BaseDaoImpl<IndexSelect> implements IndexSelectDao {

	@SuppressWarnings("unchecked")
	public List<IndexSelect> findIndexSelectListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from IndexSelect where syllabusID=?",syllabusId_Copy);
	}


}
