package com.tpm.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tpm.entity.College;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.Professional;
import com.tpm.service.LinkService;

public class LinkAction extends ActionSupport {
	private LinkService linkService;
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	private String id;	//接收Ajax传来的学院id，用于查询专业
	public void setId(String id) {
		this.id = id;
	}

	private List<String> departList;		//接收返回的专业列表
	public List<String> getDepartList() {
		return departList;	//返回departList
	}	//	返回的内容会被变为json格式

	private List<String> departDirectList;	//接收返回的专业方向列表
	public List<String> getDepartDirectList() {
		return departDirectList;
	}
	
	private List<String> experimentList;	//接收返回的实验室列表
	public List<String> getExperimentList() {	
		return experimentList;
	}

	public String findDepartment() throws Exception {	//查找对应学院的全部专业并返回List
		College college = linkService.findcollege(id);	//查找对应id的college
		List<Department> departList1 = linkService.findxglLink(college);	//查找专业List
		departList=new 	ArrayList<String>();	
		if(departList1 != null && departList1.size() != 0){
			for(int i=0;i<departList1.size();i++){	//获取专业List中的id和Cname，然后在配置文件中转为json格式		
			departList.add(departList1.get(i).getDepartmentid()+" "+departList1.get(i).getDepartmentCname());	//为departList赋值
			} 
		}
		return SUCCESS;
	}
	
	public String findDepartmentDirect() throws Exception{
		Department department = linkService.finddepartment(id);
		List<Professional> departDirectList1 = linkService.findProfessional(department);
		departDirectList=new ArrayList<String>();
		if(departDirectList1 != null && departDirectList1.size() != 0){
			for(int i=0;i<departDirectList1.size();i++){
				departDirectList.add(departDirectList1.get(i).getProfessionalid()+" "+departDirectList1.get(i).getProfessionalname());
			}
		}
		return SUCCESS;
	}
	
	public String findExperiment() throws Exception{
		College college = linkService.findcollege(id);	//查找对应id的college
		List<Experiment> experimentList1 = linkService.findExperiment(college);	//查找实验室List
		experimentList=new 	ArrayList<String>();	
		if(experimentList1 != null && experimentList1.size() != 0){
			for(int i=0;i<experimentList1.size();i++){	//获取实验室List中的id和name，然后在配置文件中转为json格式		
				experimentList.add(experimentList1.get(i).getExperimentid()+" "+experimentList1.get(i).getExperimentname());	//为experimentList赋值
			} 
		}
		return SUCCESS;
	}
	
}
