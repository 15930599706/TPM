package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.PracticeBookDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.entity.PracticeBook;
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TextBooks;
@Transactional
public class PracticeBookService {

	private PracticeBookDao practiceBookDao;

	private PracticePlanDao practicePlanDao;
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setPracticeBookDao(PracticeBookDao practiceBookDao) {
		this.practiceBookDao = practiceBookDao;
	}

	public List<PracticeBook> toMateriaTheoPage(String syllabusId, String practiceLessonid) {
		List<PracticeBook> practiceBooklist = practiceBookDao.getbytextBooks(syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		return practiceBooklist;
	}

	public void addpracticeBook(PracticeBook practiceBook) {
		practiceBookDao.add(practiceBook);	
	}

	public void updatepracticeBook(PracticeBook practiceBook) {
		practiceBookDao.update(practiceBook);
		
	}

	public void deletepracticeBook(PracticeBook practiceBook) {
		practiceBookDao.delete(practiceBook);
		
	}

	
}
