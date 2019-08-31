package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ExpermentProject;
import com.tpm.entity.ExpermentProject_Theo;

public class ExpermentProject_InnerExperimentDaoImpl extends BaseDaoImpl<ExpermentProject_Theo>
		implements ExpermentProject_InnerExperimentDao {

	public List<ExpermentProject_Theo> getbyExpermentProject_Theo(String syllabusId) {
		List<ExpermentProject_Theo> contentTheolist;
		contentTheolist = this.getHibernateTemplate().find("from ExpermentProject_Theo where syllabusid=?",syllabusId);
		return contentTheolist;
	}

}
