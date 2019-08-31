package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_CourseDesign;

public interface DistributeHour_CourseDesignDao extends BaseDao<DistributeHour_CourseDesign> {

	List<DistributeHour_CourseDesign> getbydistributeHour_CourseDesign(String syllabusId_Copy);

}
