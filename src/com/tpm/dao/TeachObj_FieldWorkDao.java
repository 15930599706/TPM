package com.tpm.dao;

import java.util.List;

import com.tpm.entity.TeachObj_FieldWork;

public interface TeachObj_FieldWorkDao extends BaseDao<TeachObj_FieldWork> {

	String findAllByCDP_FieldWork(String syllabusId);

	List<TeachObj_FieldWork> findTeachObj_FieldWorkListBySyllabusid(
			String syllabusId_Copy);

}
