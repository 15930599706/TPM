package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.Syllabus_InnerExperiment;

public class ApplicationSyllabus_InnerExperimentDaoImpl extends BaseDaoImpl<ApplicationSyllabus_InnerExperiment>
		implements ApplicationSyllabus_InnerExperimentDao {

	public List<ApplicationSyllabus_InnerExperiment> findapplicationSyllabus_InnerExperimentBySylid(
			String syllabusId) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where syllabus_InnerExperiment.syllabus_InnerExperimentid=?",Integer.valueOf(syllabusId));
	}

	public ApplicationSyllabus_InnerExperiment findappByPracAndPro(PracticeLesson practiceLesson, Professional professional) {
		List<ApplicationSyllabus_InnerExperiment> applicationSyllabus_InnerExperiment =	this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),professional);
		if(applicationSyllabus_InnerExperiment != null && applicationSyllabus_InnerExperiment.size() != 0)
			return applicationSyllabus_InnerExperiment.get(0);
		else
			return null;
	}

	public List<ApplicationSyllabus_InnerExperiment> findCurriculum(
			String syllabusId) {
		Syllabus_InnerExperiment syllabus_InnerExperiment = new Syllabus_InnerExperiment();
		syllabus_InnerExperiment.setSyllabus_InnerExperimentid(Integer.valueOf(syllabusId));
		List<ApplicationSyllabus_InnerExperiment> applicationSyllabuslsit = this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where syllabus_InnerExperiment=?",syllabus_InnerExperiment);
		if(applicationSyllabuslsit != null && applicationSyllabuslsit.size() != 0) 
			return applicationSyllabuslsit;
		else return null;
	}

}
