package com.tpm.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tpm.entity.College;
@SuppressWarnings("all")
public class CollegeDaoImpl extends BaseDaoImpl<College> implements CollegeDao {

	public College getbyname(String collegename) {
		List<College> colist = (List<College>)this.getHibernateTemplate().
				find("from College where collegeCname = ?", collegename);
		if(colist.size() != 0){
			return colist.get(0);
		}else{
			return null;
		}
	}

	public List<College> getone(String collegeid) {
		List<College> colist = (List<College>)this.getHibernateTemplate().
				find("from College where collegeid = ?", collegeid);
		return colist;
	}

}
