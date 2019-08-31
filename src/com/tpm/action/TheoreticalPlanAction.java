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
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.User;

import com.tpm.service.TheoreticalPlanService;

public class TheoreticalPlanAction extends ActionSupport implements ModelDriven<TheoreticalLesson> {		
	private String isLoad;

	public String getIsLoad() {
		return isLoad;
	}

	public void setIsLoad(String isLoad) {
		this.isLoad = isLoad;
	}
	private TheoreticalLesson theoreticalLesson = new TheoreticalLesson();
	public TheoreticalLesson getModel() {
		return theoreticalLesson;
	}
	private TheoreticalPlanService theoreticalPlanService;	//注入theoreticalPlanService
	public void setTheoreticalPlanService(
			TheoreticalPlanService theoreticalPlanService) {
		this.theoreticalPlanService = theoreticalPlanService;
	}
	
	private String tnum;	//接收登陆账户的职工号，用于查询用户级别、所在 系等	
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

	public void findUserInfo(String tnum){	//根据登陆用户id获得用户信息
		user = theoreticalPlanService.findUserById(tnum);
		ServletActionContext.getRequest().setAttribute("user", user);			
	}
	
	HttpServletRequest request = ServletActionContext.getRequest();	//放入域对象

	public void downTheoreticalTemplate(){//下载理论课模板
		theoreticalPlanService.downTheoreticalTemplate();
	}

	public String tollkPage(){	//跳转到理论课页面
		theoreticalPlanService.tollkPage(tnum,collegeid,departmentid);
		return "tollkPage";
	}	

	public String llkResult(){	//理论课页面的返回
		theoreticalPlanService.llkResult(theoreticalLesson,tnum);				
		return "llkResult";
	}

	public String totjllkPage(){ //跳转到添加理论课页面
		theoreticalPlanService.totjllkPage(collegeid,departmentid);
		return "totjllkPage";
	}		
	public String tjllkResult(){		//同上，tollkPage()
		theoreticalPlanService.tjllkResult(theoreticalLesson,collegeid,departmentid);			
		return "tjllkResult";
	}
	
	private String data;	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public void llkAddResult(){
		theoreticalPlanService.llkAddResult(data,departmentid);
	}

	//删除理论课
	public String delllk(){
		theoreticalPlanService.setid(theoreticalLesson);
		theoreticalPlanService.delTheoreticalLessonByid(theoreticalLesson.getTheoreticalLessonid());
		ServletActionContext.getRequest().setAttribute("tag", "tollkPage");		
		return "delllk";
	}
	private List<String> deletetheoreticalLessonList;
	public void setDeletetheoreticalLessonList(
			List<String> deletetheoreticalLessonList) {
		this.deletetheoreticalLessonList = deletetheoreticalLessonList;
	}
	//删除多个理论课
	public String delllkMore(){
		theoreticalPlanService.delllkMore(deletetheoreticalLessonList);
		ServletActionContext.getRequest().setAttribute("tag", "tollkPage");		
		return "delllk";
	}

	//跳转到编辑理论课页面
	public String tobjllkPage(){
		TheoreticalLesson bjllk = theoreticalPlanService.findTheoreticalLessonByid(theoreticalLesson.getTheoreticalLessonid());
		ServletActionContext.getRequest().setAttribute("theoreticalLesson", bjllk);
		return "tobjllkPage";
	}
	
	//编辑理论课
	public String llkUpdateResult(){	
		theoreticalPlanService.llkUpdate(theoreticalLesson);
		theoreticalPlanService.setid(theoreticalLesson);
		ServletActionContext.getRequest().setAttribute("tag", "tollkPage");		
		return "llkUpdateResult";
	}
	
	//导出所选理论课
	public void downTheoreticalPlan(){
		theoreticalPlanService.downTheoreticalPlan(theoreticalLesson);
		
	}
	//导出全部所选理论课
	public void downTheoreticalPlan_all(){
		theoreticalPlanService.downTheoreticalPlan_all();
	}
	

	
	private List<String> selectProfessional;
	public List<String> getSelectProfessional() {
		return selectProfessional;
	}

	public void setSelectProfessional(List<String> selectProfessional) {
		this.selectProfessional = selectProfessional;
	}
	private String syllabusId_Copy;

	public String getSyllabusId_Copy() {
		return syllabusId_Copy;
	}

