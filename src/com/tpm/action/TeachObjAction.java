package com.tpm.action;

import java.io.IOException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.TeachObj;
import com.tpm.service.TeachObjService;

public class TeachObjAction extends ActionSupport implements ModelDriven<TeachObj> {
	private TeachObj teachObj = new TeachObj();
	public TeachObj getModel() {
		return teachObj;
	}
	
	private TeachObjService teachObjService;
	public void setTeachObjService(TeachObjService teachObjService) {
		this.teachObjService = teachObjService;
	}
	
	private String data;	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	private String syllabusId;
	
	public String getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	//--------------理论课----------------
	public void addTeachObj() throws IOException{//添加教学目标
		teachObjService.addTeachObj(teachObj,data,syllabusId);
	}
	public void delTeachObj() throws IOException{//刪除教学目标
		teachObjService.delTeachObj(teachObj,data);
	}
	
	//--------------理论课——课内实验----------------
	public void addTeachObjTheoInnerExperiment() throws IOException{//添加教学目标
		teachObjService.addTeachObjTheoInnerExperiment(teachObj,data,syllabusId);
	}
	public void delTeachObjTheoInnerExperiment() throws IOException{//刪除教学目标
		teachObjService.delTeachObjTheoInnerExperiment(teachObj,data);
	}

	//--------------实践课——实习----------------
	public void addTeachObjFieldWork() throws IOException{//添加教学目标
		teachObjService.addTeachObjFieldWork(data,syllabusId);
	}
	public void delTeachObjFieldWork() throws IOException{//刪除教学目标
		teachObjService.delTeachObjFieldWork(data);
	}
	//--------------实践课——课程设计（学年论文）----------------
	public void addTeachObjCourseDesign() throws IOException{//添加教学目标
		teachObjService.addTeachObjCourseDesign(data,syllabusId);
	}
	public void delTeachObjCourseDesign() throws IOException{//刪除教学目标
		teachObjService.delTeachObjCourseDesign(data);
	}
	//--------------实践课——课内实验----------------
	public void addTeachObjInnerExperiment() throws IOException{//添加教学目标
		teachObjService.addTeachObjInnerExperiment(data,syllabusId);
	}
	public void delTeachObjInnerExperiment() throws IOException{//刪除教学目标
		teachObjService.delTeachObjInnerExperiment(data);
	}
	//--------------实践课——毕业设计----------------
	public void addTeachObjGraduationProject() throws IOException{//添加教学目标
		teachObjService.addTeachObjGraduationProject(data,syllabusId);
	}
	public void delTeachObjGraduationProject() throws IOException{//刪除教学目标
		teachObjService.delTeachObjGraduationProject(data);
	}
}
