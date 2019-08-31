package com.tpm.dao;

import java.util.List;

import com.tpm.entity.TeachObj_Gra;

public interface TeachObj_GraDao extends BaseDao<TeachObj_Gra> {

	String findAllByCDP_Gra(String syllabusId);

	List<TeachObj_Gra> findTeachObj_GraListBySyllabusid(String syllabusId_Copy);

}
