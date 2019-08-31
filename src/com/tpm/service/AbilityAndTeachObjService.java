package com.tpm.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.AbilityAndTeachObjDao;
import com.tpm.dao.AbilityAndTeachObj_CourseDesignDao;
import com.tpm.dao.AbilityAndTeachObj_FieldWorkDao;
import com.tpm.dao.AbilityAndTeachObj_GraDao;
import com.tpm.dao.AbilityAndTeachObj_InnerExperimentDao;
import com.tpm.dao.AbilityAndTeachObj_TheoInnerExperimentDao;
import com.tpm.dao.AbilityDao;
import com.tpm.dao.AnalysisDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.CurriculumMatrixDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.dao.TeachObjDao;
import com.tpm.dao.TeachObj_CourseDesignDao;
import com.tpm.dao.TeachObj_FieldWorkDao;
import com.tpm.dao.TeachObj_GraDao;
import com.tpm.dao.TeachObj_InnerExperimentDao;
import com.tpm.dao.TeachObj_TheoInnerExperimentDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.entity.AbilityAndTeachObj_CourseDesign;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.AbilityAndTeachObj_Gra;
import com.tpm.entity.AbilityAndTeachObj_InnerExperiment;
import com.tpm.entity.AbilityAndTeachObj_TheoInnerExperiment;
import com.tpm.entity.Analysis;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.Department;
import com.tpm.entity.IndexSelect;
import com.tpm.entity.IndexSelect_CourseDesign;
import com.tpm.entity.IndexSelect_FieldWork;
import com.tpm.entity.IndexSelect_Gra;
import com.tpm.entity.IndexSelect_InnerExperiment;
import com.tpm.entity.IndexSelect_TheoInnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.TeachObj;
import com.tpm.entity.TeachObj_CourseDesign;
import com.tpm.entity.TeachObj_FieldWork;
import com.tpm.entity.TeachObj_Gra;
import com.tpm.entity.TeachObj_InnerExperiment;
import com.tpm.entity.TeachObj_TheoInnerExperiment;
import com.tpm.entity.TheoreticalLesson;

@Transactional
public class AbilityAndTeachObjService {
	private AbilityAndTeachObjDao abilityAndTeachObjDao;
	private TheoreticalPlanDao theoreticalPlanDao;
	
	//------------------注入Dao------------------------------
	private PracticePlanDao practicePlanDao;
	private AbilityAndTeachObj_FieldWorkDao abilityAndTeachObj_FieldWorkDao;
	private TeachObj_FieldWorkDao teachObj_FieldWorkDao;
	
	private AbilityAndTeachObj_CourseDesignDao abilityAndTeachObj_CourseDesignDao;
	private TeachObj_CourseDesignDao teachObj_CourseDesignDao;
	
	private AbilityAndTeachObj_InnerExperimentDao abilityAndTeachObj_InnerExperimentDao;
	private TeachObj_InnerExperimentDao teachObj_InnerExperimentDao;
	
	private AbilityAndTeachObj_GraDao abilityAndTeachObj_GraDao;
	private TeachObj_GraDao teachObj_GraDao;
	
	private AbilityAndTeachObj_TheoInnerExperimentDao abilityAndTeachObj_TheoInnerExperimentDao;
	private TeachObj_TheoInnerExperimentDao teachObj_TheoInnerExperimentDao;
	
	private CurriculumMatrixDao curriculumMatrixDao;
	private CurriculumDao curriculumDao;
	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}
	public void setCurriculumMatrixDao(CurriculumMatrixDao curriculumMatrixDao) {
		this.curriculumMatrixDao = curriculumMatrixDao;
	}

	public void setAbilityAndTeachObj_TheoInnerExperimentDao(
			AbilityAndTeachObj_TheoInnerExperimentDao abilityAndTeachObj_TheoInnerExperimentDao) {
		this.abilityAndTeachObj_TheoInnerExperimentDao = abilityAndTeachObj_TheoInnerExperimentDao;
	}

	public void setTeachObj_TheoInnerExperimentDao(
			TeachObj_TheoInnerExperimentDao teachObj_TheoInnerExperimentDao) {
		this.teachObj_TheoInnerExperimentDao = teachObj_TheoInnerExperimentDao;
	}

	public void setAbilityAndTeachObj_CourseDesignDao(
			AbilityAndTeachObj_CourseDesignDao abilityAndTeachObj_CourseDesignDao) {
		this.abilityAndTeachObj_CourseDesignDao = abilityAndTeachObj_CourseDesignDao;
	}

	public void setTeachObj_CourseDesignDao(
			TeachObj_CourseDesignDao teachObj_CourseDesignDao) {
		this.teachObj_CourseDesignDao = teachObj_CourseDesignDao;
	}

	public void setAbilityAndTeachObj_InnerExperimentDao(
			AbilityAndTeachObj_InnerExperimentDao abilityAndTeachObj_InnerExperimentDao) {
		this.abilityAndTeachObj_InnerExperimentDao = abilityAndTeachObj_InnerExperimentDao;
	}

	public void setTeachObj_InnerExperimentDao(
			TeachObj_InnerExperimentDao teachObj_InnerExperimentDao) {
		this.teachObj_InnerExperimentDao = teachObj_InnerExperimentDao;
	}

	public void setAbilityAndTeachObj_GraDao(
			AbilityAndTeachObj_GraDao abilityAndTeachObj_GraDao) {
		this.abilityAndTeachObj_GraDao = abilityAndTeachObj_GraDao;
	}

	public void setTeachObj_GraDao(TeachObj_GraDao teachObj_GraDao) {
		this.teachObj_GraDao = teachObj_GraDao;
	}

	public void setTeachObj_FieldWorkDao(TeachObj_FieldWorkDao teachObj_FieldWorkDao) {
		this.teachObj_FieldWorkDao = teachObj_FieldWorkDao;
	}

	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setAbilityAndTeachObj_FieldWorkDao(
			AbilityAndTeachObj_FieldWorkDao abilityAndTeachObj_FieldWorkDao) {
		this.abilityAndTeachObj_FieldWorkDao = abilityAndTeachObj_FieldWorkDao;
	}

	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}

	private TeachObjDao teachObjDao;
	public void setTeachObjDao(TeachObjDao teachObjDao) {
		this.teachObjDao = teachObjDao;
	}

	private AbilityDao abilityDao;
	private AnalysisDao analysisDao;

	public void setAbilityDao(AbilityDao abilityDao) {
		this.abilityDao = abilityDao;
	}

	public void setAnalysisDao(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}

	public void setAbilityAndTeachObjDao(AbilityAndTeachObjDao abilityAndTeachObjDao) {
		this.abilityAndTeachObjDao = abilityAndTeachObjDao;
	}
	
	//------------------理论课--------------------------
	public String toBefourAimTheoPage(String theoreticalLessonId, String syllabusId, String curriculumId ){
		ApplicationSyllabus applicationSyllabus = abilityAndTeachObjDao.findAppSyllabus(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus.getDepartment());	//查询毕业要求
		
		if(abilityList == null || abilityList.size() == 0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写毕业生应获得的知识和能力！");
			return "noIntoAim";
		}
		else
		{
			for(int i=0;i<abilityList.size();i++){
				if(abilityList.get(i).getAbilityname() == null)
				{
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整毕业生应获得的知识和能力！");
					return "noIntoAim";
				}
			}	
		}
		List<List<Analysis>> analysisList = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){	//嵌套List存储每个毕业要求对应的指标点
			List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
			analysisList.add(analysis);
		}
		if(analysisList == null || analysisList.size() == 0){
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写指标分解！");
			return "noIntoAim";
		}else{
			for(int i=0;i<analysisList.size();i++){
				if(analysisList.get(i) == null || analysisList.get(i).size() == 0){
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整指标分解！");
					return "noIntoAim";
				}
			}
		}

		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<IndexSelect>> analysisSelectList = new ArrayList<List<IndexSelect>>();//选择的毕业要求所选择的指标点
