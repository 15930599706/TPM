package com.tpm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CurriculumDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.dao.TeachObjDao;
import com.tpm.dao.TeachObj_CourseDesignDao;
import com.tpm.dao.TeachObj_FieldWorkDao;
import com.tpm.dao.TeachObj_GraDao;
import com.tpm.dao.TeachObj_InnerExperimentDao;
import com.tpm.dao.TeachObj_TheoInnerExperimentDao;
import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.IndexSelect;
import com.tpm.entity.Professional;
import com.tpm.entity.TeachObj;
import com.tpm.entity.TeachObj_CourseDesign;
import com.tpm.entity.TeachObj_FieldWork;
import com.tpm.entity.TeachObj_Gra;
import com.tpm.entity.TeachObj_InnerExperiment;
import com.tpm.entity.TeachObj_TheoInnerExperiment;

@Transactional
public class TeachObjService {
	private TeachObjDao teachObjDao;
	
	
	private TeachObj_FieldWorkDao teachObj_FieldWorkDao;
	private TeachObj_CourseDesignDao teachObj_CourseDesignDao;
	private TeachObj_InnerExperimentDao teachObj_InnerExperimentDao;
	private TeachObj_GraDao teachObj_GraDao;
	
	private TeachObj_TheoInnerExperimentDao teachObj_TheoInnerExperimentDao;
	
	public void setTeachObj_TheoInnerExperimentDao(
			TeachObj_TheoInnerExperimentDao teachObj_TheoInnerExperimentDao) {
		this.teachObj_TheoInnerExperimentDao = teachObj_TheoInnerExperimentDao;
	}
	public void setTeachObj_CourseDesignDao(
			TeachObj_CourseDesignDao teachObj_CourseDesignDao) {
		this.teachObj_CourseDesignDao = teachObj_CourseDesignDao;
	}
	public void setTeachObj_InnerExperimentDao(
			TeachObj_InnerExperimentDao teachObj_InnerExperimentDao) {
		this.teachObj_InnerExperimentDao = teachObj_InnerExperimentDao;
	}
	public void setTeachObj_GraDao(TeachObj_GraDao teachObj_GraDao) {
		this.teachObj_GraDao = teachObj_GraDao;
	}
	public void setTeachObj_FieldWorkDao(TeachObj_FieldWorkDao teachObj_FieldWorkDao) {
		this.teachObj_FieldWorkDao = teachObj_FieldWorkDao;
	}
	public void setTeachObjDao(TeachObjDao teachObjDao) {
		this.teachObjDao = teachObjDao;
	}
	
	//--------------理论课----------------
	public void addTeachObj(TeachObj teachObj, String content, String syllabusId) throws IOException {	//添加教学目标
		teachObj.setTeachObjContent(content);
		teachObj.setSyllabusID(syllabusId);
		teachObjDao.add(teachObj);	
		// 用response把新添加的教学目标id传到客户端
		//teachObj.getTeachObjid();
		String result = "{\"error\": false, \"message\": \"" + teachObj.getTeachObjid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
	}

	public void delTeachObj(TeachObj teachObj, String data) throws IOException {//删除教学目标
		TeachObj teachobj = teachObjDao.get(Integer.valueOf(data));
		teachObjDao.delete(teachobj);
		String result = "{\"error\": false, \"message\": \"" + teachObj.getTeachObjid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
	}

	//--------------理论课——课内实验----------------
	public void addTeachObjTheoInnerExperiment(TeachObj teachObj, String content,String syllabusId) throws IOException {
		// TODO Auto-generated method stub
		TeachObj_TheoInnerExperiment teachObj_TheoInnerExperiment = new TeachObj_TheoInnerExperiment();
		teachObj_TheoInnerExperiment.setTeachObjContent_TheoInnerExperiment(content);
		teachObj_TheoInnerExperiment.setSyllabusid(syllabusId);
		teachObj_TheoInnerExperimentDao.add(teachObj_TheoInnerExperiment);	
		// 用response把新添加的教学目标id传到客户端
		//teachObj.getTeachObjid();
		String result = "{\"error\": false, \"message\": \"" + teachObj_TheoInnerExperiment.getTeachObj_TheoInnerExperimentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
	}
	public void delTeachObjTheoInnerExperiment(TeachObj teachObj, String data) throws IOException {
		// TODO Auto-generated method stub
		TeachObj_TheoInnerExperiment teachobj_TheoInnerExperiment = teachObj_TheoInnerExperimentDao.get(Integer.valueOf(data));
		teachObj_TheoInnerExperimentDao.delete(teachobj_TheoInnerExperiment);
		String result = "{\"error\": false, \"message\": \"" + teachobj_TheoInnerExperiment.getTeachObj_TheoInnerExperimentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
	}
	
