package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.Professional;

public class ProfessionalDaoImpl extends BaseDaoImpl<Professional> implements ProfessionalDao {

	public List<Professional> findbydepartment(Department department) {
		List<Professional> professionallist = this.getHibernateTemplate().find("from Professional where department = ?", department);
		if(professionallist.size()!=0)
		{
			return professionallist;
		}else{
			return null;
		}
	}
}
