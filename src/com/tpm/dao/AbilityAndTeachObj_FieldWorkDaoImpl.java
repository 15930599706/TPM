package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.IndexSelect_FieldWork;

public class AbilityAndTeachObj_FieldWorkDaoImpl extends BaseDaoImpl<AbilityAndTeachObj_FieldWork>
		implements AbilityAndTeachObj_FieldWorkDao {

	public ApplicationSyllabus_FieldWork findAppSyllabus_FieldWork(Integer syllabusId) {
		List<ApplicationSyllabus_FieldWork> applicationSyllabus_FieldWorkList = this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where syllabus_FieldWork.syllabus_FieldWorkID=?",syllabusId);
		return applicationSyllabus_FieldWorkList.get(0);
	}

	public List<IndexSelect_FieldWork> findIndexSelectByAbility_FieldWork(Ability ability, String syllabusId) {
		List<IndexSelect_FieldWork> indexSelectList_FieldWork = new ArrayList<IndexSelect_FieldWork>();

		indexSelectList_FieldWork = this.getHibernateTemplate().find("from IndexSelect_FieldWork where ability=? and syllabus_FieldWorkid=?",ability,syllabusId);

		return indexSelectList_FieldWork;
	}

	public void addSelectIndex_FieldWork(List<IndexSelect_FieldWork> newindexSelect_FieldWorkList,String syllabusId) {
		List<IndexSelect_FieldWork> indexSelectList = new ArrayList<IndexSelect_FieldWork>();
		
		indexSelectList = this.getHibernateTemplate().find("from IndexSelect_FieldWork where syllabus_FieldWorkid=?",syllabusId);

/*		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			for(int i=0;i<indexSelectList.size();i++)
			{
				this.getHibernateTemplate().delete(indexSelectList.get(i));
			}
		}
		if(newindexSelect_FieldWorkList != null && newindexSelect_FieldWorkList.size() !=0)
		{
			for(int i=0;i<newindexSelect_FieldWorkList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_FieldWorkList.get(i));
			}
		}*/
		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			if(indexSelectList.size() == newindexSelect_FieldWorkList.size())
			{
				int tag = 0;
				for(int i=0;i<indexSelectList.size();i++)
				{				
					if(indexSelectList.get(i).getAbility() != newindexSelect_FieldWorkList.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(indexSelectList.get(i).getAnalisis() != newindexSelect_FieldWorkList.get(i).getAnalisis())
					{
						tag = 1;
						break;
					}
					if(!indexSelectList.get(i).getSyllabus_FieldWorkid().equals(newindexSelect_FieldWorkList.get(i).getSyllabus_FieldWorkid()))
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
					
					for(int i=0;i<newindexSelect_FieldWorkList.size();i++){
						this.getHibernateTemplate().save(newindexSelect_FieldWorkList.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<indexSelectList.size();i++)
				{
					this.getHibernateTemplate().delete(indexSelectList.get(i));
				}
				
				for(int i=0;i<newindexSelect_FieldWorkList.size();i++){
					this.getHibernateTemplate().save(newindexSelect_FieldWorkList.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<newindexSelect_FieldWorkList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_FieldWorkList.get(i));
			}
		}
	}

	public void addAbilityAndTeachObj_FieldWork(List<AbilityAndTeachObj_FieldWork> abilityAndTeachObjSelect_FieldWork,String syllabusId) {
		List<AbilityAndTeachObj_FieldWork> abilityAndTeachObj_FieldWorkList = new ArrayList<AbilityAndTeachObj_FieldWork>();
		abilityAndTeachObj_FieldWorkList = this.getHibernateTemplate().find("from AbilityAndTeachObj_FieldWork where syllabus_FieldWorkid=?",syllabusId);
/*		if(abilityAndTeachObj_FieldWorkList != null && abilityAndTeachObj_FieldWorkList.size() != 0){
			for(int i=0;i<abilityAndTeachObj_FieldWorkList.size();i++)
			{
				this.getHibernateTemplate().delete(abilityAndTeachObj_FieldWorkList.get(i));
			}
		}
		if(abilityAndTeachObjSelect_FieldWork != null && abilityAndTeachObjSelect_FieldWork.size() != 0)
		{
			for(int i=0;i<abilityAndTeachObjSelect_FieldWork.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_FieldWork.get(i));
			}
		}*/
		if(abilityAndTeachObj_FieldWorkList != null && abilityAndTeachObj_FieldWorkList.size() != 0)
		{
			if(abilityAndTeachObj_FieldWorkList.size() == abilityAndTeachObjSelect_FieldWork.size())
			{
				int tag = 0;
				for(int i=0;i<abilityAndTeachObj_FieldWorkList.size();i++)
				{				
					if(abilityAndTeachObj_FieldWorkList.get(i).getAbility() != abilityAndTeachObjSelect_FieldWork.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(abilityAndTeachObj_FieldWorkList.get(i).getTeachObj_FieldWork() != abilityAndTeachObjSelect_FieldWork.get(i).getTeachObj_FieldWork())
					{
						tag = 1;
						break;
					}
					if(!abilityAndTeachObj_FieldWorkList.get(i).getSyllabus_FieldWorkid().equals(abilityAndTeachObjSelect_FieldWork.get(i).getSyllabus_FieldWorkid()))
					{
						tag = 1;
						break;
					}
				}
				if(tag == 1)
				{
					for(int i=0;i<abilityAndTeachObj_FieldWorkList.size();i++)
					{
						this.getHibernateTemplate().delete(abilityAndTeachObj_FieldWorkList.get(i));
					}
					for(int i=0;i<abilityAndTeachObjSelect_FieldWork.size();i++)
					{
						this.getHibernateTemplate().save(abilityAndTeachObjSelect_FieldWork.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<abilityAndTeachObj_FieldWorkList.size();i++)
				{
					this.getHibernateTemplate().delete(abilityAndTeachObj_FieldWorkList.get(i));
				}
				for(int i=0;i<abilityAndTeachObjSelect_FieldWork.size();i++)
				{
					this.getHibernateTemplate().save(abilityAndTeachObjSelect_FieldWork.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<abilityAndTeachObjSelect_FieldWork.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_FieldWork.get(i));
			}
		}
	}

	public List<AbilityAndTeachObj_FieldWork> findAbiAndTeachListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_FieldWork where syllabus_FieldWorkid=?",syllabusId_Copy);
	}

	public List<AbilityAndTeachObj_FieldWork> findTeachByAbility_FieldWork(
			Ability ability, String syllabusId) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_FieldWork where ability=? and syllabus_FieldWorkid=?",ability,syllabusId);
	}


}
