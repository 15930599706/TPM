package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.AbilityAndTeachObj_Gra;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.IndexSelect_FieldWork;
import com.tpm.entity.IndexSelect_Gra;

public class AbilityAndTeachObj_GraDaoImpl extends BaseDaoImpl<AbilityAndTeachObj_Gra> implements AbilityAndTeachObj_GraDao{

	public ApplicationSyllabus_Gra findAppSyllabus_Gra(Integer syllabusId) {
		List<ApplicationSyllabus_Gra> applicationSyllabus_Gra = this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where syllabus_Gra.syllabus_Graid=?",syllabusId);
		return applicationSyllabus_Gra.get(0);
	}

	public List<IndexSelect_Gra> findIndexSelectByAbility_Gra(Ability ability,String syllabusId) {
		List<IndexSelect_Gra> indexSelectList_Gra = new ArrayList<IndexSelect_Gra>();

		indexSelectList_Gra = this.getHibernateTemplate().find("from IndexSelect_Gra where ability=? and syllabus_Graid=?",ability,syllabusId);

		return indexSelectList_Gra;
	}

	public void addSelectIndex_Gra(List<IndexSelect_Gra> newindexSelect_GraList, String syllabusId) {
		List<IndexSelect_Gra> indexSelectList = new ArrayList<IndexSelect_Gra>();
		
		indexSelectList = this.getHibernateTemplate().find("from IndexSelect_Gra where syllabus_Graid=?",syllabusId);

/*		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			for(int i=0;i<indexSelectList.size();i++)
			{
				this.getHibernateTemplate().delete(indexSelectList.get(i));
			}
		}
		if(newindexSelect_GraList != null && newindexSelect_GraList.size() !=0)
		{
			for(int i=0;i<newindexSelect_GraList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_GraList.get(i));
			}
		}*/
		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			if(indexSelectList.size() == newindexSelect_GraList.size())
			{
				int tag = 0;
				for(int i=0;i<indexSelectList.size();i++)
				{				
					if(indexSelectList.get(i).getAbility() != newindexSelect_GraList.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(indexSelectList.get(i).getAnalisis() != newindexSelect_GraList.get(i).getAnalisis())
					{
						tag = 1;
						break;
					}
					if(!indexSelectList.get(i).getSyllabus_Graid().equals(newindexSelect_GraList.get(i).getSyllabus_Graid()))
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
					
					for(int i=0;i<newindexSelect_GraList.size();i++){
						this.getHibernateTemplate().save(newindexSelect_GraList.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<indexSelectList.size();i++)
				{
					this.getHibernateTemplate().delete(indexSelectList.get(i));
				}
				
				for(int i=0;i<newindexSelect_GraList.size();i++){
					this.getHibernateTemplate().save(newindexSelect_GraList.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<newindexSelect_GraList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_GraList.get(i));
			}
		}
	}

	public void addAbilityAndTeachObj_Gra(List<AbilityAndTeachObj_Gra> abilityAndTeachObjSelect_Gra,String syllabusId) {
		List<AbilityAndTeachObj_Gra> abilityAndTeachObj_GraList = new ArrayList<AbilityAndTeachObj_Gra>();
		abilityAndTeachObj_GraList = this.getHibernateTemplate().find("from AbilityAndTeachObj_FieldWork where syllabus_FieldWorkid=?",syllabusId);
/*		if(abilityAndTeachObj_GraList != null && abilityAndTeachObj_GraList.size() != 0){
			for(int i=0;i<abilityAndTeachObj_GraList.size();i++)
			{
				this.getHibernateTemplate().delete(abilityAndTeachObj_GraList.get(i));
			}
		}
		if(abilityAndTeachObjSelect_Gra != null && abilityAndTeachObjSelect_Gra.size() != 0)
		{
			for(int i=0;i<abilityAndTeachObjSelect_Gra.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_Gra.get(i));
			}
		}*/
		if(abilityAndTeachObj_GraList != null && abilityAndTeachObj_GraList.size() != 0)
		{
			if(abilityAndTeachObj_GraList.size() == abilityAndTeachObjSelect_Gra.size())
			{
				int tag = 0;
				for(int i=0;i<abilityAndTeachObj_GraList.size();i++)
				{				
					if(abilityAndTeachObj_GraList.get(i).getAbility() != abilityAndTeachObjSelect_Gra.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(abilityAndTeachObj_GraList.get(i).getTeachObj_Gra() != abilityAndTeachObjSelect_Gra.get(i).getTeachObj_Gra())
					{
						tag = 1;
						break;
					}
					if(!abilityAndTeachObj_GraList.get(i).getSyllabus_Graid().equals(abilityAndTeachObjSelect_Gra.get(i).getSyllabus_Graid()))
					{
						tag = 1;
						break;
					}
				}
				if(tag == 1)
				{
					for(int i=0;i<abilityAndTeachObj_GraList.size();i++)
					{
						this.getHibernateTemplate().delete(abilityAndTeachObj_GraList.get(i));
					}
					for(int i=0;i<abilityAndTeachObjSelect_Gra.size();i++)
					{
						this.getHibernateTemplate().save(abilityAndTeachObjSelect_Gra.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<abilityAndTeachObj_GraList.size();i++)
				{
					this.getHibernateTemplate().delete(abilityAndTeachObj_GraList.get(i));
				}
				for(int i=0;i<abilityAndTeachObjSelect_Gra.size();i++)
				{
					this.getHibernateTemplate().save(abilityAndTeachObjSelect_Gra.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<abilityAndTeachObjSelect_Gra.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_Gra.get(i));
			}
		}
	}

	public List<AbilityAndTeachObj_Gra> findAbiAndTeachListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_Gra where syllabus_Graid=?",syllabusId_Copy);
	}

	public List<AbilityAndTeachObj_Gra> findTeachByAbility_Gra(Ability ability,
			String syllabusId) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_Gra where ability=? and syllabus_Graid=?",ability,syllabusId);
	}

}