/*		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect> indexSelect = abilityAndTeachObjDao.findIndexSelectByAbility(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect != null && indexSelect.size() != 0){
				List<IndexSelect> indexSelectList = new ArrayList<IndexSelect>();
				abilitySelectList.add(indexSelect.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect.size();j++){
					indexSelectList.add(indexSelect.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelectList.add(indexSelectList);//添加全部选择的毕业要求和选择的指标点List
			}
		}*/
		/*if(abilitySelectList == null || abilitySelectList.size() == 0)
		{*/
		
		List<CurriculumMatrix> curriculumMatrixs = new ArrayList<CurriculumMatrix>();
		Curriculum newCurriculum = curriculumDao.get(curriculumId);
		if(newCurriculum.getCourseLei().equals("理论课"))
		{
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);

			for(int i=0;i<abilityList.size();i++)
			{
				//专业选修课多加入专业方向查询
				if(newtheoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					if(applicationSyllabus.getProfessional() == null){
						curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus.getCurriculum(),abilityList.get(i));
					}else {
						curriculumMatrixs = curriculumMatrixDao.findbycuandab_pro(applicationSyllabus.getCurriculum(),abilityList.get(i),applicationSyllabus.getProfessional());
					}
					
				}
				else
				{
					curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus.getCurriculum(),abilityList.get(i));
				}
				
				if(curriculumMatrixs != null && curriculumMatrixs.size() != 0)
				{
					List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
					List<IndexSelect> indexSelectList = new ArrayList<IndexSelect>();
					for(int j=0;j<curriculumMatrixs.size();j++)
					{
						IndexSelect indexSelect = new IndexSelect();
						indexSelect.setAnalisis(analysis.get((curriculumMatrixs.get(j).getCount()-1)));
						indexSelectList.add(indexSelect);
					}
					abilitySelectList.add(abilityList.get(i));
					analysisSelectList.add(indexSelectList);
					curriculumMatrixs.clear();
				}
				
			}
			
		}
		else
		{
			PracticeLesson newPracticeLesson = practicePlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newPracticeLesson);

			for(int i=0;i<abilityList.size();i++)
			{
				
				//专业选修课多加入专业方向查询
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					if(applicationSyllabus.getProfessional() == null){
						curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus.getCurriculum(),abilityList.get(i));
					}else {
						curriculumMatrixs = curriculumMatrixDao.findbycuandab_pro(applicationSyllabus.getCurriculum(),abilityList.get(i),applicationSyllabus.getProfessional());
					}
					
				}
				else
				{
					curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus.getCurriculum(),abilityList.get(i));
				}
				
				if(curriculumMatrixs != null && curriculumMatrixs.size() != 0)
				{
					List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
					List<IndexSelect> indexSelectList = new ArrayList<IndexSelect>();
					for(int j=0;j<curriculumMatrixs.size();j++)
					{
						IndexSelect indexSelect = new IndexSelect();
						indexSelect.setAnalisis(analysis.get((curriculumMatrixs.get(j).getCount()-1)));
						indexSelectList.add(indexSelect);
					}
					abilitySelectList.add(abilityList.get(i));
					analysisSelectList.add(indexSelectList);
					curriculumMatrixs.clear();
				}
				
			}
		}
		
		
		if(analysisSelectList == null || analysisSelectList.size() ==0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该课程没有填写专业课程矩阵");
			return "noIntoAim";
		}
		//}
		ServletActionContext.getRequest().setAttribute("abilitySelectList", abilitySelectList);//选择的毕业要求
		ServletActionContext.getRequest().setAttribute("analysisSelectList", analysisSelectList);//选择的指标分解
		ServletActionContext.getRequest().setAttribute("abilityList", abilityList);//全部毕业要求
		ServletActionContext.getRequest().setAttribute("analysisList", analysisList);//全部指标点
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		

		return "toBefourAimTheoPage";
	}

	public void toAimTheoPage(String theoreticalLessonId,String syllabusId, String curriculumId) {//跳转到毕业要求与教学目标对应页面
		ApplicationSyllabus applicationSyllabus = abilityAndTeachObjDao.findAppSyllabus(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus.getDepartment());	//查询对应专业全部毕业要求
		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<Analysis>> analylist = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){
			List<Analysis> analysilist = analysisDao.getbyability(abilityList.get(i));
			analylist.add(analysilist);
		}
		List<List<IndexSelect>> analysisSelectList = new ArrayList<List<IndexSelect>>();//选择的毕业要求所选择的指标点
		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect> indexSelect = abilityAndTeachObjDao.findIndexSelectByAbility(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect != null && indexSelect.size() != 0){
				List<IndexSelect> indexSelectList = new ArrayList<IndexSelect>();
				abilitySelectList.add(indexSelect.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect.size();j++){
					indexSelectList.add(indexSelect.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelectList.add(indexSelectList);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		
		String json = "";
		for(int i=0;i<abilitySelectList.size();i++){
				json = json +"{id:";
				int k=0;
				for(int j=0;j<abilityList.size();j++){
					if(abilitySelectList.get(i)==abilityList.get(j)){
						k=j+1;
					}
				}
				List<AbilityAndTeachObj> newAbilityAndTeachObjList = abilityAndTeachObjDao.findTeachByAbility(abilitySelectList.get(i),syllabusId);
				
				json = json + abilitySelectList.get(i).getAbilityid() +",description:\"" + k + "、";
				json = json + abilitySelectList.get(i).getAbilitycontent() +"\",points:[";
				int z = 0;
				for(int x=0;x<analysisSelectList.get(i).size();x++){
					for(int y=0;y<analylist.get(k-1).size();y++){
						if(analysisSelectList.get(i).get(x).getAnalisis()==analylist.get(k-1).get(y)){
							//if(x!=analysisSelectList.get(i).size()-1){
								z=y+1;
								json = json +"\""+ k + "-" + z + "、" + analysisSelectList.get(i).get(x).getAnalisis().getAnalysiscontent() + "\",";
							/*}else{
								z=y+1;
								json = json +"\""+ k + "-" + z + "、" + analysisSelectList.get(i).get(x).getAnalisis().getAnalysiscontent() + "\"],goals:{}},";
							}*/
						}
					}
				}	
				json = json + "]";
				json = json + ",goals:{";
				if(newAbilityAndTeachObjList != null)
				for(int x = 0; x < newAbilityAndTeachObjList.size(); x++) {
					json = json + "\"" + newAbilityAndTeachObjList.get(x).getTeachObj().getTeachObjid() + "\":true,";
				}
				json = json + "}},";

		}
		
		
		ServletActionContext.getRequest().setAttribute("json", json);//全部指标点
		String TeachObjJson = teachObjDao.findAllByCDP(syllabusId);
		ServletActionContext.getRequest().setAttribute("TeachObjJson", TeachObjJson);//教学目标
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);//大纲Id
		
		Curriculum newCurriculum = curriculumDao.get(curriculumId);
		if(newCurriculum.getCourseLei().equals("理论课"))
		{
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);

		}
		else
		{
			PracticeLesson newPracticeLesson = practicePlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newPracticeLesson);

		}
	}

	public void addAbilityAndTeachObj(String syllabusId, String data) {//添加毕业要求与教学目标对应关系
		try {
//			ApplicationSyllabus applicationSyllabus = abilityAndTeachObjDao.findAppSyllabus(Integer.valueOf(syllabusId));//通过大纲ID查询记录
			
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<AbilityAndTeachObj> abilityAndTeachObjSelect = new ArrayList<AbilityAndTeachObj>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("goals");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								AbilityAndTeachObj newabilityAndTeachObj = new AbilityAndTeachObj();
								TeachObj teachObj = teachObjDao.get(Integer.valueOf(proArr.get(j).toString()));
								newabilityAndTeachObj.setTeachObj(teachObj);
								newabilityAndTeachObj.setAbility(ability);
								newabilityAndTeachObj.setSyllabusID(syllabusId);
								abilityAndTeachObjSelect.add(newabilityAndTeachObj);
							}
						}
						}	
				abilityAndTeachObjDao.addAbilityAndTeachObj(abilityAndTeachObjSelect,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}

	public void updateSelectIndex(String syllabusId, String data) {
		try {
//			ApplicationSyllabus applicationSyllabus = abilityAndTeachObjDao.findAppSyllabus(Integer.valueOf(syllabusId));//通过大纲ID查询记录			
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<IndexSelect> newindexSelectList = new ArrayList<IndexSelect>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("items");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								IndexSelect indexSelect = new IndexSelect();
								Analysis analysis = analysisDao.get(Integer.valueOf(proArr.get(j).toString()));
								indexSelect.setAbility(ability);
								indexSelect.setAnalisis(analysis);
								indexSelect.setSyllabusID(syllabusId);
								newindexSelectList.add(indexSelect);
							}
						}
						}			
				abilityAndTeachObjDao.addSelectIndex(newindexSelectList,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}
	//------------------理论课——课内实验--------------------------
	//跳转到选择指标点页面
	public String toBefourAimTheoPageInnerExperiment(String theoreticalLessonId, String syllabusId) {
		// TODO Auto-generated method stub
		ApplicationSyllabus_TheoInnerExperiment applicationSyllabus_TheoInnerExperiment = abilityAndTeachObj_TheoInnerExperimentDao.findAppSyllabus_TheoInnerExperiment(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_TheoInnerExperiment.getDepartment());	//查询毕业要求
		
		if(abilityList == null || abilityList.size() == 0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写毕业生应获得的知识和能力！");
			return "noIntoAim";
		}
		else
		{
			for(int i=0;i<abilityList.size();i++){
				if(abilityList.get(i).getAbilityname() == null)
				{
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整毕业生应获得的知识和能力！");
					return "noIntoAim";
				}
			}	
		}
		List<List<Analysis>> analysisList = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){	//嵌套List存储每个毕业要求对应的指标点
			List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
			analysisList.add(analysis);
		}
		if(analysisList == null || analysisList.size() == 0){
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写指标分解！");
			return "noIntoAim";
		}else{
			for(int i=0;i<analysisList.size();i++){
				if(analysisList.get(i) == null || analysisList.get(i).size() == 0){
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整指标分解！");
					return "noIntoAim";
				}
			}
		}

		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<IndexSelect_TheoInnerExperiment>> analysisSelect_TheoInnerExperimentList = new ArrayList<List<IndexSelect_TheoInnerExperiment>>();//选择的毕业要求所选择的指标点
