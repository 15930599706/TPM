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

import com.tpm.dao.ApplicationSyllabus_InnerExperimentDao;
import com.tpm.dao.BaseExperimentDao;
import com.tpm.dao.DistributeHourRelateExperProjectDao;
import com.tpm.dao.DistributeHour_InnerExperimentDao;
import com.tpm.dao.ExpermentProjectDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.Syllabus_InnerExperimentDao;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.BaseExpTheoInnerRelateExpProjectTheo;
import com.tpm.entity.BaseExperiment;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DistributeHourRelateExperProject;
import com.tpm.entity.DistributeHour_InnerExperiment;
import com.tpm.entity.DistributeHour_TheoInnerExperiment;
import com.tpm.entity.ExpermentProject;
import com.tpm.entity.ExpermentProject_Theo;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus_InnerExperiment;
@Transactional
public class BaseExperimentService {
	private BaseExperimentDao baseExperimentDao;
	private ApplicationSyllabus_InnerExperimentDao applicationSyllabus_InnerExperimentDao;
	private PracticePlanDao practicePlanDao;
	private Syllabus_InnerExperimentDao syllabus_InnerExperimentDao;
	
	private DistributeHour_InnerExperimentDao innerExperimentDao;
	
	private DistributeHourRelateExperProjectDao relateDao;
	private ExpermentProjectDao expermentProjectDao;
	
	public void setRelateDao(DistributeHourRelateExperProjectDao relateDao) {
		this.relateDao = relateDao;
	}

	public void setExpermentProjectDao(ExpermentProjectDao expermentProjectDao) {
		this.expermentProjectDao = expermentProjectDao;
	}

	public void setInnerExperimentDao(
			DistributeHour_InnerExperimentDao innerExperimentDao) {
		this.innerExperimentDao = innerExperimentDao;
	}

	public void setApplicationSyllabus_InnerExperimentDao(
			ApplicationSyllabus_InnerExperimentDao applicationSyllabus_InnerExperimentDao) {
		this.applicationSyllabus_InnerExperimentDao = applicationSyllabus_InnerExperimentDao;
	}

	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setSyllabus_InnerExperimentDao(
			Syllabus_InnerExperimentDao syllabus_InnerExperimentDao) {
		this.syllabus_InnerExperimentDao = syllabus_InnerExperimentDao;
	}

	public void setBaseExperimentDao(BaseExperimentDao baseExperimentDao) {
		this.baseExperimentDao = baseExperimentDao;
	}

