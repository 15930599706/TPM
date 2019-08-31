package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.TheoreticalLesson;

public interface ApplicationSyllabusDao extends BaseDao<ApplicationSyllabus> {

	/**********************实习******************************/
	List<ApplicationSyllabus> findhaveSyllabusList(List<TheoreticalLesson> newallTheoreticalLessonlist);
	List<ApplicationSyllabus_FieldWork> findAppByPrac_FieldWork(PracticeLesson practiceLesson);
	ApplicationSyllabus_FieldWork findAppByPrac_FieldWork_E(PracticeLesson practiceLesson);

	//通过大纲id查询所有应用专业/方向
	List<ApplicationSyllabus> findapplicationSyllabusBySylid(String syllabusId);

	//通过理论课和专业查询对应关系
	ApplicationSyllabus findappByThenAndPro(TheoreticalLesson theoreticalLesson,
			Professional professional);

	List<ApplicationSyllabus> findCurriculum(String syllabusId);
	
	
	List<ApplicationSyllabus_FieldWork> findhaveSyllabusListPrac(List<PracticeLesson> newallPracticeLessonlist);
	
	//通过理论课查询应用应用List
	List<ApplicationSyllabus> findAppByTheo(TheoreticalLesson theoreticalLesson);
	ApplicationSyllabus findAppByTheo_E(TheoreticalLesson theoreticalLesson);

	/**********************课程设计******************************/
	List<ApplicationSyllabus_CourseDesign> findhaveSyllabusListPrac_CourseDesign(
			List<PracticeLesson> newallPracticeLessonlist);
	List<ApplicationSyllabus_CourseDesign> findAppByPrac_CourseDesign(PracticeLesson practiceLesson);
	ApplicationSyllabus_CourseDesign findAppByPrac_CourseDesign_E(PracticeLesson practiceLesson);

	/**********************课内实验******************************/
	List<ApplicationSyllabus_InnerExperiment> findhaveSyllabusListPrac_InnerExperiment(
			List<PracticeLesson> newallPracticeLessonlist);
	List<ApplicationSyllabus_InnerExperiment> findAppByPrac_InnerExperiment(PracticeLesson practiceLesson);
	ApplicationSyllabus_InnerExperiment findAppByPrac_InnerExperiment_E(PracticeLesson practiceLesson);
	
	/**********************毕业设计******************************/
	List<ApplicationSyllabus_Gra> findhaveSyllabusListPrac_Gra(
			List<PracticeLesson> newallPracticeLessonlist);
	List<ApplicationSyllabus_Gra> findAppByPrac_Gra(PracticeLesson practiceLesson);
	ApplicationSyllabus_Gra findAppByPrac_Gra_E(PracticeLesson practiceLesson);
	
	/**********************理论课课内实验******************************/
	List<ApplicationSyllabus_TheoInnerExperiment> findhaveSyllabus_TheoInnerExperimentList(
			List<TheoreticalLesson> allTheoreticalLessonlist);
	List<ApplicationSyllabus_TheoInnerExperiment> findAppByTheo_TheoInnerExperiment(
			TheoreticalLesson theoreticalLesson);
	
	
	//其他
	ApplicationSyllabus_TheoInnerExperiment findAppByTheo_TheoInnerExperiment_E(
			TheoreticalLesson theoreticalLesson);
	List<ApplicationSyllabus> findPracticeLessonhaveSyllabusList(
			List<PracticeLesson> newallTheoreticalLessonlist);
	List<ApplicationSyllabus> findPracticeLessonAppByTheo(
			PracticeLesson practiceLesson);
	ApplicationSyllabus findappByThenAndProOther(PracticeLesson practiceLesson,
			Professional professional);
	ApplicationSyllabus findappByPracAndPro(PracticeLesson newPracticeLesson,
			Professional professional);
	ApplicationSyllabus findAppByPrac_E(PracticeLesson newPracticeLesson);

	

}
