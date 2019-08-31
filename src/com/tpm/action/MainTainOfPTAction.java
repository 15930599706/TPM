package com.tpm.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tpm.entity.PageBean;
import com.tpm.entity.Professional;
import com.tpm.service.MainTainOfPTService;
import com.tpm.service.PPTrainingConceptService;
import com.tpm.service.TrainingEventsService;

public class MainTainOfPTAction extends ActionSupport {
	private List<String> week1;
	private List<String> week2;
	private List<String> week3;
	private List<String> week4;
	private List<String> week5;
	private List<String> week6;
	private List<String> week7;
	private List<String> week8;
	private List<String> week9;
	private List<String> week10;
	private List<String> week11;
	private List<String> week12;
	private List<String> week13;
	private List<String> week14;
	private List<String> week15;
	private List<String> week16;
	private List<String> week17;
	private List<String> week18;
	private List<String> week19;
	private List<String> week20;
	private List<String> semester1;
	private List<String> semester2;
	private List<String> semester3;
	private List<String> semester4;
	private List<String> semester5;
	private List<String> semester6;
	private List<String> semester7;
	private List<String> semester8;
	private List<String> semester9;
	private List<String> semester10;
	private String tnum;
	private String collegeid;
	private Integer currentpage;
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public List<String> getWeek1() {
		return week1;
	}
	public void setWeek1(List<String> week1) {
		this.week1 = week1;
	}
	public List<String> getWeek2() {
		return week2;
	}
	public void setWeek2(List<String> week2) {
		this.week2 = week2;
	}
	public List<String> getWeek3() {
		return week3;
	}
	public void setWeek3(List<String> week3) {
		this.week3 = week3;
	}
	public List<String> getWeek4() {
		return week4;
	}
	public void setWeek4(List<String> week4) {
		this.week4 = week4;
	}
	public List<String> getWeek5() {
		return week5;
	}
	public void setWeek5(List<String> week5) {
		this.week5 = week5;
	}
	public List<String> getWeek6() {
		return week6;
	}
	public void setWeek6(List<String> week6) {
		this.week6 = week6;
	}
	public List<String> getWeek7() {
		return week7;
	}
	public void setWeek7(List<String> week7) {
		this.week7 = week7;
	}
	public List<String> getWeek8() {
		return week8;
	}
	public void setWeek8(List<String> week8) {
		this.week8 = week8;
	}
	public List<String> getWeek9() {
		return week9;
	}
	public void setWeek9(List<String> week9) {
		this.week9 = week9;
	}
	public List<String> getWeek10() {
		return week10;
	}
	public void setWeek10(List<String> week10) {
		this.week10 = week10;
	}
	public List<String> getWeek11() {
		return week11;
	}
	public void setWeek11(List<String> week11) {
		this.week11 = week11;
	}
	public List<String> getWeek12() {
		return week12;
	}
	public void setWeek12(List<String> week12) {
		this.week12 = week12;
	}
	public List<String> getWeek13() {
		return week13;
	}
	public void setWeek13(List<String> week13) {
		this.week13 = week13;
	}
	public List<String> getWeek14() {
		return week14;
	}
	public void setWeek14(List<String> week14) {
		this.week14 = week14;
	}
	public List<String> getWeek15() {
		return week15;
	}
	public void setWeek15(List<String> week15) {
		this.week15 = week15;
	}
	public List<String> getWeek16() {
		return week16;
	}
	public void setWeek16(List<String> week16) {
		this.week16 = week16;
	}
	public List<String> getWeek17() {
		return week17;
	}
	public void setWeek17(List<String> week17) {
		this.week17 = week17;
	}
	public List<String> getWeek18() {
		return week18;
	}
	public void setWeek18(List<String> week18) {
		this.week18 = week18;
	}
	public List<String> getWeek19() {
		return week19;
	}
	public void setWeek19(List<String> week19) {
		this.week19 = week19;
	}
	public List<String> getWeek20() {
		return week20;
	}
	public void setWeek20(List<String> week20) {
		this.week20 = week20;
	}
	public List<String> getSemester1() {
		return semester1;
	}
	public void setSemester1(List<String> semester1) {
		this.semester1 = semester1;
	}
	public List<String> getSemester2() {
		return semester2;
	}
	public void setSemester2(List<String> semester2) {
		this.semester2 = semester2;
	}
	public List<String> getSemester3() {
		return semester3;
	}
	public void setSemester3(List<String> semester3) {
		this.semester3 = semester3;
	}
	public List<String> getSemester4() {
		return semester4;
	}
	public void setSemester4(List<String> semester4) {
		this.semester4 = semester4;
	}
	public List<String> getSemester5() {
		return semester5;
	}
	public void setSemester5(List<String> semester5) {
		this.semester5 = semester5;
	}
	public List<String> getSemester6() {
		return semester6;
	}
	public void setSemester6(List<String> semester6) {
		this.semester6 = semester6;
	}
	public List<String> getSemester7() {
		return semester7;
	}
	public void setSemester7(List<String> semester7) {
		this.semester7 = semester7;
	}
	public List<String> getSemester8() {
		return semester8;
	}
	public void setSemester8(List<String> semester8) {
		this.semester8 = semester8;
	}
	public List<String> getSemester9() {
		return semester9;
	}
	public void setSemester9(List<String> semester9) {
		this.semester9 = semester9;
	}
	public List<String> getSemester10() {
		return semester10;
	}
	public void setSemester10(List<String> semester10) {
		this.semester10 = semester10;
	}
	private String departmentid;
	private Integer trainingEventsid;
	public Integer getTrainingEventsid() {
		return trainingEventsid;
	}
	public void setTrainingEventsid(Integer trainingEventsid) {
		this.trainingEventsid = trainingEventsid;
	}
	private String trainingEventsname;
	public String getTrainingEventsname() {
		return trainingEventsname;
	}
	public void setTrainingEventsname(String trainingEventsname) {
		this.trainingEventsname = trainingEventsname;
	}
	private TrainingEventsService trainingEventsService;
	public void setTrainingEventsService(TrainingEventsService trainingEventsService) {
		this.trainingEventsService = trainingEventsService;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	private MainTainOfPTService mainTainOfPTService;
	public void setMainTainOfPTService(MainTainOfPTService mainTainOfPTService) {
		this.mainTainOfPTService = mainTainOfPTService;
	}
	private PPTrainingConceptService ppTrainingConceptService;
	public void setPpTrainingConceptService(
			PPTrainingConceptService ppTrainingConceptService) {
		this.ppTrainingConceptService = ppTrainingConceptService;
	}
	public String tobjapPage(){
		boolean ok = mainTainOfPTService.checkpyxx(departmentid);
		if(ok)
		{
			String tag = mainTainOfPTService.bjapTag(departmentid);
			if(tag.equals("noProfessional"))
			{
				mainTainOfPTService.bjap(departmentid);
				return "tobjapPage";
			}
			else if(tag.equals("chancetag"))
			{
				return "tochancetagPage";
			}
			else if(tag.equals("havaProfessionalOne"))
			{
				mainTainOfPTService.bjap(departmentid);
				return "bjap_havaProOne";
			}
			else
			{
				bjapMore();
				return "bjap_havaProMore";
			}
		}
		else{
			ServletActionContext.getRequest().setAttribute("tag", "topyxxPage");
			return "tobjapPageerr";
		}
		
	}

	private String tag;
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String chancetag(){
		mainTainOfPTService.chancetag(departmentid,tag);
		return tobjapPage();
	}
	public String revisechancetag(){
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
		return chancetag();
	}
	public String changetag(){
		mainTainOfPTService.changetag(departmentid);
		return tobjapPage();
	}
	public String revisechangetag(){
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
		return changetag();
	}
	//分专业方向录入
	List<String> professionalList;
	public void setProfessionalList(List<String> professionalList) {
		this.professionalList = professionalList;
	}
	public String bjapMore(){
		mainTainOfPTService.bjapMore(departmentid,professionalList);
		return "bjap_havaProMore";
	}
	public String bjapdeletePro(){
		mainTainOfPTService.bjapdeletePro(departmentid,professionalList);
		return bjapMore();
	}
	public String bjaptochangeProPage(){
		mainTainOfPTService.bjaptochangeProPage(departmentid,professionalList);
		return "bjap_changePro";
	}
	List<String> changePro;
	public void setChangePro(List<String> changePro) {
		this.changePro = changePro;
	}
	public String bjapChangePro(){
		mainTainOfPTService.bjapChangePro(departmentid,professionalList,changePro);
		return "bjap_havaProMore";
	}
	public String bjapNew(){
		mainTainOfPTService.bjapNew(departmentid,professionalList);
		return "tobjapNew";
	}
	public String addTrainingEventsNew(){
		trainingEventsService.addTraingEventsNew(departmentid,trainingEventsname,professionalList);
		return bjapNew();
	}
	public String delTrainingEventsNew(){
		trainingEventsService.delTrainingEventsNew(departmentid,trainingEventsid,professionalList);
		return bjapNew();
	}
	public String bjapaddTrainingEventsNew(){
		trainingEventsService.addTraingEventsNew(departmentid,trainingEventsname,professionalList);
		return bjapMore();
	}
	public String bjapdelTrainingEventsNew(){
		trainingEventsService.delTrainingEventsNew(departmentid,trainingEventsid,professionalList);
		return bjapMore();
	}
	public String updatemainTainOfPTNew(){
		mainTainOfPTService.updatemainTainOfPTNew(week1,week2,week3,week4,week5,week6,week7,week8,week9,week10,week11,week12,week13,week14,week15,week16,week17,week18,week19,week20,semester1,semester2,semester3,semester4,semester5,semester6,semester7,semester8,semester9,semester10,departmentid,professionalList);
		ServletActionContext.getRequest().setAttribute("tag", "topyapPage");
		return "updatemainTainOfPT";
	}
	
	public String addTrainingEvents(){
		trainingEventsService.addTrainingEvents(departmentid,trainingEventsname);
		return tobjapPage();
	}
	public String delTrainingEvents(){
		trainingEventsService.delTrainingEvents(departmentid,trainingEventsid);
		return tobjapPage();
	}
	public String updatemainTainOfPT(){
		mainTainOfPTService.updatemainTainOfPT(week1,week2,week3,week4,week5,week6,week7,week8,week9,week10,week11,week12,week13,week14,week15,week16,week17,week18,week19,week20,semester1,semester2,semester3,semester4,semester5,semester6,semester7,semester8,semester9,semester10,departmentid);
		ServletActionContext.getRequest().setAttribute("tag", "toanotherPage");
		return "updatemainTainOfPT";
	}
	public String updatemainTainOfPT1(){
		mainTainOfPTService.updatemainTainOfPT(week1,week2,week3,week4,week5,week6,week7,week8,week9,week10,week11,week12,week13,week14,week15,week16,week17,week18,week19,week20,semester1,semester2,semester3,semester4,semester5,semester6,semester7,semester8,semester9,semester10,departmentid);
		ServletActionContext.getRequest().setAttribute("tag", "topyapPage");
		return "updatemainTainOfPT";
	}
	
	public String topyapPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = ppTrainingConceptService.zypy(tnum,currentpage,collegeid);
		ServletActionContext.getRequest().setAttribute("collegeid", collegeid);
		setAttr(pageBean);
		return "topyapPage";
	}
	public String checkbjapPage(){
		String tag = mainTainOfPTService.checkbjap(departmentid);
		if(tag.equals("noxx")){		
			ServletActionContext.getRequest().setAttribute("tag", "topyapPage");
			return "tobjapPageerr";
		}
		else{
			if(tag.equals("0"))
			{
				return "checkbjapPage";
			}
			else
			{
				return "checkbjapPage_More";
			}
		}
	}
	
	public String checkbjapMoreSearch(){
		bjapMore();
		return "checkbjapPage_More";
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
