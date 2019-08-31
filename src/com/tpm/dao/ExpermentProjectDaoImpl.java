package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ContentTheo;
import com.tpm.entity.ExpermentProject;

public class ExpermentProjectDaoImpl extends BaseDaoImpl<ExpermentProject> implements ExpermentProjectDao {
	public List<ExpermentProject> getbyExpermentProject(String syllabusId){
		List<ExpermentProject> contentTheolist;
		contentTheolist = this.getHibernateTemplate().find("from ExpermentProject where syllabus_InnerExperimentid=?",syllabusId);
		return contentTheolist;
	}
}
