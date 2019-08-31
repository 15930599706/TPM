package com.tpm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CollegeDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.TeachCalDao;
import com.tpm.dao.UserDao;
import com.tpm.entity.College;
import com.tpm.entity.NoticeFile;
import com.tpm.entity.PageBean;
import com.tpm.entity.TeachCal;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.User;
@Transactional
public class TeachCalService {
	private TeachCalDao teachCalDao;
	private UserDao userDao;
	private CollegeDao collegeDao;
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setTeachCalDao(TeachCalDao teachCalDao) {
		this.teachCalDao = teachCalDao;
	}
	public Integer getCountByName(String uploadFileName) {
		return teachCalDao.getCountByName(uploadFileName);
	}
	public void addteachCalFile(TeachCal teachCal) {
		teachCalDao.add(teachCal);
	}
	public PageBean findByTeachCalFirstPage(TeachCal teachCal,Integer currentpage) 
	{
		PageBean pageBean=new PageBean();
		List<TeachCal> listteachCal=new ArrayList<TeachCal>(); 
		User teacher=userDao.get(teachCal.getUser().getTnum());
		if(teacher.getAdminlevel()==0)
		{
			listteachCal=teachCalDao.findByUser(teacher);
		}
		else if(teacher.getAdminlevel()==1)
		{
			listteachCal=teachCalDao.findByUsersDepart(teacher);
		}
		else if(teacher.getAdminlevel()==3)
		{
			College college=teacher.getCollege();
			List<College> listcollege=new ArrayList<College>();
			listcollege.add(college);
			pageBean.setCollegelist(listcollege);
			if(!"-1".equals(teachCal.getUser().getDepartment().getDepartmentid())&&!"".equals(teachCal.getUser().getDepartment().getDepartmentid()))
			{
				User teacher1=new User();
				teacher1.setDepartment(departmentDao.get(teachCal.getUser().getDepartment().getDepartmentid()));
				listteachCal=teachCalDao.findByUsersDepart(teacher1);
			}
			else
			{
				listteachCal=teachCalDao.findByUsersCollege(teacher);
			}
			
		}
		else 
		{
			List<College> collegelist=collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
			if(!"".equals(teachCal.getUser().getCollege().getCollegeid())
					&&("-1".equals(teachCal.getUser().getDepartment().getDepartmentid()) || !teachCal.getUser().getDepartment().getDepartmentid().substring(0, 2).equals(teachCal.getUser().getCollege().getCollegeid())))
			{
				User teacher1=new User();
				teacher1.setCollege(collegeDao.get(teachCal.getUser().getCollege().getCollegeid()));
				listteachCal=teachCalDao.findByUsersCollege(teacher1);
				
			}
			
			else if(!"".equals(teachCal.getUser().getDepartment().getDepartmentid()))
			{
				User teacher1=new User();
				teacher1.setDepartment(departmentDao.get(teachCal.getUser().getDepartment().getDepartmentid()));
				listteachCal=teachCalDao.findByUsersDepart(teacher1);
			}
			else
			{
				listteachCal=teachCalDao.findAll();
			}			
		}
		int totalCount = listteachCal.size();		
		
		int pageSize = 10;
		int totalPage = 0;
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
		
		if(totalPage == 0){
			currentpage = 1;
		}
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		int begin = totalCount-(currentpage-1)*pageSize;
		
		List<TeachCal> listteachCal2=OrderfindT(begin,pageSize,listteachCal);
		pageBean.setTeachCallist(listteachCal2);
		
		return pageBean;
	}
	public List<TeachCal> OrderfindT(int begin, int pageSize, List<TeachCal> listteachCal) {
		List<TeachCal> nflist = listteachCal;
		List<TeachCal> newnflist = new ArrayList<TeachCal>();
		if(begin > pageSize){
			for(int i=begin-1,j=0;j<pageSize;j++,i--){
				newnflist.add(nflist.get(i));
			}
		}else{
			for(int i=begin-1;i>=0;i--){
				newnflist.add(nflist.get(i));
			}
		}
		return newnflist;
	}
	public void delTeachCalPage(Integer teachCalfileid) {
		TeachCal teachCal=teachCalDao.get(teachCalfileid);
		teachCalDao.delete(teachCal);
		System.out.println("end");
	}
	public TeachCal get(Integer teachCalfileid) {
		return (TeachCal)teachCalDao.get(teachCalfileid);
	}
}
