package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.Syllabus_CourseDesign;

public class ApplicationSyllabus_CourseDesignDaoImpl extends BaseDaoImpl<ApplicationSyllabus_CourseDesign>
		implements ApplicationSyllabus_CourseDesignDao {

	public List<ApplicationSyllabus_CourseDesign> findapplicationSyllabus_CourseDesignBySylid(
			String syllabusId) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where syllabus_CourseDesign.syllabus_CourseDesignid=?",Integer.valueOf(syllabusId));
	}

	public ApplicationSyllabus_CourseDesign findappByPracAndPro(PracticeLesson practiceLesson, Professional professional) {
		List<ApplicationSyllabus_CourseDesign> applicationSyllabus_CourseDesign =	this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),professional);
		if(applicationSyllabus_CourseDesign != null && applicationSyllabus_CourseDesign.size() != 0)
			return applicationSyllabus_CourseDesign.get(0);
		else
			return null;
	}
	public List<ApplicationSyllabus_CourseDesign> findCurriculum(String syllabusId) {
		Syllabus_CourseDesign syllabus_CourseDesign = new Syllabus_CourseDesign();
		syllabus_CourseDesign.setSyllabus_CourseDesignid(Integer.valueOf(syllabusId));
		List<ApplicationSyllabus_CourseDesign> applicationSyllabuslsit = this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where syllabus_CourseDesign=?",syllabus_CourseDesign);
		if(applicationSyllabuslsit != null && applicationSyllabuslsit.size() != 0) 
			return applicationSyllabuslsit;
		else return null;
	}

}
