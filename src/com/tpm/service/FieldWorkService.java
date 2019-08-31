package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabus_FieldWorkDao;
import com.tpm.dao.FieldWorkDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.Syllabus_FieldWorkDao;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ContentGra;
import com.tpm.entity.Curriculum;
import com.tpm.entity.FieldWork;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus;
import com.tpm.entity.Syllabus_FieldWork;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class FieldWorkService {

	private FieldWorkDao fieldWorkDao;
	private ApplicationSyllabus_FieldWorkDao applicationSyllabus_FieldWorkDao;
	private PracticePlanDao practicePlanDao;
	private Syllabus_FieldWorkDao syllabus_FieldWorkDao;
	
	public void setSyllabus_FieldWorkDao(Syllabus_FieldWorkDao syllabus_FieldWorkDao) {
		this.syllabus_FieldWorkDao = syllabus_FieldWorkDao;
	}

	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setApplicationSyllabus_FieldWorkDao(
			ApplicationSyllabus_FieldWorkDao applicationSyllabus_FieldWorkDao) {
		this.applicationSyllabus_FieldWorkDao = applicationSyllabus_FieldWorkDao;
	}

	public void setFieldWorkDao(FieldWorkDao fieldWorkDao) {
		this.fieldWorkDao = fieldWorkDao;
	}

	public void updateFieldWork(FieldWork fieldWork, String syllabusId_FieldWorkid, String practiceLessonid) {
		
		if(fieldWork.getFieldWorkid() != null){
			fieldWorkDao.update(fieldWork);
		}else{
			fieldWorkDao.add(fieldWork);
			
			Syllabus_FieldWork syllabus_FieldWork = syllabus_FieldWorkDao.get(Integer.valueOf(syllabusId_FieldWorkid));
		
			syllabus_FieldWork.setFieldWork(fieldWork);
			syllabus_FieldWorkDao.update(syllabus_FieldWork);
		}
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId_FieldWorkid);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson", newpracticeLesson);

		ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
		ServletActionContext.getRequest().setAttribute("tag", "toPracLes_FieldWorkPage");
	}

	public void toBasePracticePage(String syllabusId_FieldWorkid,String practiceLessonid) {
		List<ApplicationSyllabus_FieldWork> applicationSyllabus_FieldWork = applicationSyllabus_FieldWorkDao.findCurriculum(syllabusId_FieldWorkid);//在大纲应用专业表找课程信息
		
		Curriculum curriculum = applicationSyllabus_FieldWork.get(0).getCurriculum();
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("syllabusId_FieldWorkid", syllabusId_FieldWorkid);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		if(applicationSyllabus_FieldWork != null && applicationSyllabus_FieldWork.size() != 0)
		{
			if(applicationSyllabus_FieldWork.get(0).getProfessional() != null)
			{
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_FieldWork);
				ServletActionContext.getRequest().setAttribute("flage", 1);
			}
				
			else{ 
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_FieldWork.get(0));
				ServletActionContext.getRequest().setAttribute("flage", -1);
			}
		}
		Syllabus_FieldWork syllabus_FieldWork = syllabus_FieldWorkDao.get(Integer.valueOf(syllabusId_FieldWorkid));
		if(syllabus_FieldWork.getFieldWork() != null){
			ServletActionContext.getRequest().setAttribute("fieldWork", syllabus_FieldWork.getFieldWork());
		}
		
	}
	
}
