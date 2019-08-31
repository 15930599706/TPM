package com.tpm.dao;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import com.tpm.entity.TeachObj_TheoInnerExperiment;

public class TeachObj_TheoInnerExperimentDaoImpl extends BaseDaoImpl<TeachObj_TheoInnerExperiment>
		implements TeachObj_TheoInnerExperimentDao {

	public String findAllByCDP_TheoInnerExperimentList(String syllabusId) {
		List<TeachObj_TheoInnerExperiment> teachObj_TheoInnerExperimentList = this.getHibernateTemplate().find("from TeachObj_TheoInnerExperiment where syllabusid=?",syllabusId);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setExcludes(new String[]{"setAbilityAndTeachObj_TheoInnerExperiment"});
		JSONArray jSONArray = JSONArray.fromObject(teachObj_TheoInnerExperimentList, jsonConfig);
		String contentTheoStr = jSONArray.toString();
		
	/*	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
		String str = JSONArray.fromObject(teachObj_InnerExperimentList).toString();*/
		return contentTheoStr;
	}

	public List<TeachObj_TheoInnerExperiment> findTeachObjListBySyllabusid_TheoInnerExperiment(
			String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from TeachObj_TheoInnerExperiment where syllabusid=?",syllabusId_Copy);

	}

}
