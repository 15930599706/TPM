package com.tpm.service;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabus_GraDao;
import com.tpm.dao.ContentGraDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.Syllabus_GraDao;
import com.tpm.entity.ContentGra;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus_CourseDesign;
import com.tpm.entity.Syllabus_Gra;

@Transactional
public class ContentGraService {
	private ContentGraDao contentGraDao;
	private Syllabus_GraDao syllabus_GraDao;
	private PracticePlanDao practicePlanDao;
	

	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setSyllabus_GraDao(Syllabus_GraDao syllabus_GraDao) {
		this.syllabus_GraDao = syllabus_GraDao;
	}

	public void setContentGraDao(ContentGraDao contentGraDao) {
		this.contentGraDao = contentGraDao;
	}
	
	public void toContentGraPage(String syllabusId, String practiceLessonid) {
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		Syllabus_Gra syllabus_Gra = syllabus_GraDao.get(Integer.valueOf(syllabusId));
		if(syllabus_Gra.getContentGra() != null){
			ServletActionContext.getRequest().setAttribute("contentGra", syllabus_Gra.getContentGra());
		}
	}

	public void updateContentGra(ContentGra contentGra, String syllabusId, String practiceLessonid) {
		
		if(contentGra.getContentGraid() != null){
			Syllabus_Gra syllabus_Gra = syllabus_GraDao.get(Integer.valueOf(syllabusId));
			syllabus_Gra.setContentGra(contentGra);
			contentGraDao.update(contentGra);
		}else{
			contentGraDao.add(contentGra);
			
			Syllabus_Gra syllabus_Gra = syllabus_GraDao.get(Integer.valueOf(syllabusId));
		
			syllabus_Gra.setContentGra(contentGra);;
			syllabus_GraDao.update(syllabus_Gra);
		}
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson", newpracticeLesson);

		ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
		ServletActionContext.getRequest().setAttribute("tag", "toPracLes_GraPage");
	}
	
}
