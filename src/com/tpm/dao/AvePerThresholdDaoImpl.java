package com.tpm.dao;

import java.util.List;

import com.tpm.entity.AvePerThreshold;

public class AvePerThresholdDaoImpl extends BaseDaoImpl<AvePerThreshold> implements
		AvePerThresholdDao {

	public AvePerThreshold findAvePer() {
		List<AvePerThreshold> avePerThresholdList = this.getHibernateTemplate().find("from AvePerThreshold");
		if(avePerThresholdList != null && avePerThresholdList.size() != 0){
			return avePerThresholdList.get(0);
		}
		else{
			return null;
		}
	}

}
