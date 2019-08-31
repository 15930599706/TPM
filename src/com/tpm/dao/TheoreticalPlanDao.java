package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.User;

public interface TheoreticalPlanDao extends BaseDao<TheoreticalLesson>{
	//多条件查询理论课
	List<TheoreticalLesson> findTheoreticalLesson(TheoreticalLesson theoreticalLesson);

	//多条件查询课程，用户选择培养计划
	List<Curriculum> findCurriculum(TheoreticalLesson theoreticalLesson);
	
	//查询对应系管理员的专业方向
	List<Professional> findProfessional(String departmentId);

	//插入选定的理论课
	void addSelectLesson(TheoreticalLesson SelectTheoreticalLesson);

	List<TheoreticalLesson> findTheoreticalLessonDepartment(
			Department department);

	//查询所有理论课
	List<Curriculum> findAllTheoreticalLesson(College college,Department department);

	//查询没有选curriculumid的专业
	List<Professional> findUnselectedProfessionalList(String departmentid,
			String curriculumid);

//-----------------
	List<Curriculum> findcurrbycollege(College college);

	List<TheoreticalLesson> findtheolenbycurr(Curriculum curriculum);

	Curriculum findcurrbyId(String curriculumid);

	List<Department> finddepartbycollege(College college);

	Department findchoosedepart(String string);

	TheoreticalLesson findTheoByCurrAndDepart(Curriculum curriculum,
			Professional professional);

	TheoreticalLesson findTheoByCurrAndDepart(Curriculum curriculum,
			Department department);

	List<TheoreticalLesson> findTheolenByTeachDepartment(
			Department teachDepartment);

	
	
	List<TheoreticalLesson> getbyuser(User user);

	//查询专业培养计划
	List<TheoreticalLesson> findSeleTheoLessonByDepart(String zhuanye,
			String zhuanyefangxiang);

	List<TheoreticalLesson> getbyuser(User user,
			TheoreticalLesson newtheo);

	//根据理论课查询该课程大纲id
	String findSyllabusidByTheo(TheoreticalLesson theoreticalLesson);

	List<TheoreticalLesson> getbyuser_HaveHourOfExp(User user);
	
	List<TheoreticalLesson> getbyuser_HaveHourOfExp(User user,TheoreticalLesson newtheo);

	String findSyllabusidByTheo_InnerExperiment(
			TheoreticalLesson theoreticalLesson);

	List<TheoreticalLesson> getbycollegeAndsearch(TheoreticalLesson theoreticalLesson);

	List<TheoreticalLesson> getbyCurriculumAndDepartment(
			TheoreticalLesson newtheo);

	List<TheoreticalLesson> getbycollegeAndsearch_HaveHourOfExp(
			TheoreticalLesson theoreticalLesson);

	List<List<TheoreticalLesson>> findTheoreticalLessonProfessional(
			List<Professional> professionalList);

	List<TheoreticalLesson> findTheoreticalLessonProfessional_Department(
			String departmentID);

	TheoreticalLesson findTheoByProAndCur(Curriculum curriculumNature,
			String string);

	TheoreticalLesson findTheoByDepAndCur(Curriculum curriculumNature,
			String string);

	List<List<TheoreticalLesson>> findTheoreticalLessonProfessional_Pro(
			List<Professional> professionalList);

	
	//其他
	List<PracticeLesson> getPracticeLessonbyuser(User user);

	String findPracticeLessonSyllabusidByTheo(PracticeLesson theo);

	List<PracticeLesson> getPracticeLessonbyuser(User user,
			PracticeLesson newtheo);

	List<PracticeLesson> getbycollegeAndsearchOther(
			PracticeLesson theoreticalLesson);

	List<PracticeLesson> getbyCurriculumAndDepartmentOther(
			PracticeLesson newtheo);

	List<Curriculum> findcurrbycollegeExp(College college);

	List<TheoreticalLesson> findTheolenByExperiment(Experiment experiment);

	List<TheoreticalLesson> findTheolenByExperimentAndCollege(
			Experiment experiment, Curriculum curriculum);

	List<TheoreticalLesson> getbyexperimenter(User user);

	List<TheoreticalLesson> getbydepartment(Department newDepartment);

	List<TheoreticalLesson> getbyexperiment(Experiment newExperiment);

	List<TheoreticalLesson> findTheolenByTeacherDepartment(
			Department cteachDepartment);
	List<TheoreticalLesson> getbycollegeAndsearch0(TheoreticalLesson theoreticalLesson); 
	List<TheoreticalLesson> getbycollegeAndsearch_HaveHourOfExp0(TheoreticalLesson theoreticalLesson);
}
