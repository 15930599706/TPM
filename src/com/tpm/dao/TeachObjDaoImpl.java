package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import net.sf.json.JSONArray;


import com.tpm.entity.TeachObj;

public class TeachObjDaoImpl extends BaseDaoImpl<TeachObj> implements TeachObjDao {

	public String findAllByCDP(String syllabusId) {
		List<TeachObj> teachObjList = this.getHibernateTemplate().find("from TeachObj where syllabusID=?",syllabusId);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setExcludes(new String[]{"setAbilityAndTeachObj"});  
		JSONArray jSONArray = JSONArray.fromObject(teachObjList, jsonConfig);
		String contentTheoStr = jSONArray.toString();

		
		/*JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[]{"setAbilityAndTeachObj"});
		String str = JSONArray.fromObject(TeachObjList,jsonConfig).toString();*/
		return contentTheoStr;
	}

	public List<TeachObj> findTeachObjListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from TeachObj where syllabusID=?",syllabusId_Copy);
	}

}
