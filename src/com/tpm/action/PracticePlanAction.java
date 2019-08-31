package com.tpm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.PageBean;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.User;
import com.tpm.service.CollegeService;
import com.tpm.service.PracticePlanService;

public class PracticePlanAction extends ActionSupport implements
		ModelDriven<PracticeLesson> {
	// private String result;
	private String isLoad;

	public String getIsLoad() {
		return isLoad;
	}

	public void setIsLoad(String isLoad) {
		this.isLoad = isLoad;
	}


	private PracticeLesson practiceLesson = new PracticeLesson(); // 模型驱动

	public PracticeLesson getModel() {
		return practiceLesson;
	}

	private PracticePlanService practicePlanService; // service注入

	public void setPracticePlanService(PracticePlanService practicePlanService) {
		this.practicePlanService = practicePlanService;
	}

	private CollegeService collegeService; // 注入collegeService

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	private String tnum; // 接收登陆账户的职工号，用于查询用户级别、所在 系等

	public void setTnum(String tnum) {
		this.tnum = tnum;
	}

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	private String collegeid;

	public String getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}

	private String departmentid;

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public void findUserInfo(String tnum) { // 根据登陆用户id获得用户信息
		user = practicePlanService.findUserById(tnum);
		ServletActionContext.getRequest().setAttribute("user", user);
	}

	private List<College> collegelist; // 接收第一个下拉框数据
	private List<Department> departmentlist; // 声明系列表

	public void searchCollegeList() { // 查询所有学院列表
		collegelist = collegeService.findAllCollege();
		ServletActionContext.getRequest().setAttribute("collegelist",
				collegelist);
	}

	HttpServletRequest request = ServletActionContext.getRequest(); // 放入域对象

	public void downPracticeTemplate() { // 下载实践课模板
		practicePlanService.downPracticeTemplate();
	}

	public String tojzsjkPage() { // 跳转到集中实践课页面
		practicePlanService.tojzsjkPage(tnum, collegeid, departmentid);
		return "tojzsjkPage";
	}

	public String jzsjkResult() { // 集中实践课页面的返回
		practicePlanService.jzsjkResult(practiceLesson, tnum);
		return "jzsjkResult";
	}

	public String totjsjkPage() { // 跳转到添加实践课页面
		practicePlanService.totjsjkPage(collegeid, departmentid);
		return "totjsjkPage";
	}

	public String tjsjkResult() { // 添加实践课页面的返回
		practicePlanService
				.tjsjkResult(practiceLesson, collegeid, departmentid);
		return "tjsjkResult";
	}

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void sjkAddResult() {
		practicePlanService.sjkAddResult(data, departmentid);
	}

	// 删除实践课
	public String delsjk() {
		practicePlanService.setid(practiceLesson);
		practicePlanService.delPracticeLessonByid(practiceLesson
				.getPracticeLessonid());
		ServletActionContext.getRequest().setAttribute("tag", "tojzsjkPage");
		return "delsjk";
	}

	private List<String> deletepracticeLessonList;

	public void setDeletepracticeLessonList(
			List<String> deletepracticeLessonList) {
		this.deletepracticeLessonList = deletepracticeLessonList;
	}

	// 删除多个实践课
	public String delsjkMore() {
		practicePlanService.delsjkMore(deletepracticeLessonList);
		ServletActionContext.getRequest().setAttribute("tag", "tojzsjkPage");
		return "delsjk";
	}

	// 跳转到编辑实践课页面
	public String tobjsjkPage() {
		PracticeLesson bjsjk = practicePlanService
				.findPracticeLessonByid(practiceLesson.getPracticeLessonid());
		ServletActionContext.getRequest().setAttribute("practiceLesson", bjsjk);
		return "tobjsjkPage";
	}

	// 编辑实践课
	public String sjkUpdateResult() {
		practicePlanService.sjkUpdate(practiceLesson);
		practicePlanService.setid(practiceLesson);
		ServletActionContext.getRequest().setAttribute("tag", "tojzsjkPage");
		return "sjkUpdateResult";
	}

	// 导出所选实践课
	public void downPracticePlan() {
		practicePlanService.downPracticePlan(practiceLesson);

	}

	// 导出全部所选实践课
	public void downPracticePlan_all() {
		practicePlanService.downPracticePlan_all();
	}

	private List<String> selectProfessional;

	public List<String> getSelectProfessional() {
		return selectProfessional;
	}

	public void setSelectProfessional(List<String> selectProfessional) {
		this.selectProfessional = selectProfessional;
	}

	private List<String> haveselectProfessional;// 已编辑大纲的选择专业方向

	public List<String> getHaveselectProfessional() {
		return haveselectProfessional;
	}

	public void setHaveselectProfessional(List<String> haveselectProfessional) {
		this.haveselectProfessional = haveselectProfessional;
	}

	private String syllabusId;// 大纲ID

	public String getSyllabusId() {
		return syllabusId;
	}

	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}

	private String syllabusId_Copy;// 复制大纲的ID

	public String getSyllabusId_Copy() {
		return syllabusId_Copy;
	}

	public void setSyllabusId_Copy(String syllabusId_Copy) {
		this.syllabusId_Copy = syllabusId_Copy;
	}

	// ----------------实习------------------------
	public String toCheckPracLesFieldWorkPage() {// 到编辑大纲实践课——实习验证页面
		practiceLesson.setTeacher(user);
		practicePlanService.toCheckPracLes_FieldWorkPage(practiceLesson);
		return "toCheckPracLes_FieldWorkPage";
	}

	public String toCheckPracLesFieldWorkPageAdmin() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckPracLesFieldWorkPageAdmin(tnum,
				practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckPracLesFieldWorkPageAdmin";
	}

	public String toPracLesFieldWorkPage() {// 未编辑大纲到编辑实践课——实习大纲页面
		String tag = practicePlanService.toPracLesFieldWorkPage(practiceLesson,
				selectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesFieldWorkPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toPracLes_FieldWorkPage";
		}
	}

	public String toPracLesFieldWorkPageCopy() {// 未编辑大纲到复制实践课——实习大纲页面
		practicePlanService.toPracLesFieldWorkPageCopy(practiceLesson,
				selectProfessional, syllabusId_Copy);
		return "toPracLesFieldWorkPageCopy";
	}

	public String toHavePracLesFieldWorkPage() {// 已编辑大纲到编辑实践课——实习大纲页面
		String tag = practicePlanService.toHavePracLesFieldWorkPage(syllabusId,
				practiceLesson, haveselectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesFieldWorkPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toHavePracLesFieldWorkPage";
		}

	}

	public String deletePracSyllabusFieldWork() {// 删除实践课大纲——实习
		practicePlanService.deletePracSyllabusFieldWork(syllabusId);
		return "deletePracSyllabusFieldWork";
	}

	// ----------------课程设计--------------------------
	public String toCheckPracLesCourseDesignPage() {// 到编辑大纲实践课——课程设计验证页面
		practiceLesson.setTeacher(user);
		practicePlanService.toCheckPracLesCourseDesignPage(practiceLesson);
		return "toCheckPracLesCourseDesignPage";
	}

	public String toCheckCourseDesignPageAdmin() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckCourseDesignPageAdmin(tnum, practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckCourseDesignPageAdmin";
	}

	public String toPracLesCourseDesignPage() {// 未编辑大纲到编辑实践课——课程设计大纲页面
		String tag = practicePlanService.toPracLesCourseDesignPage(
				practiceLesson, selectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesCourseDesignPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toPracLesCourseDesignPage";
		}
	}

	public String toPracLesCourseDesignPageCopy() {// 未编辑大纲到复制实践课——课程设计大纲页面
		practicePlanService.toPracLesCourseDesignPageCopy(practiceLesson,
				selectProfessional, syllabusId_Copy);
		return "toPracLesCourseDesignPageCopy";
	}

	public String toHavePracLesCourseDesignPage() {// 已编辑大纲到编辑实践课——课程设计大纲页面
		String tag = practicePlanService.toHavePracLesCourseDesignPage(
				syllabusId, practiceLesson, haveselectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesCourseDesignPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toHavePracLesCourseDesignPage";
		}

	}

	public String deletePracSyllabusCourseDesign() {// 删除实践课大纲——课设
		practicePlanService.deletePracSyllabusCourseDesign(syllabusId);
		return "deletePracSyllabusCourseDesign";
	}

	// ----------------课内实验--------------------------
	public String toCheckPracLesInnerExperimentPage() {// 到编辑大纲实践课——课内实验验证页面
		practiceLesson.setTeacher(user);
		practicePlanService.toCheckPracLesInnerExperimentPage(practiceLesson);
		return "toCheckPracLesInnerExperimentPage";
	}

	public String toCheckInnerExperimentPageAdmin() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckInnerExperimentPageAdmin(tnum,
				practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckInnerExperimentPageAdmin";
	}

	public String toPracLesInnerExperimentPage() {// 未编辑大纲到编辑实践课——课内实验大纲页面
		String tag = practicePlanService.toPracLesInnerExperimentPage(
				practiceLesson, selectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesInnerExperimentPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toPracLesInnerExperimentPage";
		}
	}

	public String toPracLesInnerExperimentPageCopy() {// 未编辑大纲到复制实践课——课内实验大纲页面
		practicePlanService.toPracLesInnerExperimentPageCopy(practiceLesson,
				selectProfessional, syllabusId_Copy);
		return "toPracLesInnerExperimentPageCopy";
	}

	public String toHavePracLesInnerExperimentPage() {// 已编辑大纲到编辑实践课——课内实验大纲页面
		String tag = practicePlanService.toHavePracLesInnerExperimentPage(
				syllabusId, practiceLesson, haveselectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesInnerExperimentPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toHavePracLesInnerExperimentPage";
		}
	}

	public String deletePracSyllabusInnerExperiment() {// 删除实践课大纲——课内实验
		practicePlanService.deletePracSyllabusInnerExperiment(syllabusId);
		return "deletePracSyllabusInnerExperiment";
	}

	// -------------------毕业设计------------------------
	public String toCheckPracLesGraduationProjectPage() {// 到编辑大纲实践课——毕业设计验证页面
		practiceLesson.setTeacher(user);
		practicePlanService.toCheckPracLesGraduationProjectPage(practiceLesson);
		return "toCheckPracLesGraduationProjectPage";
	}

	public String toCheckGraduationProjectPageAdmin() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckGraduationProjectPageAdmin(tnum,
				practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckGraduationProjectPageAdmin";
	}

	public String toPracLesGraduationProjectPage() {// 未编辑大纲到编辑实践课——毕业设计大纲页面
		String tag = practicePlanService.toPracLesGraduationProjectPage(
				practiceLesson, selectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesGraduationProjectPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toPracLesGraduationProjectPage";
		}
	}

	public String toPracLesGraduationProjectPageCopy() {// 未编辑大纲到复制实践课——毕业设计大纲页面
		practicePlanService.toPracLesGraduationProjectPageCopy(practiceLesson,
				selectProfessional, syllabusId_Copy);
		return "toPracLesGraduationProjectPageCopy";
	}

	public String toHavePracLesGraduationProjectPage() {// 已编辑大纲到编辑实践课——毕业设计大纲页面
		String tag = practicePlanService.toHavePracLesGraduationProjectPage(
				syllabusId, practiceLesson, haveselectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesGraduationProjectPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toHavePracLesGraduationProjectPage";
		}
	}

	public String deletePracSyllabusGraduationProject() {// 删除实践课大纲——毕业设计
		practicePlanService.deletePracSyllabusGraduationProject(syllabusId);
		return "deletePracSyllabusGraduationProject";
	}

	// -------------------其他------------------------
	public String toPracLesOtherPage() {// 未编辑大纲到编辑实践课——其他大纲页面
		String tag = practicePlanService.toPracLesOtherPage(practiceLesson,
				selectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesOtherPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toPracLesOtherPage";
		}
	}

	public String toCheckOtherPageAdmin() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckOtherPageAdmin(tnum, practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckOtherPageAdmin";
	}

	public String toPracLesOtherPageCopy() {// 未编辑大纲到复制实践课——其他大纲页面
		practicePlanService.toPracLesOtherPageCopy(practiceLesson,
				selectProfessional, syllabusId_Copy);
		return "toPracLesOtherPageCopy";
	}

	public String toHavePracLesOtherPage() {// 已编辑大纲到编辑实践课——其他大纲页面
		String tag = practicePlanService.toHavePracLesOtherPage(syllabusId,
				practiceLesson, haveselectProfessional);
		if (tag.equals("no")) {
			ServletActionContext.getRequest().setAttribute("tag",
					"toCheckPracLesOtherPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		} else {
			return "toHavePracLesOtherPage";
		}

	}

	public String deletePracSyllabusOther() {// 删除实践课大纲——其他
		practicePlanService.deletePracSyllabusOther(syllabusId);
		return "deletePracSyllabusOther";
	}

	private Integer currentpage;

	public Integer getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}

	private Integer getcurrentpage(Integer currentpage) {
		if (currentpage == null || currentpage == 0) {
			currentpage = 1;
		}
		return currentpage;
	}

	private String departmenttag;

	public String getDepartmenttag() {
		return departmenttag;
	}

	public void setDepartmenttag(String departmenttag) {
		this.departmenttag = departmenttag;
	}

	public void setAttr(PageBean pageBean) {
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	}

	private List<String> depart;
	private String curriculumid;

	public List<String> getDepart() {
		return depart;
	}

	public void setDepart(List<String> depart) {
		this.depart = depart;
	}

	public String getCurriculumid() {
		return curriculumid;
	}

	public void setCurriculumid(String curriculumid) {
		this.curriculumid = curriculumid;
	}

	private String usertag;

	public String getUsertag() {
		return usertag;
	}

	public void setUsertag(String usertag) {
		this.usertag = usertag;
	}

	private List<String> newchoosedepartlist;

	public List<String> getNewchoosedepartlist() {
		return newchoosedepartlist;
	}

	public void setNewchoosedepartlist(List<String> newchoosedepartlist) {
		this.newchoosedepartlist = newchoosedepartlist;
	}

	private Integer newcurrentpage;

	public Integer getNewcurrentpage() {
		return newcurrentpage;
	}

	public void setNewcurrentpage(Integer newcurrentpage) {
		this.newcurrentpage = newcurrentpage;
	}

	private Integer getnewcurrentpage(Integer newcurrentpage) {
		return newcurrentpage;
	}

	private String newdepartmenttag1;

	public String getNewdepartmenttag1() {
		return newdepartmenttag1;
	}

	public void setNewdepartmenttag1(String newdepartmenttag1) {
		this.newdepartmenttag1 = newdepartmenttag1;
	}

	/* 实践课分配到专业 */
	public String prctodepartment() {
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if (newcurrentpage == null || newcurrentpage == 0) {// 第一次进入课内实验分配到老师的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = practicePlanService.findtheolen(currentpage,
					practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "prctodepartment";
		} else {// 分配或修改课内实验时
			PageBean pageBean = practicePlanService.findtheolen(newcurrentpage,
					practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage",
					newcurrentpage);
			setAttr(pageBean);
			return "prctodepartment";
		}
	}

	public String gogivedepartment() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				newdepartmenttag1);

		Curriculum curriculum = practicePlanService.findcurrbyId(practiceLesson
				.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest()
				.setAttribute("Curriculum", curriculum);
		College college = curriculum.getCollege();
		List<Department> departmentlist = practicePlanService
				.finddepartbycollege(college);
		List<Professional> choosedepartlist = practicePlanService
				.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("departmentlist",
				departmentlist);
		ServletActionContext.getRequest().setAttribute("choosedepartlist",
				choosedepartlist);
		return "gogivedepartment";
	}

	public String goupdatedepartment() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				newdepartmenttag1);

		practicePlanService.goupdatedepartment(practiceLesson,
				newchoosedepartlist);
		ServletActionContext.getRequest()
				.setAttribute("tag", "sjktodepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "goupdatedepartment";
	}

	/* 实践课分配到老师 */
	public String prctousercollege() {
		findUserInfo(tnum);
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if (newcurrentpage == null || newcurrentpage == 0) {// 第一次进入课内实验分配到老师的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = practicePlanService.findtheolendirectuser(
					currentpage, practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			if (user.getAdminlevel() == 3) {
				return "prctousercollege";
			} else {
				return "prctousercollege_department";
			}
		} else {// 分配或修改课内实验时
			PageBean pageBean = practicePlanService.findtheolendirectuser(
					newcurrentpage, practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage",
					newcurrentpage);
			setAttr(pageBean);
			if (user.getAdminlevel() == 3) {
				return "prctousercollege";
			} else {
				return "prctousercollege_department";
			}
		}
	}

	public String kctousercollege() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				newdepartmenttag1);

		Curriculum curriculum = practicePlanService.findcurrbyId(practiceLesson
				.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest()
				.setAttribute("curriculum", curriculum);// 显示当前课程
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = practicePlanService.findUserByCollege(currentpage,
				practiceLesson, curriculum.getCollege());
		setAttr(pageBean);

		List<Professional> choosedepartlist = practicePlanService
				.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("choosedepartlist",
				choosedepartlist);
		ServletActionContext.getRequest().setAttribute("id",
				practiceLesson.getTeacher().getTnum());
		ServletActionContext.getRequest().setAttribute("uname",
				practiceLesson.getTeacher().getUsername());

		return "kctousercollege";
	}

	public String kctouser() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				newdepartmenttag1);

		practicePlanService.updateTheolenByCollegeDirectUser(practiceLesson,
				depart);
		ServletActionContext.getRequest().setAttribute("tag",
				"sjktousercollege");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "kctouser";
	}

	public String touserdepartment() {
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = practicePlanService.findtheolenByDepartToUser(
				currentpage, practiceLesson, usertag);
		ServletActionContext.getRequest().setAttribute("newusertag", usertag);
		ServletActionContext.getRequest().setAttribute("id",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("cname",
				practiceLesson.getCurriculum().getCurriculumCname());
		setAttr(pageBean);
		return "touserdepartment";
	}

	public String kctouserpagedepartment() {
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = practicePlanService.findUserByDepart(currentpage,
				practiceLesson, depart);
		setAttr(pageBean);
		Curriculum curriculum = practicePlanService.findcurrbyId(practiceLesson
				.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest()
				.setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("id",
				practiceLesson.getTeacher().getTnum());
		ServletActionContext.getRequest().setAttribute("uname",
				practiceLesson.getTeacher().getUsername());
		return "kctouserpagedepartment";
	}

	public String kctouserbydepart() {
		practicePlanService.updateTheolenByDepartToUser(practiceLesson, depart);
		ServletActionContext.getRequest().setAttribute("tag",
				"sjktouserdepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "kctouserbydepart";
	}

	public String prctokcfpPage() {
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = practicePlanService.findAllTheolen(currentpage,
				practiceLesson);
		ServletActionContext.getRequest().setAttribute("cid",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("id",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("cname",
				practiceLesson.getCurriculum().getCurriculumCname());
		setAttr(pageBean);
		return "prctokcfpPage";
	}

	public String A_key_distribution() {
		currentpage = getcurrentpage(currentpage);
		practicePlanService.findtheolen2(currentpage, practiceLesson,
				departmenttag);
		ServletActionContext.getRequest()
				.setAttribute("tag", "sjktodepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "a_key_distribution2";
	}

	// 导出实践课模板//李艳
	public void exportpracticecourseExcel() {
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = practicePlanService.findtheolen1(currentpage,
				practiceLesson, departmenttag);
		practicePlanService.downpracticecourse(pageBean);
	}

	// 系管理导出实践课模板
	public void teacherexportpracticecourseExcel() {
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = practicePlanService.findpractice(currentpage,
				practiceLesson, usertag);
		practicePlanService.teacherdownpracticecourse(pageBean);
	}

	// 分配实践课到实验室
	public String prctoExpdepartment() {
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if (newcurrentpage == null || newcurrentpage == 0) {// 第一次进入实践课分配到实验室的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = practicePlanService.findExppraclen(currentpage,
					practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "prctoExpdepartment";
		} else {// 分配或修改实验室时
			PageBean pageBean = practicePlanService.findExppraclen(
					newcurrentpage, practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage",
					newcurrentpage);
			setAttr(pageBean);
			return "prctoExpdepartment";
		}
	}

	public String prcgogivedepartment() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				departmenttag);

		Curriculum curriculum = practicePlanService.findcurrbyId(practiceLesson
				.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest()
				.setAttribute("Curriculum", curriculum);
		List<Professional> choosedepartlist = practicePlanService
				.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("choosedepartlist",
				choosedepartlist);
		// 获得实验室列表
		List<Experiment> experiments = practicePlanService.findAllExperiment();
		ServletActionContext.getRequest().setAttribute("experimentslist",
				experiments);
		return "prcgogivedepartment";
	}

	public String prcgoupdateExpdepartment() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				departmenttag);

		practicePlanService.prcgoupdateExpdepartment(practiceLesson,
				newchoosedepartlist);
		ServletActionContext.getRequest().setAttribute("tag",
				"prcgoupdateExpdepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "prcgoupdateExpdepartment";
	}

	// 分配实践课到实验员
	public String prctoExpusercollege() {
		findUserInfo(tnum);
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if (newcurrentpage == null || newcurrentpage == 0) {// 第一次进入实践课分配到实验员的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = practicePlanService.Expfindtheolendirectuser(
					currentpage, practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "prctoExpusercollege";
		} else {// 分配或修改课内实验时
			PageBean pageBean = practicePlanService.Expfindtheolendirectuser(
					newcurrentpage, practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage",
					newcurrentpage);
			setAttr(pageBean);
			return "prctoExpusercollege";
		}
	}

	public String proexpkctousercollege() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				departmenttag);

		Curriculum curriculum = practicePlanService.findcurrbyId(practiceLesson
				.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest()
				.setAttribute("curriculum", curriculum);// 显示当前课程
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = practicePlanService.findExperimenter(currentpage,
				practiceLesson);
		setAttr(pageBean);

		List<Professional> choosedepartlist = practicePlanService
				.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("choosedepartlist",
				choosedepartlist);
		ServletActionContext.getRequest().setAttribute("id",
				practiceLesson.getExperimenter().getTnum());
		ServletActionContext.getRequest().setAttribute("uname",
				practiceLesson.getExperimenter().getUsername());
		return "proexpkctousercollege";
	}

	public String proexpkctouser() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				departmenttag);

		practicePlanService.updateTheolenByCollegeDirectUser1(practiceLesson,
				depart);
		ServletActionContext.getRequest().setAttribute("cname",
				practiceLesson.getCurriculum().getCurriculumCname());
		ServletActionContext.getRequest().setAttribute("tag",
				"protollkkcToExper");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "proexpkctouser";
	}

	// --------------------------------实验室主任分配实践课到实验员
	// 李艳李婧2018-07-19--------------------------------------
	public String prctoExpuser() {
		findUserInfo(tnum);
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if (newcurrentpage == null || newcurrentpage == 0) {// 第一次进入实践课分配到实验员的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = practicePlanService
					.proExpfindtheolendirectuser(currentpage, practiceLesson,
							departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "prctoExpuser";
		} else {// 分配或修改课内实验时
			PageBean pageBean = practicePlanService
					.proExpfindtheolendirectuser(newcurrentpage,
							practiceLesson, departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag",
					departmenttag);
			ServletActionContext.getRequest().setAttribute("id",
					practiceLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname",
					practiceLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage",
					newcurrentpage);
			setAttr(pageBean);
			return "prctoExpuser";
		}
	}

	public String protoexpkctouser() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				departmenttag);

		Curriculum curriculum = practicePlanService.findcurrbyId(practiceLesson
				.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest()
				.setAttribute("curriculum", curriculum);// 显示当前课程
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = practicePlanService.findExperimenter(currentpage,
				practiceLesson);
		setAttr(pageBean);

		List<Professional> choosedepartlist = practicePlanService
				.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("choosedepartlist",
				choosedepartlist);
		ServletActionContext.getRequest().setAttribute("id",
				practiceLesson.getExperimenter().getTnum());
		ServletActionContext.getRequest().setAttribute("uname",
				practiceLesson.getExperimenter().getUsername());
		return "protoexpkctouser";
	}

	public String proexpkctouser1() {
		// 分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",
				newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1",
				departmenttag);

		practicePlanService.updateTheolenByCollegeUser(practiceLesson, depart);
		ServletActionContext.getRequest().setAttribute("cname",
				practiceLesson.getCurriculum().getCurriculumCname());
		ServletActionContext.getRequest().setAttribute("tag",
				"protollkkcToExper1");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "proexpkctouser1";
	}

	// 实践课实习数据库传是否
	public String LoadFlagUp() {
		if ("1".equals(isLoad)) {
			practiceLesson.setWorkload("是");
		} else {
			practiceLesson.setWorkload("否");
		}
		practicePlanService.upLoadFlag(practiceLesson);
		toCheckPracLesFieldWorkPage();
		return "toCheckPracLes_FieldWorkPage";
	}

	// 实践课课程设计数据库传是否
	public String LoadFlagUp1() {
		if ("1".equals(isLoad)) {
			practiceLesson.setWorkload1("是");
		} else {
			practiceLesson.setWorkload1("否");
		}
		practicePlanService.upLoadFlag1(practiceLesson);
		toCheckPracLesCourseDesignPage();
		return "toCheckPracLesCourseDesignPage";
	}

	// 实践课实验数据库传是否
	public String LoadFlagUp2() {
		if ("1".equals(isLoad)) {
			practiceLesson.setWorkload2("是");
		} else {
			practiceLesson.setWorkload2("否");
		}
		practicePlanService.upLoadFlag2(practiceLesson);
		toCheckPracLesInnerExperimentPage();
		return "toCheckPracLesInnerExperimentPage";
	}

	// 实践课毕业设计数据库传是否
	public String LoadFlagUp3() {
		if ("1".equals(isLoad)) {
			practiceLesson.setWorkload3("是");
		} else {
			practiceLesson.setWorkload3("否");
		}
		practicePlanService.upLoadFlag3(practiceLesson);
		toCheckPracLesGraduationProjectPage();
		return "toCheckPracLesGraduationProjectPage";
	}
	public String toCheckPracticeOther(){//跳转到理论课验证页面
		practiceLesson.setTeacher(user);
		practicePlanService.toCheckPracticeOther(practiceLesson);
		return "toCheckPracticeOther";
	}
	
	// 实践课其他数据库传是否
	public String LoadFlagUp4() {
		if ("1".equals(isLoad)) {
			practiceLesson.setWorkload4("是");
		} else {
			practiceLesson.setWorkload4("否");
		}
		practicePlanService.upLoadFlag4(practiceLesson);

		toCheckPracticeOther();
		return "toCheckPracticeOther";
	}

	public String toCheckPracLesFieldWorkPageAdmin1() {// 跳转到院、校管理员查看大纲页面

		practicePlanService.toCheckPracLesFieldWorkPageAdmin1(tnum,
				practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckPracLesFieldWorkPageAdmin1";
	}

	// 修改
	public String toCheckCourseDesignPageAdmin1() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckCourseDesignPageAdmin1(tnum, practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckCourseDesignPageAdmin1";
	}

	public String toCheckInnerExperimentPageAdmin1() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckInnerExperimentPageAdmin1(tnum,
				practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckInnerExperimentPageAdmin1";
	}

	public String toCheckGraduationProjectPageAdmin1() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckGraduationProjectPageAdmin1(tnum,
				practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckGraduationProjectPageAdmin1";
	}

	public String toCheckOtherPageAdmin1() {// 跳转到院、校管理员查看大纲页面
		practicePlanService.toCheckOtherPageAdmin1(tnum, practiceLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan",
				practiceLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid",
				practiceLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname",
				practiceLesson.getCurriculum().getCurriculumCname());
		return "toCheckOtherPageAdmin1";
	}




}
