package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.TeaMethodTheo;

public class Syllabus {
	private Integer syllabusid;
	private BaseTheo baseTheo;
	private TeaMethodTheo teaMethodTheo;
	private Set<ApplicationSyllabus> setApplicationSyllabus = new HashSet<ApplicationSyllabus>();
	
	public Set<ApplicationSyllabus> getSetApplicationSyllabus() {
		return setApplicationSyllabus;
	}
	public void setSetApplicationSyllabus(
			Set<ApplicationSyllabus> setApplicationSyllabus) {
		this.setApplicationSyllabus = setApplicationSyllabus;
	}
	public Integer getSyllabusid() {
		return syllabusid;
	}
	public void setSyllabusid(Integer syllabusid) {
		this.syllabusid = syllabusid;
	}

	public TeaMethodTheo getTeaMethodTheo() {
		return teaMethodTheo;
	}
	public void setTeaMethodTheo(TeaMethodTheo teaMethodTheo) {
		this.teaMethodTheo = teaMethodTheo;
	}
	public BaseTheo getBaseTheo() {
		return baseTheo;
	}
	public void setBaseTheo(BaseTheo baseTheo) {
		this.baseTheo = baseTheo;
	}
	

}