/*		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_TheoInnerExperiment> indexSelect_TheoInnerExperimentList = abilityAndTeachObj_TheoInnerExperimentDao.findIndexSelect_TheoInnerExperimentByAbility(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_TheoInnerExperimentList != null && indexSelect_TheoInnerExperimentList.size() != 0){
				List<IndexSelect_TheoInnerExperiment> indexSelectList_TheoInnerExperiment = new ArrayList<IndexSelect_TheoInnerExperiment>();
				abilitySelectList.add(indexSelect_TheoInnerExperimentList.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_TheoInnerExperimentList.size();j++){
					indexSelectList_TheoInnerExperiment.add(indexSelect_TheoInnerExperimentList.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_TheoInnerExperimentList.add(indexSelectList_TheoInnerExperiment);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		
		if(abilitySelectList == null || abilitySelectList.size() == 0)
		{*/
		TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
		List<CurriculumMatrix> curriculumMatrixs = new ArrayList<CurriculumMatrix>();
		for(int i=0;i<abilityList.size();i++)
		{
			//专业选修课多加入专业方向查询
			if(newtheoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
			{
				if(applicationSyllabus_TheoInnerExperiment.getProfessional() ==  null){
					curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_TheoInnerExperiment.getCurriculum(),abilityList.get(i));
				}else{
					curriculumMatrixs = curriculumMatrixDao.findbycuandab_pro(applicationSyllabus_TheoInnerExperiment.getCurriculum(),abilityList.get(i),applicationSyllabus_TheoInnerExperiment.getProfessional());
				}
				
			}
			else
			{
				curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_TheoInnerExperiment.getCurriculum(),abilityList.get(i));
			}
			
			if(curriculumMatrixs != null && curriculumMatrixs.size() != 0)
			{
				List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
				List<IndexSelect_TheoInnerExperiment> indexSelectList = new ArrayList<IndexSelect_TheoInnerExperiment>();
				for(int j=0;j<curriculumMatrixs.size();j++)
				{
					IndexSelect_TheoInnerExperiment indexSelect = new IndexSelect_TheoInnerExperiment();
					indexSelect.setAnalisis(analysis.get((curriculumMatrixs.get(j).getCount()-1)));
					indexSelectList.add(indexSelect);
				}
				abilitySelectList.add(abilityList.get(i));
				analysisSelect_TheoInnerExperimentList.add(indexSelectList);
				curriculumMatrixs.clear();
			}
		}
		if(analysisSelect_TheoInnerExperimentList == null || analysisSelect_TheoInnerExperimentList.size() ==0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该课程没有填写专业课程矩阵");
			return "noIntoAim";
		}
