package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Analysis {
	private Integer analysisid;
	private String analysiscontent;
	private Ability ability;
	private Set<IndexSelect> setIndexSelect = new HashSet<IndexSelect>();
	private Set<IndexSelect_TheoInnerExperiment> setIndexSelect_TheoInnerExperiment = new HashSet<IndexSelect_TheoInnerExperiment>();
	private Set<IndexSelect_FieldWork> setIndexSelect_FieldWork = new HashSet<IndexSelect_FieldWork>();
	private Set<IndexSelect_CourseDesign> setIndexSelect_CourseDesign = new HashSet<IndexSelect_CourseDesign>();
	private Set<IndexSelect_InnerExperiment> setIndexSelect_InnerExperiment = new HashSet<IndexSelect_InnerExperiment>();
	private Set<IndexSelect_Gra> setIndexSelect_Gra = new HashSet<IndexSelect_Gra>();
	
	
	
	public Set<IndexSelect_FieldWork> getSetIndexSelect_FieldWork() {
		return setIndexSelect_FieldWork;
	}
	public void setSetIndexSelect_FieldWork(
			Set<IndexSelect_FieldWork> setIndexSelect_FieldWork) {
		this.setIndexSelect_FieldWork = setIndexSelect_FieldWork;
	}
	public Set<IndexSelect_CourseDesign> getSetIndexSelect_CourseDesign() {
		return setIndexSelect_CourseDesign;
	}
	public void setSetIndexSelect_CourseDesign(
			Set<IndexSelect_CourseDesign> setIndexSelect_CourseDesign) {
		this.setIndexSelect_CourseDesign = setIndexSelect_CourseDesign;
	}
	public Set<IndexSelect_InnerExperiment> getSetIndexSelect_InnerExperiment() {
		return setIndexSelect_InnerExperiment;
	}
	public void setSetIndexSelect_InnerExperiment(
			Set<IndexSelect_InnerExperiment> setIndexSelect_InnerExperiment) {
		this.setIndexSelect_InnerExperiment = setIndexSelect_InnerExperiment;
	}
	public Set<IndexSelect_Gra> getSetIndexSelect_Gra() {
		return setIndexSelect_Gra;
	}
	public void setSetIndexSelect_Gra(Set<IndexSelect_Gra> setIndexSelect_Gra) {
		this.setIndexSelect_Gra = setIndexSelect_Gra;
	}
	public Set<IndexSelect_TheoInnerExperiment> getSetIndexSelect_TheoInnerExperiment() {
		return setIndexSelect_TheoInnerExperiment;
	}
	public void setSetIndexSelect_TheoInnerExperiment(
			Set<IndexSelect_TheoInnerExperiment> setIndexSelect_TheoInnerExperiment) {
		this.setIndexSelect_TheoInnerExperiment = setIndexSelect_TheoInnerExperiment;
	}
	public Set<IndexSelect> getSetIndexSelect() {
		return setIndexSelect;
	}
	public void setSetIndexSelect(Set<IndexSelect> setIndexSelect) {
		this.setIndexSelect = setIndexSelect;
	}
	public Integer getAnalysisid() {
		return analysisid;
	}
	public void setAnalysisid(Integer analysisid) {
		this.analysisid = analysisid;
	}
	public String getAnalysiscontent() {
		return analysiscontent;
	}
	public void setAnalysiscontent(String analysiscontent) {
		this.analysiscontent = analysiscontent;
	}
	public Ability getAbility() {
		return ability;
	}
	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	
}
