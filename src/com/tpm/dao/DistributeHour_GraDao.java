package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_Gra;

public interface DistributeHour_GraDao extends BaseDao<DistributeHour_Gra> {

	List<DistributeHour_Gra> getbyDistributeHour_Gra(String syllabusId_Copy);

}