//		}
		
		ServletActionContext.getRequest().setAttribute("abilitySelectList", abilitySelectList);//选择的毕业要求
		ServletActionContext.getRequest().setAttribute("analysisSelectList", analysisSelect_TheoInnerExperimentList);//选择的指标分解
		ServletActionContext.getRequest().setAttribute("abilityList", abilityList);//全部毕业要求
		ServletActionContext.getRequest().setAttribute("analysisList", analysisList);//全部指标点
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);
		return "toBefourAimTheoPageInnerExperiment";
	}
	//存储选择的指标点
	public void updateSelectIndexTheoInnerExperiment(String syllabusId,String data) {
		// TODO Auto-generated method stub
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<IndexSelect_TheoInnerExperiment> newindexSelect_TheoInnerExperimentList = new ArrayList<IndexSelect_TheoInnerExperiment>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("items");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								IndexSelect_TheoInnerExperiment indexSelect_TheoInnerExperiment = new IndexSelect_TheoInnerExperiment();
								Analysis analysis = analysisDao.get(Integer.valueOf(proArr.get(j).toString()));
								indexSelect_TheoInnerExperiment.setAbility(ability);
								indexSelect_TheoInnerExperiment.setAnalisis(analysis);
								indexSelect_TheoInnerExperiment.setSyllabusid(syllabusId);
								newindexSelect_TheoInnerExperimentList.add(indexSelect_TheoInnerExperiment);
							}
						}
						}			
				abilityAndTeachObj_TheoInnerExperimentDao.addSelectIndex_TheoInnerExperiment(newindexSelect_TheoInnerExperimentList,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}
	//跳转到毕业要求与教学目标对应页面
	public void toAimTheoPageInnerExperiment(String theoreticalLessonId,String syllabusId) {
		// TODO Auto-generated method stub
		ApplicationSyllabus_TheoInnerExperiment applicationSyllabus_TheoInnerExperiment = abilityAndTeachObj_TheoInnerExperimentDao.findAppSyllabus_TheoInnerExperiment(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_TheoInnerExperiment.getDepartment());	//查询对应专业全部毕业要求
		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<Analysis>> analylist = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){
			List<Analysis> analysilist = analysisDao.getbyability(abilityList.get(i));
			analylist.add(analysilist);
		}
		List<List<IndexSelect_TheoInnerExperiment>> analysisSelect_TheoInnerExperiment = new ArrayList<List<IndexSelect_TheoInnerExperiment>>();//选择的毕业要求所选择的指标点
		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_TheoInnerExperiment> indexSelect_TheoInnerExperiment = abilityAndTeachObj_TheoInnerExperimentDao.findIndexSelect_TheoInnerExperimentByAbility(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_TheoInnerExperiment != null && indexSelect_TheoInnerExperiment.size() != 0){
				List<IndexSelect_TheoInnerExperiment> indexSelect_TheoInnerExperimentList = new ArrayList<IndexSelect_TheoInnerExperiment>();
				abilitySelectList.add(indexSelect_TheoInnerExperiment.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_TheoInnerExperiment.size();j++){
					indexSelect_TheoInnerExperimentList.add(indexSelect_TheoInnerExperiment.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_TheoInnerExperiment.add(indexSelect_TheoInnerExperimentList);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		String json = "";
		for(int i=0;i<abilitySelectList.size();i++){
				json = json +"{id:";
				int k=0;
				for(int j=0;j<abilityList.size();j++){
					if(abilitySelectList.get(i)==abilityList.get(j)){
						k=j+1;
					}
				}
				List<AbilityAndTeachObj_TheoInnerExperiment> newAbilityAndTeachObjList_TheoInnerExperiment = abilityAndTeachObj_TheoInnerExperimentDao.findTeachByAbility_TheoInnerExperiment(abilitySelectList.get(i),syllabusId);
				
				json = json + abilitySelectList.get(i).getAbilityid() +",description:\"" + k + "、";
				json = json + abilitySelectList.get(i).getAbilitycontent() +"\",points:[";
				int z = 0;
				for(int x=0;x<analysisSelect_TheoInnerExperiment.get(i).size();x++){
					for(int y=0;y<analylist.get(k-1).size();y++){
						//if(x!=analysisSelect_TheoInnerExperiment.get(i).size()-1){
						if(analysisSelect_TheoInnerExperiment.get(i).get(x).getAnalisis()==analylist.get(k-1).get(y)){
							z=y+1;
							json = json +"\""+ k + "-" + z + "、" + analysisSelect_TheoInnerExperiment.get(i).get(x).getAnalisis().getAnalysiscontent() + "\",";
						}
					}
				}
				
				
				json = json + "]";
				json = json + ",goals:{";
				if(newAbilityAndTeachObjList_TheoInnerExperiment != null)
				for(int x = 0; x < newAbilityAndTeachObjList_TheoInnerExperiment.size(); x++) {
					json = json + "\"" + newAbilityAndTeachObjList_TheoInnerExperiment.get(x).getTeachObj_TheoInnerExperiment().getTeachObj_TheoInnerExperimentid() + "\":true,";
				}
				json = json + "}},";
			
		}
		ServletActionContext.getRequest().setAttribute("json", json);//全部指标点
		String TeachObjJson = teachObj_TheoInnerExperimentDao.findAllByCDP_TheoInnerExperimentList(syllabusId);
		ServletActionContext.getRequest().setAttribute("TeachObjJson", TeachObjJson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
		ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);
	}
	//添加毕业要求与教学目标对应关系
	public void addAbilityAndTeachObjTheoInnerExperiment(String syllabusId,
			String data) {
		// TODO Auto-generated method stub
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<AbilityAndTeachObj_TheoInnerExperiment> abilityAndTeachObjSelect_TheoInnerExperiment = new ArrayList<AbilityAndTeachObj_TheoInnerExperiment>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("goals");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								AbilityAndTeachObj_TheoInnerExperiment newabilityAndTeachObj_TheoInnerExperiment = new AbilityAndTeachObj_TheoInnerExperiment();
								TeachObj_TheoInnerExperiment teachObj_TheoInnerExperiment = teachObj_TheoInnerExperimentDao.get(Integer.valueOf(proArr.get(j).toString()));
								newabilityAndTeachObj_TheoInnerExperiment.setTeachObj_TheoInnerExperiment(teachObj_TheoInnerExperiment);
								newabilityAndTeachObj_TheoInnerExperiment.setAbility(ability);
								newabilityAndTeachObj_TheoInnerExperiment.setSyllabusid(syllabusId);
								abilityAndTeachObjSelect_TheoInnerExperiment.add(newabilityAndTeachObj_TheoInnerExperiment);
							}
						}
						}	
				abilityAndTeachObj_TheoInnerExperimentDao.addAbilityAndTeachObj_TheoInnerExperiment(abilityAndTeachObjSelect_TheoInnerExperiment,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}
	
	
	//------------------实践课——实习--------------------------
	//跳转到选择指标点页面
	public String toBefourAimFieldWorkPage(String practiceLessonid,String syllabusId) {
		ApplicationSyllabus_FieldWork applicationSyllabus_FieldWork = abilityAndTeachObj_FieldWorkDao.findAppSyllabus_FieldWork(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_FieldWork.getDepartment());	//查询毕业要求
		
		if(abilityList == null || abilityList.size() == 0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写毕业生应获得的知识和能力！");
			return "noIntoAim";
		}
		else
		{
			for(int i=0;i<abilityList.size();i++){
				if(abilityList.get(i).getAbilityname() == null)
				{
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整毕业生应获得的知识和能力！");
					return "noIntoAim";
				}
			}	
		}
		List<List<Analysis>> analysisList = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){	//嵌套List存储每个毕业要求对应的指标点
			List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
			analysisList.add(analysis);
		}
		if(analysisList == null || analysisList.size() == 0){
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写指标分解！");
			return "noIntoAim";
		}else{
			for(int i=0;i<analysisList.size();i++){
				if(analysisList.get(i) == null || analysisList.get(i).size() == 0){
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整指标分解！");
					return "noIntoAim";
				}
			}
		}

		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<IndexSelect_FieldWork>> analysisSelect_FieldWorkList = new ArrayList<List<IndexSelect_FieldWork>>();//选择的毕业要求所选择的指标点
/*		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_FieldWork> indexSelect_FieldWork = abilityAndTeachObj_FieldWorkDao.findIndexSelectByAbility_FieldWork(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_FieldWork != null && indexSelect_FieldWork.size() != 0){
				List<IndexSelect_FieldWork> indexSelectList_FieldWork = new ArrayList<IndexSelect_FieldWork>();
				abilitySelectList.add(indexSelect_FieldWork.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_FieldWork.size();j++){
					indexSelectList_FieldWork.add(indexSelect_FieldWork.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_FieldWorkList.add(indexSelect_FieldWork);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		
		if(abilitySelectList == null || abilitySelectList.size() == 0)
		{*/
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		List<CurriculumMatrix> curriculumMatrixs = new ArrayList<CurriculumMatrix>();
			for(int i=0;i<abilityList.size();i++)
			{
				//专业选修课多加入专业方向查询
				if(newpracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					if(applicationSyllabus_FieldWork.getProfessional() == null){
						curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_FieldWork.getCurriculum(),abilityList.get(i));
					}else{
						curriculumMatrixs = curriculumMatrixDao.findbycuandab_pro(applicationSyllabus_FieldWork.getCurriculum(),abilityList.get(i),applicationSyllabus_FieldWork.getProfessional());
					}
					
				}
				else
				{
					curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_FieldWork.getCurriculum(),abilityList.get(i));
				}
				
				if(curriculumMatrixs != null && curriculumMatrixs.size() != 0)
				{
					List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
					List<IndexSelect_FieldWork> indexSelectList = new ArrayList<IndexSelect_FieldWork>();
					for(int j=0;j<curriculumMatrixs.size();j++)
					{
						IndexSelect_FieldWork indexSelect = new IndexSelect_FieldWork();
						indexSelect.setAnalisis(analysis.get((curriculumMatrixs.get(j).getCount()-1)));
						indexSelectList.add(indexSelect);
					}
					abilitySelectList.add(abilityList.get(i));
					analysisSelect_FieldWorkList.add(indexSelectList);
					curriculumMatrixs.clear();
				}
			}
			if(analysisSelect_FieldWorkList == null || analysisSelect_FieldWorkList.size() ==0)
			{
				ServletActionContext.getRequest().setAttribute("msg", "该课程没有填写专业课程矩阵");
				return "noIntoAim";
			}
