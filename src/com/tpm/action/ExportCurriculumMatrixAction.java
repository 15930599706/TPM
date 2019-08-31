package com.tpm.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.Professional;
import com.tpm.service.CurriculumMatrixService;
import com.tpm.service.ExportCurriculumMatrixService;

public class ExportCurriculumMatrixAction extends ActionSupport {
	private ExportCurriculumMatrixService exportCurriculumMatrixService;

	public void setExportCurriculumMatrixService(
			ExportCurriculumMatrixService exportCurriculumMatrixService) {
		this.exportCurriculumMatrixService = exportCurriculumMatrixService;
	}
	private CurriculumMatrixService curriculumMatrixService;
	public void setCurriculumMatrixService(
			CurriculumMatrixService curriculumMatrixService) {
		this.curriculumMatrixService = curriculumMatrixService;
	}
	private String departmentID;
	
	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public void exportMatrix() throws Exception
	{

		List<Curriculum> curriculumlist = curriculumMatrixService.findcurriculumbydepartment(departmentID);
		List<Professional> professionalList = curriculumMatrixService.findProfessionbydepartment(departmentID);
		List<List<Curriculum>> curriculumlist_pro = new ArrayList<List<Curriculum>>();//存储有专业方向的课程
		if(professionalList != null && professionalList.size() !=0)
		{
			//改
			//慢
			curriculumlist_pro =  curriculumMatrixService.findcurriculumbydepartment_pro(departmentID);
		}
			
		List<Ability> abilitielist = curriculumMatrixService.findabilitielist(departmentID);
		List<List<Analysis>> anlist = curriculumMatrixService.findanalysisbyability(abilitielist);
		List<List<List<CurriculumMatrix>>> curriculumMatrixlist = curriculumMatrixService.findcurriculumMatrixbyall(curriculumlist,abilitielist,anlist);
		List<List<List<List<CurriculumMatrix>>>> curriculumMatrixlist_pro = new ArrayList<List<List<List<CurriculumMatrix>>>>();	
		if(professionalList != null && professionalList.size() !=0)
		{
			curriculumMatrixlist_pro = curriculumMatrixService.findcurriculumMatrixbyall_pro(curriculumlist_pro,abilitielist,anlist,professionalList);
		}
			
		exportCurriculumMatrixService.exportCurriculumMatrix(departmentID,curriculumlist,curriculumlist_pro,professionalList,abilitielist,anlist,curriculumMatrixlist,curriculumMatrixlist_pro);
	}

}
