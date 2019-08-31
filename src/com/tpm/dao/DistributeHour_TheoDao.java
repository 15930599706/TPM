package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_Theo;

public interface DistributeHour_TheoDao extends BaseDao<DistributeHour_Theo> {

	List<DistributeHour_Theo> getbyDistributeHour_Theo(String syllabusId_Copy);

}
