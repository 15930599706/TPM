package com.tpm.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.dao.NoticeDao;
import com.tpm.dao.NoticeFileDao;
import com.tpm.entity.PageBean;
import com.tpm.entity.User;
import com.tpm.service.CollegeService;
import com.tpm.service.CurriculumService;
import com.tpm.service.DepartmentService;
import com.tpm.service.ExperimentService;
import com.tpm.service.NatureOfCourseService;
import com.tpm.service.NoticeFileService;
import com.tpm.service.NoticeService;
import com.tpm.service.ProfessionalService;
import com.tpm.service.ScoreThresholdService;
import com.tpm.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private String oldpwd;
	private String newpwd;
	private Integer currentpage;
	private File excelFile;
	public User getModel() {
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private CollegeService collegeService;
	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}
	private DepartmentService departmentService;
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	private CurriculumService curriculumService;
	public void setCurriculumService(CurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}
	private NatureOfCourseService natureOfCourseService;
	public void setNatureOfCourseService(NatureOfCourseService natureOfCourseService) {
		this.natureOfCourseService = natureOfCourseService;
	}
	private ProfessionalService professionalService;
	public void setProfessionalService(ProfessionalService professionalService) {
		this.professionalService = professionalService;
	}
	private NoticeFileService noticeFileService;
	public void setNoticeFileService(NoticeFileService noticeFileService) {
		this.noticeFileService = noticeFileService;
	}
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	private ScoreThresholdService scoreThresholdService;
	public void setScoreThresholdService(ScoreThresholdService scoreThresholdService) {
		this.scoreThresholdService = scoreThresholdService;
	}
	private ExperimentService experimentService;
	public void setExperimentService(ExperimentService experimentService) {
		this.experimentService = experimentService;
	}
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	
	
	
	
	
	
	
	public String loginsuccess(){
		return "loginsuccess";
	}
	public String toeditpwdPage(){//跳转到修改密码页面
		return "toeditpwdPage";
	}
	public String editpwd(){//修改密码
		userService.editpwd(user,oldpwd,newpwd);
		return "editpwd";
	}
	public String toxybmPage(){//跳转到学院信息页面
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = collegeService.xybm(currentpage);
		setAttr(pageBean);
		return "toxybmPage";
	}
	public String tozybmPage(){//专业编码
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = departmentService.zybm(currentpage,user);
		ServletActionContext.getRequest().setAttribute("newcollege",user.getCollege().getCollegeid());
		setAttr(pageBean);
		return "tozybmPage";
	}
	
	public String tosysbmPage(){//实验室编码
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = experimentService.sysbm(currentpage,user);
		ServletActionContext.getRequest().setAttribute("newcollege",user.getCollege().getCollegeid());
		setAttr(pageBean);
		return "tosysbmPage";
	}

	public String tozyfxPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = professionalService.kck(currentpage,user);
		ServletActionContext.getRequest().setAttribute("newcollege",user.getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("newdepart",user.getDepartment().getDepartmentid());
		setAttr(pageBean);
		return "tozyfxPage";
	}
	public String importCollegeExcel() throws FileNotFoundException{//导入学院表
		String path=excelFile.getAbsolutePath();
		collegeService.importCollegeExcel(path);
		return "importCollegeExcel";
	}
	public String importDepartmentExcel() throws FileNotFoundException{//导入系表
		String path=excelFile.getAbsolutePath();
		departmentService.importDepartmentExcel(path);
		return "importDepartmentExcel";
	}
	public String importUserExcel() throws FileNotFoundException{//导入用户表
		String path=excelFile.getAbsolutePath();
		userService.importUserExcel(path);
		return "importUserExcel";
	}
	public String importNatureOfCourseExcel() throws FileNotFoundException{//导入课程性质表
		String path=excelFile.getAbsolutePath();
		natureOfCourseService.importNatureOfCourseExcel(path);
		return "importNatureOfCourseExcel";
	}
	public String importCurriculumExcel() throws FileNotFoundException{//导入课程表
		String path=excelFile.getAbsolutePath();
		curriculumService.importCurriculumExcel(path);
		return "importCurriculumExcel";
	}
	public String importProfessionalExcel() throws FileNotFoundException{//导入专业方向表
		String path=excelFile.getAbsolutePath();
		professionalService.importProfessionalExcel(path);
		return "importProfessionalExcel";
	}
	public String importScoreThresholdExcel() throws FileNotFoundException{//导入学分阈值表
		String path=excelFile.getAbsolutePath();
		scoreThresholdService.importScoreThresholdExcel(path);
		return "importScoreThresholdExcel";
	}
	public String importExperimentExcel() throws FileNotFoundException{//导入实验室表
		String path=excelFile.getAbsolutePath();
		experimentService.importExperimentExcel(path);
		return "importExperimentExcel";
	}
	public String importExperimenterExcel() throws FileNotFoundException{//导入实验员表
		String path=excelFile.getAbsolutePath();
		experimentService.importExperimenterExcel(path);
		return "importExperimenterExcel";
	}
	public void exportExperimenterModelExcel(){//导出实验员模板
		experimentService.exportExperimenterModelExcel();
	}
	public void exportExperimentModelExcel(){//导出实验室模板
		experimentService.exportExperimentModelExcel();
	}
	public void exportScoreThresholdExcel(){//导出学分阈值表模板
		scoreThresholdService.exportScoreThresholdExcel();
	}
	public void exportCollegeModelExcel(){//导出学院表模板
		collegeService.exportCollegeModelExcel();
	}
	public void exportDepartmentModelExcel(){//导出系表模板
		departmentService.exportDepartmentModelExcel();
	}
	public void exportCurriculumModelExcel(){//导出课程表模板
		curriculumService.exportCurriculumModelExcel();
	}
	public void exportProfessionalModelExcel(){//导出专业方向表模板
		professionalService.exportProfessionalModelExcel();
	}
	public void exportUserModelExcel(){//导出用户表模板
		userService.exportUserModelExcel();
	}
	public void exportNatureOfCourseModelExcel(){//导出课程性质表模板
		natureOfCourseService.exportNatureOfCourseModelExcel();
	}
	public void exportCollegeExcel(){//导出学院信息
		collegeService.exportCollegeExcel();
	}
	public void exportDepartmentExcel(){//导出系信息
		departmentService.exportDepartmentExcel();
	}
	public void exportCurriculumExcel(){//导出课程信息
		curriculumService.exportCurriculumExcel();
	}
	public void exportProfessionalExcel(){//导出专业方向信息
		professionalService.exportProfessionalExcel();
	}
	public String tomainPage(){
		PageBean pageBean = noticeFileService.zyxz(1);
		pageBean = noticeService.tzfb(1,pageBean);
		setAttr(pageBean);
		return "tomainPage";
	}
	public String todlzhPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.dlzh(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "todlzhPage";
	}
	public String totjdlPage(){
		userService.tjdl(user);
		return "totjdlPage";
	}
	public String adduser(){
		Integer count = userService.adduser(user);
		if(count != 0){
			ServletActionContext.getRequest().setAttribute("tag", "add_todlzhPage");
			ServletActionContext.getRequest().setAttribute("msg", "添加失败，该职工号已存在！");
			return "adderr";
		}else{
			ServletActionContext.getRequest().setAttribute("tag", "add_todlzhPage");
			ServletActionContext.getRequest().setAttribute("msg", "用户添加成功！");
			return "addsuc";
		}
	}
	public String changeuser(){
		userService.changeuser(user);
		return "changeuser";
	}
	public String deluser(){
		String result=userService.deluser(user);
		if(result.equals("yes")){
			ServletActionContext.getRequest().setAttribute("msg", "用户删除成功！");
		}
		else{
			ServletActionContext.getRequest().setAttribute("msg", "用户删除失败，因为该老师已被分配撰写课程大纲！");
		}
		ServletActionContext.getRequest().setAttribute("tag", "del_todlzhPage");
		
		return "deluser";
	}
	public String toshcooladmin(){
		userService.toshcooladmin(user);
		ServletActionContext.getRequest().setAttribute("tag", "toxxglPage");
		ServletActionContext.getRequest().setAttribute("msg", "权限修改成功！");
		return "toshcooladmin";
	}
	public String tocollegeadmin(){
		userService.tocollegeadmin(user);
		ServletActionContext.getRequest().setAttribute("tag", "toxyglPage");
		ServletActionContext.getRequest().setAttribute("msg", "权限修改成功！");
		return "tocollegeadmin";
	}
	public String todepartmentadmin(){
		userService.todepartmentadmin(user);
		ServletActionContext.getRequest().setAttribute("tag", "toxglPage");
		ServletActionContext.getRequest().setAttribute("msg", "权限修改成功！");
		return "todepartmentadmin";
	}
	public String toeasyuser(){
		userService.toeasyuser(user);
		ServletActionContext.getRequest().setAttribute("tag", "down_todlzhPage");
		ServletActionContext.getRequest().setAttribute("msg", "权限修改成功！");
		return "toeasyuser";
	}
	public String tobjdlPage(){
		userService.bjdl(user);
		return "tobjdlPage";
	}
	public String updateuser(){
		userService.updateuser(user);
		ServletActionContext.getRequest().setAttribute("tag", "update_todlzhPage");
		ServletActionContext.getRequest().setAttribute("msg", "修改成功!");
		return "updateuser";
	}
	public String toxxglPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.xxgl(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "toxxglPage";
	}
	public String totjxxPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.tjxx(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "totjxxPage";
	}
	public String toxyglPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.xygl(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "toxyglPage";
	}
	public String totjxyPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.tjxy(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "totjxyPage";
	}
	public String toxglPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.xgl(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "toxglPage";
	}
	public String totjxPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.tjx(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "totjxPage";
	}
	/*********实验室主任****************************/
	private String modifyid;//修改者id
	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}
	public String tosyszrglPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.syszrgl(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "tosyszrglPage";
	}
	public String toeasyexperimentAdmin(){
		userService.toeasyexperimentAdmin(modifyid,user);
		ServletActionContext.getRequest().setAttribute("tag", "tosyszrglPage");
		ServletActionContext.getRequest().setAttribute("msg", "权限修改成功！");
		return "toeasyexperimentAdmin";
	}
	public String totjsyszrPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.tjsyszr(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "totjsyszrPage";
	}
	public String toexperimentadmin(){
		userService.toexperimentadmin(modifyid,user);
		ServletActionContext.getRequest().setAttribute("tag", "tosyszrglPage");
		ServletActionContext.getRequest().setAttribute("msg", "权限修改成功！");
		return "toexperimentadmin";
	}
	
	public String tosysrzAdmin(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.tosysrzAdmin(currentpage);
		setAttr(pageBean);
		return "tosysrzAdmin";
	}
	/*********实验室主任****************************/
	/*********实验员****************************/
	public String tosyyglPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.syygl(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "tosyyglPage";
	}
	public String toeasyexperimenter(){
		userService.toeasyexperimenter(modifyid,user);
		ServletActionContext.getRequest().setAttribute("tag", "tosyyglPage");
		ServletActionContext.getRequest().setAttribute("msg", "权限修改成功！");
		return "toeasyexperimenter";
	}
	public String totjsyyPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = userService.tjsyy(user,currentpage);
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("newuser", user);
		return "totjsyyPage";
	}
	public String toexperimenter(){
		userService.toexperimenter(modifyid,user);
		ServletActionContext.getRequest().setAttribute("tag", "tosyyglPage");
		ServletActionContext.getRequest().setAttribute("msg", "权限修改成功！");
		return "toexperimenter";
	}
	/*********实验员****************************/
	
	public String togetInPage(){
		PageBean pageBean = userService.togetInPage(user);
		setAttr(pageBean);
		return "togetInPage";
	}
		

	public String initpwd;
	public String resetpassword() throws Exception{//跳转到登陆账号查询页面
			userService.initPassword(user,initpwd);			
			return "initpwd";
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
