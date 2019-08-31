package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;

public interface ApplicationSyllabus_InnerExperimentDao extends BaseDao<ApplicationSyllabus_InnerExperiment> {

	List<ApplicationSyllabus_InnerExperiment> findapplicationSyllabus_InnerExperimentBySylid(
			String syllabusId);

	ApplicationSyllabus_InnerExperiment findappByPracAndPro(
			PracticeLesson practiceLesson, Professional professional);

	List<ApplicationSyllabus_InnerExperiment> findCurriculum(String syllabusId);

}
