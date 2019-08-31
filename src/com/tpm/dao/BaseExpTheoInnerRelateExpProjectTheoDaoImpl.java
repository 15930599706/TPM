package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseExpTheoInnerRelateExpProjectTheo;

public class BaseExpTheoInnerRelateExpProjectTheoDaoImpl extends BaseDaoImpl<BaseExpTheoInnerRelateExpProjectTheo>
	implements BaseExpTheoInnerRelateExpProjectTheoDao{
	
	public List<BaseExpTheoInnerRelateExpProjectTheo> findRelateByExpid(Integer expid){
		return this.getHibernateTemplate().find("from BaseExpTheoInnerRelateExpProjectTheo where theoInnerExperiment.distributeHour_TheoInnerExperimentid=?",expid);
	}

}
