package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
@SuppressWarnings("all")
public class AnalysisDaoImpl extends BaseDaoImpl<Analysis> implements AnalysisDao {

	public List<Analysis> getbyability(Ability ability) {
		List<Analysis> analysilist = (List<Analysis>)this.getHibernateTemplate().
				find("from Analysis where ability=?", ability);
		if(analysilist.size() != 0){
			return analysilist;
		}else{
			return null;
		}
	}

}
