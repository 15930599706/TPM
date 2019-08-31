package com.tpm.dao;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.tpm.entity.TeachObj_CourseDesign;

public class TeachObj_CourseDesignDaoImpl extends BaseDaoImpl<TeachObj_CourseDesign> implements
		TeachObj_CourseDesignDao {

	public String findAllByCDP_CourseDesign(String syllabusId) {
		List<TeachObj_CourseDesign> teachObj_CourseDesignList = this.getHibernateTemplate().find("from TeachObj_CourseDesign where syllabus_CourseDesignid=?",syllabusId);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setExcludes(new String[]{"setAbilityAndTeachObj_CourseDesign"});
		JSONArray jSONArray = JSONArray.fromObject(teachObj_CourseDesignList, jsonConfig);
		String contentTheoStr = jSONArray.toString();
		
	/*	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
		String str = JSONArray.fromObject(teachObj_CourseDesignList,jsonConfig).toString();*/
		return contentTheoStr;
	}

	public List<TeachObj_CourseDesign> findTeachObj_CourseDesignListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from TeachObj_CourseDesign where syllabus_CourseDesignid=?",syllabusId_Copy);
		
	}

}
