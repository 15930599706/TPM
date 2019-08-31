package com.tpm.service;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabus_TheoInnerExperimentDao;
import com.tpm.dao.BaseExpTheoInnerRelateExpProjectTheoDao;
import com.tpm.dao.BaseExperiment_TheoInnerExperimentDao;
import com.tpm.dao.DistributeHour_TheoInnerExperimentDao;
import com.tpm.dao.ExpermentProject_InnerExperimentDao;
import com.tpm.dao.Syllabus_TheoInnerExperimentDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.BaseExpTheoInnerRelateExpProjectTheo;
import com.tpm.entity.BaseExperiment_TheoInnerExperiment;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DistributeHour_TheoInnerExperiment;
import com.tpm.entity.ExpermentProject_Theo;
import com.tpm.entity.Syllabus_TheoInnerExperiment;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class BaseExperiment_TheoInnerExperimentService {

	private BaseExperiment_TheoInnerExperimentDao baseExperiment_TheoInnerExperimentDao;
	private ApplicationSyllabus_TheoInnerExperimentDao applicationSyllabus_TheoInnerExperimentDao;
	private TheoreticalPlanDao theoreticalPlanDao;
	private Syllabus_TheoInnerExperimentDao syllabus_TheoInnerExperimentDao;
	private ExpermentProject_InnerExperimentDao expermentProject_InnerExperimentDao;
	
	private BaseExpTheoInnerRelateExpProjectTheoDao relateDao;
	private DistributeHour_TheoInnerExperimentDao distributeHour_TheoInnerExperimentDao;
	
	public void setRelateDao(BaseExpTheoInnerRelateExpProjectTheoDao relateDao) {
		this.relateDao = relateDao;
	}

	public void setDistributeHour_TheoInnerExperimentDao(
			DistributeHour_TheoInnerExperimentDao distributeHour_TheoInnerExperimentDao) {
		this.distributeHour_TheoInnerExperimentDao = distributeHour_TheoInnerExperimentDao;
	}

	public void setExpermentProject_InnerExperimentDao(
			ExpermentProject_InnerExperimentDao expermentProject_InnerExperimentDao) {
		this.expermentProject_InnerExperimentDao = expermentProject_InnerExperimentDao;
	}
	
	public void setBaseExperiment_TheoInnerExperimentDao(
			BaseExperiment_TheoInnerExperimentDao baseExperiment_TheoInnerExperimentDao) {
		this.baseExperiment_TheoInnerExperimentDao = baseExperiment_TheoInnerExperimentDao;
	}

	public void setApplicationSyllabus_TheoInnerExperimentDao(
			ApplicationSyllabus_TheoInnerExperimentDao applicationSyllabus_TheoInnerExperimentDao) {
		this.applicationSyllabus_TheoInnerExperimentDao = applicationSyllabus_TheoInnerExperimentDao;
	}

	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}

	public Syllabus_TheoInnerExperimentDao getSyllabus_TheoInnerExperimentDao() {
		return syllabus_TheoInnerExperimentDao;
	}

	public void setSyllabus_TheoInnerExperimentDao(
			Syllabus_TheoInnerExperimentDao syllabus_TheoInnerExperimentDao) {
		this.syllabus_TheoInnerExperimentDao = syllabus_TheoInnerExperimentDao;
	}

	public void toBaseTheoExpPage(String syllabusId, String theoreticalLessonId) {
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabus_TheoInnerExperiment = applicationSyllabus_TheoInnerExperimentDao.findCurriculum(syllabusId);//在大纲应用专业表找课程信息
		
		Curriculum curriculum = applicationSyllabus_TheoInnerExperiment.get(0).getCurriculum();
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		
		TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
		ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);
		
		List<DistributeHour_TheoInnerExperiment> distributeHour_TheoInnerExperimentlist = baseExperiment_TheoInnerExperimentDao.findDistributeHour_TheoInnerExperiment(syllabusId);
		ServletActionContext.getRequest().setAttribute("distributeHour_TheoInnerExperimentlist",distributeHour_TheoInnerExperimentlist);
		
		
		if(applicationSyllabus_TheoInnerExperiment != null && applicationSyllabus_TheoInnerExperiment.size() != 0)
		{
			if(applicationSyllabus_TheoInnerExperiment.get(0).getProfessional() != null)
			{
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_TheoInnerExperiment);
				ServletActionContext.getRequest().setAttribute("flage", 1);
			}
				
			else{ 
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_TheoInnerExperiment.get(0));
				ServletActionContext.getRequest().setAttribute("flage", -1);
			}
		}
		
		Syllabus_TheoInnerExperiment syllabus = syllabus_TheoInnerExperimentDao.get(Integer.valueOf(syllabusId));
		if(syllabus.getBaseExperiment_TheoInnerExperiment() != null){
			ServletActionContext.getRequest().setAttribute("BaseExperiment_TheoInnerExperiment", syllabus.getBaseExperiment_TheoInnerExperiment());
		}
		
	}

	public void updateBaseTheoExperiment(
			BaseExperiment_TheoInnerExperiment baseExperiment_TheoInnerExperiment,
			String syllabusId, String theoreticalLessonId, String data) throws Exception{
		if(baseExperiment_TheoInnerExperiment.getBaseExperiment_TheoInnerExperimentid() != null){
			Syllabus_TheoInnerExperiment syllabus_TheoInnerExperiment = syllabus_TheoInnerExperimentDao.get(Integer.valueOf(syllabusId));
			syllabus_TheoInnerExperiment.setBaseExperiment_TheoInnerExperiment(baseExperiment_TheoInnerExperiment);
	
			baseExperiment_TheoInnerExperimentDao.update(baseExperiment_TheoInnerExperiment);
		}else{
			baseExperiment_TheoInnerExperimentDao.add(baseExperiment_TheoInnerExperiment);
			
			Syllabus_TheoInnerExperiment syllabus_TheoInnerExperiment = syllabus_TheoInnerExperimentDao.get(Integer.valueOf(syllabusId));
			syllabus_TheoInnerExperiment.setBaseExperiment_TheoInnerExperiment(baseExperiment_TheoInnerExperiment);
			syllabus_TheoInnerExperimentDao.add(syllabus_TheoInnerExperiment);
		}
		TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newtheoreticalLesson", newtheoreticalLesson);
		
		

		String result = URLDecoder.decode(data,"UTF-8");
		JSONArray jsonArray = new JSONArray(result);
		int iSize = jsonArray.length();	
//		baseExperiment_TheoInnerExperimentDao.deleteDistributeHour_TheoInnerExperiment(syllabusId);
		List<DistributeHour_TheoInnerExperiment> exists = baseExperiment_TheoInnerExperimentDao.findDistributeHour_TheoInnerExperiment(syllabusId);
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < iSize; i++) 
		{
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			String namecourse =jsonObj.get("namecourse").toString();
			String hoursOfClass = jsonObj.get("classhour").toString();
			String hoursOfExp = jsonObj.get("exphour").toString();
 			Object idObj = null;
			try {
				idObj = jsonObj.get("id");
			} catch (Exception e) {
				idObj = null;
			}
			String conhour = "";
			try {
				Object conhourObj = jsonObj.get("conhour");
				if (null != conhourObj) {
					conhour = conhourObj.toString();
				}
			} catch (Exception e) {
				conhour = null;
			}
			DistributeHour_TheoInnerExperiment distributeHour_TheoInnerExperiment = new DistributeHour_TheoInnerExperiment();
			if (null != idObj) {
				distributeHour_TheoInnerExperiment = distributeHour_TheoInnerExperimentDao.get(Integer.parseInt(idObj.toString()));
			}
			
			distributeHour_TheoInnerExperiment.setName(namecourse);
			distributeHour_TheoInnerExperiment.setHoursOfClass(hoursOfClass);
			distributeHour_TheoInnerExperiment.setHoursOfExp(hoursOfExp);
			distributeHour_TheoInnerExperiment.setSyllabusid(syllabusId);
			distributeHour_TheoInnerExperiment.setExp(conhour);
			if (null != idObj) {
				//数据库中存在的
				Integer id = Integer.parseInt(idObj.toString());
				ids.add(id);
				distributeHour_TheoInnerExperimentDao.update(distributeHour_TheoInnerExperiment);
				//更新实验表
				Integer theoid = null;
				List<BaseExpTheoInnerRelateExpProjectTheo> relates = relateDao.findRelateByExpid(id);
				if (null != relates && relates.size() > 0) {
					BaseExpTheoInnerRelateExpProjectTheo relate = relates.get(0) ;
					theoid= relate.getTheo().getExpermentProject_Theoid();
				}
				//实验内容表
				if (null != theoid) {
					ExpermentProject_Theo theo = expermentProject_InnerExperimentDao.get(theoid);
					theo.setName(namecourse);
					int classH = 0;
					int expH = 0;
					if (null != hoursOfClass && !"".equals(hoursOfClass)) {
						classH = Integer.parseInt(hoursOfClass);
					}
					if (null != hoursOfExp && !"".equals(hoursOfExp)) {
						expH = Integer.parseInt(hoursOfExp);
					}
					Integer total = classH + expH;
					theo.setTime(total.toString());
					expermentProject_InnerExperimentDao.update(theo);
				}
				
			} else {
				//新添加的
				baseExperiment_TheoInnerExperimentDao.addDistributeHour_TheoInnerExperiment(distributeHour_TheoInnerExperiment);
				//插入实验内容表
				ExpermentProject_Theo theo = new ExpermentProject_Theo();
				theo.setSyllabusid(syllabusId);
				theo.setName(namecourse);
				int classH = 0;
				int expH = 0;
				if (null != hoursOfClass && !"".equals(hoursOfClass)) {
					classH = Integer.parseInt(hoursOfClass);
				}
				if (null != hoursOfExp && !"".equals(hoursOfExp)) {
					expH = Integer.parseInt(hoursOfExp);
				}
				Integer total = classH + expH;
				theo.setTime(total.toString());
				expermentProject_InnerExperimentDao.add(theo);
				//插入关联表
				BaseExpTheoInnerRelateExpProjectTheo relate = new BaseExpTheoInnerRelateExpProjectTheo();
				relate.setTheoInnerExperiment(distributeHour_TheoInnerExperiment);
				relate.setTheo(theo);
				relateDao.add(relate);
			}
		}
		
		//待删除的
		List<Integer> existIds = new ArrayList<Integer>();
		for (DistributeHour_TheoInnerExperiment exDis : exists) {
			existIds.add(exDis.getDistributeHour_TheoInnerExperimentid());
		}
		for (Integer id : ids) {
			if (existIds.contains(id)) {
				existIds.remove(id);
			}
		}
		if (null != existIds && existIds.size() > 0) {
			for (Integer id : existIds) {
				DistributeHour_TheoInnerExperiment dis = distributeHour_TheoInnerExperimentDao.get(id);
				//删除关联表
				Integer theoid = null;
				List<BaseExpTheoInnerRelateExpProjectTheo> relates = relateDao.findRelateByExpid(id);
				if (null != relates && relates.size() > 0) {
					BaseExpTheoInnerRelateExpProjectTheo relate = relates.get(0) ;
					theoid= relate.getTheo().getExpermentProject_Theoid();
					relateDao.delete(relate);
				}
				//删除实验内容表
				if (null != theoid) {
					ExpermentProject_Theo exp = expermentProject_InnerExperimentDao.get(theoid);
					expermentProject_InnerExperimentDao.delete(exp);
				}
				
				distributeHour_TheoInnerExperimentDao.delete(dis);
				
			}
		}
		
		String res = "{\"error\": false, \"message\":\"保存成功，请填写其他大纲内容！\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(res);
	//	ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
	//	ServletActionContext.getRequest().setAttribute("tag", "toBase_TheoInnerExperimentPage");
		
	}
		
	}
	

