package com.tpm.dao;

import java.util.List;

import com.tpm.dao.BaseDaoImpl;
import com.tpm.dao.DistributeHour_CourseDesignDao;
import com.tpm.entity.DistributeHour_CourseDesign;
import com.tpm.entity.DistributeHour_Theo;

public class DistributeHour_CourseDesignDaoImpl extends BaseDaoImpl<DistributeHour_CourseDesign>
		implements DistributeHour_CourseDesignDao {

	public List<DistributeHour_CourseDesign> getbydistributeHour_CourseDesign(String syllabusId_Copy) {
		List<DistributeHour_CourseDesign> distributeHour_CourseDesignlist;
		distributeHour_CourseDesignlist = this.getHibernateTemplate().find("from DistributeHour_CourseDesign where syllabusid=?", syllabusId_Copy);
		return distributeHour_CourseDesignlist;
	}


}
