package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.Syllabus;
import com.tpm.entity.TheoreticalLesson;

public class ApplicationSyllabusDaoImpl extends BaseDaoImpl<ApplicationSyllabus> implements
		ApplicationSyllabusDao {

	public List<ApplicationSyllabus> findhaveSyllabusList(List<TheoreticalLesson> newallTheoreticalLessonlist) {
	
		List<ApplicationSyllabus> applicationSyllabusList = new ArrayList<ApplicationSyllabus>();
		for(int thi=0;thi<newallTheoreticalLessonlist.size();thi++)
		{
			ApplicationSyllabus newApplicationSyllabus = new ApplicationSyllabus();
			if(newallTheoreticalLessonlist.get(thi).getProfessional() != null)
			{
				List<ApplicationSyllabus> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=? and professional=?",newallTheoreticalLessonlist.get(thi).getCurriculum(),newallTheoreticalLessonlist.get(thi).getDepartment(),newallTheoreticalLessonlist.get(thi).getProfessional());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			else {
				List<ApplicationSyllabus> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=?",newallTheoreticalLessonlist.get(thi).getCurriculum(),newallTheoreticalLessonlist.get(thi).getDepartment());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			applicationSyllabusList.add(newApplicationSyllabus);
		}
			return applicationSyllabusList;
	}

	//通过大纲id查询所有应用专业/方向
	public List<ApplicationSyllabus> findapplicationSyllabusBySylid(String syllabusId) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus where syllabus.syllabusid=?",Integer.valueOf(syllabusId));
	}

	//通过理论课和专业方向查询对应关系
	public ApplicationSyllabus findappByThenAndPro(TheoreticalLesson theoreticalLesson,Professional professional) {
		List<ApplicationSyllabus> applicationSyllabusList =	this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=? and professional=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment(),professional);
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0) 
			return applicationSyllabusList.get(0);
		else
			return null;
	}

	//通过实践课和专业方向查询对应关系
	public ApplicationSyllabus findappByPracAndPro(PracticeLesson newPracticeLesson,Professional professional) {
		List<ApplicationSyllabus> applicationSyllabusList =	this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=? and professional=?",newPracticeLesson.getCurriculum(),newPracticeLesson.getDepartment(),professional);
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0) 
			return applicationSyllabusList.get(0);
		else
			return null;
	}
	public List<ApplicationSyllabus> findCurriculum(String syllabusId) {
		Syllabus syllabus = new Syllabus();
		syllabus.setSyllabusid(Integer.valueOf(syllabusId));
		List<ApplicationSyllabus> applicationSyllabuslsit = this.getHibernateTemplate().find("from ApplicationSyllabus where syllabus=?",syllabus);
		if(applicationSyllabuslsit != null && applicationSyllabuslsit.size() != 0) 
			return applicationSyllabuslsit;
		else return null;
	}

	
	/**********************实习******************************/
	public List<ApplicationSyllabus_FieldWork> findhaveSyllabusListPrac(List<PracticeLesson> newallPracticeLessonlist) {
		List<ApplicationSyllabus_FieldWork> applicationSyllabusList = new ArrayList<ApplicationSyllabus_FieldWork>();
		for(int thi=0;thi<newallPracticeLessonlist.size();thi++)
		{
			ApplicationSyllabus_FieldWork newApplicationSyllabus = new ApplicationSyllabus_FieldWork();
			if(newallPracticeLessonlist.get(thi).getProfessional() != null)
			{
				List<ApplicationSyllabus_FieldWork> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where curriculum=? and department=? and professional=?",newallPracticeLessonlist.get(thi).getCurriculum(),newallPracticeLessonlist.get(thi).getDepartment(),newallPracticeLessonlist.get(thi).getProfessional());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			else {
				List<ApplicationSyllabus_FieldWork> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where curriculum=? and department=?",newallPracticeLessonlist.get(thi).getCurriculum(),newallPracticeLessonlist.get(thi).getDepartment());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			applicationSyllabusList.add(newApplicationSyllabus);
		}
			return applicationSyllabusList;
	}
	
	
	
	/**********************课程设计******************************/
	public List<ApplicationSyllabus_CourseDesign> findhaveSyllabusListPrac_CourseDesign(List<PracticeLesson> newallPracticeLessonlist) {
		List<ApplicationSyllabus_CourseDesign> applicationSyllabusList = new ArrayList<ApplicationSyllabus_CourseDesign>();
		for(int thi=0;thi<newallPracticeLessonlist.size();thi++)
		{
			ApplicationSyllabus_CourseDesign newApplicationSyllabus = new ApplicationSyllabus_CourseDesign();
			if(newallPracticeLessonlist.get(thi).getProfessional() != null)
			{
				List<ApplicationSyllabus_CourseDesign> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where curriculum=? and department=? and professional=?",newallPracticeLessonlist.get(thi).getCurriculum(),newallPracticeLessonlist.get(thi).getDepartment(),newallPracticeLessonlist.get(thi).getProfessional());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			else {
				List<ApplicationSyllabus_CourseDesign> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where curriculum=? and department=?",newallPracticeLessonlist.get(thi).getCurriculum(),newallPracticeLessonlist.get(thi).getDepartment());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			applicationSyllabusList.add(newApplicationSyllabus);
		}
			return applicationSyllabusList;
	}
	
	/**********************课内实验******************************/
	public List<ApplicationSyllabus_InnerExperiment> findhaveSyllabusListPrac_InnerExperiment(List<PracticeLesson> newallPracticeLessonlist) {
		List<ApplicationSyllabus_InnerExperiment> applicationSyllabusList = new ArrayList<ApplicationSyllabus_InnerExperiment>();
		for(int thi=0;thi<newallPracticeLessonlist.size();thi++)
		{
			ApplicationSyllabus_InnerExperiment newApplicationSyllabus = new ApplicationSyllabus_InnerExperiment();
			if(newallPracticeLessonlist.get(thi).getProfessional() != null)
			{
				List<ApplicationSyllabus_InnerExperiment> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where curriculum=? and department=? and professional=?",newallPracticeLessonlist.get(thi).getCurriculum(),newallPracticeLessonlist.get(thi).getDepartment(),newallPracticeLessonlist.get(thi).getProfessional());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			else {
				List<ApplicationSyllabus_InnerExperiment> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where curriculum=? and department=?",newallPracticeLessonlist.get(thi).getCurriculum(),newallPracticeLessonlist.get(thi).getDepartment());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			applicationSyllabusList.add(newApplicationSyllabus);
		}
			return applicationSyllabusList;
	}
	
	/**********************毕业设计******************************/
	public List<ApplicationSyllabus_Gra> findhaveSyllabusListPrac_Gra(List<PracticeLesson> newallPracticeLessonlist) {
		List<ApplicationSyllabus_Gra> applicationSyllabusList = new ArrayList<ApplicationSyllabus_Gra>();
		for(int thi=0;thi<newallPracticeLessonlist.size();thi++)
		{
			ApplicationSyllabus_Gra newApplicationSyllabus = new ApplicationSyllabus_Gra();
			if(newallPracticeLessonlist.get(thi).getProfessional() != null)
			{
				List<ApplicationSyllabus_Gra> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where curriculum=? and department=? and professional=?",newallPracticeLessonlist.get(thi).getCurriculum(),newallPracticeLessonlist.get(thi).getDepartment(),newallPracticeLessonlist.get(thi).getProfessional());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			else {
				List<ApplicationSyllabus_Gra> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where curriculum=? and department=?",newallPracticeLessonlist.get(thi).getCurriculum(),newallPracticeLessonlist.get(thi).getDepartment());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			applicationSyllabusList.add(newApplicationSyllabus);
		}
			return applicationSyllabusList;
	}

	public List<ApplicationSyllabus> findAppByTheo(TheoreticalLesson theoreticalLesson) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment());
	}
	public ApplicationSyllabus findAppByTheo_E(TheoreticalLesson theoreticalLesson) {
		List<ApplicationSyllabus> ApplicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment());
		if(ApplicationSyllabus != null && ApplicationSyllabus.size() != 0)
			return ApplicationSyllabus.get(0);
		else
			return null;
	}
	public ApplicationSyllabus findAppByPrac_E(PracticeLesson newPracticeLesson) {
		List<ApplicationSyllabus> ApplicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=?",newPracticeLesson.getCurriculum(),newPracticeLesson.getDepartment());
		if(ApplicationSyllabus != null && ApplicationSyllabus.size() != 0)
			return ApplicationSyllabus.get(0);
		else
			return null;
	}

	public List<ApplicationSyllabus_FieldWork> findAppByPrac_FieldWork(PracticeLesson practiceLesson) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
	}
	public ApplicationSyllabus_FieldWork findAppByPrac_FieldWork_E(PracticeLesson practiceLesson) {
		List<ApplicationSyllabus_FieldWork> ApplicationSyllabus_FieldWork = this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
		if(ApplicationSyllabus_FieldWork != null && ApplicationSyllabus_FieldWork.size() != 0)
			return ApplicationSyllabus_FieldWork.get(0);
		else
			return null;
	}

	public List<ApplicationSyllabus_CourseDesign> findAppByPrac_CourseDesign(
			PracticeLesson practiceLesson) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
	}
	public ApplicationSyllabus_CourseDesign findAppByPrac_CourseDesign_E(
			PracticeLesson practiceLesson) {
		List<ApplicationSyllabus_CourseDesign> ApplicationSyllabus_CourseDesign = this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
		if(ApplicationSyllabus_CourseDesign != null && ApplicationSyllabus_CourseDesign.size() != 0)
			return ApplicationSyllabus_CourseDesign.get(0);
		else
			return null;
	}

	public List<ApplicationSyllabus_InnerExperiment> findAppByPrac_InnerExperiment(
			PracticeLesson practiceLesson) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
	}
	public ApplicationSyllabus_InnerExperiment findAppByPrac_InnerExperiment_E(
			PracticeLesson practiceLesson) {
		List<ApplicationSyllabus_InnerExperiment> ApplicationSyllabus_InnerExperiment = this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
		if(ApplicationSyllabus_InnerExperiment != null && ApplicationSyllabus_InnerExperiment.size() != 0)
			return ApplicationSyllabus_InnerExperiment.get(0);
		else
			return null;
	}

	public List<ApplicationSyllabus_Gra> findAppByPrac_Gra(
			PracticeLesson practiceLesson) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
	}
	public ApplicationSyllabus_Gra findAppByPrac_Gra_E(
			PracticeLesson practiceLesson) {
		List<ApplicationSyllabus_Gra> ApplicationSyllabus_Gra = this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
		if(ApplicationSyllabus_Gra != null && ApplicationSyllabus_Gra.size() != 0)
			return ApplicationSyllabus_Gra.get(0);
		else
			return null;
	}
	
	public List<ApplicationSyllabus_TheoInnerExperiment> findAppByTheo_TheoInnerExperiment(
			TheoreticalLesson theoreticalLesson) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where curriculum=? and department=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment());
	}
	public ApplicationSyllabus_TheoInnerExperiment findAppByTheo_TheoInnerExperiment_E(
			TheoreticalLesson theoreticalLesson) {
		List<ApplicationSyllabus_TheoInnerExperiment> ApplicationSyllabus_TheoInnerExperiment = this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where curriculum=? and department=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment());
		if(ApplicationSyllabus_TheoInnerExperiment != null && ApplicationSyllabus_TheoInnerExperiment.size() != 0)
			return ApplicationSyllabus_TheoInnerExperiment.get(0);
		else
			return null;
		
	}

	/**********************理论课课内实验******************************/
	public List<ApplicationSyllabus_TheoInnerExperiment> findhaveSyllabus_TheoInnerExperimentList(List<TheoreticalLesson> allTheoreticalLessonlist) {
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabusList_TheoInnerExperiment = new ArrayList<ApplicationSyllabus_TheoInnerExperiment>();
		for(int thi=0;thi<allTheoreticalLessonlist.size();thi++)
		{
			ApplicationSyllabus_TheoInnerExperiment newApplicationSyllabus = new ApplicationSyllabus_TheoInnerExperiment();
			if(allTheoreticalLessonlist.get(thi).getProfessional() != null)
			{
				List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where curriculum=? and department=? and professional=?",allTheoreticalLessonlist.get(thi).getCurriculum(),allTheoreticalLessonlist.get(thi).getDepartment(),allTheoreticalLessonlist.get(thi).getProfessional());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			else {
				List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where curriculum=? and department=?",allTheoreticalLessonlist.get(thi).getCurriculum(),allTheoreticalLessonlist.get(thi).getDepartment());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			applicationSyllabusList_TheoInnerExperiment.add(newApplicationSyllabus);
		}
			return applicationSyllabusList_TheoInnerExperiment;
	}

	public List<ApplicationSyllabus> findPracticeLessonhaveSyllabusList(List<PracticeLesson> newallTheoreticalLessonlist) {
		
		List<ApplicationSyllabus> applicationSyllabusList = new ArrayList<ApplicationSyllabus>();
		for(int thi=0;thi<newallTheoreticalLessonlist.size();thi++)
		{
			ApplicationSyllabus newApplicationSyllabus = new ApplicationSyllabus();
			if(newallTheoreticalLessonlist.get(thi).getProfessional() != null)
			{
				List<ApplicationSyllabus> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=? and professional=?",newallTheoreticalLessonlist.get(thi).getCurriculum(),newallTheoreticalLessonlist.get(thi).getDepartment(),newallTheoreticalLessonlist.get(thi).getProfessional());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			else {
				List<ApplicationSyllabus> applicationSyllabus = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=?",newallTheoreticalLessonlist.get(thi).getCurriculum(),newallTheoreticalLessonlist.get(thi).getDepartment());
				if(applicationSyllabus != null && applicationSyllabus.size() != 0)
					newApplicationSyllabus = applicationSyllabus.get(0);
				else continue;
			}
			applicationSyllabusList.add(newApplicationSyllabus);
		}
			return applicationSyllabusList;
	}

	public List<ApplicationSyllabus> findPracticeLessonAppByTheo(PracticeLesson theoreticalLesson) {
		return this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment());
	}
	
	//通过实践课其他和专业方向查询对应关系
	public ApplicationSyllabus findappByThenAndProOther(PracticeLesson practiceLesson,Professional professional) {
		List<ApplicationSyllabus> applicationSyllabusList =	this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),professional);
		return applicationSyllabusList.get(0);
	}

}
