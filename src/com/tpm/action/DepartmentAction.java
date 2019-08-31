package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.Department;
import com.tpm.service.DepartmentService;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	private Department department = new Department();
	public Department getModel() {
		return department;
	}
	private DepartmentService departmentService;
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public String deleteDepartment(){
		String tag = departmentService.deleteDepartment(department);
		if(tag.equals("success"))
		{
			return "deleteDepartment_success";
		}
		else
		{
			return "deleteDepartment_failure";
		}

	}
	public String toaddProPage()
	{
		departmentService.toaddProPage();
		return "toaddProPage";
	}
	
	public String addPro()
	{
		String tag = departmentService.addPro(department);
		if(tag.equals("success"))
		{
			return "addPro_success";
		}
		else
		{
			return "addPro_failure";
		}
	}
}
