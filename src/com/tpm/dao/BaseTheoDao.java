package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DistributeHour_Theo;

public interface BaseTheoDao extends BaseDao<BaseTheo>{

	void addDistributeHour_Theo(DistributeHour_Theo distributeHour_Theo);

	void deleteDistributeHour_Theo(String syllabusId);

	List<DistributeHour_Theo> findDistributeHour_Theo(String syllabusId);

}