//		}
		
		ServletActionContext.getRequest().setAttribute("abilitySelectList", abilitySelectList);//选择的毕业要求
		ServletActionContext.getRequest().setAttribute("analysisSelectList", analysisSelect_FieldWorkList);//选择的指标分解
		ServletActionContext.getRequest().setAttribute("abilityList", abilityList);//全部毕业要求
		ServletActionContext.getRequest().setAttribute("analysisList", analysisList);//全部指标点
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		return "toBefourAimFieldWorkPage";
	}

	//存储选择的指标点
	public void updateSelectIndexFieldWork(String syllabusId, String data) {
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<IndexSelect_FieldWork> newindexSelect_FieldWorkList = new ArrayList<IndexSelect_FieldWork>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("items");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								IndexSelect_FieldWork indexSelect_FieldWork = new IndexSelect_FieldWork();
								Analysis analysis = analysisDao.get(Integer.valueOf(proArr.get(j).toString()));
								indexSelect_FieldWork.setAbility(ability);
								indexSelect_FieldWork.setAnalisis(analysis);
								indexSelect_FieldWork.setSyllabus_FieldWorkid(syllabusId);
								newindexSelect_FieldWorkList.add(indexSelect_FieldWork);
							}
						}
						}			
				abilityAndTeachObj_FieldWorkDao.addSelectIndex_FieldWork(newindexSelect_FieldWorkList,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}

	//跳转到毕业要求与教学目标对应页面
	public void toAimFieldWorkPage(String practiceLessonid, String syllabusId) {
		ApplicationSyllabus_FieldWork applicationSyllabus_FieldWork = abilityAndTeachObj_FieldWorkDao.findAppSyllabus_FieldWork(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_FieldWork.getDepartment());	//查询对应专业全部毕业要求
		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<Analysis>> analylist = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){
			List<Analysis> analysilist = analysisDao.getbyability(abilityList.get(i));
			analylist.add(analysilist);
		}
		List<List<IndexSelect_FieldWork>> analysisSelect_FieldWorkList = new ArrayList<List<IndexSelect_FieldWork>>();//选择的毕业要求所选择的指标点
		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_FieldWork> indexSelect_FieldWork = abilityAndTeachObj_FieldWorkDao.findIndexSelectByAbility_FieldWork(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_FieldWork != null && indexSelect_FieldWork.size() != 0){
				List<IndexSelect_FieldWork> indexSelect_FieldWorkList = new ArrayList<IndexSelect_FieldWork>();
				abilitySelectList.add(indexSelect_FieldWork.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_FieldWork.size();j++){
					indexSelect_FieldWorkList.add(indexSelect_FieldWork.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_FieldWorkList.add(indexSelect_FieldWorkList);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		String json = "";
		for(int i=0;i<abilitySelectList.size();i++){
				json = json +"{id:";
				int k=0;
				for(int j=0;j<abilityList.size();j++){
					if(abilitySelectList.get(i)==abilityList.get(j)){
						k=j+1;
					}
				}
				List<AbilityAndTeachObj_FieldWork> newAbilityAndTeachObjList_FieldWork = abilityAndTeachObj_FieldWorkDao.findTeachByAbility_FieldWork(abilitySelectList.get(i),syllabusId);
				
				json = json + abilitySelectList.get(i).getAbilityid() +",description:\"" + k + "、";
				json = json + abilitySelectList.get(i).getAbilitycontent() +"\",points:[";
				int z = 0;
				for(int x=0;x<analysisSelect_FieldWorkList.get(i).size();x++){
					for(int y=0;y<analylist.get(k-1).size();y++){
						if(analysisSelect_FieldWorkList.get(i).get(x).getAnalisis()==analylist.get(k-1).get(y)){
								z=y+1;
								json = json +"\""+ k + "-" + z + "、" + analysisSelect_FieldWorkList.get(i).get(x).getAnalisis().getAnalysiscontent() + "\",";
						}
					}
				}	
				json = json + "],goals:{";
				if(newAbilityAndTeachObjList_FieldWork != null)
				for(int x = 0; x < newAbilityAndTeachObjList_FieldWork.size(); x++) {
					json = json + "\"" + newAbilityAndTeachObjList_FieldWork.get(x).getTeachObj_FieldWork().getTeachObj_FieldWorkid() + "\":true,";
				}
				json = json + "}},";
		}
		ServletActionContext.getRequest().setAttribute("json", json);//全部指标点
		String TeachObj_FieldWorkJson = teachObj_FieldWorkDao.findAllByCDP_FieldWork(syllabusId);
		ServletActionContext.getRequest().setAttribute("TeachObjJson", TeachObj_FieldWorkJson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		}

	//添加毕业要求与教学目标对应关系
	public void addAbilityAndTeachObjFieldWork(String syllabusId, String data) {
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<AbilityAndTeachObj_FieldWork> abilityAndTeachObjSelect_FieldWork = new ArrayList<AbilityAndTeachObj_FieldWork>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("goals");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								AbilityAndTeachObj_FieldWork newabilityAndTeachObj_FieldWork = new AbilityAndTeachObj_FieldWork();
								TeachObj_FieldWork teachObj_FieldWork = teachObj_FieldWorkDao.get(Integer.valueOf(proArr.get(j).toString()));
								newabilityAndTeachObj_FieldWork.setTeachObj_FieldWork(teachObj_FieldWork);
								newabilityAndTeachObj_FieldWork.setAbility(ability);
								newabilityAndTeachObj_FieldWork.setSyllabus_FieldWorkid(syllabusId);
								abilityAndTeachObjSelect_FieldWork.add(newabilityAndTeachObj_FieldWork);
							}
						}
						}	
				abilityAndTeachObj_FieldWorkDao.addAbilityAndTeachObj_FieldWork(abilityAndTeachObjSelect_FieldWork,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}

	//------------------实践课——课程设计（学年论文）--------------------------
	//跳转到选择指标点页面
	public String toBefourAimCourseDesignPage(String practiceLessonid,String syllabusId) {
		ApplicationSyllabus_CourseDesign applicationSyllabus_CourseDesign = abilityAndTeachObj_CourseDesignDao.findAppSyllabus_CourseDesign(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_CourseDesign.getDepartment());	//查询毕业要求
		
		if(abilityList == null || abilityList.size() == 0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写毕业生应获得的知识和能力！");
			return "noIntoAim";
		}
		else
		{
			for(int i=0;i<abilityList.size();i++){
				if(abilityList.get(i).getAbilityname() == null)
				{
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整毕业生应获得的知识和能力！");
					return "noIntoAim";
				}
			}	
		}
		List<List<Analysis>> analysisList = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){	//嵌套List存储每个毕业要求对应的指标点
			List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
			analysisList.add(analysis);
		}
		if(analysisList == null || analysisList.size() == 0){
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写指标分解！");
			return "noIntoAim";
		}else{
			for(int i=0;i<analysisList.size();i++){
				if(analysisList.get(i) == null || analysisList.get(i).size() == 0){
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整指标分解！");
					return "noIntoAim";
				}
			}
		}

		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<IndexSelect_CourseDesign>> analysisSelect_CourseDesignList = new ArrayList<List<IndexSelect_CourseDesign>>();//选择的毕业要求所选择的指标点
/*		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_CourseDesign> indexSelect_CourseDesign = abilityAndTeachObj_CourseDesignDao.findIndexSelectByAbility_CourseDesign(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_CourseDesign != null && indexSelect_CourseDesign.size() != 0){
				List<IndexSelect_CourseDesign> indexSelectList_FieldWork = new ArrayList<IndexSelect_CourseDesign>();
				abilitySelectList.add(indexSelect_CourseDesign.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_CourseDesign.size();j++){
					indexSelectList_FieldWork.add(indexSelect_CourseDesign.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_CourseDesignList.add(indexSelect_CourseDesign);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		
		if(abilitySelectList == null || abilitySelectList.size() == 0)
		{*/
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		List<CurriculumMatrix> curriculumMatrixs = new ArrayList<CurriculumMatrix>();
		for(int i=0;i<abilityList.size();i++)
		{
			//专业选修课多加入专业方向查询
			if(newpracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
			{
				if(applicationSyllabus_CourseDesign.getProfessional() == null){
					curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_CourseDesign.getCurriculum(),abilityList.get(i));
				}else{
					curriculumMatrixs = curriculumMatrixDao.findbycuandab_pro(applicationSyllabus_CourseDesign.getCurriculum(),abilityList.get(i),applicationSyllabus_CourseDesign.getProfessional());
				}
				
			}
			else
			{
				curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_CourseDesign.getCurriculum(),abilityList.get(i));
			}
			if(curriculumMatrixs != null && curriculumMatrixs.size() != 0)
			{
				List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
				List<IndexSelect_CourseDesign> indexSelectList = new ArrayList<IndexSelect_CourseDesign>();
				for(int j=0;j<curriculumMatrixs.size();j++)
				{
					IndexSelect_CourseDesign indexSelect = new IndexSelect_CourseDesign();
					indexSelect.setAnalisis(analysis.get((curriculumMatrixs.get(j).getCount()-1)));
					indexSelectList.add(indexSelect);
				}
				abilitySelectList.add(abilityList.get(i));
				analysisSelect_CourseDesignList.add(indexSelectList);
				curriculumMatrixs.clear();
			}
		}
		if(analysisSelect_CourseDesignList == null || analysisSelect_CourseDesignList.size() ==0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该课程没有填写专业课程矩阵");
			return "noIntoAim";
		}
