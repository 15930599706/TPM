package com.tpm.service;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ConCourseDesignDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.Syllabus_CourseDesignDao;
import com.tpm.entity.ConCourseDesign;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus;
import com.tpm.entity.Syllabus_CourseDesign;
import com.tpm.entity.Syllabus_FieldWork;
@Transactional
public class ConCourseDesignService {

	private ConCourseDesignDao conCourseDesignDao;
	private PracticePlanDao practicePlanDao;
	private Syllabus_CourseDesignDao syllabus_CourseDesignDao;
	
	
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setSyllabus_CourseDesignDao(
			Syllabus_CourseDesignDao syllabus_CourseDesignDao) {
		this.syllabus_CourseDesignDao = syllabus_CourseDesignDao;
	}

	public void setConCourseDesignDao(ConCourseDesignDao conCourseDesignDao) {
		this.conCourseDesignDao = conCourseDesignDao;
	}

	public void toContentCouDesPage(String syllabusId, String practiceLessonid) {
		
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
		if(syllabus_CourseDesign.getConCourseDesign() != null){
			ServletActionContext.getRequest().setAttribute("conCourseDesign", syllabus_CourseDesign.getConCourseDesign());
		}
		
	}

	public void updateConCourseDesign(ConCourseDesign conCourseDesign, String syllabusId, String practiceLessonid) {
		if(conCourseDesign.getConCourseDesignid() != null){
			Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
			syllabus_CourseDesign.setConCourseDesign(conCourseDesign);
			conCourseDesignDao.update(conCourseDesign);
		}else{
			conCourseDesignDao.add(conCourseDesign);
			
			Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
		
			syllabus_CourseDesign.setConCourseDesign(conCourseDesign);
			syllabus_CourseDesignDao.update(syllabus_CourseDesign);
		}
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson", newpracticeLesson);

		ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
		ServletActionContext.getRequest().setAttribute("tag", "toPracLes_CourseDesignPage");
	}
	
}
