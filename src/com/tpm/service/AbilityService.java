package com.tpm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.AbilityDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.entity.Ability;
import com.tpm.entity.Department;

@Transactional
public class AbilityService {
	private AbilityDao abilityDao;
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void setAbilityDao(AbilityDao abilityDao) {
		this.abilityDao = abilityDao;
	}

	public void updateability(List<Integer> abilityid,
			List<String> abilityname, List<String> abilitycontent,
			String departmentid) {
		for(int i=0;i<abilityid.size();i++){
			Ability ability = abilityDao.get(abilityid.get(i));
			ability.setAbilityname(abilityname.get(i));
			ability.setAbilitycontent(abilitycontent.get(i));
			ability.setDepartment(departmentDao.get(departmentid));
			abilityDao.update(ability);
		}
	}
	public Ability getbytitle(String string, String departmentid) {
		Department department = departmentDao.get(departmentid);
		return abilityDao.getbyabilityname(string,department);
	}
}
