package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.IndexSelect;
import com.tpm.entity.Professional;

public class AbilityAndTeachObjImpl extends BaseDaoImpl<AbilityAndTeachObj> implements AbilityAndTeachObjDao {

	public List<IndexSelect> findIndexSelectByAbility(Ability ability,String syllabusId) {
		List<IndexSelect> indexSelectList = new ArrayList<IndexSelect>();

		indexSelectList = this.getHibernateTemplate().find("from IndexSelect where ability=? and syllabusID=?",ability,syllabusId);

		return indexSelectList;
	}

	public void addSelectIndex(List<IndexSelect> newindexSelectList,String syllabusId) {
		List<IndexSelect> indexSelectList = new ArrayList<IndexSelect>();
		
		indexSelectList = this.getHibernateTemplate().find("from IndexSelect where syllabusID=?",syllabusId);

/*		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			for(int i=0;i<indexSelectList.size();i++)
			{
				this.getHibernateTemplate().delete(indexSelectList.get(i));
			}
		}
		if(newindexSelectList != null && newindexSelectList.size() !=0)
		{
			for(int i=0;i<newindexSelectList.size();i++){
				this.getHibernateTemplate().save(newindexSelectList.get(i));
			}
		}*/
		if(indexSelectList != null && indexSelectList.size() != 0)
		{
			if(indexSelectList.size() == newindexSelectList.size())
			{
				int tag = 0;
				for(int i=0;i<indexSelectList.size();i++)
				{				
					if(indexSelectList.get(i).getAbility() != newindexSelectList.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(indexSelectList.get(i).getAnalisis() != newindexSelectList.get(i).getAnalisis())
					{
						tag = 1;
						break;
					}
					if(!indexSelectList.get(i).getSyllabusID().equals(newindexSelectList.get(i).getSyllabusID()))
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
					
					for(int i=0;i<newindexSelectList.size();i++){
						this.getHibernateTemplate().save(newindexSelectList.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<indexSelectList.size();i++)
				{
					this.getHibernateTemplate().delete(indexSelectList.get(i));
				}
				
				for(int i=0;i<newindexSelectList.size();i++){
					this.getHibernateTemplate().save(newindexSelectList.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<newindexSelectList.size();i++){
				this.getHibernateTemplate().save(newindexSelectList.get(i));
			}
		}
	}

	public void addAbilityAndTeachObj(List<AbilityAndTeachObj> abilityAndTeachSelect,String syllabusId) {
		List<AbilityAndTeachObj> abilityAndTeachObjList = new ArrayList<AbilityAndTeachObj>();
		abilityAndTeachObjList = this.getHibernateTemplate().find("from AbilityAndTeachObj where syllabusID=?",syllabusId);

		/*		if(abilityAndTeachObjList != null && abilityAndTeachObjList.size() != 0){
			for(int i=0;i<abilityAndTeachObjList.size();i++)
			{
				this.getHibernateTemplate().delete(abilityAndTeachObjList.get(i));
			}
		}
		if(abilityAndTeachSelect != null && abilityAndTeachSelect.size() != 0)
		{
			for(int i=0;i<abilityAndTeachSelect.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachSelect.get(i));
			}
		}*/
		
		if(abilityAndTeachObjList != null && abilityAndTeachObjList.size() != 0)
		{
			if(abilityAndTeachObjList.size() == abilityAndTeachSelect.size())
			{
				int tag = 0;
				for(int i=0;i<abilityAndTeachObjList.size();i++)
				{				
					if(abilityAndTeachObjList.get(i).getAbility() != abilityAndTeachSelect.get(i).getAbility())
					{
						tag = 1;
						break;
					}
					if(abilityAndTeachObjList.get(i).getTeachObj() != abilityAndTeachSelect.get(i).getTeachObj())
					{
						tag = 1;
						break;
					}
					if(!abilityAndTeachObjList.get(i).getSyllabusID().equals(abilityAndTeachSelect.get(i).getSyllabusID()))
					{
						tag = 1;
						break;
					}
				}
				if(tag == 1)
				{
					for(int i=0;i<abilityAndTeachObjList.size();i++)
					{
						this.getHibernateTemplate().delete(abilityAndTeachObjList.get(i));
					}
					for(int i=0;i<abilityAndTeachSelect.size();i++)
					{
						this.getHibernateTemplate().save(abilityAndTeachSelect.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<abilityAndTeachObjList.size();i++)
				{
					this.getHibernateTemplate().delete(abilityAndTeachObjList.get(i));
				}
				for(int i=0;i<abilityAndTeachSelect.size();i++)
				{
					this.getHibernateTemplate().save(abilityAndTeachSelect.get(i));
				}
			}
		}
		else
		{
			for(int i=0;i<abilityAndTeachSelect.size();i++)
			{
				this.getHibernateTemplate().save(abilityAndTeachSelect.get(i));
			}
		}
	
	}

	//通过大纲ID查询记录
	public ApplicationSyllabus findAppSyllabus(Integer syllabusId) {
		List<ApplicationSyllabus> applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus where syllabus.syllabusid=?",syllabusId);
		return applicationSyllabusList.get(0);
	}

	public List<AbilityAndTeachObj> findAbiAndTeachListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj where syllabusID=?",syllabusId_Copy);
	}

	//通过选择的毕业要求查询选择的教学目标
	public List<AbilityAndTeachObj> findTeachByAbility(Ability ability,String syllabusId) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj where ability=? and syllabusID=?",ability,syllabusId);
	}


}
