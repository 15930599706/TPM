package com.tpm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CollegeDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.PPTrainingConceptDao;
import com.tpm.dao.UserDao;
import com.tpm.entity.College;
import com.tpm.entity.Department;
import com.tpm.entity.PPTrainingConcept;
import com.tpm.entity.PageBean;
import com.tpm.entity.User;
@Transactional
public class PPTrainingConceptService {
	private PPTrainingConceptDao ppTrainingConceptDao;
	private UserDao userDao;
	private CollegeDao collegeDao;
	private DepartmentDao departmentDao;
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setPpTrainingConceptDao(PPTrainingConceptDao ppTrainingConceptDao) {
		this.ppTrainingConceptDao = ppTrainingConceptDao;
	}

	public PageBean zypy(String tnum, Integer currentpage, String collegeid) {
		return pagebean(tnum,currentpage,collegeid);
	}
	public void bjzy(PPTrainingConcept ppTrainingConcept) {
		PPTrainingConcept newppTrainingConcept = ppTrainingConceptDao.getbydepartment(ppTrainingConcept.getDepartment());
		Department department = departmentDao.get(ppTrainingConcept.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("ppTrainingConcept", newppTrainingConcept);
		ServletActionContext.getRequest().setAttribute("department", department);
	}
	public void revisebjzy(PPTrainingConcept ppTrainingConcept) {
		bjzy(ppTrainingConcept);
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
	}
	public void updateppTrainingConcept(PPTrainingConcept ppTrainingConcept) {
		if(ppTrainingConcept.getPPTrainingConceptid() != null && !"".equals(ppTrainingConcept.getPPTrainingConceptid())){
			ppTrainingConceptDao.update(ppTrainingConcept);
		}else{
			ppTrainingConceptDao.add(ppTrainingConcept);
		}
		ServletActionContext.getRequest().setAttribute("msg", "操作成功");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private PageBean pagebean(String tnum, Integer currentpage, String collegeid) {
		PageBean pageBean = new PageBean();
		int pageSize = 10;
		int totalPage = 0;
		int totalCount = 0;
		User user = userDao.get(tnum);
		if(user.getAdminlevel() == 3){
			totalCount = departmentDao.findCountbyCollege(user.getCollege());
		}
		if(user.getAdminlevel() == 5){
			totalCount = departmentDao.finddepartmentCount(collegeDao.get(collegeid));
		}
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		if(user.getAdminlevel() == 3){
			List<Department> departmentlist = departmentDao.findTbyCollege(begin, pageSize,user.getCollege());
			pageBean.setDepartmentlist(departmentlist);
			College college = user.getCollege();
			ServletActionContext.getRequest().setAttribute("college", college);
		}
		if(user.getAdminlevel() == 5){
			List<Department> departmentlist = departmentDao.finddepartmentT(begin, pageSize,collegeDao.get(collegeid));
			pageBean.setDepartmentlist(departmentlist);
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		return pageBean;
	}

	
	
	
	
	
	
}
