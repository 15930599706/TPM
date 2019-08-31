package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BasePractice;
import com.tpm.entity.DistributeHour_Gra;
import com.tpm.entity.DistributeHour_Theo;

public interface BasePracticeDao extends BaseDao<BasePractice> {

	void addDistributeHour_Gra(DistributeHour_Gra distributeHour_Gra);

	void deleteDistributeHour_Gra(String syllabusId);

	List<DistributeHour_Gra> findDistributeHour_Theo(String syllabusId);

}
