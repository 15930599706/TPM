package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_FieldWork;

public interface IndexSelect_FieldWorkDao extends BaseDao<IndexSelect_FieldWork> {

	List<IndexSelect_FieldWork> findIndexSelect_FieldWorkListBySyllabusid(
			String syllabusId_Copy);

}
