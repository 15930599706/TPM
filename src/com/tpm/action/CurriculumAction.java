package com.tpm.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.PageBean;
import com.tpm.entity.User;
import com.tpm.service.CollegeService;
import com.tpm.service.CurriculumService;
import com.tpm.service.UserService;

public class CurriculumAction extends ActionSupport implements ModelDriven<Curriculum>{
	private Curriculum curriculum = new Curriculum();
	public Curriculum getModel() {
		return curriculum;
	}
	private CurriculumService curriculumService;
	public void setCurriculumService(CurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}
	private CollegeService collegeService;
	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private Integer currentpage;
	private String departmenttag;
	private String usertag;
	public String getUsertag() {
		return usertag;
	}
	public void setUsertag(String usertag) {
		this.usertag = usertag;
	}
	public String getDepartmenttag() {
		return departmenttag;
	}
	public void setDepartmenttag(String departmenttag) {
		this.departmenttag = departmenttag;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}


	public String findcurriculum(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = curriculumService.findcurriculum(currentpage,curriculum);
		pageBean = collegeService.getall(pageBean);
		setAttr(pageBean);
		setAttr(curriculum);
		return "findcurriculum";
	}
	public String tokcxxPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = curriculumService.findcurriculum(currentpage,curriculum);
		pageBean = collegeService.getall(pageBean);
		setAttr(pageBean);
		setAttr(curriculum);
		return "tokcxxPage";
	}
	private String tnum;	
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getTnum() {
		return tnum;
	}
	public String tokbjPage(){
		currentpage = getcurrentpage(currentpage);
		User user = userService.getuser(tnum);
		PageBean pageBean = curriculumService.findcurriculumnew(currentpage,curriculum,user);
		if(user.getAdminlevel() == 5){
			pageBean = collegeService.getall(pageBean);
		}else{
			List<College> college = collegeService.getone(user.getCollege().getCollegeid());
			pageBean.setCollegelist(college);
		}
		setAttr(pageBean);
		setAttr(curriculum);
		return "tokbjPage";
	}
	public String tokcaddPage(){
		curriculumService.kcadd(curriculum,tnum);
		return "tokcaddPage";
	}
	private String perCreErr;
	public String getPerCreErr() {
		return perCreErr;
	}
	public void setPerCreErr(String perCreErr) {
		this.perCreErr = perCreErr;
	}
	private String keyCouEmpty;
	public String getKeyCouEmpty() {
		return keyCouEmpty;
	}
	public void setKeyCouEmpty(String keyCouEmpty) {
		this.keyCouEmpty = keyCouEmpty;
	}
	public String addcurriculum(){
		if(curriculum.getCurriculumid() == null || "".equals(curriculum.getCurriculumid()) || (curriculum.getCurriculumid() != null && !curriculum.getCurriculumid().substring(0,2).equals(curriculum.getCollege().getCollegeid()))){
			if(curriculum.getCurriculumid() != null){
				curriculumService.kcadddel(curriculum);
			}
			curriculumService.addcurriculum(curriculum,tnum);
			ServletActionContext.getRequest().setAttribute("tag", "add_tokcxxPage");
		}else{
			curriculumService.updatecurriculum(curriculum);
			if(perCreErr != null && perCreErr.equals("1")){//错误发现与追踪页面的修改
				ServletActionContext.getRequest().setAttribute("tag", "topreCreErrPage");
			}
			if(keyCouEmpty != null && keyCouEmpty.equals("1")){//关键课程信息为空页面的修改
				ServletActionContext.getRequest().setAttribute("tag", "tokeyCouEmptyPage");
			}
			else{
				ServletActionContext.getRequest().setAttribute("tag", "add_tokcxxPage");
			}
		}
		return "addcurriculum";
	}

	public String tokcupdatePage(){
		curriculumService.kcadd(curriculum,tnum);
		curriculumService.kcupdate(curriculum);
		if(perCreErr != null && perCreErr.equals("1")){//错误发现与追踪页面的修改
			ServletActionContext.getRequest().setAttribute("perCreErr", perCreErr);
		}
		if(keyCouEmpty != null && keyCouEmpty.equals("1")){//关键课程信息为空页面的修改
			ServletActionContext.getRequest().setAttribute("keyCouEmpty", keyCouEmpty);
		}
		return "tokcupdatePage";
	}
	public String tokcdelPage(){
		curriculumService.kcdel(curriculum);
		if(perCreErr != null && perCreErr.equals("1")){//错误发现与追踪页面的删除
			ServletActionContext.getRequest().setAttribute("tag", "topreCreErrPage");
		}
		if(keyCouEmpty != null && keyCouEmpty.equals("1")){//关键课程信息为空页面的删除
			ServletActionContext.getRequest().setAttribute("tag", "tokeyCouEmptyPage");
		}
		else{
			ServletActionContext.getRequest().setAttribute("tag", "del_tokcxxPage");
		}
		return "tokcdelPage";
	}
	public String tokcupdateaddPage(){
		curriculumService.kcadd(curriculum,tnum);
		curriculumService.kcupdateadd(curriculum);
		return "tokcupdateaddPage";
	}
	public String tokcfpPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = curriculumService.findcurriculum(currentpage,curriculum);
		pageBean = collegeService.getall(pageBean);
		setAttr(pageBean);
		setAttr(curriculum);
		return "tokcfpPage";
	}
