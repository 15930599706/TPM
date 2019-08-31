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

public interface PracticePlanDao extends BaseDao<PracticeLesson>{

	//多条件查询实践课
	List<PracticeLesson> findPracticeLesson(PracticeLesson practiceLesson);

	//都条件查询实践课
	List<Curriculum> findCurriculum(PracticeLesson practiceLesson);

	//插入选定的实践课
	void addSelectLesson(PracticeLesson selectPracticeLesson);

	//查询对应系的全部实践课
	List<PracticeLesson> findpracticeLessonDepartment(Department department);

	//查询所有实践课
	List<Curriculum> findAllPracticeLesson(College college,Department department);

	List<PracticeLesson> getbyuser(User user, String zuzhixingshi);
	
	//实验室主任分配实践课到实验员
	List<PracticeLesson> findtheolenbycurr1(Curriculum curriculum);
	

	List<Curriculum> findcurrbycollege(College college);
	List<PracticeLesson> findtheolenbycurr(Curriculum curriculum);
	Curriculum findcurrbyId(String curriculumid);
	List<Department> finddepartbycollege(College college);

	Department findchoosedepart(String string);

	PracticeLesson findTheoByCurrAndDepart(Curriculum curriculum,
			Professional professional);

	PracticeLesson findTheoByCurrAndDepart(Curriculum curriculum,
			Department department);

	List<PracticeLesson> findTheolenByTeachDepartment(
			Department teachDepartment);

	//查询对应系管理员的专业方向
	List<Professional> findProfessional(String departmentId);

	//查询没有选curriculumid的专业
	List<Professional> findUnselectedProfessionalList(String departmentid,
			String curriculumid);

	//查询实践课培养计划
	List<PracticeLesson> findSelePraLessonByDepart(String zhuanye,
			String zhuanyefangxiang);
	
	//查询user的全部课程
	List<PracticeLesson> findPracticebyuser(User user);

	List<PracticeLesson> getbyuser(User user, PracticeLesson newprac,String zuzhixingshi);

	//实习
	String findSyllabusidByPrac_FieldWork(PracticeLesson practiceLesson);

	//课设
	String findSyllabusidByPrac_CourseDesign(PracticeLesson practiceLesson);

	//课内实验
	String findSyllabusidByPrac_InnerExperiment(PracticeLesson practiceLesson);
		
	//毕业设计
	String findSyllabusidByPrac_Gra(PracticeLesson practiceLesson);

	//管理员查看大纲编写情况
	List<PracticeLesson> getbycollegeAndsearch(PracticeLesson practiceLesson,
			String zuzhixingshi);
	List<PracticeLesson> getbyCurriculumAndDepartment(PracticeLesson newprac,
			String zuzhixingshi);

	List<List<PracticeLesson>> findpracticeLessonProfessional(
			List<Professional> professionalList);

	List<PracticeLesson> findpracticeLessonProfessional_Department(
			String departmentID);

	PracticeLesson findTheoByProAndCur(Curriculum curriculumNature,
			String string);

	PracticeLesson findTheoByDepAndCur(Curriculum curriculumNature,
			String string);

	List<List<PracticeLesson>> findpracticeLessonProfessional_Pro(
			List<Professional> professionalList);

	List<PracticeLesson> findPracticebydepartment(Department newDepartment);

	List<PracticeLesson> getbycollegeAndsearch0(PracticeLesson practiceLesson, String zuzhixingshi);
	List<PracticeLesson> getbycollegeAndsearch1(PracticeLesson practiceLesson, String zuzhixingshi);
	List<PracticeLesson> getbycollegeAndsearch2(PracticeLesson practiceLesson, String zuzhixingshi);
	List<PracticeLesson> getbycollegeAndsearch3(PracticeLesson practiceLesson, String zuzhixingshi);
}
