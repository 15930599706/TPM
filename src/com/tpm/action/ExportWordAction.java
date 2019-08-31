package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.tpm.service.ExportWordService;

public class ExportWordAction extends ActionSupport {

	private ExportWordService exportWordService;

	public void setExportWordService(ExportWordService exportWordService) {
		this.exportWordService = exportWordService;
	}
	private String theoid;
	private String syllabusid;
	

	public String getSyllabusid() {
		return syllabusid;
	}

	public void setSyllabusid(String syllabusid) {
		this.syllabusid = syllabusid;
	}

	public String getTheoid() {
		return theoid;
	}

	public void setTheoid(String theoid) {
		this.theoid = theoid;
	}
	private String curriculumId;
	
	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}

	public String exportTheo() throws Exception
	{
		exportWordService.exportTheo(theoid,syllabusid,curriculumId);
		return null;
	}
	
	private String pracid;
	
	public String getPracid() {
		return pracid;
	}

	public void setPracid(String pracid) {
		this.pracid = pracid;
	}

	public String exportGra() throws Exception
	{
		exportWordService.exportGra(pracid,syllabusid);
		return null;
	}
	
	private String fieldid;
	
	public String getFieldid() {
		return fieldid;
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

	public String exportFieldwork() throws Exception
	{
		exportWordService.exportFieldwork(fieldid,syllabusid);
		return null;
	}
	
	private String courseid;

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String exportCourseDesign() throws Exception
	{
		exportWordService.exportCourseDesign(courseid,syllabusid);
		return null;
	}
	
	private String expeid;


	public String getExpeid() {
		return expeid;
	}

	public void setExpeid(String expeid) {
		this.expeid = expeid;
	}

	public String exportBaseExperiment() throws Exception
	{
		exportWordService.exportBaseExperiment(expeid,syllabusid);
		return null;
	}
	
	private String theoexpeid;
	
	public String getTheoexpeid() {
		return theoexpeid;
	}

	public void setTheoexpeid(String theoexpeid) {
		this.theoexpeid = theoexpeid;
	}

	public void exportTheoInnerExperiment() throws Exception
	{
		exportWordService.exportTheoInnerExperiment(theoexpeid,syllabusid);
	}
}
