package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabus_FieldWorkDao;
import com.tpm.dao.FieldContentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.Syllabus_FieldWorkDao;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ContentGra;
import com.tpm.entity.Curriculum;
import com.tpm.entity.FieldContent;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus_FieldWork;

@Transactional
public class FieldContentService {

	private FieldContentDao fieldContentDao;
	private ApplicationSyllabus_FieldWorkDao applicationSyllabus_FieldWorkDao;
	private PracticePlanDao practicePlanDao;
	private Syllabus_FieldWorkDao syllabus_FieldWorkDao;
	
	public void setApplicationSyllabus_FieldWorkDao(
			ApplicationSyllabus_FieldWorkDao applicationSyllabus_FieldWorkDao) {
		this.applicationSyllabus_FieldWorkDao = applicationSyllabus_FieldWorkDao;
	}

	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setSyllabus_FieldWorkDao(Syllabus_FieldWorkDao syllabus_FieldWorkDao) {
		this.syllabus_FieldWorkDao = syllabus_FieldWorkDao;
	}

	public void setFieldContentDao(FieldContentDao fieldContentDao) {
		this.fieldContentDao = fieldContentDao;
	}

	public void toContentPracticePage(String syllabusId_FieldWorkid,String practiceLessonid) {
		
		ServletActionContext.getRequest().setAttribute("syllabusId_FieldWorkid", syllabusId_FieldWorkid);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		Syllabus_FieldWork syllabus_FieldWork = syllabus_FieldWorkDao.get(Integer.valueOf(syllabusId_FieldWorkid));
		if(syllabus_FieldWork.getFieldContent() != null){
			ServletActionContext.getRequest().setAttribute("fieldContent", syllabus_FieldWork.getFieldContent());
		}
	}

	public void updateFieldContent(FieldContent fieldContent, String syllabusId_FieldWorkid, String practiceLessonid) {
		
		if(fieldContent.getFieldContentid() != null){
			fieldContentDao.update(fieldContent);
		}else{
			fieldContentDao.add(fieldContent);
			
			Syllabus_FieldWork syllabus_FieldWork = syllabus_FieldWorkDao.get(Integer.valueOf(syllabusId_FieldWorkid));
		
			syllabus_FieldWork.setFieldContent(fieldContent);
			syllabus_FieldWorkDao.update(syllabus_FieldWork);
		}
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId_FieldWorkid);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson", newpracticeLesson);

		ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
		ServletActionContext.getRequest().setAttribute("tag", "toPracLes_FieldWorkPage");
	}
	
}
