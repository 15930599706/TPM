package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect;

public interface IndexSelectDao extends BaseDao<IndexSelect> {

	List<IndexSelect> findIndexSelectListBySyllabusid(String syllabusId_Copy);

}
