package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BasePractice;
import com.tpm.entity.DistributeHour_Gra;
import com.tpm.entity.DistributeHour_Theo;

public class BasePracticeDaoImpl extends BaseDaoImpl<BasePractice> implements BasePracticeDao{

	public void deleteDistributeHour_Gra(String syllabusId) {
		List<DistributeHour_Gra> distributeHour_Gralsit = this.getHibernateTemplate().find("from DistributeHour_Gra where syllabusid=?",syllabusId);
		for(int i=0;i<distributeHour_Gralsit.size();i++)
		{
			this.getHibernateTemplate().delete(distributeHour_Gralsit.get(i));
		}
		
	}

	public void addDistributeHour_Gra(DistributeHour_Gra distributeHour_Gra) {
		this.getHibernateTemplate().save(distributeHour_Gra);
		
	}

	public List<DistributeHour_Gra> findDistributeHour_Theo(String syllabusId) {
		return this.getHibernateTemplate().find("from DistributeHour_Gra where syllabusid=?",syllabusId);
	}

}
