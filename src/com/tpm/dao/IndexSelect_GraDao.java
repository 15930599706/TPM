package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_Gra;

public interface IndexSelect_GraDao extends BaseDao<IndexSelect_Gra> {

	List<IndexSelect_Gra> findIndexSelect_GraListBySyllabusid(
			String syllabusId_Copy);

}
