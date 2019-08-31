package com.tpm.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.service.AbilityAndTeachObjService;

public class AbilityAndTeachObjAction extends ActionSupport implements ModelDriven<AbilityAndTeachObj> {

	private AbilityAndTeachObj abilityAndTeachObj =new AbilityAndTeachObj();
	public AbilityAndTeachObj getModel() {
		return abilityAndTeachObj;
	}
	
	private AbilityAndTeachObjService abilityAndTeachObjService;
	public void setAbilityAndTeachObjService(
			AbilityAndTeachObjService abilityAndTeachObjService) {
		this.abilityAndTeachObjService = abilityAndTeachObjService;
	}
	
	private String syllabusId;
	
	public String getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	private String theoreticalLessonId;
	public String getTheoreticalLessonId() {
		return theoreticalLessonId;
	}
	public void setTheoreticalLessonId(String theoreticalLessonId) {
		this.theoreticalLessonId = theoreticalLessonId;
	}

	private String data;	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	private String practiceLessonid;
	public String getPracticeLessonid() {
		return practiceLessonid;
	}
	public void setPracticeLessonid(String practiceLessonid) {
		this.practiceLessonid = practiceLessonid;
	}
	private String curriculumId;
	
	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}
	//------------------理论课--------------------------
	public String toBefourAimTheoPage(){//跳转到选择指标点页面
		String tag = abilityAndTeachObjService.toBefourAimTheoPage(theoreticalLessonId,syllabusId,curriculumId);
		if(tag.equals("noIntoAim"))
		{
			return "noIntoAim";
		}
		else
		return "toBefourAimTheoPage";
	}
	
	public void updateSelectIndex(){//存储选择的指标点
		abilityAndTeachObjService.updateSelectIndex(syllabusId,data);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
	}
	
	public String toAimTheoPage(){//跳转到毕业要求与教学目标对应页面
		abilityAndTeachObjService.toAimTheoPage(theoreticalLessonId,syllabusId,curriculumId);
		return "toAimTheoPage";
	}
	
	public void addAbilityAndTeachObj(){//添加毕业要求与教学目标对应关系
		abilityAndTeachObjService.addAbilityAndTeachObj(syllabusId,data);
	}
	
	//------------------理论课——课内实验--------------------------
		public String toBefourAimTheoPageInnerExperiment(){//跳转到选择指标点页面
			String tag = abilityAndTeachObjService.toBefourAimTheoPageInnerExperiment(theoreticalLessonId,syllabusId);
			if(tag.equals("noIntoAim"))
			{
				return "noIntoAim";
			}
			else
			return "toBefourAimTheoPageInnerExperiment";
		}
		
		public void updateSelectIndexTheoInnerExperiment(){//存储选择的指标点
			abilityAndTeachObjService.updateSelectIndexTheoInnerExperiment(syllabusId,data);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		}
		
		public String toAimTheoPageInnerExperiment(){//跳转到毕业要求与教学目标对应页面
			abilityAndTeachObjService.toAimTheoPageInnerExperiment(theoreticalLessonId,syllabusId);
			return "toAimTheoPageInnerExperiment";
		}
		
		public void addAbilityAndTeachObjTheoInnerExperiment(){//添加毕业要求与教学目标对应关系
			abilityAndTeachObjService.addAbilityAndTeachObjTheoInnerExperiment(syllabusId,data);
		}


	//------------------实践课——实习--------------------------
	public String toBefourAimFieldWorkPage(){//跳转到选择指标点页面
		String tag = abilityAndTeachObjService.toBefourAimFieldWorkPage(practiceLessonid,syllabusId);
		if(tag.equals("noIntoAim"))
		{
			return "noIntoAim";
		}
		else
		return "toBefourAimFieldWorkPage";
	}
	
	public void updateSelectIndexFieldWork(){//存储选择的指标点
		abilityAndTeachObjService.updateSelectIndexFieldWork(syllabusId,data);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
	}
	
	public String toAimFieldWorkPage(){//跳转到毕业要求与教学目标对应页面
		abilityAndTeachObjService.toAimFieldWorkPage(practiceLessonid,syllabusId);
		return "toAimFieldWorkPage";
	}
	
	public void addAbilityAndTeachObjFieldWork(){//添加毕业要求与教学目标对应关系
		abilityAndTeachObjService.addAbilityAndTeachObjFieldWork(syllabusId,data);
	}
	
	
	//------------------实践课——课程设计（学年论文）--------------------------
	public String toBefourAimCourseDesignPage(){//跳转到选择指标点页面
		String tag = abilityAndTeachObjService.toBefourAimCourseDesignPage(practiceLessonid,syllabusId);
		if(tag.equals("noIntoAim"))
		{
			return "noIntoAim";
		}
		else
		return "toBefourAimCourseDesignPage";
	}
	
	public void updateSelectIndexCourseDesign(){//存储选择的指标点
		abilityAndTeachObjService.updateSelectIndexCourseDesign(syllabusId,data);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
	}
	
	public String toAimCourseDesignPage(){//跳转到毕业要求与教学目标对应页面
		abilityAndTeachObjService.toAimCourseDesignPage(practiceLessonid,syllabusId);
		return "toAimCourseDesignPage";
	}
	
	public void addAbilityAndTeachObjCourseDesign(){//添加毕业要求与教学目标对应关系
		abilityAndTeachObjService.addAbilityAndTeachObjCourseDesign(syllabusId,data);
	}
	
	//------------------实践课——课内实验--------------------------
	public String toBefourAimInnerExperimentPage(){//跳转到选择指标点页面
		String tag = abilityAndTeachObjService.toBefourAimInnerExperimentPage(practiceLessonid,syllabusId);
		if(tag.equals("noIntoAim"))
		{
			return "noIntoAim";
		}
		else
		return "toBefourAimInnerExperimentPage";
	}
	
	public void updateSelectIndexInnerExperiment(){//存储选择的指标点
		abilityAndTeachObjService.updateSelectIndexInnerExperiment(syllabusId,data);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
	}
	
	public String toAimInnerExperimentPage(){//跳转到毕业要求与教学目标对应页面
		abilityAndTeachObjService.toAimInnerExperimentPage(practiceLessonid,syllabusId);
		return "toAimInnerExperimentPage";
	}
	
	public void addAbilityAndTeachObjInnerExperiment(){//添加毕业要求与教学目标对应关系
		abilityAndTeachObjService.addAbilityAndTeachObjInnerExperiment(syllabusId,data);
	}
	
	//------------------实践课——毕业设计--------------------------
	public String toBefourAimGraduationProjectPage(){//跳转到选择指标点页面
		String tag = abilityAndTeachObjService.toBefourAimGraduationProjectPage(practiceLessonid,syllabusId);
		if(tag.equals("noIntoAim"))
		{
			return "noIntoAim";
		}
		else
		return "toBefourAimGraduationProjectPage";
	}
	
	public void updateSelectIndexGraduationProject(){//存储选择的指标点
		abilityAndTeachObjService.updateSelectIndexGraduationProject(syllabusId,data);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
	}
	
	public String toAimGraduationProjectPage(){//跳转到毕业要求与教学目标对应页面
		abilityAndTeachObjService.toAimGraduationProjectPage(practiceLessonid,syllabusId);
		return "toAimGraduationProjectPage";
	}
	
	public void addAbilityAndTeachObjGraduationProject(){//添加毕业要求与教学目标对应关系
		abilityAndTeachObjService.addAbilityAndTeachObjGraduationProject(syllabusId,data);
	}
	
}
