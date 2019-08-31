package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.AbilityDao;
import com.tpm.dao.AnalysisDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
import com.tpm.entity.Department;
@Transactional
public class AnalysisService {
	private AnalysisDao analysisDao;
	private DepartmentDao departmentDao;
	private AbilityDao abilityDao;
	public void setAbilityDao(AbilityDao abilityDao) {
		this.abilityDao = abilityDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void setAnalysisDao(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}
	public Integer byyq(String departmentid) {
		Department department = departmentDao.get(departmentid);
		List<Ability> abilitylist = abilityDao.getbydepartment(department);
		if(abilitylist != null && abilitylist.size() != 0){
			for(int i=0;i<abilitylist.size();i++){
				if(abilitylist.get(i).getAbilityname() == null)
					return 0;
			}
			String ability = "";
			Integer count = 0;
			for(int i=0;i<abilitylist.size();i++){
				List<Analysis> analysilist = analysisDao.getbyability(abilitylist.get(i));
				if(analysilist != null){
					count = count + analysilist.size();
				}
				if(count != 0)break;
			}
			if(count == 0){
				for(int i=0;i<abilitylist.size();i++){
					ability = ability + "{ title: \""+abilitylist.get(i).getAbilityname()+"\", abilitycontent: \""+abilitylist.get(i).getAbilitycontent().replaceAll("\\s*", "")+"\", id: "+(i+1)+", point_arr: [\"\"] },";
				}
			}else{
				for(int i=0;i<abilitylist.size();i++){
					String content = "";
					List<Analysis> analysilist = analysisDao.getbyability(abilitylist.get(i));
					if(analysilist != null && analysilist.size() != 0){
						for(int j=0;j<analysilist.size();j++){
							if(j<analysilist.size()-1){
								content = content + "\""+analysilist.get(j).getAnalysiscontent()+"\",";
							}else{
								content = content + "\""+analysilist.get(j).getAnalysiscontent()+"\"";
							}
						}
						ability = ability + "{ title: \""+abilitylist.get(i).getAbilityname()+"\", abilitycontent: \""+abilitylist.get(i).getAbilitycontent().replaceAll("\\s*", "")+"\", id: "+(i+1)+", point_arr: ["+content+"] },";
					}
					else{
						ability = ability + "{ title: \""+abilitylist.get(i).getAbilityname()+"\", abilitycontent: \""+abilitylist.get(i).getAbilitycontent().replaceAll("\\s*", "")+"\", id: "+(i+1)+", point_arr: [\"\"] },";
					}
				}
			}
			ServletActionContext.getRequest().setAttribute("department", department);
			ServletActionContext.getRequest().setAttribute("ability", ability);
			return abilitylist.size();
		}else{
			ServletActionContext.getRequest().setAttribute("department", department);
			return 0;
		}
	}
	public void addanalysis(Analysis newAnalysis) {
		analysisDao.add(newAnalysis);
	}
	public List<Analysis> getbyability(Ability ability) {
		return analysisDao.getbyability(ability);
	}
	public void del(Analysis analysis) {
		analysisDao.delete(analysis);
	}
	

	
	
}
