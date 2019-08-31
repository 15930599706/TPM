package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationMainTainOfPT;
import com.tpm.entity.Professional;

public class ApplicationMainTainOfPTDaoImpl extends BaseDaoImpl<ApplicationMainTainOfPT> implements
		ApplicationMainTainOfPTDao {

	public List<ApplicationMainTainOfPT> findbyProfessional(
			Professional professional) {
		List<ApplicationMainTainOfPT> applicationMainTainOfPTList = (List<ApplicationMainTainOfPT>)this.getHibernateTemplate().find("from ApplicationMainTainOfPT where professional=?", professional);
		if(applicationMainTainOfPTList != null && applicationMainTainOfPTList.size() != 0)
		{
			return applicationMainTainOfPTList;
		}
		else{
			return null;
		}
	}

}
