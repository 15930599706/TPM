package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_Gra;

public class DistributeHour_GraDaoImpl extends BaseDaoImpl<DistributeHour_Gra> implements
		DistributeHour_GraDao {

	public List<DistributeHour_Gra> getbyDistributeHour_Gra(String syllabusId_Copy) {
		List<DistributeHour_Gra> DistributeHour_Gralist;
		DistributeHour_Gralist = this.getHibernateTemplate().find("from DistributeHour_Gra where syllabusid=?", syllabusId_Copy);
		return DistributeHour_Gralist;
	}

}
