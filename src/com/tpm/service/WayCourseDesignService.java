package com.tpm.service;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.Syllabus_CourseDesignDao;
import com.tpm.dao.WayCourseDesignDao;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus_CourseDesign;
import com.tpm.entity.WayCourseDesign;
@Transactional
public class WayCourseDesignService {

	private WayCourseDesignDao wayCourseDesignDao;
	private PracticePlanDao practicePlanDao;
	private Syllabus_CourseDesignDao syllabus_CourseDesignDao;
	

	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	public void setSyllabus_CourseDesignDao(
			Syllabus_CourseDesignDao syllabus_CourseDesignDao) {
		this.syllabus_CourseDesignDao = syllabus_CourseDesignDao;
	}
	public void setWayCourseDesignDao(WayCourseDesignDao wayCourseDesignDao) {
		this.wayCourseDesignDao = wayCourseDesignDao;
	}
	public void toTeaMethodCouDesPage(String syllabusId, String practiceLessonid) {

		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
		if(syllabus_CourseDesign.getWayCourseDesign() != null){
			ServletActionContext.getRequest().setAttribute("wayCourseDesign",syllabus_CourseDesign.getWayCourseDesign());
		}
		
	}
	public void updateWayCourseDesign(WayCourseDesign wayCourseDesign, String syllabusId, String practiceLessonid) {
		if(wayCourseDesign.getWayCourseDesignid() != null){
			Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
			syllabus_CourseDesign.setWayCourseDesign(wayCourseDesign);
			wayCourseDesignDao.update(wayCourseDesign);
		}else{
			wayCourseDesignDao.add(wayCourseDesign);
			
			Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
		
			syllabus_CourseDesign.setWayCourseDesign(wayCourseDesign);
			syllabus_CourseDesignDao.update(syllabus_CourseDesign);
		}
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson", newpracticeLesson);

		ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
		ServletActionContext.getRequest().setAttribute("tag", "toPracLes_CourseDesignPage");
		
	}
}
