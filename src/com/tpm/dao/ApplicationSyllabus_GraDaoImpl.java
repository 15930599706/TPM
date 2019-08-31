package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.Syllabus_Gra;

public class ApplicationSyllabus_GraDaoImpl extends BaseDaoImpl<ApplicationSyllabus_Gra> implements
		ApplicationSyllabus_GraDao {

	public List<ApplicationSyllabus_Gra> findapplicationSyllabus_GraBySylid(
			String syllabusId) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where syllabus_Gra.syllabus_Graid=?",Integer.valueOf(syllabusId));
		
	}

	public ApplicationSyllabus_Gra findappByPracAndPro(PracticeLesson practiceLesson, Professional professional) {
		List<ApplicationSyllabus_Gra> applicationSyllabus_Gra =	this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),professional);
		if(applicationSyllabus_Gra != null && applicationSyllabus_Gra.size() != 0)
			return applicationSyllabus_Gra.get(0);
		else
			return null;
	}
	public List<ApplicationSyllabus_Gra> findCurriculum(String syllabusId) {
		Syllabus_Gra syllabus_Gra = new Syllabus_Gra();
		syllabus_Gra.setSyllabus_Graid(Integer.valueOf(syllabusId));
		List<ApplicationSyllabus_Gra> applicationSyllabuslsit = this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where syllabus_Gra=?",syllabus_Gra);
		if(applicationSyllabuslsit != null && applicationSyllabuslsit.size() != 0) 
			return applicationSyllabuslsit;
		else return null;
	}

}