	public void setSyllabusId_Copy(String syllabusId_Copy) {
		this.syllabusId_Copy = syllabusId_Copy;
	}
	
	private List<String> haveselectProfessional;//已编辑大纲的选择专业方向
	public List<String> getHaveselectProfessional() {
		return haveselectProfessional;
	}

	public void setHaveselectProfessional(List<String> haveselectProfessional) {
		this.haveselectProfessional = haveselectProfessional;
	}
	
	private String syllabusId;//大纲ID
	public String getSyllabusId() {
		return syllabusId;
	}

	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	
	//------------------理论课大纲--------------------------
	public String toCheckTheoLes(){//跳转到理论课验证页面
		theoreticalLesson.setTeacher(user);
		theoreticalPlanService.TheoLes(theoreticalLesson);
		return "toCheckTheoLes";
	}
	public String toCheckTheoLesAdmin(){//跳转到院、校管理员查看大纲页面
		theoreticalPlanService.toCheckTheoLesAdmin(tnum,theoreticalLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan", theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid", theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname", theoreticalLesson.getCurriculum().getCurriculumCname());
		return "toCheckTheoLesAdmin";
	}
	public String toTheoLesPage(){//未编写大纲到大纲编写页面
		String tag = theoreticalPlanService.TheoLes1(theoreticalLesson,selectProfessional);
		if(tag.equals("no"))
		{
			ServletActionContext.getRequest().setAttribute("tag", "toCheckTheoLes");
			ServletActionContext.getRequest().setAttribute("msg", "由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		}
		else
		{
			return "toTheoLesPage1";
		}
	}
	public String toTheoLesPageCopy(){//未编写大纲到大纲编写页面——复制
		theoreticalPlanService.toTheoLesPage_Copy(theoreticalLesson,selectProfessional,syllabusId_Copy);
		return "toTheoLesPage_Copy";
	}
	public String toupdateTheoLesPage(){//已编写大纲到大纲编写页面
		String tag = theoreticalPlanService.TheoLes2(syllabusId,theoreticalLesson,haveselectProfessional);
		if(tag.equals("no"))
		{
			ServletActionContext.getRequest().setAttribute("tag", "toCheckTheoLes");
			ServletActionContext.getRequest().setAttribute("msg", "由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲");
			return "toerrorPage";
		}
		else
		{
			return "toTheoLesPage1";
		}
		
	}
	
	public String deleteTheoSyllabus(){//删除大纲
		theoreticalPlanService.deleteTheoSyllabus(syllabusId);
		return "deleteTheoSyllabus";
	}
	
	
	//------------------理论课课内实验大纲--------------------------
	public String toCheckTheoLesInnerExperiment(){//跳转到理论课大纲——课内实验验证页面
		//**************************
		theoreticalLesson.setExperimenter(user);
		theoreticalPlanService.toCheckTheoLesInnerExperiment(theoreticalLesson);
		return "toCheckTheoLesInnerExperiment";
	}
	public String toCheckTheoLesInnerExperimentAdmin(){//跳转到院、校管理员查看大纲页面
		theoreticalPlanService.toCheckTheoLesInnerExperimentAdmin(tnum,theoreticalLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan", theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid", theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname", theoreticalLesson.getCurriculum().getCurriculumCname());
		return "toCheckTheoLesInnerExperimentAdmin";
	}
	public String toTheoLesPageInnerExperiment(){//未编写大纲到大纲——课内实验编写页面
		String tag = theoreticalPlanService.toTheoLesPageInnerExperiment(theoreticalLesson,selectProfessional);
		if(tag.equals("no"))
		{
			ServletActionContext.getRequest().setAttribute("tag", "toCheckTheoLesInnerExperiment");
			ServletActionContext.getRequest().setAttribute("msg", "由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		}
		else
		{
			return "toTheoLesPageInnerExperiment";
		}
	}
	public String toTheoLesPageCopyInnerExperiment(){//未编写大纲到大纲——课内实验页面——复制
		theoreticalPlanService.toTheoLesPageCopy(theoreticalLesson,selectProfessional,syllabusId_Copy);
		return "toTheoLesPageCopyInnerExperiment";
	}
	public String toupdateTheoLesPageInnerExperiment(){//已编写大纲到大纲——课内实验编写页面
		String tag = theoreticalPlanService.toupdateTheoLesPageInnerExperiment(syllabusId,theoreticalLesson,haveselectProfessional);
		if(tag.equals("no"))
		{
			ServletActionContext.getRequest().setAttribute("tag", "toCheckTheoLesInnerExperiment");
			ServletActionContext.getRequest().setAttribute("msg", "由于该课程对应当前所选专业方向的课程矩阵不同，所以不能同时填写一份大纲。");
			return "toerrorPage";
		}
		else
		{
			return "toupdateTheoLesPageInnerExperiment";
		}
	}
	
	public String deleteTheoSyllabusInnerExperiment(){//删除大纲——课内实验
		theoreticalPlanService.deleteTheoSyllabusInnerExperiment(syllabusId);
		return "deleteTheoSyllabusInnerExperiment";
	}
	
	
	private PracticeLesson practiceLesson = new PracticeLesson();
	
	public PracticeLesson getPracticeLesson() {
		return practiceLesson;
	}
	public void setPracticeLesson(PracticeLesson practiceLesson) {
		this.practiceLesson = practiceLesson;
	}

//------------------实践课其他课大纲--------------------------

	
	
	private Integer currentpage;
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	private Integer getcurrentpage(Integer currentpage) {
		if(currentpage == null||currentpage == 0){
			currentpage=1;
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

	
//分配理论课程到专业
	public String todepartment(){
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if(newcurrentpage == null ||newcurrentpage == 0){//第一次进入课内实验分配到实验室的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = theoreticalPlanService.findtheolen(currentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "todepartment";
		}else{//分配或修改课内实验时
			PageBean pageBean = theoreticalPlanService.findtheolen(newcurrentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage", newcurrentpage);
			setAttr(pageBean);
			return "todepartment";
		}		
	}
	public void setAttr(PageBean pageBean) {
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	}
	public String gogivedepartment(){//单击“分配开课专业”后的后台处理
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("Curriculum", curriculum);
		College college = curriculum.getCollege();
		List<Department> departmentlist=theoreticalPlanService.finddepartbycollege(college);
		List<Professional> choosedepartlist=theoreticalPlanService.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
		ServletActionContext.getRequest().setAttribute("choosedepartlist", choosedepartlist);
		
		return "gogivedepartment";
	}
	public String goupdatedepartment(){//单击“保存”后的后台处理
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		theoreticalPlanService.goupdatedepartment(theoreticalLesson,newchoosedepartlist);
		ServletActionContext.getRequest().setAttribute("tag", "llktodepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "goupdatedepartment";
	}
	
	
//分配理论课程到老师
	public String tousercollege(){
		findUserInfo(tnum);
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if(newcurrentpage == null ||newcurrentpage == 0){//第一次进入课内实验分配到实验室的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = theoreticalPlanService.findtheolendirectuser(currentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			if(user.getAdminlevel() == 3)
			{
				return "tousercollege";
			}
			else
			{
				return "tousercollege_department";
			}
		}else{//分配或修改课内实验时
			PageBean pageBean = theoreticalPlanService.findtheolendirectuser(newcurrentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage", newcurrentpage);
			setAttr(pageBean);
			if(user.getAdminlevel() == 3)
			{
				return "tousercollege";
			}
			else
			{
				return "tousercollege_department";
			}
		}		
	}
	public String kctousercollege(){
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);//显示当前课程
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean=theoreticalPlanService.findUserByCollege(currentpage, theoreticalLesson,curriculum.getCollege());		
		setAttr(pageBean);
		
		List<Professional> choosedepartlist=theoreticalPlanService.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("choosedepartlist", choosedepartlist);
		ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getTeacher().getTnum());
		ServletActionContext.getRequest().setAttribute("uname", theoreticalLesson.getTeacher().getUsername());
		ServletActionContext.getRequest().setAttribute("currentpage", newcurrentpage);
		return "kctousercollege";
	}
	//院管理员直接分配任课老师 存入数据库
	public String tokctouser(){
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		theoreticalPlanService.toupdateTheolenByCollegeDirectUser(theoreticalLesson,depart);
		ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
		ServletActionContext.getRequest().setAttribute("tag", "tollkkcToUser");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "tokctouser";
	}
	
	
//系管理员根据系分配任课老师
	public String touserdepartment(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = theoreticalPlanService.findtheolenByDepartToUser(currentpage,theoreticalLesson,usertag);
		ServletActionContext.getRequest().setAttribute("newusertag", usertag);
		ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
		setAttr(pageBean);
		ServletActionContext.getRequest().setAttribute("tag", "tollkkcToUserDepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");	
		return "touserdepartment";
	}
	
	
	//系管理员查询所有任课老师进行任课老师分配
	public String kctouserpagedepartment(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean=theoreticalPlanService.findUserByDepart(currentpage, theoreticalLesson,depart);
		setAttr(pageBean);
		Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getTeacher().getTnum());
		ServletActionContext.getRequest().setAttribute("uname", theoreticalLesson.getTeacher().getUsername());	
		return "kctouserpagedepartment";
	}
	public String kctouserbydepart(){
		theoreticalPlanService.updateTheolenByDepartToUser(theoreticalLesson,depart);
		ServletActionContext.getRequest().setAttribute("tag", "llktouserdepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "kctouserbydepart";
	}
	private String usertag;
	public String getUsertag(){
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
	
	public String tokcfpPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = theoreticalPlanService.findAllTheolen(currentpage,theoreticalLesson);
		ServletActionContext.getRequest().setAttribute("cid", theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
		setAttr(pageBean);
		return "tokcfpPage";
	}

	public String toExportPlanPage(){
		theoreticalPlanService.toExportPlanPage(tnum);
		return "toExportPlanPage";
		
	}

	public String A_key_distribution(){
		currentpage = getcurrentpage(currentpage);
		theoreticalPlanService.findtheolen2(currentpage,theoreticalLesson,departmenttag);
		ServletActionContext.getRequest().setAttribute("tag", "llktodepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "a_key_distribution1";
	}
	
	
//分配课内实验到实验室
	public String toExpdepartment(){
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if(newcurrentpage == null ||newcurrentpage == 0){//第一次进入课内实验分配到实验室的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = theoreticalPlanService.findExptheolen(currentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "toExpdepartmentPage";
		}else{//分配或修改课内实验时
			PageBean pageBean = theoreticalPlanService.findExptheolen(newcurrentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage", newcurrentpage);
			setAttr(pageBean);
			return "toExpdepartmentPage";
		}		
	}
	//到分配实验室页面
	public String gogiveExpdepartment(){
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("Curriculum", curriculum);
		List<Professional> choosedepartlist=theoreticalPlanService.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("choosedepartlist", choosedepartlist);
		//获得实验室列表
		List<Experiment> experiments=theoreticalPlanService.findAllExperiment();
		ServletActionContext.getRequest().setAttribute("experimentslist", experiments);
		return "gogiveExpdepartment";
	}
	//保存所分配的实验室
	public String goupdateExpdepartment(){
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		theoreticalPlanService.goupdateExpdepartment(theoreticalLesson,newchoosedepartlist);
		ServletActionContext.getRequest().setAttribute("tag", "goupdateExpdepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "goupdateExpdepartment";
	}
	
	
//分配课内实验给实验员
	public String toExpusercollege(){
		findUserInfo(tnum);
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if(newcurrentpage == null ||newcurrentpage == 0){//第一次进入课内实验分配到实验员的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = theoreticalPlanService.Expfindtheolendirectuser(currentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "toExpusercollegePage";
		}else{//分配或修改课内实验时
			PageBean pageBean = theoreticalPlanService.Expfindtheolendirectuser(newcurrentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			ServletActionContext.getRequest().setAttribute("currentpage", newcurrentpage);
			setAttr(pageBean);
			return "toExpusercollegePage";
		}	
	}
	//进入实验员选择页面
	public String expkctousercollege(){
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);//显示当前课程
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean=theoreticalPlanService.findExperimenter(currentpage, theoreticalLesson);	
		setAttr(pageBean);
		
		List<Professional> choosedepartlist=theoreticalPlanService.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("choosedepartlist", choosedepartlist);
		ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getExperimenter().getTnum());
		ServletActionContext.getRequest().setAttribute("uname", theoreticalLesson.getExperimenter().getUsername());
		return "expkctousercollege";
	}
	//院管理员直接分配实验员存入数据库
	public String expkctouser(){
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		theoreticalPlanService.updateTheolenByCollegeDirectUser(theoreticalLesson,depart);
		ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
		ServletActionContext.getRequest().setAttribute("tag", "tollkkcToExper");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "expkctouser";
	}
	//实验室主任进入实验员分配主界面
	public String exptouserdepartment(){                                                                               
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = theoreticalPlanService.findtheolenByExperierToUser(currentpage,theoreticalLesson,usertag);
		ServletActionContext.getRequest().setAttribute("expid", theoreticalLesson.getExperiment().getExperimentid());
		ServletActionContext.getRequest().setAttribute("cid", theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("newusertag", usertag);
		ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
		setAttr(pageBean);
		return "exptouserdepartment";
	}
	//进入实验员页面
	public String expkctouserdepartment(){
		Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);//显示当前课程
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean=theoreticalPlanService.findExperimenterByDepart(currentpage, theoreticalLesson,tnum);	
		setAttr(pageBean);
		
		List<Professional> choosedepartlist=theoreticalPlanService.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("choosedepartlist", choosedepartlist);
		return "expkctouserdepartment";
	}
	//实验室主任分配实验员更新数据库
	public String updeatexpkctouserdepartment(){
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		theoreticalPlanService.updateTheolenByExperimentDirectUser(theoreticalLesson,depart);
		ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
		ServletActionContext.getRequest().setAttribute("tag", "tollkkcToExperDepart");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "updeatexpkctouserdepartment";
	}
	
//新加的功能
/*//分配理论课程到专业
	public String todepartment(){
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		if(newcurrentpage == null ||newcurrentpage == 0){//第一次进入课内实验分配到实验室的页面
			currentpage = getcurrentpage(currentpage);
			PageBean pageBean = theoreticalPlanService.findtheolen(currentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "todepartment";
		}else{//分配或修改课内实验时
			PageBean pageBean = theoreticalPlanService.findtheolen(newcurrentpage,theoreticalLesson,departmenttag);
			ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
			ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
			ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
			setAttr(pageBean);
			return "todepartment";
		}		
	}
	public void setAttr(PageBean pageBean) {
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	}
	public String gogivedepartment(){//单击“分配开课专业”后的后台处理
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("Curriculum", curriculum);
		College college = curriculum.getCollege();
		List<Department> departmentlist=theoreticalPlanService.finddepartbycollege(college);
		List<Professional> choosedepartlist=theoreticalPlanService.findchoosedepartbydepartId(depart);
		ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
		ServletActionContext.getRequest().setAttribute("choosedepartlist", choosedepartlist);
		
		return "gogivedepartment";
	}
	public String goupdatedepartment(){//单击“保存”后的后台处理
		//分配成功之后跳转到之前分配的页面，需要用到当前的信息
		newcurrentpage = getnewcurrentpage(newcurrentpage);
		ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
		ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
		
		theoreticalPlanService.goupdatedepartment(theoreticalLesson,newchoosedepartlist);
		ServletActionContext.getRequest().setAttribute("tag", "llktodepartment");
		ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
		return "goupdatedepartment";
	}	*/

	//新加的功能
	
	//院管理员根据课内实验分配到专业
		public String toExpdepart(){
			newcurrentpage = getnewcurrentpage(newcurrentpage);
			if(newcurrentpage == null ||newcurrentpage == 0){//第一次进入课内实验分配到专业的页面
				currentpage = getcurrentpage(currentpage);
				PageBean pageBean = theoreticalPlanService.tofindExptheolen(currentpage,theoreticalLesson,departmenttag);
				ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
				ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
				ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
				setAttr(pageBean);
				return "toExpdepart";
			}else{//分配或修改课内实验时
				PageBean pageBean = theoreticalPlanService.tofindExptheolen(newcurrentpage,theoreticalLesson,departmenttag);
				ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
				ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
				ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
				ServletActionContext.getRequest().setAttribute("currentpage", newcurrentpage);
				setAttr(pageBean);
				return "toExpdepart";
			}		
		}
		public String gogiveExpdepart(){//到分配专业页面
			//分配成功之后跳转到之前分配的页面，需要用到当前的信息
			newcurrentpage = getnewcurrentpage(newcurrentpage);
			ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
			ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
			//查找课程列表
			Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());	
			ServletActionContext.getRequest().setAttribute("Curriculum", curriculum);
			College college = curriculum.getCollege();//开课学院	
			//开课专业列表
			List<Department> departmentlist=theoreticalPlanService.finddepartbycollege(college);	
			//选课列表
			List<Professional> choosedepartlist=theoreticalPlanService.findchoosedepartbydepartId(depart);
			ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
			ServletActionContext.getRequest().setAttribute("choosedepartlist", choosedepartlist);
			return "gogiveExpdepart";	
		}
			public String goupdateExpdepart(){//保存所分配的专业
				//分配成功之后跳转到之前分配的页面，需要用到当前的信息
				newcurrentpage = getnewcurrentpage(newcurrentpage);
				ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
				ServletActionContext.getRequest().setAttribute("newdepartmenttag1",newdepartmenttag1);
				
				theoreticalPlanService.goupdateExpdepart(theoreticalLesson,newchoosedepartlist);
				ServletActionContext.getRequest().setAttribute("tag", "goupdateExpdepart");
				ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
				return "goupdateExpdepart";
			}
			
			
	//分配课内实验到老师页面
			public String toExpuser(){
				findUserInfo(tnum);
				newcurrentpage = getnewcurrentpage(newcurrentpage);
				if(newcurrentpage == null ||newcurrentpage == 0){//第一次进入课内实验分配到老师的页面
					currentpage = getcurrentpage(currentpage);
					PageBean pageBean = theoreticalPlanService.toExpfindtheolendirectuser(currentpage,theoreticalLesson,departmenttag);
					ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
					ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
					ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
					setAttr(pageBean);
					return "toExpuser";
				}else{//分配或修改课内实验时
					PageBean pageBean = theoreticalPlanService.toExpfindtheolendirectuser(newcurrentpage,theoreticalLesson,departmenttag);
					ServletActionContext.getRequest().setAttribute("newdepartmenttag", departmenttag);
					ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
					ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
					ServletActionContext.getRequest().setAttribute("currentpage", newcurrentpage);
					setAttr(pageBean);
					return "toExpuser";
				}	
			}
			public String gogiveuser(){//到分配课内实验到老师页面
				//分配成功之后跳转到之前分配的页面，需要用到当前的信息
				newcurrentpage = getnewcurrentpage(newcurrentpage);
				ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
				ServletActionContext.getRequest().setAttribute("newdepartmenttag1", departmenttag);
				//查找课程列表
				Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());
				ServletActionContext.getRequest().setAttribute("curriculum", curriculum);//显示当前课程
				currentpage = getcurrentpage(currentpage);
				//查找老师信息
				PageBean pageBean=theoreticalPlanService.tofindUserByCollege(currentpage, theoreticalLesson,curriculum.getCollege());		
				setAttr(pageBean);
				List<Professional> choosedepartlist=theoreticalPlanService.findchoosedepartbydepartId(depart);
				ServletActionContext.getRequest().setAttribute("choosedepartlist", choosedepartlist);
				ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCteacher().getTnum());
				ServletActionContext.getRequest().setAttribute("uname", theoreticalLesson.getCteacher().getUsername());
				ServletActionContext.getRequest().setAttribute("currentpage", newcurrentpage);
				return "gogiveuser";	
			}
			public String tokctouser1(){
				//分配成功之后跳转到之前分配的页面，需要用到当前的信息
				newcurrentpage = getnewcurrentpage(newcurrentpage);
				ServletActionContext.getRequest().setAttribute("newcurrentpage",newcurrentpage );
				ServletActionContext.getRequest().setAttribute("newdepartmenttag1",newdepartmenttag1);
			
				/*currentpage = getcurrentpage(currentpage);*/
				theoreticalPlanService.toupdateTheolenByCollegeDirectUser1(theoreticalLesson,depart);
				ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
				ServletActionContext.getRequest().setAttribute("tag", "llkkcToUser");
				ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
				return "tokctouser1";
			}
			
			
			
			//导出课内实验模板
			public void exportInclassexperimentExcel(){
				PageBean pageBean = theoreticalPlanService.toExpfindtheolendirectuser1(theoreticalLesson,departmenttag);
				theoreticalPlanService.downInclassexperiment(pageBean);
			}
			
			//导出理论课模板
			public void exportTheoryclassExcel(){
	//			PageBean pageBean = theoreticalPlanService.toExpfindtheolendirectuser1(theoreticalLesson,departmenttag);
			PageBean pageBean = theoreticalPlanService.findtheolen1(theoreticalLesson,departmenttag);
				theoreticalPlanService.downTheoryclass(pageBean);
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
			//系管理分配课内实验到老师
			public String toExpteacher(){
						currentpage = getcurrentpage(currentpage);
						PageBean pageBean = theoreticalPlanService.findtheolenByDepartToTeacher(currentpage,theoreticalLesson,usertag);
						ServletActionContext.getRequest().setAttribute("newusertag", usertag);
						ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCurriculum().getCurriculumid());
						ServletActionContext.getRequest().setAttribute("cname", theoreticalLesson.getCurriculum().getCurriculumCname());
						setAttr(pageBean);
						ServletActionContext.getRequest().setAttribute("tag", "tollkkcToTeacherDepartment");
						ServletActionContext.getRequest().setAttribute("msg", "分配成功！");	
						return "toExpteacher";
							}
			//系管理员查询所有任课老师进行任课老师分配
					public String gogiveCteacher(){
						currentpage = getcurrentpage(currentpage);
						PageBean pageBean=theoreticalPlanService.findTeacherByDepart(currentpage, theoreticalLesson,depart);
						setAttr(pageBean);
						Curriculum curriculum=theoreticalPlanService.findcurrbyId(theoreticalLesson.getCurriculum().getCurriculumid());//查找课表
						ServletActionContext.getRequest().setAttribute("curriculum", curriculum);//显示当前课程
						//查找老师信息
						/*ServletActionContext.getRequest().setAttribute("id", theoreticalLesson.getCteacher().getTnum());
						ServletActionContext.getRequest().setAttribute("uname", theoreticalLesson.getCteacher().getUsername());	*/
						return "gogiveCteacher";
					}
					public String kctoteacherbydepart(){
						theoreticalPlanService.updateTheolenByDepartToTeacher(theoreticalLesson,depart);
						ServletActionContext.getRequest().setAttribute("tag", "llktoteacherdepartment");
						ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
						return "kctoteacherbydepart";
					}
			//导出课内实验模板
					public void exportInexperimentExcel(){
						currentpage = getcurrentpage(currentpage);
						PageBean pageBean = theoreticalPlanService.toExpfindtheolendirectteacher(currentpage,theoreticalLesson,usertag);
						theoreticalPlanService.downInexperiment(pageBean);
							}
			//导出理论课模板
					public void exportIntheoryExcel(){
						currentpage = getcurrentpage(currentpage);
						PageBean pageBean = theoreticalPlanService.toExpfindtheolenteacher(currentpage,theoreticalLesson,usertag);
						theoreticalPlanService.downIntheolen(pageBean);
							}

		// 课内实验“一键分配”功能
		public String B_key_distribution() {
			currentpage = getcurrentpage(currentpage);
			theoreticalPlanService.findtheolen3(currentpage, theoreticalLesson,
					departmenttag);
			ServletActionContext.getRequest().setAttribute("tag",
					"goupdateExpdepart");
			ServletActionContext.getRequest().setAttribute("msg", "分配成功！");
			return "a_key_distribution1";
		}
		//理论课大纲全体下载=====卢亚飞

		public String canLoadFlagUp() {
		if ("1".equals(isLoad)) {
			theoreticalLesson.setIsDow("是");
		} else {
			theoreticalLesson.setIsDow("否");
		}
		theoreticalPlanService.upLoadFlag(theoreticalLesson);
		toCheckTheoLes();
		return "toCheckTheoLes";
		}
		//理论课课内实验数据库传是否=====卢亚飞
	public String canLoadFlagUp1() {
		if ("1".equals(isLoad)) {
			theoreticalLesson.setIsDown("是");
		} else {
			theoreticalLesson.setIsDown("否");
		}
		theoreticalPlanService.upLoadFlag1(theoreticalLesson);
		toCheckTheoLesInnerExperiment();
		return "toCheckTheoLesInnerExperiment";
	}
	public String toCheckTheoLesAdmin1(){//跳转到院、校管理员查看大纲页面
		theoreticalPlanService.toCheckTheoLesAdmin1(tnum,theoreticalLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan", theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid", theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname", theoreticalLesson.getCurriculum().getCurriculumCname());
		return "toCheckTheoLesAdmin1";
	}


	
	public String toCheckTheoLesInnerExperimentAdmin1(){//跳转到院、校管理员查看大纲页面    复制以上内容
		theoreticalPlanService.toCheckTheoLesInnerExperimentAdmin1(tnum,theoreticalLesson);
		ServletActionContext.getRequest().setAttribute("xueyuan", theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		ServletActionContext.getRequest().setAttribute("currid", theoreticalLesson.getCurriculum().getCurriculumid());
		ServletActionContext.getRequest().setAttribute("currname", theoreticalLesson.getCurriculum().getCurriculumCname());
		return "toCheckTheoLesInnerExperimentAdmin1";
	}
	


}