	public void toBaseExpPage(String syllabusId, String practiceLessonid) {
		List<ApplicationSyllabus_InnerExperiment> applicationSyllabus_InnerExperiment = applicationSyllabus_InnerExperimentDao.findCurriculum(syllabusId);//在大纲应用专业表找课程信息
		
		Curriculum curriculum = applicationSyllabus_InnerExperiment.get(0).getCurriculum();
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		List<DistributeHour_InnerExperiment> distributeHour_InnerExperimentlist = baseExperimentDao.findDistributeHour_InnerExperiment(syllabusId);
		ServletActionContext.getRequest().setAttribute("distributeHour_InnerExperimentlist",distributeHour_InnerExperimentlist);
		
		
		if(applicationSyllabus_InnerExperiment != null && applicationSyllabus_InnerExperiment.size() != 0)
		{
			if(applicationSyllabus_InnerExperiment.get(0).getProfessional() != null)
			{
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_InnerExperiment);
				ServletActionContext.getRequest().setAttribute("flage", 1);
			}
				
			else{ 
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_InnerExperiment.get(0));
				ServletActionContext.getRequest().setAttribute("flage", -1);
			}
		}
		
		Syllabus_InnerExperiment syllabus = syllabus_InnerExperimentDao.get(Integer.valueOf(syllabusId));
		if(syllabus.getBaseExperiment() != null){
			ServletActionContext.getRequest().setAttribute("BaseExperiment", syllabus.getBaseExperiment());
		}
	}

	public void updateBaseExperiment(BaseExperiment baseExperiment, String syllabusId, String practiceLessonid, String data) throws Exception{
		if(baseExperiment.getBaseExperimentid() != null){
			Syllabus_InnerExperiment syllabus_InnerExperiment = syllabus_InnerExperimentDao.get(Integer.valueOf(syllabusId));
			syllabus_InnerExperiment.setBaseExperiment(baseExperiment);
	
			baseExperimentDao.update(baseExperiment);
		}else{
			baseExperimentDao.add(baseExperiment);
			
			Syllabus_InnerExperiment syllabus_InnerExperiment = syllabus_InnerExperimentDao.get(Integer.valueOf(syllabusId));
			syllabus_InnerExperiment.setBaseExperiment(baseExperiment);
			syllabus_InnerExperimentDao.add(syllabus_InnerExperiment);
		}
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson", newpracticeLesson);
		

		String result = URLDecoder.decode(data,"UTF-8");
		JSONArray jsonArray = new JSONArray(result);
		int iSize = jsonArray.length();	
//		baseExperimentDao.deleteDistributeHour_InnerExperiment(syllabusId);
		List<DistributeHour_InnerExperiment> exists = baseExperimentDao.findDistributeHour_InnerExperiment(syllabusId);
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
			DistributeHour_InnerExperiment distributeHour_InnerExperiment = new DistributeHour_InnerExperiment();
			
			if (null != idObj) {
				distributeHour_InnerExperiment = innerExperimentDao.get(Integer.parseInt(idObj.toString()));
			}
			
			distributeHour_InnerExperiment.setName(namecourse);
			distributeHour_InnerExperiment.setHoursOfClass(hoursOfClass);
			distributeHour_InnerExperiment.setHoursOfExp(hoursOfExp);
			distributeHour_InnerExperiment.setExp(conhour);
			distributeHour_InnerExperiment.setSyllabusid(syllabusId);
			if (null != idObj) {
				//数据库中存在的
				Integer id = Integer.parseInt(idObj.toString());
				ids.add(id);
				innerExperimentDao.update(distributeHour_InnerExperiment);
				//更新实验表
				Integer expid = null;
				List<DistributeHourRelateExperProject> relates = relateDao.findRelateByExpid(id);
				if (null != relates && relates.size() > 0) {
					DistributeHourRelateExperProject relate = relates.get(0) ;
					expid= relate.getExpermentProject().getExpermentProjectid();
				}
				//实验内容表
				if (null != expid) {
					ExpermentProject expProject = expermentProjectDao.get(expid);
					expProject.setName(namecourse);
					int classH = 0;
					int expH = 0;
					if (null != hoursOfClass && !"".equals(hoursOfClass)) {
						classH = Integer.parseInt(hoursOfClass);
					}
					if (null != hoursOfExp && !"".equals(hoursOfExp)) {
						expH = Integer.parseInt(hoursOfExp);
					}
					Integer total = classH + expH;
					expProject.setTime(total.toString());
					expermentProjectDao.update(expProject);
				}
				
			} else {
				baseExperimentDao.addDistributeHour_InnerExperiment(distributeHour_InnerExperiment);
				
				//插入实验内容表
				ExpermentProject expProject = new ExpermentProject();
				expProject.setSyllabus_InnerExperimentid(syllabusId);
				expProject.setName(namecourse);
				int classH = 0;
				int expH = 0;
				if (null != hoursOfClass && !"".equals(hoursOfClass)) {
					classH = Integer.parseInt(hoursOfClass);
				}
				if (null != hoursOfExp && !"".equals(hoursOfExp)) {
					expH = Integer.parseInt(hoursOfExp);
				}
				Integer total = classH + expH;
				expProject.setTime(total.toString());
				expermentProjectDao.add(expProject);
				//插入关联表
				DistributeHourRelateExperProject relate = new DistributeHourRelateExperProject();
				relate.setInnerExperiment(distributeHour_InnerExperiment);
				relate.setExpermentProject(expProject);
				relateDao.add(relate);
			}
		}
		//待删除的
		List<Integer> existIds = new ArrayList<Integer>();
		for (DistributeHour_InnerExperiment exDis : exists) {
			existIds.add(exDis.getDistributeHour_InnerExperimentid());
		}
		for (Integer id : ids) {
			if (existIds.contains(id)) {
				existIds.remove(id);
			}
		}
		if (null != existIds && existIds.size() > 0) {
			for (Integer id : existIds) {
				DistributeHour_InnerExperiment dis = innerExperimentDao.get(id);
				//删除关联表
				Integer expid = null;
				List<DistributeHourRelateExperProject> relates = relateDao.findRelateByExpid(id);
				if (null != relates && relates.size() > 0) {
					DistributeHourRelateExperProject relate = relates.get(0) ;
					expid= relate.getExpermentProject().getExpermentProjectid();
					relateDao.delete(relate);
				}
				//删除实验内容表
				if (null != expid) {
					ExpermentProject exp = expermentProjectDao.get(expid);
					expermentProjectDao.delete(exp);
				}
				innerExperimentDao.delete(dis);
				
			}
		}
		String res = "{\"error\": false, \"message\":\"保存成功，请填写其他大纲内容！\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(res);
	//	ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
	//	ServletActionContext.getRequest().setAttribute("tag", "toPracLes_CourseDesignPage");
		
	}

}
