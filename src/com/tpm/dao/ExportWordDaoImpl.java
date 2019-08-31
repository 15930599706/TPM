package com.tpm.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj;
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
import com.tpm.entity.BaseExperiment;
import com.tpm.entity.BasePractice;
import com.tpm.entity.BaseTheo;
import com.tpm.entity.ConCourseDesign;
import com.tpm.entity.ContentGra;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
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
import com.tpm.entity.Professional;
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
@SuppressWarnings("all")
public class ExportWordDaoImpl extends HibernateDaoSupport implements ExportWordDao{
	public TheoreticalLesson findTheo(String theoid){
		@SuppressWarnings("unchecked")
		List<TheoreticalLesson> theoreticalLessonlist = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where theoreticalLessonid=?",Integer.parseInt(theoid));
		if(theoreticalLessonlist != null && theoreticalLessonlist.size() != 0){
			return theoreticalLessonlist.get(0);
		}else{
			return null;
		}
	}
	public TheoreticalLesson findOther1(String theoid){
		@SuppressWarnings("unchecked")
		List<TheoreticalLesson> theoreticalLessonlist = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where theoreticalLessonid=?",Integer.parseInt(theoid));
		if(theoreticalLessonlist != null && theoreticalLessonlist.size() != 0){
			return theoreticalLessonlist.get(0);
		}else{
			return null;
		}
	}
	public PracticeLesson findOther(String theoid){
		@SuppressWarnings("unchecked")
		List<PracticeLesson> theoreticalLessonlist = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where practiceLessonid=?",Integer.parseInt(theoid));
		if(theoreticalLessonlist != null && theoreticalLessonlist.size() != 0){
			return theoreticalLessonlist.get(0);
		}else{
			return null;
		}
	}
	public List<AbilityAndTeachObj> findAbilityAndTeachObj(String syllabusid) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj where syllabusID=?",syllabusid);
	}
	public List<TeachObj> findTeachObj(String syllabusid) {
		return this.getHibernateTemplate().find("from TeachObj where syllabusID=?",syllabusid);
	}
	
	public List<IndexSelect> findIndexSelect(String syllabusid)
	{
		return this.getHibernateTemplate().find("from IndexSelect where syllabusID=?",syllabusid);
	}
	
	public List<ContentTheo> findContentTheo(String syllabusid) {
		return this.getHibernateTemplate().find("from ContentTheo where syllabusID=?",syllabusid);
	}
	
	public List<ExperimentContent> findexpermentContent(String syllabusid) {
		return this.getHibernateTemplate().find("from ExperimentContent where syllabusID=?",syllabusid);
	}
	
	public List<TextBooks> findbookInfo(String syllabusid) {
		return  this.getHibernateTemplate().find("from TextBooks where syllabusID=?",syllabusid);
	}
	public List<DiscussContent> findDiscussContent(String syllabusid) {
		return this.getHibernateTemplate().find("from DiscussContent where syllabusID=?",syllabusid);
	}
	public List<ThreeProject> findThreeProject(String syllabusid) {
		return this.getHibernateTemplate().find("from ThreeProject where syllabusID=?",syllabusid);
	}
	public List<OtherContent> findOtherContent(String syllabusid) {
		return this.getHibernateTemplate().find("from OtherContent where syllabusID=?",syllabusid);
	}
	
	/**********************毕业设计*****************************/
	public PracticeLesson findPrac(String pracid) {
		List<PracticeLesson> theoreticalLessonlist = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where practiceLessonid=?",Integer.parseInt(pracid));
		if(theoreticalLessonlist != null && theoreticalLessonlist.size() != 0){
			return theoreticalLessonlist.get(0);
		}else{
			return null;
		}
	}

	public List<PracticeBook> findPracticeBook(String syllabusid) {
		return  this.getHibernateTemplate().find("from PracticeBook where syllabus_Graid=?",syllabusid); 
	}
	
	/***********************实习******************************/
	public PracticeLesson findField(String fieldid) {
		List<PracticeLesson> theoreticalLessonlist = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where practiceLessonid=?",Integer.parseInt(fieldid));
		if(theoreticalLessonlist != null && theoreticalLessonlist.size() != 0){
			return theoreticalLessonlist.get(0);
		}else{
			return null;
		}
	}

	/********************课程设计（学年论文）*****************************/
	public PracticeLesson findCourseDesign(String courseid) {
		List<PracticeLesson> theoreticalLessonlist = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where practiceLessonid=?",Integer.parseInt(courseid));
		if(theoreticalLessonlist != null && theoreticalLessonlist.size() != 0){
			return theoreticalLessonlist.get(0);
		}else{
			return null;
		}
	}

	public List<ExpermentProject> findExpermentProject(String syllabusid) {
		return this.getHibernateTemplate().find("from ExpermentProject where syllabus_InnerExperimentid=?",syllabusid);
	}

	public List<Analysis> findGraid(Ability str) {
		List<Analysis> Analysislist = (List<Analysis>)this.getHibernateTemplate().find("from Analysis where ability=?",str);
		if(Analysislist != null && Analysislist.size() != 0){
			return Analysislist;
		}else{
			return null;
		}
	}
	public List<ApplicationSyllabus> findApplicationSyllabus(String syllabusid) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus where syllabus.syllabusid=?",Integer.valueOf(syllabusid));

	}

	public List<ApplicationSyllabus_Gra> findApplicationSyllabus_Gra(
			String syllabusid) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where syllabus_Gra.syllabus_Graid=?",Integer.valueOf(syllabusid));
	}
	public List<TeachObj_Gra> findTeachObj_Gra(String syllabusid) {
		return this.getHibernateTemplate().find("from TeachObj_Gra where syllabus_Graid=?",syllabusid);
	}
	public List<IndexSelect_Gra> findIndexSelect_Gra(String syllabusid) {
		return this.getHibernateTemplate().find("from IndexSelect_Gra where syllabus_Graid=?",syllabusid);
	}
	public List<AbilityAndTeachObj_Gra> findAbilityAndTeachObj_Gra(
			String syllabusid) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_Gra where syllabus_Graid=?",syllabusid);
	}
	public List<ApplicationSyllabus_FieldWork> findApplicationSyllabus_FieldWork(String syllabusid) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where syllabus_FieldWork.syllabus_FieldWorkID=?",Integer.valueOf(syllabusid));
	}
	public List<TeachObj_FieldWork> findTeachObj_FieldWork(String syllabusid) {
		return this.getHibernateTemplate().find("from TeachObj_FieldWork where syllabus_FieldWorkid=?",syllabusid);
	}
	public List<IndexSelect_FieldWork> findIndexSelect_FieldWork(
			String syllabusid) {
		return this.getHibernateTemplate().find("from IndexSelect_FieldWork where syllabus_FieldWorkid=?",syllabusid);
	}
	public List<AbilityAndTeachObj_FieldWork> findAbilityAndTeachObj_FieldWork(
			String syllabusid) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_FieldWork where syllabus_FieldWorkid=?",syllabusid);
	}
	public List<ApplicationSyllabus_CourseDesign> findApplicationSyllabus_CourseDesig(
			String syllabusid) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where syllabus_CourseDesign.syllabus_CourseDesignid=?",Integer.valueOf(syllabusid));
	}
	public List<TeachObj_CourseDesign> findTeachObj_CourseDesign(
			String syllabusid) {
		return this.getHibernateTemplate().find("from TeachObj_CourseDesign where syllabus_CourseDesignid=?",syllabusid);
	}
	public List<IndexSelect_CourseDesign> findIndexSelect_CourseDesign(
			String syllabusid) {
		return this.getHibernateTemplate().find("from IndexSelect_CourseDesign where syllabus_CourseDesignid=?",syllabusid);
	}
	public List<AbilityAndTeachObj_CourseDesign> findAbilityAndTeachObj_CourseDesign(
			String syllabusid) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_CourseDesign where syllabus_CourseDesignid=?",syllabusid);
	}
	public List<PracticeBooks_CourseDesign> findPracticeBooks_CourseDesign(
			String syllabusid) {
		return  this.getHibernateTemplate().find("from PracticeBooks_CourseDesign where syllabus_CourseDesignid=?",syllabusid); 
	}
	public List<ApplicationSyllabus_InnerExperiment> findApplicationSyllabus_InnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where syllabus_InnerExperiment.syllabus_InnerExperimentid=?",Integer.valueOf(syllabusid));
	}
	public List<TeachObj_InnerExperiment> findTeachObj_InnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from TeachObj_InnerExperiment where syllabus_InnerExperimentid=?",syllabusid);
	}
	public List<IndexSelect_InnerExperiment> findIndexSelect_InnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from IndexSelect_InnerExperiment where syllabus_InnerExperimentid=?",syllabusid);
	}
	public List<AbilityAndTeachObj_InnerExperiment> findAbilityAndTeachObj_InnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_InnerExperiment where syllabus_InnerExperimentid=?",syllabusid);
	}
	public List<PracticeBooks_InnerExperiment> findPracticeBooks_InnerExperiment(
			String syllabusid) {
		return  this.getHibernateTemplate().find("from PracticeBooks_InnerExperiment where syllabus_InnerExperimentid=?",syllabusid); 
	}
	public List<ApplicationSyllabus_TheoInnerExperiment> findApplicationSyllabus_TheoInnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where syllabus_TheoInnerExperiment.syllabus_TheoInnerExperimentid=?",Integer.valueOf(syllabusid));
	}
	public List<TeachObj_TheoInnerExperiment> findTeachObj_TheoInnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from TeachObj_TheoInnerExperiment where syllabusid=?",syllabusid);
	}
	public List<IndexSelect_TheoInnerExperiment> findIndexSelect_TheoInnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from IndexSelect_TheoInnerExperiment where syllabusid=?",syllabusid);
	}
	public List<AbilityAndTeachObj_TheoInnerExperiment> findAbilityAndTeachObj_TheoInnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from AbilityAndTeachObj_TheoInnerExperiment where syllabusid=?",syllabusid);
	}
	public List<ExpermentProject_Theo> findExpermentProject_Theo(
			String syllabusid) {
		return this.getHibernateTemplate().find("from ExpermentProject_Theo where syllabusid=?",syllabusid);
	}
	public List<TextBooks_InnerExperiment> findPracticeTextBooks_InnerExperiment(
			String syllabusid) {
		return  this.getHibernateTemplate().find("from TextBooks_InnerExperiment where syllabusID=?",syllabusid); 
	}
	public List<DistributeHour_Theo> findDistributeHour_Theo(String syllabusid) {
		return this.getHibernateTemplate().find("from DistributeHour_Theo where syllabusid=?",syllabusid);
	}
	public List<DistributeHour_Gra> findDistributeHour_Gra(String syllabusid) {
		return this.getHibernateTemplate().find("from DistributeHour_Gra where syllabusid=?",syllabusid);
	}
	public List<DistributeHour_InnerExperiment> findDistributeHour_InnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from DistributeHour_InnerExperiment where syllabusid=?",syllabusid);
	}
	public List<DistributeHour_TheoInnerExperiment> findDistributeHour_TheoInnerExperiment(
			String syllabusid) {
		return this.getHibernateTemplate().find("from DistributeHour_TheoInnerExperiment where syllabusid=?",syllabusid);
	}
	public List<DistributeHour_CourseDesign> findDistributeHour_CourseDesign(
			String syllabusid) {
		return this.getHibernateTemplate().find("from DistributeHour_CourseDesign where syllabusid=?",syllabusid);
	}



	
	
	
}

