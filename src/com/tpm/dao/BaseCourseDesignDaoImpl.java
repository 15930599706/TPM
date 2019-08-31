package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseCourseDesign;
import com.tpm.entity.BasePractice;
import com.tpm.entity.DistributeHour_CourseDesign;
import com.tpm.entity.DistributeHour_Gra;

public class BaseCourseDesignDaoImpl extends BaseDaoImpl<BaseCourseDesign> implements BaseCourseDesignDao {

	public List<DistributeHour_CourseDesign> findDistributeHour_CourseDesign(
			String syllabusId) {
		return this.getHibernateTemplate().find("from DistributeHour_CourseDesign where syllabusid=?",syllabusId);
	}

	public void deleteDistributeHour_CourseDesign(String syllabusId) {
		List<DistributeHour_CourseDesign> distributeHour_CourseDesignlsit = this.getHibernateTemplate().find("from DistributeHour_CourseDesign where syllabusid=?",syllabusId);
		for(int i=0;i<distributeHour_CourseDesignlsit.size();i++)
		{
			this.getHibernateTemplate().delete(distributeHour_CourseDesignlsit.get(i));
		}
		
		
	}

	public void addDistributeHour_CourseDesign(DistributeHour_CourseDesign distributeHour_CourseDesign) {
		this.getHibernateTemplate().save(distributeHour_CourseDesign);
		
	}
}
