package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;

public interface ApplicationSyllabus_FieldWorkDao extends BaseDao<ApplicationSyllabus_FieldWork> {

	////通过大纲id查询该大纲的所有应用专业
	List<ApplicationSyllabus_FieldWork> findapplicationSyllabus_FieldWorkBySylid(
			String syllabusId);

	ApplicationSyllabus_FieldWork findappByPracAndPro(
			PracticeLesson practiceLesson, Professional professional);

	List<ApplicationSyllabus_FieldWork> findCurriculum(
			String syllabusId_FieldWorkid);

}