/*	public String todepartment(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = curriculumService.findcurriculum(currentpage,curriculum,departmenttag);
		List<College> collegelist = collegeService.getone(curriculum.getCollege().getCollegeid());
		pageBean.setCollegelist(collegelist);
		setAttr(pageBean);
		setAttr(curriculum);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
		return "todepartment";
	}*/
/*	public String gogivedepartment(){
		curriculumService.gogivedepartment(curriculum);
		return "gogivedepartment";
	}*/
/*	public String goupdatedepartment(){
		curriculumService.goupdatedepartment(curriculum);
		return "goupdatedepartment";
	}*/
/*	public String tousercollege(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = curriculumService.findcurriculumuser(currentpage,curriculum,usertag);
		List<College> collegelist = collegeService.getone(curriculum.getCollege().getCollegeid());
		pageBean.setCollegelist(collegelist);
		setAttr(pageBean);
		setAttr(curriculum);
		ServletActionContext.getRequest().setAttribute("newusertag", usertag);
		return "tousercollege";
	}*/
/*	public String kctouserpagecollege(){
		Curriculum newCurriculum = curriculumService.findone(curriculum.getCurriculumid());
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = curriculumService.kctouserpagecollege(currentpage,curriculum);
		List<College> collegelist = collegeService.getone(curriculum.getCollege().getCollegeid());
		pageBean.setCollegelist(collegelist);
		setAttr(pageBean);
		setAttr(curriculum);
		ServletActionContext.getRequest().setAttribute("newCurriculum", newCurriculum);
		return "kctouserpagecollege";
	}*/
/*	public String touserdepartment(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = curriculumService.findcurriculumuserdepartment(currentpage,curriculum,usertag);
		List<College> collegelist = collegeService.getone(curriculum.getCollege().getCollegeid());
		pageBean.setCollegelist(collegelist);
		List<Department> departmentlist = userService.finddepartment(curriculum.getDepartment().getDepartmentid());
		pageBean.setDepartmentlist(departmentlist);
		setAttr(pageBean);
		setAttr(curriculum);
		ServletActionContext.getRequest().setAttribute("newusertag", usertag);
		return "touserdepartment";
	}*/
/*	public String kctouserpagedepartment(){
		Curriculum newCurriculum = curriculumService.findone(curriculum.getCurriculumid());
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = curriculumService.kctouserpagedepartment(currentpage,curriculum);
		List<College> collegelist = collegeService.getone(curriculum.getCollege().getCollegeid());
		List<Department> departmentlist = userService.finddepartment(curriculum.getDepartment().getDepartmentid());
		pageBean.setDepartmentlist(departmentlist);
		pageBean.setCollegelist(collegelist);
		setAttr(pageBean);
		setAttr(curriculum);
		ServletActionContext.getRequest().setAttribute("newCurriculum", newCurriculum);
		return "kctouserpagedepartment";
	}*/
/*	public String kctouser(){
		curriculumService.kctouser(curriculum);
		return "kctouser";
	}*/
	public String toTheoLesPage(){
		curriculumService.TheoLes(curriculum,tnum);
		return "toTheoLesPage";
	}
	public String toPracLesPage(){
		curriculumService.PracLes(curriculum,tnum);
		return "toPracLesPage";
	}
	

	private void setAttr(Curriculum curriculum) {
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
	}
	private void setAttr(PageBean pageBean) {
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	}
	private Integer getcurrentpage(Integer currentpage) {
		if(currentpage == null||currentpage == 0){
			currentpage=1;
		}
		return currentpage;
	}
}
