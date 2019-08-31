package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;

public interface ApplicationSyllabus_CourseDesignDao extends BaseDao<ApplicationSyllabus_CourseDesign> {

	List<ApplicationSyllabus_CourseDesign> findapplicationSyllabus_CourseDesignBySylid(
			String syllabusId);

	ApplicationSyllabus_CourseDesign findappByPracAndPro(
			PracticeLesson practiceLesson, Professional professional);

	List<ApplicationSyllabus_CourseDesign> findCurriculum(String syllabusId);

}
