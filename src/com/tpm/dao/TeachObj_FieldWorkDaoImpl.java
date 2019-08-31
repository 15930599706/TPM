package com.tpm.dao;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.tpm.entity.TeachObj_FieldWork;

public class TeachObj_FieldWorkDaoImpl extends BaseDaoImpl<TeachObj_FieldWork> implements
		TeachObj_FieldWorkDao {

	public String findAllByCDP_FieldWork(String syllabusId) {
			List<TeachObj_FieldWork> TeachObj_FieldWorkList = this.getHibernateTemplate().find("from TeachObj_FieldWork where syllabus_FieldWorkid=?",syllabusId);
			
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			jsonConfig.setExcludes(new String[]{"setAbilityAndTeachObj_FieldWork"});
			JSONArray jSONArray = JSONArray.fromObject(TeachObj_FieldWorkList, jsonConfig);
			String contentTheoStr = jSONArray.toString();
			
			/*JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
			String str = JSONArray.fromObject(TeachObj_FieldWorkList,jsonConfig).toString();*/
			return contentTheoStr;
		}

	public List<TeachObj_FieldWork> findTeachObj_FieldWorkListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from TeachObj_FieldWork where syllabus_FieldWorkid=?",syllabusId_Copy);
	}
	}

