package com.tpm.dao;

import java.util.List;
import com.tpm.entity.ExperimentContent;

public class ExperimentContentDaoImpl extends BaseDaoImpl<ExperimentContent> implements ExperimentContentDao {
	public List<ExperimentContent> getbyexperimentContent(String syllabusId) {
		List<ExperimentContent> experimentContentlist;
		experimentContentlist = this.getHibernateTemplate().find("from ExperimentContent where syllabusID=?", syllabusId);
		return experimentContentlist;
	}

}
