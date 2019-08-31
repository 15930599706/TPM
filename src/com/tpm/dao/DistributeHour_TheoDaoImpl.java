package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_Theo;

public class DistributeHour_TheoDaoImpl extends BaseDaoImpl<DistributeHour_Theo> implements
		DistributeHour_TheoDao {

	public List<DistributeHour_Theo> getbyDistributeHour_Theo(String syllabusId_Copy) {
		List<DistributeHour_Theo> distributeHour_Theolist;
		distributeHour_Theolist = this.getHibernateTemplate().find("from DistributeHour_Theo where syllabusid=?", syllabusId_Copy);
		return distributeHour_Theolist;
	}

}