	//--------------实践课——实习----------------
	public void addTeachObjFieldWork(String data, String syllabusId) throws IOException {
		TeachObj_FieldWork teachObj_FieldWork = new TeachObj_FieldWork();
		teachObj_FieldWork.setTeachObjContent_FieldWork(data);
		teachObj_FieldWork.setSyllabus_FieldWorkid(syllabusId);
		teachObj_FieldWorkDao.add(teachObj_FieldWork);	
		// 用response把新添加的教学目标id传到客户端
		//teachObj.getTeachObjid();
		String result = "{\"error\": false, \"message\": \"" + teachObj_FieldWork.getTeachObj_FieldWorkid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		
	}

	public void delTeachObjFieldWork(String data) throws IOException {
		TeachObj_FieldWork teachobj_FieldWork = teachObj_FieldWorkDao.get(Integer.valueOf(data));
		teachObj_FieldWorkDao.delete(teachobj_FieldWork);
		String result = "{\"error\": false, \"message\": \"" + teachobj_FieldWork.getTeachObj_FieldWorkid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		
	}
	
	//--------------实践课——课程设计（学年论文）----------------
	public void addTeachObjCourseDesign(String data, String syllabusId) throws IOException {
		TeachObj_CourseDesign teachObj_CourseDesign = new TeachObj_CourseDesign();
		teachObj_CourseDesign.setTeachObjContent_CourseDesign(data);
		teachObj_CourseDesign.setSyllabus_CourseDesignid(syllabusId);
		teachObj_CourseDesignDao.add(teachObj_CourseDesign);	
		// 用response把新添加的教学目标id传到客户端
		//teachObj.getTeachObjid();
		String result = "{\"error\": false, \"message\": \"" + teachObj_CourseDesign.getTeachObj_CourseDesignid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		
	}
	public void delTeachObjCourseDesign(String data) throws IOException {
		TeachObj_CourseDesign teachobj_CourseDesign = teachObj_CourseDesignDao.get(Integer.valueOf(data));
		teachObj_CourseDesignDao.delete(teachobj_CourseDesign);
		String result = "{\"error\": false, \"message\": \"" + teachobj_CourseDesign.getTeachObj_CourseDesignid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		
	}
	
	//--------------实践课——课内实验----------------
	public void addTeachObjInnerExperiment(String data, String syllabusId) throws IOException {
		TeachObj_InnerExperiment teachObj_InnerExperiment = new TeachObj_InnerExperiment();
		teachObj_InnerExperiment.setTeachObjContent_InnerExperiment(data);
		teachObj_InnerExperiment.setSyllabus_InnerExperimentid(syllabusId);
		teachObj_InnerExperimentDao.add(teachObj_InnerExperiment);	
		// 用response把新添加的教学目标id传到客户端
		//teachObj.getTeachObjid();
		String result = "{\"error\": false, \"message\": \"" + teachObj_InnerExperiment.getTeachObj_InnerExperimentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		
	}
	public void delTeachObjInnerExperiment(String data) throws IOException {
		TeachObj_InnerExperiment teachobj_InnerExperiment = teachObj_InnerExperimentDao.get(Integer.valueOf(data));
		teachObj_InnerExperimentDao.delete(teachobj_InnerExperiment);
		String result = "{\"error\": false, \"message\": \"" + teachobj_InnerExperiment.getTeachObj_InnerExperimentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		
	}
	
	//--------------实践课——毕业设计----------------
	public void addTeachObjGraduationProject(String data, String syllabusId) throws IOException {
		TeachObj_Gra teachObj_Gra = new TeachObj_Gra();
		teachObj_Gra.setTeachObjContent_Gra(data);
		teachObj_Gra.setSyllabus_Graid(syllabusId);
		teachObj_GraDao.add(teachObj_Gra);	
		// 用response把新添加的教学目标id传到客户端
		//teachObj.getTeachObjid();
		String result = "{\"error\": false, \"message\": \"" + teachObj_Gra.getTeachObj_Graid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		
	}
	public void delTeachObjGraduationProject(String data) throws IOException {
		TeachObj_Gra teachobj_Gra = teachObj_GraDao.get(Integer.valueOf(data));
		teachObj_GraDao.delete(teachobj_Gra);
		String result = "{\"error\": false, \"message\": \"" + teachobj_Gra.getTeachObj_Graid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		
	}

	
	

}
