package com.tpm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CollegeDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.ExperimentDao;
import com.tpm.dao.LinkDao;
import com.tpm.entity.College;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.Professional;
@Transactional
public class LinkService {
	private LinkDao linkDao;
	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}
	private CollegeDao collegeDao;
	private DepartmentDao departmentDao;
	private ExperimentDao experimentDao;
	public void setExperimentDao(ExperimentDao experimentDao) {
		this.experimentDao = experimentDao;
	}
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public List<Department> findxglLink(College college) {
		return departmentDao.findTbyCollege(college);
	}
	//根据id查询学院
	public College findcollege(String id) {
		return collegeDao.get(id);
	}
	//根据id查询专业
	public Department finddepartment(String id) {
		return departmentDao.get(id);
	}
	public List<Professional> findProfessional(Department department) {
		return linkDao.findProfessional(department);
	}
	public List<Experiment> findExperiment(College college) {
		List<Experiment> experimentlist=experimentDao.findbyCollege(college);
		return experimentlist;
	}
	
}
