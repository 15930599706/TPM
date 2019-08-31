package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.AbilityAndTeachObj_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.IndexSelect_FieldWork;
import com.tpm.entity.IndexSelect_InnerExperiment;

public class AbilityAndTeachObj_InnerExperimentDaoImpl extends BaseDaoImpl<AbilityAndTeachObj_InnerExperiment> implements AbilityAndTeachObj_InnerExperimentDao{

	public ApplicationSyllabus_InnerExperiment findAppSyllabus_InnerExperiment(Integer syllabusId) {
		List<ApplicationSyllabus_InnerExperiment> applicationSyllabus_InnerExperimentList = this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where syllabus_InnerExperiment.syllabus_InnerExperimentid=?",syllabusId);
		return applicationSyllabus_InnerExperimentList.get(0);
	}

	public List<IndexSelect_InnerExperiment> findIndexSelectByAbility_InnerExperiment(Ability ability, String syllabusId) {
		List<IndexSelect_InnerExperiment> indexSelectList_InnerExperiment = new ArrayList<IndexSelect_InnerExperiment>();

		indexSelectList_InnerExperiment = this.getHibernateTemplate().find("from IndexSelect_InnerExperiment where ability=? and syllabus_InnerExperimentid=?",ability,syllabusId);

		return indexSelectList_InnerExperiment;
	}

	public void addSelectIndex_InnerExperiment(List<IndexSelect_InnerExperiment> newindexSelect_InnerExperimentList,String syllabusId) {
		List<IndexSelect_InnerExperiment> indexSelectList = new ArrayList<IndexSelect_InnerExperiment>();
		
		indexSelectList = this.getHibernateTemplate().find("from IndexSelect_InnerExperiment where syllabus_InnerExperimentid=?",syllabusId);

/*		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			for(int i=0;i<indexSelectList.size();i++)
			{
				this.getHibernateTemplate().delete(indexSelectList.get(i));
			}
		}
		if(newindexSelect_InnerExperimentList != null && newindexSelect_InnerExperimentList.size() !=0)
		{
			for(int i=0;i<newindexSelect_InnerExperimentList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_InnerExperimentList.get(i));
			}
		}*/
		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			if(indexSelectList.size() == newindexSelect_InnerExperimentList.size())
			{
				int tag = 0;
				for(int i=0;i<indexSelectList.size();i++)
				{				
					if(indexSelectList.get(i).getAbility() != newindexSelect_InnerExperimentList.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(indexSelectList.get(i).getAnalisis() != newindexSelect_InnerExperimentList.get(i).getAnalisis())
					{
						tag = 1;
						break;
					}
					if(!indexSelectList.get(i).getSyllabus_InnerExperimentid().equals(newindexSelect_InnerExperimentList.get(i).getSyllabus_InnerExperimentid()))
					{
						tag = 1;
						break;
					}
				}
				if(tag == 1)
				{
					for(int i=0;i<indexSelectList.size();i++)
					{
						this.getHibernateTemplate().delete(indexSelectList.get(i));
					}
					
					for(int i=0;i<newindexSelect_InnerExperimentList.size();i++){
						this.getHibernateTemplate().save(newindexSelect_InnerExperimentList.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<indexSelectList.size();i++)
				{
					this.getHibernateTemplate().delete(indexSelectList.get(i));
				}
				
				for(int i=0;i<newindexSelect_InnerExperimentList.size();i++){
					this.getHibernateTemplate().save(newindexSelect_InnerExperimentList.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<newindexSelect_InnerExperimentList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_InnerExperimentList.get(i));
			}
		}
	}

	public void addAbilityAndTeachObj_InnerExperiment(List<AbilityAndTeachObj_InnerExperiment> abilityAndTeachObjSelect_InnerExperiment,String syllabusId) {
		List<AbilityAndTeachObj_InnerExperiment> abilityAndTeachObj_InnerExperimentList = new ArrayList<AbilityAndTeachObj_InnerExperiment>();
		abilityAndTeachObj_InnerExperimentList = this.getHibernateTemplate().find("from AbilityAndTeachObj_InnerExperiment where syllabus_InnerExperimentid=?",syllabusId);
/*		if(abilityAndTeachObj_InnerExperimentList != null && abilityAndTeachObj_InnerExperimentList.size() != 0){
			for(int i=0;i<abilityAndTeachObj_InnerExperimentList.size();i++)
			{
				this.getHibernateTemplate().delete(abilityAndTeachObj_InnerExperimentList.get(i));
			}
		}
		if(abilityAndTeachObjSelect_InnerExperiment != null && abilityAndTeachObjSelect_InnerExperiment.size() != 0)
		{
			for(int i=0;i<abilityAndTeachObjSelect_InnerExperiment.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_InnerExperiment.get(i));
			}
		}*/
		if(abilityAndTeachObj_InnerExperimentList != null && abilityAndTeachObj_InnerExperimentList.size() != 0)
		{
			if(abilityAndTeachObj_InnerExperimentList.size() == abilityAndTeachObjSelect_InnerExperiment.size())
			{
				int tag = 0;
				for(int i=0;i<abilityAndTeachObj_InnerExperimentList.size();i++)
				{				
					if(abilityAndTeachObj_InnerExperimentList.get(i).getAbility() != abilityAndTeachObjSelect_InnerExperiment.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(abilityAndTeachObj_InnerExperimentList.get(i).getTeachObj_InnerExperiment() != abilityAndTeachObjSelect_InnerExperiment.get(i).getTeachObj_InnerExperiment())
					{
						tag = 1;
						break;
					}
					if(!abilityAndTeachObj_InnerExperimentList.get(i).getSyllabus_InnerExperimentid().equals(abilityAndTeachObjSelect_InnerExperiment.get(i).getSyllabus_InnerExperimentid()))
					{
						tag = 1;
						break;
					}
				}
				if(tag == 1)
				{
					for(int i=0;i<abilityAndTeachObj_InnerExperimentList.size();i++)
					{
						this.getHibernateTemplate().delete(abilityAndTeachObj_InnerExperimentList.get(i));
					}
					for(int i=0;i<abilityAndTeachObjSelect_InnerExperiment.size();i++)
					{
						this.getHibernateTemplate().save(abilityAndTeachObjSelect_InnerExperiment.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<abilityAndTeachObj_InnerExperimentList.size();i++)
				{
					this.getHibernateTemplate().delete(abilityAndTeachObj_InnerExperimentList.get(i));
				}
				for(int i=0;i<abilityAndTeachObjSelect_InnerExperiment.size();i++)
				{
					this.getHibernateTemplate().save(abilityAndTeachObjSelect_InnerExperiment.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<abilityAndTeachObjSelect_InnerExperiment.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_InnerExperiment.get(i));
			}
		}
		
	}

	public List<AbilityAndTeachObj_InnerExperiment> findAbiAndTeachListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_InnerExperiment where syllabus_InnerExperimentid=?",syllabusId_Copy);
		
	}

	public List<AbilityAndTeachObj_InnerExperiment> findTeachByAbility_InnerExperiment(
			Ability ability, String syllabusId) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_InnerExperiment where ability=? and syllabus_InnerExperimentid=?",ability,syllabusId);
	}

}
