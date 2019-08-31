package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.TopologyTag;

public class TopologyTagDaoImpl extends BaseDaoImpl<TopologyTag> implements TopologyTagDao {

	public List<TopologyTag> findbydepartment(Department department) {
		List<TopologyTag> topologyTaglist = this.getHibernateTemplate().find("from TopologyTag where department = ?", department);
		if(topologyTaglist.size() != 0){
			return topologyTaglist;
		}else{
			return null;
		}
	}

}