//		}
		
		ServletActionContext.getRequest().setAttribute("abilitySelectList", abilitySelectList);//选择的毕业要求
		ServletActionContext.getRequest().setAttribute("analysisSelectList", analysisSelect_CourseDesignList);//选择的指标分解
		ServletActionContext.getRequest().setAttribute("abilityList", abilityList);//全部毕业要求
		ServletActionContext.getRequest().setAttribute("analysisList", analysisList);//全部指标点
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		return "toBefourAimCourseDesignPage";
	}
	//存储选择的指标点
	public void updateSelectIndexCourseDesign(String syllabusId, String data) {
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<IndexSelect_CourseDesign> newindexSelect_CourseDesignList = new ArrayList<IndexSelect_CourseDesign>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("items");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								IndexSelect_CourseDesign indexSelect_CourseDesign = new IndexSelect_CourseDesign();
								Analysis analysis = analysisDao.get(Integer.valueOf(proArr.get(j).toString()));
								indexSelect_CourseDesign.setAbility(ability);
								indexSelect_CourseDesign.setAnalisis(analysis);
								indexSelect_CourseDesign.setSyllabus_CourseDesignid(syllabusId);
								newindexSelect_CourseDesignList.add(indexSelect_CourseDesign);
							}
						}
						}			
				//
				abilityAndTeachObj_CourseDesignDao.addSelectIndex_CourseDesign(newindexSelect_CourseDesignList,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}
	//跳转到毕业要求与教学目标对应页面
	public void toAimCourseDesignPage(String practiceLessonid, String syllabusId) {
		ApplicationSyllabus_CourseDesign applicationSyllabus_CourseDesign = abilityAndTeachObj_CourseDesignDao.findAppSyllabus_CourseDesign(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_CourseDesign.getDepartment());	//查询对应专业全部毕业要求
		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<Analysis>> analylist = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){
			List<Analysis> analysilist = analysisDao.getbyability(abilityList.get(i));
			analylist.add(analysilist);
		}
		List<List<IndexSelect_CourseDesign>> analysisSelect_CourseDesignList = new ArrayList<List<IndexSelect_CourseDesign>>();//选择的毕业要求所选择的指标点
		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_CourseDesign> indexSelect_CourseDesign = abilityAndTeachObj_CourseDesignDao.findIndexSelectByAbility_CourseDesign(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_CourseDesign != null && indexSelect_CourseDesign.size() != 0){
				List<IndexSelect_CourseDesign> indexSelect_CourseDesignList = new ArrayList<IndexSelect_CourseDesign>();
				abilitySelectList.add(indexSelect_CourseDesign.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_CourseDesign.size();j++){
					indexSelect_CourseDesignList.add(indexSelect_CourseDesign.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_CourseDesignList.add(indexSelect_CourseDesignList);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		String json = "";
		for(int i=0;i<abilitySelectList.size();i++){
				json = json +"{id:";
				int k=0;
				for(int j=0;j<abilityList.size();j++){
					if(abilitySelectList.get(i)==abilityList.get(j)){
						k=j+1;
					}
				}
				List<AbilityAndTeachObj_CourseDesign> newAbilityAndTeachObjList_CourseDesign = abilityAndTeachObj_CourseDesignDao.findTeachByAbility_CourseDesign(abilitySelectList.get(i),syllabusId);
				
				json = json + abilitySelectList.get(i).getAbilityid() +",description:\"" + k + "、";
				json = json + abilitySelectList.get(i).getAbilitycontent() +"\",points:[";
				int z = 0;
				for(int x=0;x<analysisSelect_CourseDesignList.get(i).size();x++){
					for(int y=0;y<analylist.get(k-1).size();y++){
						if(analysisSelect_CourseDesignList.get(i).get(x).getAnalisis()==analylist.get(k-1).get(y)){
							z=y+1;
							json = json +"\""+ k + "-" + z + "、" + analysisSelect_CourseDesignList.get(i).get(x).getAnalisis().getAnalysiscontent() + "\",";
						}
					}
				}	
				json = json + "],goals:{";
				if(newAbilityAndTeachObjList_CourseDesign != null)
				for(int x = 0; x < newAbilityAndTeachObjList_CourseDesign.size(); x++) {
					json = json + "\"" + newAbilityAndTeachObjList_CourseDesign.get(x).getTeachObj_CourseDesign().getTeachObj_CourseDesignid() + "\":true,";
				}
				json = json + "}},";
		}
		ServletActionContext.getRequest().setAttribute("json", json);//全部指标点
		//
		String TeachObj_CourseDesignJson = teachObj_CourseDesignDao.findAllByCDP_CourseDesign(syllabusId);
		ServletActionContext.getRequest().setAttribute("TeachObjJson", TeachObj_CourseDesignJson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		
	}
	//添加毕业要求与教学目标对应关系
	public void addAbilityAndTeachObjCourseDesign(String syllabusId, String data) {
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<AbilityAndTeachObj_CourseDesign> abilityAndTeachObjSelect_CourseDesign = new ArrayList<AbilityAndTeachObj_CourseDesign>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("goals");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								AbilityAndTeachObj_CourseDesign newabilityAndTeachObj_CourseDesign = new AbilityAndTeachObj_CourseDesign();
								TeachObj_CourseDesign teachObj_CourseDesign = teachObj_CourseDesignDao.get(Integer.valueOf(proArr.get(j).toString()));
								newabilityAndTeachObj_CourseDesign.setTeachObj_CourseDesign(teachObj_CourseDesign);
								newabilityAndTeachObj_CourseDesign.setAbility(ability);
								newabilityAndTeachObj_CourseDesign.setSyllabus_CourseDesignid(syllabusId);
								abilityAndTeachObjSelect_CourseDesign.add(newabilityAndTeachObj_CourseDesign);
							}
						}
						}	
				abilityAndTeachObj_CourseDesignDao.addAbilityAndTeachObj_CourseDesign(abilityAndTeachObjSelect_CourseDesign,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
		
	}

	//------------------实践课——课内实验--------------------------
	//跳转到选择指标点页面
	public String toBefourAimInnerExperimentPage(String practiceLessonid,String syllabusId) {
		ApplicationSyllabus_InnerExperiment applicationSyllabus_InnerExperiment = abilityAndTeachObj_InnerExperimentDao.findAppSyllabus_InnerExperiment(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_InnerExperiment.getDepartment());	//查询毕业要求
		
		if(abilityList == null || abilityList.size() == 0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写毕业生应获得的知识和能力！");
			return "noIntoAim";
		}
		else
		{
			for(int i=0;i<abilityList.size();i++){
				if(abilityList.get(i).getAbilityname() == null)
				{
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整毕业生应获得的知识和能力！");
					return "noIntoAim";
				}
			}	
		}
		List<List<Analysis>> analysisList = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){	//嵌套List存储每个毕业要求对应的指标点
			List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
			analysisList.add(analysis);
		}
		if(analysisList == null || analysisList.size() == 0){
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写指标分解！");
			return "noIntoAim";
		}else{
			for(int i=0;i<analysisList.size();i++){
				if(analysisList.get(i) == null || analysisList.get(i).size() == 0){
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整指标分解！");
					return "noIntoAim";
				}
			}
		}

		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<IndexSelect_InnerExperiment>> analysisSelect_InnerExperimentList = new ArrayList<List<IndexSelect_InnerExperiment>>();//选择的毕业要求所选择的指标点
/*		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_InnerExperiment> indexSelect_InnerExperiment = abilityAndTeachObj_InnerExperimentDao.findIndexSelectByAbility_InnerExperiment(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_InnerExperiment != null && indexSelect_InnerExperiment.size() != 0){
				List<IndexSelect_InnerExperiment> indexSelectList_InnerExperiment = new ArrayList<IndexSelect_InnerExperiment>();
				abilitySelectList.add(indexSelect_InnerExperiment.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_InnerExperiment.size();j++){
					indexSelectList_InnerExperiment.add(indexSelect_InnerExperiment.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_InnerExperimentList.add(indexSelect_InnerExperiment);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		
		if(abilitySelectList == null || abilitySelectList.size() == 0)
		{*/
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		List<CurriculumMatrix> curriculumMatrixs = new ArrayList<CurriculumMatrix>();
		for(int i=0;i<abilityList.size();i++)
		{
			//专业选修课多加入专业方向查询
			if(newpracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
			{
				if(applicationSyllabus_InnerExperiment.getProfessional() == null){
					curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_InnerExperiment.getCurriculum(),abilityList.get(i));
				}else{
					curriculumMatrixs = curriculumMatrixDao.findbycuandab_pro(applicationSyllabus_InnerExperiment.getCurriculum(),abilityList.get(i),applicationSyllabus_InnerExperiment.getProfessional());
				}
				
			}
			else
			{
				curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_InnerExperiment.getCurriculum(),abilityList.get(i));
			}
			
			if(curriculumMatrixs != null && curriculumMatrixs.size() != 0)
			{
				List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
				List<IndexSelect_InnerExperiment> indexSelectList = new ArrayList<IndexSelect_InnerExperiment>();
				for(int j=0;j<curriculumMatrixs.size();j++)
				{
					IndexSelect_InnerExperiment indexSelect = new IndexSelect_InnerExperiment();
					indexSelect.setAnalisis(analysis.get((curriculumMatrixs.get(j).getCount()-1)));
					indexSelectList.add(indexSelect);
				}
				abilitySelectList.add(abilityList.get(i));
				analysisSelect_InnerExperimentList.add(indexSelectList);
				curriculumMatrixs.clear();
			}
		}
		if(analysisSelect_InnerExperimentList == null || analysisSelect_InnerExperimentList.size() ==0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该课程没有填写专业课程矩阵");
			return "noIntoAim";
		}
