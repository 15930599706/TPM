package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseCourseDesign;
import com.tpm.entity.BasePractice;
import com.tpm.entity.DistributeHour_CourseDesign;

public interface BaseCourseDesignDao extends BaseDao<BaseCourseDesign> {

	List<DistributeHour_CourseDesign> findDistributeHour_CourseDesign(
			String syllabusId);

	void deleteDistributeHour_CourseDesign(String syllabusId);

	void addDistributeHour_CourseDesign(
			DistributeHour_CourseDesign distributeHour_CourseDesign);
	
}
