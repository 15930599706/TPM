package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPTTag;

public class MainTainOfPTTagDaoImpl extends BaseDaoImpl<MainTainOfPTTag> implements
		MainTainOfPTTagDao {

	public List<MainTainOfPTTag> findbydepartment(Department department) {
		List<MainTainOfPTTag> MainTainOfPTTaglist = this.getHibernateTemplate().find("from MainTainOfPTTag where department = ?",department);
		if(MainTainOfPTTaglist != null && MainTainOfPTTaglist.size() != 0){
			return MainTainOfPTTaglist;
		}else{
			return null;
		}
	}


}
