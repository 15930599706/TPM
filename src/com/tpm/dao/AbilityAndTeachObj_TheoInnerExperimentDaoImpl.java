package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_InnerExperiment;
import com.tpm.entity.AbilityAndTeachObj_TheoInnerExperiment;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.IndexSelect_InnerExperiment;
import com.tpm.entity.IndexSelect_TheoInnerExperiment;

public class AbilityAndTeachObj_TheoInnerExperimentDaoImpl extends
		BaseDaoImpl<AbilityAndTeachObj_TheoInnerExperiment> implements AbilityAndTeachObj_TheoInnerExperimentDao {

	public ApplicationSyllabus_TheoInnerExperiment findAppSyllabus_TheoInnerExperiment(Integer syllabusId) {
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabus_TheoInnerExperimentList = this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where syllabus_TheoInnerExperiment.syllabus_TheoInnerExperimentid=?",syllabusId);
		return applicationSyllabus_TheoInnerExperimentList.get(0);
	}

	public List<IndexSelect_TheoInnerExperiment> findIndexSelect_TheoInnerExperimentByAbility(Ability ability, String syllabusId) {
		List<IndexSelect_TheoInnerExperiment> indexSelectList_TheoInnerExperiment = new ArrayList<IndexSelect_TheoInnerExperiment>();

		indexSelectList_TheoInnerExperiment = this.getHibernateTemplate().find("from IndexSelect_TheoInnerExperiment where ability=? and syllabusid=?",ability,syllabusId);

		return indexSelectList_TheoInnerExperiment;
	}

	public void addSelectIndex_TheoInnerExperiment(List<IndexSelect_TheoInnerExperiment> newindexSelect_TheoInnerExperimentList,String syllabusId) {
		List<IndexSelect_TheoInnerExperiment> indexSelectList = new ArrayList<IndexSelect_TheoInnerExperiment>();
		
		indexSelectList = this.getHibernateTemplate().find("from IndexSelect_TheoInnerExperiment where syllabusid=?",syllabusId);

/*		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			for(int i=0;i<indexSelectList.size();i++)
			{
				this.getHibernateTemplate().delete(indexSelectList.get(i));
			}
		}
		if(newindexSelect_TheoInnerExperimentList != null && newindexSelect_TheoInnerExperimentList.size() !=0)
		{
			for(int i=0;i<newindexSelect_TheoInnerExperimentList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_TheoInnerExperimentList.get(i));
			}
		}*/
		
		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			if(indexSelectList.size() == newindexSelect_TheoInnerExperimentList.size())
			{
				int tag = 0;
				for(int i=0;i<indexSelectList.size();i++)
				{				
					if(indexSelectList.get(i).getAbility() != newindexSelect_TheoInnerExperimentList.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(indexSelectList.get(i).getAnalisis() != newindexSelect_TheoInnerExperimentList.get(i).getAnalisis())
					{
						tag = 1;
						break;
					}
					if(!indexSelectList.get(i).getSyllabusid().equals(newindexSelect_TheoInnerExperimentList.get(i).getSyllabusid()))
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
					
					for(int i=0;i<newindexSelect_TheoInnerExperimentList.size();i++){
						this.getHibernateTemplate().save(newindexSelect_TheoInnerExperimentList.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<indexSelectList.size();i++)
				{
					this.getHibernateTemplate().delete(indexSelectList.get(i));
				}
				
				for(int i=0;i<newindexSelect_TheoInnerExperimentList.size();i++){
					this.getHibernateTemplate().save(newindexSelect_TheoInnerExperimentList.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<newindexSelect_TheoInnerExperimentList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_TheoInnerExperimentList.get(i));
			}
		}
		
	}

	public void addAbilityAndTeachObj_TheoInnerExperiment(List<AbilityAndTeachObj_TheoInnerExperiment> abilityAndTeachObjSelect_TheoInnerExperiment,String syllabusId) {
		List<AbilityAndTeachObj_TheoInnerExperiment> abilityAndTeachObj_TheoInnerExperimentList = new ArrayList<AbilityAndTeachObj_TheoInnerExperiment>();
		abilityAndTeachObj_TheoInnerExperimentList = this.getHibernateTemplate().find("from AbilityAndTeachObj_TheoInnerExperiment where syllabusid=?",syllabusId);
/*		if(abilityAndTeachObj_TheoInnerExperimentList != null && abilityAndTeachObj_TheoInnerExperimentList.size() != 0){
			for(int i=0;i<abilityAndTeachObj_TheoInnerExperimentList.size();i++)
			{
				this.getHibernateTemplate().delete(abilityAndTeachObj_TheoInnerExperimentList.get(i));
			}
		}
		if(abilityAndTeachObjSelect_TheoInnerExperiment != null && abilityAndTeachObjSelect_TheoInnerExperiment.size() != 0)
		{
			for(int i=0;i<abilityAndTeachObjSelect_TheoInnerExperiment.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_TheoInnerExperiment.get(i));
			}
		}*/
		if(abilityAndTeachObj_TheoInnerExperimentList != null && abilityAndTeachObj_TheoInnerExperimentList.size() != 0)
		{
			if(abilityAndTeachObj_TheoInnerExperimentList.size() == abilityAndTeachObjSelect_TheoInnerExperiment.size())
			{
				int tag = 0;
				for(int i=0;i<abilityAndTeachObj_TheoInnerExperimentList.size();i++)
				{				
					if(abilityAndTeachObj_TheoInnerExperimentList.get(i).getAbility() != abilityAndTeachObjSelect_TheoInnerExperiment.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(abilityAndTeachObj_TheoInnerExperimentList.get(i).getTeachObj_TheoInnerExperiment() != abilityAndTeachObjSelect_TheoInnerExperiment.get(i).getTeachObj_TheoInnerExperiment())
					{
						tag = 1;
						break;
					}
					if(!abilityAndTeachObj_TheoInnerExperimentList.get(i).getSyllabusid().equals(abilityAndTeachObjSelect_TheoInnerExperiment.get(i).getSyllabusid()))
					{
						tag = 1;
						break;
					}
				}
				if(tag == 1)
				{
					for(int i=0;i<abilityAndTeachObj_TheoInnerExperimentList.size();i++)
					{
						this.getHibernateTemplate().delete(abilityAndTeachObj_TheoInnerExperimentList.get(i));
					}
					for(int i=0;i<abilityAndTeachObjSelect_TheoInnerExperiment.size();i++)
					{
						this.getHibernateTemplate().save(abilityAndTeachObjSelect_TheoInnerExperiment.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<abilityAndTeachObj_TheoInnerExperimentList.size();i++)
				{
					this.getHibernateTemplate().delete(abilityAndTeachObj_TheoInnerExperimentList.get(i));
				}
				for(int i=0;i<abilityAndTeachObjSelect_TheoInnerExperiment.size();i++)
				{
					this.getHibernateTemplate().save(abilityAndTeachObjSelect_TheoInnerExperiment.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<abilityAndTeachObjSelect_TheoInnerExperiment.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_TheoInnerExperiment.get(i));
			}
		}
		
	}

	public List<AbilityAndTeachObj_TheoInnerExperiment> findAbiAndTeachListBySyllabusid_TheoInnerExperiment(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_TheoInnerExperiment where syllabusid=?",syllabusId_Copy);

	}

	public List<AbilityAndTeachObj_TheoInnerExperiment> findTeachByAbility_TheoInnerExperiment(
			Ability ability, String syllabusId) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_TheoInnerExperiment where ability=? and syllabusid=?",ability,syllabusId);
	}

}
