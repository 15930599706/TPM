package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_CourseDesign;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.IndexSelect_CourseDesign;
import com.tpm.entity.IndexSelect_FieldWork;

public class AbilityAndTeachObj_CourseDesignDaoImpl extends BaseDaoImpl<AbilityAndTeachObj_CourseDesign>
		implements AbilityAndTeachObj_CourseDesignDao {

	public ApplicationSyllabus_CourseDesign findAppSyllabus_CourseDesign(
			Integer syllabusId) {
		List<ApplicationSyllabus_CourseDesign> applicationSyllabus_CourseDesignList = this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where syllabus_CourseDesign.syllabus_CourseDesignid=?",syllabusId);
		return applicationSyllabus_CourseDesignList.get(0);

	}

	public List<IndexSelect_CourseDesign> findIndexSelectByAbility_CourseDesign(Ability ability, String syllabusId) {
		List<IndexSelect_CourseDesign> indexSelectList_CourseDesign = new ArrayList<IndexSelect_CourseDesign>();

		indexSelectList_CourseDesign = this.getHibernateTemplate().find("from IndexSelect_CourseDesign where ability=? and syllabus_CourseDesignid=?",ability,syllabusId);

		return indexSelectList_CourseDesign;
	}

	public void addSelectIndex_CourseDesign(List<IndexSelect_CourseDesign> newindexSelect_CourseDesignList,String syllabusId) {
		List<IndexSelect_CourseDesign> indexSelectList = new ArrayList<IndexSelect_CourseDesign>();
		
		indexSelectList = this.getHibernateTemplate().find("from IndexSelect_CourseDesign where syllabus_CourseDesignid=?",syllabusId);

/*		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			for(int i=0;i<indexSelectList.size();i++)
			{
				this.getHibernateTemplate().delete(indexSelectList.get(i));
			}
		}
		if(newindexSelect_CourseDesignList != null && newindexSelect_CourseDesignList.size() !=0)
		{
			for(int i=0;i<newindexSelect_CourseDesignList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_CourseDesignList.get(i));
			}
		}*/
		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			if(indexSelectList.size() == newindexSelect_CourseDesignList.size())
			{
				int tag = 0;
				for(int i=0;i<indexSelectList.size();i++)
				{				
					if(indexSelectList.get(i).getAbility() != newindexSelect_CourseDesignList.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(indexSelectList.get(i).getAnalisis() != newindexSelect_CourseDesignList.get(i).getAnalisis())
					{
						tag = 1;
						break;
					}
					if(!indexSelectList.get(i).getSyllabus_CourseDesignid().equals(newindexSelect_CourseDesignList.get(i).getSyllabus_CourseDesignid()))
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
					
					for(int i=0;i<newindexSelect_CourseDesignList.size();i++){
						this.getHibernateTemplate().save(newindexSelect_CourseDesignList.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<indexSelectList.size();i++)
				{
					this.getHibernateTemplate().delete(indexSelectList.get(i));
				}
				
				for(int i=0;i<newindexSelect_CourseDesignList.size();i++){
					this.getHibernateTemplate().save(newindexSelect_CourseDesignList.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<newindexSelect_CourseDesignList.size();i++){
				this.getHibernateTemplate().save(newindexSelect_CourseDesignList.get(i));
			}
		}
		
	}

	public void addAbilityAndTeachObj_CourseDesign(List<AbilityAndTeachObj_CourseDesign> abilityAndTeachObjSelect_CourseDesign,String syllabusId) {
		List<AbilityAndTeachObj_CourseDesign> abilityAndTeachObj_CourseDesignList = new ArrayList<AbilityAndTeachObj_CourseDesign>();
		abilityAndTeachObj_CourseDesignList = this.getHibernateTemplate().find("from AbilityAndTeachObj_FieldWork where syllabus_FieldWorkid=?",syllabusId);
/*		if(abilityAndTeachObj_CourseDesignList != null && abilityAndTeachObj_CourseDesignList.size() != 0){
			for(int i=0;i<abilityAndTeachObj_CourseDesignList.size();i++)
			{
				this.getHibernateTemplate().delete(abilityAndTeachObj_CourseDesignList.get(i));
			}
		}
		if(abilityAndTeachObjSelect_CourseDesign != null && abilityAndTeachObjSelect_CourseDesign.size() != 0)
		{
			for(int i=0;i<abilityAndTeachObjSelect_CourseDesign.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_CourseDesign.get(i));
			}
		}*/
		if(abilityAndTeachObj_CourseDesignList != null && abilityAndTeachObj_CourseDesignList.size() != 0)
		{
			if(abilityAndTeachObj_CourseDesignList.size() == abilityAndTeachObjSelect_CourseDesign.size())
			{
				int tag = 0;
				for(int i=0;i<abilityAndTeachObj_CourseDesignList.size();i++)
				{				
					if(abilityAndTeachObj_CourseDesignList.get(i).getAbility() != abilityAndTeachObjSelect_CourseDesign.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(abilityAndTeachObj_CourseDesignList.get(i).getTeachObj_CourseDesign() != abilityAndTeachObjSelect_CourseDesign.get(i).getTeachObj_CourseDesign())
					{
						tag = 1;
						break;
					}
					if(!abilityAndTeachObj_CourseDesignList.get(i).getSyllabus_CourseDesignid().equals(abilityAndTeachObjSelect_CourseDesign.get(i).getSyllabus_CourseDesignid()))
					{
						tag = 1;
						break;
					}
				}
				if(tag == 1)
				{
					for(int i=0;i<abilityAndTeachObj_CourseDesignList.size();i++)
					{
						this.getHibernateTemplate().delete(abilityAndTeachObj_CourseDesignList.get(i));
					}
					for(int i=0;i<abilityAndTeachObjSelect_CourseDesign.size();i++)
					{
						this.getHibernateTemplate().save(abilityAndTeachObjSelect_CourseDesign.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<abilityAndTeachObj_CourseDesignList.size();i++)
				{
					this.getHibernateTemplate().delete(abilityAndTeachObj_CourseDesignList.get(i));
				}
				for(int i=0;i<abilityAndTeachObjSelect_CourseDesign.size();i++)
				{
					this.getHibernateTemplate().save(abilityAndTeachObjSelect_CourseDesign.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<abilityAndTeachObjSelect_CourseDesign.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachObjSelect_CourseDesign.get(i));
			}
		}
		
	}

	public List<AbilityAndTeachObj_CourseDesign> findAbiAndTeachListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_CourseDesign where syllabus_CourseDesignid=?",syllabusId_Copy);
		
	}

	public List<AbilityAndTeachObj_CourseDesign> findTeachByAbility_CourseDesign(
			Ability ability, String syllabusId) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_CourseDesign where ability=? and syllabus_CourseDesignid=?",ability,syllabusId);
	}

}
