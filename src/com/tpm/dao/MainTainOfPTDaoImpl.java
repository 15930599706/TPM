package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPT;
import com.tpm.entity.Professional;
import com.tpm.entity.User;
@SuppressWarnings("all")
public class MainTainOfPTDaoImpl extends BaseDaoImpl<MainTainOfPT> implements MainTainOfPTDao {

	public List<MainTainOfPT> findbydepartment(Department department) {
		List<MainTainOfPT> mainTainOfPTlist = (List<MainTainOfPT>)this.getHibernateTemplate().
				find("from MainTainOfPT where department=?", department);
		if(mainTainOfPTlist !=null && mainTainOfPTlist.size() != 0){
			return mainTainOfPTlist;
		}else{
			return null;
		}
	}


}
