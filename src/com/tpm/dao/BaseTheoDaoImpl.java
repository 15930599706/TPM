package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BasePractice;
import com.tpm.entity.BaseTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DistributeHour_Theo;

public class BaseTheoDaoImpl extends BaseDaoImpl<BaseTheo> implements BaseTheoDao {

	public void addDistributeHour_Theo(DistributeHour_Theo distributeHour_Theo) {
		this.getHibernateTemplate().save(distributeHour_Theo);
		
	}

	public void deleteDistributeHour_Theo(String syllabusId) {
		List<DistributeHour_Theo> distributeHour_Theolsit = this.getHibernateTemplate().find("from DistributeHour_Theo where syllabusid=?",syllabusId);
		for(int i=0;i<distributeHour_Theolsit.size();i++)
		{
			this.getHibernateTemplate().delete(distributeHour_Theolsit.get(i));
		}
				
		
	}

	public List<DistributeHour_Theo> findDistributeHour_Theo(String syllabusId) 
	{
		return this.getHibernateTemplate().find("from DistributeHour_Theo where syllabusid=?",syllabusId);
	}

}
