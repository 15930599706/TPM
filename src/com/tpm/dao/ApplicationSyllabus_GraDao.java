package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;

public interface ApplicationSyllabus_GraDao extends BaseDao<ApplicationSyllabus_Gra> {

	List<ApplicationSyllabus_Gra> findapplicationSyllabus_GraBySylid(
			String syllabusId);

	ApplicationSyllabus_Gra findappByPracAndPro(PracticeLesson practiceLesson,
			Professional professional);

	List<ApplicationSyllabus_Gra> findCurriculum(String syllabusId);

}
