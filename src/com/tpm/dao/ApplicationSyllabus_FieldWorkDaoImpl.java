package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.Syllabus;
import com.tpm.entity.Syllabus_FieldWork;

public class ApplicationSyllabus_FieldWorkDaoImpl extends BaseDaoImpl<ApplicationSyllabus_FieldWork> implements ApplicationSyllabus_FieldWorkDao {

	public List<ApplicationSyllabus_FieldWork> findapplicationSyllabus_FieldWorkBySylid(
			String syllabusId) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where syllabus_FieldWork.syllabus_FieldWorkID=?",Integer.valueOf(syllabusId));

	}

	public ApplicationSyllabus_FieldWork findappByPracAndPro(
			PracticeLesson practiceLesson, Professional professional) {
		List<ApplicationSyllabus_FieldWork> applicationSyllabus_FieldWorkList =	this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),professional);
		if(applicationSyllabus_FieldWorkList != null && applicationSyllabus_FieldWorkList.size() != 0)
			return applicationSyllabus_FieldWorkList.get(0);
		else
			return null;
	}

	public List<ApplicationSyllabus_FieldWork> findCurriculum(String syllabusId_FieldWorkid) {
		Syllabus_FieldWork syllabus_FieldWork = new Syllabus_FieldWork();
		syllabus_FieldWork.setSyllabus_FieldWorkID(Integer.valueOf(syllabusId_FieldWorkid));
		List<ApplicationSyllabus_FieldWork> applicationSyllabuslsit = this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where syllabus_FieldWork=?",syllabus_FieldWork);
		if(applicationSyllabuslsit != null && applicationSyllabuslsit.size() != 0) 
			return applicationSyllabuslsit;
		else return null;
	}

}
