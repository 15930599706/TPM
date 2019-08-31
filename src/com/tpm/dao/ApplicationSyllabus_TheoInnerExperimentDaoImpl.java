package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.Professional;
import com.tpm.entity.TheoreticalLesson;

public class ApplicationSyllabus_TheoInnerExperimentDaoImpl extends
		BaseDaoImpl<ApplicationSyllabus_TheoInnerExperiment> implements ApplicationSyllabus_TheoInnerExperimentDao {

	public List<ApplicationSyllabus_TheoInnerExperiment> findapplicationSyllabusBySylid_TheoInnerExperiment(
			String syllabusId) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where syllabus_TheoInnerExperiment.syllabus_TheoInnerExperimentid=?",Integer.valueOf(syllabusId));

	}

	public ApplicationSyllabus_TheoInnerExperiment findappByThenAndPro_TheoInnerExperiment(TheoreticalLesson theoreticalLesson, Professional professional) {
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabus_TheoInnerExperimentList =	this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where curriculum=? and department=? and professional=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment(),professional);
		if(applicationSyllabus_TheoInnerExperimentList != null && applicationSyllabus_TheoInnerExperimentList.size() != 0)
			return applicationSyllabus_TheoInnerExperimentList.get(0);
		else
			return null;
	}

	public List<ApplicationSyllabus_TheoInnerExperiment> findCurriculum(
			String syllabusId) {

	return this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where syllabus_TheoInnerExperiment.syllabus_TheoInnerExperimentid=?",Integer.valueOf(syllabusId));
	}


}
