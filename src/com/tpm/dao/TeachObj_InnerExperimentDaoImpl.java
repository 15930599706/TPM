package com.tpm.dao;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.tpm.entity.TeachObj_InnerExperiment;

public class TeachObj_InnerExperimentDaoImpl extends BaseDaoImpl<TeachObj_InnerExperiment> implements
		TeachObj_InnerExperimentDao {

	public String findAllByCDP_InnerExperiment(String syllabusId) {
		List<TeachObj_InnerExperiment> teachObj_InnerExperimentList = this.getHibernateTemplate().find("from TeachObj_InnerExperiment where syllabus_InnerExperimentid=?",syllabusId);
	
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setExcludes(new String[]{"setAbilityAndTeachObj_InnerExperiment"});
		JSONArray jSONArray = JSONArray.fromObject(teachObj_InnerExperimentList, jsonConfig);
		String contentTheoStr = jSONArray.toString();
		
	/*	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
		String str = JSONArray.fromObject(teachObj_InnerExperimentList).toString();*/
		return contentTheoStr;
	}

	public List<TeachObj_InnerExperiment> findTeachObj_InnerExperimentListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from TeachObj_InnerExperiment where syllabus_InnerExperimentid=?",syllabusId_Copy);
	}


}
