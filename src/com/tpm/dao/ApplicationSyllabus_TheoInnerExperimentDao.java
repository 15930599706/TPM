package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.Professional;
import com.tpm.entity.TheoreticalLesson;

public interface ApplicationSyllabus_TheoInnerExperimentDao extends BaseDao<ApplicationSyllabus_TheoInnerExperiment> {

	List<ApplicationSyllabus_TheoInnerExperiment> findapplicationSyllabusBySylid_TheoInnerExperiment(
			String syllabusId);

	ApplicationSyllabus_TheoInnerExperiment findappByThenAndPro_TheoInnerExperiment(
			TheoreticalLesson theoreticalLesson, Professional professional);

	List<ApplicationSyllabus_TheoInnerExperiment> findCurriculum(
			String syllabusId);

}
