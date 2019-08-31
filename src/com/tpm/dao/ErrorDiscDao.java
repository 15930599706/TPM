package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.ScoreThreshold;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.TrainingEvents;

public interface ErrorDiscDao  {

	//查询所有学院
	List<College> findAllCollege();

	//查询培养计划基本信息为空的学院
	List<Department> findPTBasicInformationEmpty(String collegeid);
	//查询毕业生应获得的知识和能力为空的专业
	List<Department> findAbilityEmpty(String collegeid);	
	//查询专业人才培养理念为空的专业
	List<Department> findPPTrainingConceptEmpty(String collegeid);
	
	//查询关键课程为空的课
	List<Curriculum> findKeyCouEmpty(String collegeid);

	//查询学时学分对应错误的课程
	List<Curriculum> findPreCreErr(String collegeid);

	//根据学院id查询专业
	List<Department> findDepartmentByCollegeId(String collegeid);

	//查询学分为空的专业
	List<ScoreThreshold> findCreditEmpty(String collegeid);

	List[][][] findprofession6(String pid);

	List[][] findprofession7(String pid);

	List<List<TheoreticalLesson>> findlilunke(String pid);

	List<List<PracticeLesson>> findshijianke(String pid);

	List<Professional> findzhuanyenum(String pid);

	List<List<TheoreticalLesson>> findgexueqililunke(String pid, int xuenian);

	PTBasicInformation findPTBasicInformation(String pid);

	List<TrainingEvents> findpeiyangshijian(String pid);
	
}
