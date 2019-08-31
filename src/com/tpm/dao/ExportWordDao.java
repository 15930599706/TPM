package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_CourseDesign;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.AbilityAndTeachObj_Gra;
import com.tpm.entity.AbilityAndTeachObj_InnerExperiment;
import com.tpm.entity.AbilityAndTeachObj_TheoInnerExperiment;
import com.tpm.entity.Analysis;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.BaseCourseDesign;
import com.tpm.entity.BasePractice;
import com.tpm.entity.BaseTheo;
import com.tpm.entity.ConCourseDesign;
import com.tpm.entity.ContentGra;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.DiscussContent;
import com.tpm.entity.DistributeHour_CourseDesign;
import com.tpm.entity.DistributeHour_Gra;
import com.tpm.entity.DistributeHour_InnerExperiment;
import com.tpm.entity.DistributeHour_Theo;
import com.tpm.entity.DistributeHour_TheoInnerExperiment;
import com.tpm.entity.ExperimentContent;
import com.tpm.entity.ExpermentProject;
import com.tpm.entity.ExpermentProject_Theo;
import com.tpm.entity.FieldContent;
import com.tpm.entity.FieldWork;
import com.tpm.entity.IndexSelect;
import com.tpm.entity.IndexSelect_CourseDesign;
import com.tpm.entity.IndexSelect_FieldWork;
import com.tpm.entity.IndexSelect_Gra;
import com.tpm.entity.IndexSelect_InnerExperiment;
import com.tpm.entity.IndexSelect_TheoInnerExperiment;
import com.tpm.entity.OtherContent;
import com.tpm.entity.PracticeBook;
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.PracticeBooks_InnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TeaMethodTheo;
import com.tpm.entity.TeachObj;
import com.tpm.entity.TeachObj_CourseDesign;
import com.tpm.entity.TeachObj_FieldWork;
import com.tpm.entity.TeachObj_Gra;
import com.tpm.entity.TeachObj_InnerExperiment;
import com.tpm.entity.TeachObj_TheoInnerExperiment;
import com.tpm.entity.TextBooks;
import com.tpm.entity.TextBooks_InnerExperiment;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.ThreeProject;
import com.tpm.entity.WayCourseDesign;

public interface ExportWordDao {

	TheoreticalLesson findTheo(String theoid);

	List<ContentTheo> findContentTheo(String syllabusid);

	List<TextBooks> findbookInfo(String syllabusid);

	List<ExperimentContent> findexpermentContent(String syllabusid);

	List<DiscussContent> findDiscussContent(String syllabusid);

	List<ThreeProject> findThreeProject(String syllabusid);

	List<OtherContent> findOtherContent(String syllabusid);

	PracticeLesson findPrac(String pracid);


	List<PracticeBook> findPracticeBook(String syllabusid);

	PracticeLesson findField(String fieldid);


	PracticeLesson findCourseDesign(String courseid);


	List<ExpermentProject> findExpermentProject(String syllabusid);

	List<TeachObj> findTeachObj(String curriculumid);

	List<IndexSelect> findIndexSelect(String syllabusid);

	List<Analysis> findGraid(Ability str);

	List<com.tpm.entity.AbilityAndTeachObj> findAbilityAndTeachObj(
			String curriculumid);


	List<ApplicationSyllabus> findApplicationSyllabus(String syllabusid);

	List<ApplicationSyllabus_Gra> findApplicationSyllabus_Gra(String syllabusid);

	List<TeachObj_Gra> findTeachObj_Gra(String syllabusid);

	List<IndexSelect_Gra> findIndexSelect_Gra(String syllabusid);

	List<AbilityAndTeachObj_Gra> findAbilityAndTeachObj_Gra(String syllabusid);

	List<ApplicationSyllabus_FieldWork> findApplicationSyllabus_FieldWork(String syllabusid);

	List<TeachObj_FieldWork> findTeachObj_FieldWork(String syllabusid);

	List<IndexSelect_FieldWork> findIndexSelect_FieldWork(String syllabusid);

	List<AbilityAndTeachObj_FieldWork> findAbilityAndTeachObj_FieldWork(
			String syllabusid);

	List<ApplicationSyllabus_CourseDesign> findApplicationSyllabus_CourseDesig(
			String syllabusid);

	List<TeachObj_CourseDesign> findTeachObj_CourseDesign(String syllabusid);

	List<IndexSelect_CourseDesign> findIndexSelect_CourseDesign(
			String syllabusid);

	List<AbilityAndTeachObj_CourseDesign> findAbilityAndTeachObj_CourseDesign(
			String syllabusid);

	List<PracticeBooks_CourseDesign> findPracticeBooks_CourseDesign(
			String syllabusid);

	List<ApplicationSyllabus_InnerExperiment> findApplicationSyllabus_InnerExperiment(
			String syllabusid);

	List<TeachObj_InnerExperiment> findTeachObj_InnerExperiment(
			String syllabusid);

	List<IndexSelect_InnerExperiment> findIndexSelect_InnerExperiment(
			String syllabusid);

	List<AbilityAndTeachObj_InnerExperiment> findAbilityAndTeachObj_InnerExperiment(
			String syllabusid);

	List<PracticeBooks_InnerExperiment> findPracticeBooks_InnerExperiment(
			String syllabusid);

	List<ApplicationSyllabus_TheoInnerExperiment> findApplicationSyllabus_TheoInnerExperiment(
			String syllabusid);

	List<TeachObj_TheoInnerExperiment> findTeachObj_TheoInnerExperiment(
			String syllabusid);

	List<IndexSelect_TheoInnerExperiment> findIndexSelect_TheoInnerExperiment(
			String syllabusid);

	List<AbilityAndTeachObj_TheoInnerExperiment> findAbilityAndTeachObj_TheoInnerExperiment(
			String syllabusid);

	List<ExpermentProject_Theo> findExpermentProject_Theo(String syllabusid);

	List<TextBooks_InnerExperiment> findPracticeTextBooks_InnerExperiment(
			String syllabusid);

	List<DistributeHour_Theo> findDistributeHour_Theo(String syllabusid);

	List<DistributeHour_Gra> findDistributeHour_Gra(String syllabusid);

	List<DistributeHour_InnerExperiment> findDistributeHour_InnerExperiment(
			String syllabusid);

	List<DistributeHour_TheoInnerExperiment> findDistributeHour_TheoInnerExperiment(
			String syllabusid);

	List<DistributeHour_CourseDesign> findDistributeHour_CourseDesign(
			String syllabusid);


	PracticeLesson findOther(String theoid);
	
	TheoreticalLesson findOther1(String theoid);

}
