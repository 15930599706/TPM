package com.tpm.service;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CollegeDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.TrainingAnotherDao;
import com.tpm.entity.Department;
import com.tpm.entity.TrainingAnother;
@Transactional
public class TrainingAnotherService {

	private TrainingAnotherDao trainingAnotherDao;

	public void setTrainingAnotherDao(TrainingAnotherDao trainingAnotherDao) {
		this.trainingAnotherDao = trainingAnotherDao;
	}
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void toanotherPage(TrainingAnother trainingAnother) {
		TrainingAnother newtrainingAnother= trainingAnotherDao.getbyTrainingAnother(trainingAnother);
		Department department = departmentDao.get(trainingAnother.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("department", department);
		if(newtrainingAnother != null)
		{
			ServletActionContext.getRequest().setAttribute("trainingAnother", newtrainingAnother);
			
		}
	}
	public void revisetoanotherPage(TrainingAnother trainingAnother) {
		toanotherPage(trainingAnother);
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
	}

	public void updataanother(TrainingAnother trainingAnother) {
		Department department = departmentDao.get(trainingAnother.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("departmentid", department.getDepartmentid());
		ServletActionContext.getRequest().setAttribute("collegeid", department.getCollege().getCollegeid());
		if(trainingAnother.getTrainingAnotherid() != null)
		{
			trainingAnotherDao.update(trainingAnother);
		}
		else trainingAnotherDao.add(trainingAnother);

		ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
	}



		
	}

