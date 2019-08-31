package com.tpm.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tpm.entity.Department;
import com.tpm.entity.Professional;

public class LinkDaoImpl extends HibernateDaoSupport  implements LinkDao {

	//查找对应专业的专业方向
	public List<Professional> findProfessional(Department department) {
		return this.getHibernateTemplate().find("from Professional where department=?",department);
	}

}