//		}
		
		ServletActionContext.getRequest().setAttribute("abilitySelectList", abilitySelectList);//选择的毕业要求
		ServletActionContext.getRequest().setAttribute("analysisSelectList", analysisSelect_InnerExperimentList);//选择的指标分解
		ServletActionContext.getRequest().setAttribute("abilityList", abilityList);//全部毕业要求
		ServletActionContext.getRequest().setAttribute("analysisList", analysisList);//全部指标点
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		return "toBefourAimInnerExperimentPage";
	}
	//存储选择的指标点
	public void updateSelectIndexInnerExperiment(String syllabusId, String data) {
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<IndexSelect_InnerExperiment> newindexSelect_InnerExperimentList = new ArrayList<IndexSelect_InnerExperiment>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("items");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								IndexSelect_InnerExperiment indexSelect_InnerExperiment = new IndexSelect_InnerExperiment();
								Analysis analysis = analysisDao.get(Integer.valueOf(proArr.get(j).toString()));
								indexSelect_InnerExperiment.setAbility(ability);
								indexSelect_InnerExperiment.setAnalisis(analysis);
								indexSelect_InnerExperiment.setSyllabus_InnerExperimentid(syllabusId);
								newindexSelect_InnerExperimentList.add(indexSelect_InnerExperiment);
							}
						}
						}			
				//
				abilityAndTeachObj_InnerExperimentDao.addSelectIndex_InnerExperiment(newindexSelect_InnerExperimentList,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}
	//跳转到毕业要求与教学目标对应页面
	public void toAimInnerExperimentPage(String practiceLessonid,String syllabusId) {
		ApplicationSyllabus_InnerExperiment applicationSyllabus_InnerExperiment = abilityAndTeachObj_InnerExperimentDao.findAppSyllabus_InnerExperiment(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_InnerExperiment.getDepartment());	//查询对应专业全部毕业要求
		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<Analysis>> analylist = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){
			List<Analysis> analysilist = analysisDao.getbyability(abilityList.get(i));
			analylist.add(analysilist);
		}
		List<List<IndexSelect_InnerExperiment>> analysisSelect_InnerExperimentList = new ArrayList<List<IndexSelect_InnerExperiment>>();//选择的毕业要求所选择的指标点
		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_InnerExperiment> indexSelect_InnerExperiment = abilityAndTeachObj_InnerExperimentDao.findIndexSelectByAbility_InnerExperiment(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_InnerExperiment != null && indexSelect_InnerExperiment.size() != 0){
				List<IndexSelect_InnerExperiment> indexSelect_InnerExperimentList = new ArrayList<IndexSelect_InnerExperiment>();
				abilitySelectList.add(indexSelect_InnerExperiment.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_InnerExperiment.size();j++){
					indexSelect_InnerExperimentList.add(indexSelect_InnerExperiment.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_InnerExperimentList.add(indexSelect_InnerExperimentList);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		String json = "";
		for(int i=0;i<abilitySelectList.size();i++){
				json = json +"{id:";
				int k=0;
				for(int j=0;j<abilityList.size();j++){
					if(abilitySelectList.get(i)==abilityList.get(j)){
						k=j+1;
					}
				}
				List<AbilityAndTeachObj_InnerExperiment> newAbilityAndTeachObjList_InnerExperiment = abilityAndTeachObj_InnerExperimentDao.findTeachByAbility_InnerExperiment(abilitySelectList.get(i),syllabusId);
				
				json = json + abilitySelectList.get(i).getAbilityid() +",description:\"" + k + "、";
				json = json + abilitySelectList.get(i).getAbilitycontent() +"\",points:[";
				int z = 0;
				for(int x=0;x<analysisSelect_InnerExperimentList.get(i).size();x++){
					for(int y=0;y<analylist.get(k-1).size();y++){
							if(x!=analysisSelect_InnerExperimentList.get(i).size()-1){
								z=y+1;
								json = json +"\""+ k + "-" + z + "、" + analysisSelect_InnerExperimentList.get(i).get(x).getAnalisis().getAnalysiscontent() + "\",";
						}
					}
				}	
				json = json + "],goals:{";
				if(newAbilityAndTeachObjList_InnerExperiment != null)
				for(int x = 0; x < newAbilityAndTeachObjList_InnerExperiment.size(); x++) {
					json = json + "\"" + newAbilityAndTeachObjList_InnerExperiment.get(x).getTeachObj_InnerExperiment().getTeachObj_InnerExperimentid() + "\":true,";
				}
				json = json + "}},";
		}
		ServletActionContext.getRequest().setAttribute("json", json);//全部指标点
		//
		String TeachObj_InnerExperimentJson = teachObj_InnerExperimentDao.findAllByCDP_InnerExperiment(syllabusId);
		ServletActionContext.getRequest().setAttribute("TeachObjJson", TeachObj_InnerExperimentJson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		
	}
	//添加毕业要求与教学目标对应关系
	public void addAbilityAndTeachObjInnerExperiment(String syllabusId,String data) {
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<AbilityAndTeachObj_InnerExperiment> abilityAndTeachObjSelect_InnerExperiment = new ArrayList<AbilityAndTeachObj_InnerExperiment>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("goals");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								AbilityAndTeachObj_InnerExperiment newabilityAndTeachObj_InnerExperiment = new AbilityAndTeachObj_InnerExperiment();
								TeachObj_InnerExperiment teachObj_InnerExperiment = teachObj_InnerExperimentDao.get(Integer.valueOf(proArr.get(j).toString()));
								newabilityAndTeachObj_InnerExperiment.setTeachObj_InnerExperiment(teachObj_InnerExperiment);
								newabilityAndTeachObj_InnerExperiment.setAbility(ability);
								newabilityAndTeachObj_InnerExperiment.setSyllabus_InnerExperimentid(syllabusId);
								abilityAndTeachObjSelect_InnerExperiment.add(newabilityAndTeachObj_InnerExperiment);
							}
						}
						}	
				abilityAndTeachObj_InnerExperimentDao.addAbilityAndTeachObj_InnerExperiment(abilityAndTeachObjSelect_InnerExperiment,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
	}

	//------------------实践课——毕业设计--------------------------
	//跳转到选择指标点页面
	public String toBefourAimGraduationProjectPage(String practiceLessonid,String syllabusId) {
		ApplicationSyllabus_Gra applicationSyllabus_Gra = abilityAndTeachObj_GraDao.findAppSyllabus_Gra(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_Gra.getDepartment());	//查询毕业要求
		
		if(abilityList == null || abilityList.size() == 0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写毕业生应获得的知识和能力！");
			return "noIntoAim";
		}
		else
		{
			for(int i=0;i<abilityList.size();i++){
				if(abilityList.get(i).getAbilityname() == null)
				{
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整毕业生应获得的知识和能力！");
					return "noIntoAim";
				}
			}	
		}
		List<List<Analysis>> analysisList = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){	//嵌套List存储每个毕业要求对应的指标点
			List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
			analysisList.add(analysis);
		}
		if(analysisList == null || analysisList.size() == 0){
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写指标分解！");
			return "noIntoAim";
		}else{
			for(int i=0;i<analysisList.size();i++){
				if(analysisList.get(i) == null || analysisList.get(i).size() == 0){
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整指标分解！");
					return "noIntoAim";
				}
			}
		}

		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<IndexSelect_Gra>> analysisSelect_GraList = new ArrayList<List<IndexSelect_Gra>>();//选择的毕业要求所选择的指标点
/*		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_Gra> indexSelect_Gra = abilityAndTeachObj_GraDao.findIndexSelectByAbility_Gra(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_Gra != null && indexSelect_Gra.size() != 0){
				List<IndexSelect_Gra> indexSelectList_Gra = new ArrayList<IndexSelect_Gra>();
				abilitySelectList.add(indexSelect_Gra.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_Gra.size();j++){
					indexSelectList_Gra.add(indexSelect_Gra.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_GraList.add(indexSelectList_Gra);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		if(abilitySelectList == null || abilitySelectList.size() == 0)
		{*/
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		List<CurriculumMatrix> curriculumMatrixs = new ArrayList<CurriculumMatrix>();
		for(int i=0;i<abilityList.size();i++)
		{
			//专业选修课多加入专业方向查询
			if(newpracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
			{
				if(applicationSyllabus_Gra.getProfessional() == null){
					curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_Gra.getCurriculum(),abilityList.get(i));
				}else{
					curriculumMatrixs = curriculumMatrixDao.findbycuandab_pro(applicationSyllabus_Gra.getCurriculum(),abilityList.get(i),applicationSyllabus_Gra.getProfessional());
				}
				
			}
			else
			{
				curriculumMatrixs = curriculumMatrixDao.findbycuandab(applicationSyllabus_Gra.getCurriculum(),abilityList.get(i));
			}
			
			if(curriculumMatrixs != null && curriculumMatrixs.size() != 0)
			{
				List<Analysis> analysis = analysisDao.getbyability(abilityList.get(i));
				List<IndexSelect_Gra> indexSelectList = new ArrayList<IndexSelect_Gra>();
				for(int j=0;j<curriculumMatrixs.size();j++)
				{
					IndexSelect_Gra indexSelect = new IndexSelect_Gra();
					indexSelect.setAnalisis(analysis.get((curriculumMatrixs.get(j).getCount()-1)));
					indexSelectList.add(indexSelect);
				}
				abilitySelectList.add(abilityList.get(i));
				analysisSelect_GraList.add(indexSelectList);
				curriculumMatrixs.clear();
			}
		}
		if(analysisSelect_GraList == null || analysisSelect_GraList.size() ==0)
		{
			ServletActionContext.getRequest().setAttribute("msg", "该课程没有填写专业课程矩阵");
			return "noIntoAim";
		}
//		}
		
		ServletActionContext.getRequest().setAttribute("abilitySelectList", abilitySelectList);//选择的毕业要求
		ServletActionContext.getRequest().setAttribute("analysisSelectList", analysisSelect_GraList);//选择的指标分解
		ServletActionContext.getRequest().setAttribute("abilityList", abilityList);//全部毕业要求
		ServletActionContext.getRequest().setAttribute("analysisList", analysisList);//全部指标点
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		return "toBefourAimGraduationProjectPage";
	}
	//存储选择的指标点
	public void updateSelectIndexGraduationProject(String syllabusId,String data) {
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<IndexSelect_Gra> newindexSelect_GraList = new ArrayList<IndexSelect_Gra>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("items");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								IndexSelect_Gra indexSelect_Gra = new IndexSelect_Gra();
								Analysis analysis = analysisDao.get(Integer.valueOf(proArr.get(j).toString()));
								indexSelect_Gra.setAbility(ability);
								indexSelect_Gra.setAnalisis(analysis);
								indexSelect_Gra.setSyllabus_Graid(syllabusId);
								newindexSelect_GraList.add(indexSelect_Gra);
							}
						}
						}			
				abilityAndTeachObj_GraDao.addSelectIndex_Gra(newindexSelect_GraList,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
		
		
	}
	//跳转到毕业要求与教学目标对应页面
	public void toAimGraduationProjectPage(String practiceLessonid,String syllabusId) {
		ApplicationSyllabus_Gra applicationSyllabus_Gra = abilityAndTeachObj_GraDao.findAppSyllabus_Gra(Integer.valueOf(syllabusId));//通过大纲ID查询记录
		
		List<Ability> abilityList = abilityDao.getbydepartment(applicationSyllabus_Gra.getDepartment());	//查询对应专业全部毕业要求
		List<Ability> abilitySelectList = new ArrayList<Ability>();//选择的毕业要求
		List<List<Analysis>> analylist = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilityList.size();i++){
			List<Analysis> analysilist = analysisDao.getbyability(abilityList.get(i));
			analylist.add(analysilist);
		}
		List<List<IndexSelect_Gra>> analysisSelect_GraList = new ArrayList<List<IndexSelect_Gra>>();//选择的毕业要求所选择的指标点
		for(int i=0;i<abilityList.size();i++)
		{
			List<IndexSelect_Gra> indexSelect_Gra = abilityAndTeachObj_GraDao.findIndexSelectByAbility_Gra(abilityList.get(i),syllabusId);//查询全部毕业要求中所选择毕业要求及指标点
			if(indexSelect_Gra != null && indexSelect_Gra.size() != 0){
				List<IndexSelect_Gra> indexSelect_GraList = new ArrayList<IndexSelect_Gra>();
				abilitySelectList.add(indexSelect_Gra.get(0).getAbility());//添加到选择的毕业要求List
				for(int j=0;j<indexSelect_Gra.size();j++){
					indexSelect_GraList.add(indexSelect_Gra.get(j));//添加到毕业要求所选择的指标点List
				}
				analysisSelect_GraList.add(indexSelect_GraList);//添加全部选择的毕业要求和选择的指标点List
			}
		}
		String json = "";
		for(int i=0;i<abilitySelectList.size();i++){
				json = json +"{id:";
				int k=0;
				for(int j=0;j<abilityList.size();j++){
					if(abilitySelectList.get(i)==abilityList.get(j)){
						k=j+1;
					}
				}
				List<AbilityAndTeachObj_Gra> newAbilityAndTeachObjList_Gra = abilityAndTeachObj_GraDao.findTeachByAbility_Gra(abilitySelectList.get(i),syllabusId);
				
				json = json + abilitySelectList.get(i).getAbilityid() +",description:\"" + k + "、";
				json = json + abilitySelectList.get(i).getAbilitycontent() +"\",points:[";
				int z = 0;
				for(int x=0;x<analysisSelect_GraList.get(i).size();x++){
					for(int y=0;y<analylist.get(k-1).size();y++){
						if(analysisSelect_GraList.get(i).get(x).getAnalisis()==analylist.get(k-1).get(y)){
							z=y+1;
							json = json +"\""+ k + "-" + z + "、" + analysisSelect_GraList.get(i).get(x).getAnalisis().getAnalysiscontent() + "\",";
						}
					}
				}	
				json = json + "],goals:{";
				if(newAbilityAndTeachObjList_Gra != null)
				for(int x = 0; x < newAbilityAndTeachObjList_Gra.size(); x++) {
					json = json + "\"" + newAbilityAndTeachObjList_Gra.get(x).getTeachObj_Gra().getTeachObj_Graid() + "\":true,";
				}
				json = json + "}},";

		}
		ServletActionContext.getRequest().setAttribute("json", json);//全部指标点
		//
		String TeachObj_GraJson = teachObj_GraDao.findAllByCDP_Gra(syllabusId);
		ServletActionContext.getRequest().setAttribute("TeachObjJson", TeachObj_GraJson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		
		
	}
	//添加毕业要求与教学目标对应关系
	public void addAbilityAndTeachObjGraduationProject(String syllabusId,String data) {
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();	
				List<AbilityAndTeachObj_Gra> abilityAndTeachObjSelect_Gra = new ArrayList<AbilityAndTeachObj_Gra>();
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						JSONArray proArr = jsonObj.getJSONArray("goals");
						int proSize = proArr.length();
						if(proSize != 0){
							Integer Abilityid =Integer.valueOf(jsonObj.get("id").toString());
							Ability ability = abilityDao.get(Abilityid);
							for(int j=0;j<proSize;j++){
								AbilityAndTeachObj_Gra newabilityAndTeachObj_Gra = new AbilityAndTeachObj_Gra();
								TeachObj_Gra teachObj_Gra = teachObj_GraDao.get(Integer.valueOf(proArr.get(j).toString()));
								newabilityAndTeachObj_Gra.setTeachObj_Gra(teachObj_Gra);
								newabilityAndTeachObj_Gra.setAbility(ability);
								newabilityAndTeachObj_Gra.setSyllabus_Graid(syllabusId);
								abilityAndTeachObjSelect_Gra.add(newabilityAndTeachObj_Gra);
							}
						}
						}	
				abilityAndTeachObj_GraDao.addAbilityAndTeachObj_Gra(abilityAndTeachObjSelect_Gra,syllabusId);
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};	
		
	}


	
	
	

}
