package com.tpm.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tpm.service.AbilityService;

public class AbilityAction extends ActionSupport {
	private List<Integer> abilityid;
	private List<String> abilityname;
	private List<String> abilitycontent;
	private String departmentid;
	
	public List<Integer> getAbilityid() {
		return abilityid;
	}
	public void setAbilityid(List<Integer> abilityid) {
		this.abilityid = abilityid;
	}
	public List<String> getAbilityname() {
		return abilityname;
	}
	public void setAbilityname(List<String> abilityname) {
		this.abilityname = abilityname;
	}
	public List<String> getAbilitycontent() {
		return abilitycontent;
	}
	public void setAbilitycontent(List<String> abilitycontent) {
		this.abilitycontent = abilitycontent;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public AbilityService getAbilityService() {
		return abilityService;
	}
	private AbilityService abilityService;
	public void setAbilityService(AbilityService abilityService) {
		this.abilityService = abilityService;
	}
	public String updateability(){
		abilityService.updateability(abilityid,abilityname,abilitycontent,departmentid);
		ServletActionContext.getRequest().setAttribute("tag", "tozypyPage");
		ServletActionContext.getRequest().setAttribute("msg", "保存成功！");
		return "updateability";
	}

}
