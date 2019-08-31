package com.tpm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.User;
import com.tpm.service.ErrorDiscService;

public class ErrorDiscAction extends ActionSupport {
	private ErrorDiscService errorDiscService;
	public void setErrorDiscService(ErrorDiscService errorDiscService) {
		this.errorDiscService = errorDiscService;
	}
	
	HttpServletRequest request = ServletActionContext.getRequest();	//放入域对象
	
	public void findAllCollege(){	//查找所有学院，生成List用于返回
		List<College> collegeList = errorDiscService.findAllCollege();
		request.setAttribute("collegelist", collegeList);			
	}
	
	private String tnum;	//接收登陆账户的职工号，用于查询用户级别、所在 系等	
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	private User user;	
	public void setUser(User user) {
		this.user = user;
	}

	public void findUserInfo(String tnum){	//根据登陆用户id获得用户信息
		user = errorDiscService.findUserById(tnum);
		ServletActionContext.getRequest().setAttribute("user", user);			
	}
	
	private String collegeid;	
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	
	public void setCollegeidByLevel(){	//如果学院传过来的值为null，则设定查找全部学院
		findUserInfo(tnum);
		if(user.getAdminlevel() == 5){			
			if(collegeid == null || "".equals(collegeid)){
				setCollegeid("-1");
			}
		}
		if(user.getAdminlevel() == 3)
		{
			setCollegeid(user.getCollege().getCollegeid());
		}
	}

	public String toproIncoPage(){	//跳转到专业信息不完整页面
		findAllCollege();
		setCollegeidByLevel();		
		List<Department> ptBasicInformationEmptyList = errorDiscService.findPTBasicInformationEmpty(collegeid);		//检索培养计划基本信息为空的专业
		List<Department> abilityEmptyList = errorDiscService.findAbilityEmpty(collegeid);		//检索毕业生应获得的知识和能力的专业
		List<Department> ppTrainingConceptEmptyList = errorDiscService.findPPTrainingConceptEmpty(collegeid);	//检索专业人才培养理念为空的专业
		
		List<Department> departmentEmptyList = new ArrayList<Department>();	//检索上述三项有空的专业
		List<List<String>> infoEmptyList = new ArrayList<List<String>>();	//每个专业为空的信息
		List<Department> departmentList = errorDiscService.findDepartmentByCollegeId(collegeid);
		for(int i=0;i<departmentList.size();i++){
			if(ptBasicInformationEmptyList.contains(departmentList.get(i)) || abilityEmptyList.contains(departmentList.get(i)) || ppTrainingConceptEmptyList.contains(departmentList.get(i))){
				departmentEmptyList.add(departmentList.get(i));
				List<String> inInfoEmptyList = new ArrayList<String>();
				if(ptBasicInformationEmptyList.contains(departmentList.get(i))){
					inInfoEmptyList.add("培养计划基本信息");
				}
				if(abilityEmptyList.contains(departmentList.get(i))){
					inInfoEmptyList.add("毕业生应获得的知识和能力");
				}
				if(ppTrainingConceptEmptyList.contains(departmentList.get(i))){
					inInfoEmptyList.add("专业人才培养理念");
				}
				infoEmptyList.add(inInfoEmptyList);
			}
		}
		Integer departmentEmptyListCount = departmentEmptyList.size();
		request.setAttribute("departmentEmptyList", departmentEmptyList);
		request.setAttribute("departmentEmptyListCount", departmentEmptyListCount);
		request.setAttribute("infoEmptyList", infoEmptyList);
		request.setAttribute("xueyuan", collegeid);	
		return "toproIncoPage";
	}
	
	public String toavePerPage(){	//跳转到平均周学时页面
		findAllCollege();
		setCollegeidByLevel();
		errorDiscService.findAvePer(collegeid);
		request.setAttribute("xueyuan", collegeid);	
		return "toavePerPage";
	}
	
	public String tokeyCouEmptyPage(){	//跳转到关键课程信息为空页面
		findAllCollege();
		setCollegeidByLevel();
		errorDiscService.findKeyCouEmpty(collegeid);		
		return "tokeyCouEmptyPage";
	}
	
	public void exportKeyCouEmptyExcel(){//导出关键课程信息为空Excel
		findAllCollege();
		setCollegeidByLevel();
		errorDiscService.exportKeyCouEmptyExcel(collegeid);
	}
	
	public String topreCreErrPage(){	//跳转到学时学分对应错误页面
		findAllCollege();
		setCollegeidByLevel();
		errorDiscService.findPreCreErr(collegeid);		
		return "topreCreErrPage";
	}
	
	public void exportPreCreErrExcel(){//导出学时学分对应错误Excel
		findAllCollege();
		setCollegeidByLevel();
		errorDiscService.exportPreCreErrExcel(collegeid);
	}
	
	public String tocreditEmptyPage(){	//查询专业总学分为空专业
		findAllCollege();
		setCollegeidByLevel();
		errorDiscService.findCreditEmpty(collegeid);
		return "tocreditEmptyPage";
	}
	
	public void exportCreditEmptyExcel(){	//导出专业总学分为空Excel
		findAllCollege();
		setCollegeidByLevel();
		errorDiscService.exportCreditEmptyExcel(collegeid);
	}
	

	public String tototalCrePage(){	//跳转到总学分超标页面
		findAllCollege();
		setCollegeidByLevel();
		errorDiscService.findTotalCre(collegeid);
		request.setAttribute("xueyuan", collegeid);	
		return "tototalCrePage";
	}

}
