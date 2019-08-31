package com.tpm.dao;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.tpm.entity.TeachObj_Gra;

public class TeachObj_GraDaoImpl extends BaseDaoImpl<TeachObj_Gra> implements
		TeachObj_GraDao {

	public String findAllByCDP_Gra(String syllabusId) {
		List<TeachObj_Gra> teachObj_GraList = this.getHibernateTemplate().find("from TeachObj_Gra where syllabus_Graid=?",syllabusId);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setExcludes(new String[]{"setAbilityAndTeachObj_Gra"});
		JSONArray jSONArray = JSONArray.fromObject(teachObj_GraList, jsonConfig);
		String contentTheoStr = jSONArray.toString();
		
	/*	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
		String str = JSONArray.fromObject(teachObj_GraList,jsonConfig).toString();*/
		return contentTheoStr;
	}

	public List<TeachObj_Gra> findTeachObj_GraListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from TeachObj_Gra where syllabus_Graid=?",syllabusId_Copy);
		
	}

}
