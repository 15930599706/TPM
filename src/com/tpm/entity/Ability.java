package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Ability {
	private Integer abilityid;
	private String abilityname;
	private String abilitycontent;
	private Department department;
	private Integer departmentid;
	private Set<Analysis> setAnalysis = new HashSet<Analysis>();
	private Set<CurriculumMatrix> setCurriculumMatrix = new HashSet<CurriculumMatrix>();
	private Set<AbilityAndTeachObj> setAbilityAndTeachObj = new HashSet<AbilityAndTeachObj>();
	private Set<IndexSelect> setIndexSelect = new HashSet<IndexSelect>();
	private Set<AbilityAndTeachObj_TheoInnerExperiment> setAbilityAndTeachObj_TheoInnerExperiment = new HashSet<AbilityAndTeachObj_TheoInnerExperiment>();
	private Set<IndexSelect_TheoInnerExperiment> setIndexSelect_TheoInnerExperiment = new HashSet<IndexSelect_TheoInnerExperiment>();
	
	private Set<AbilityAndTeachObj_FieldWork> setAbilityAndTeachObj_FieldWork = new HashSet<AbilityAndTeachObj_FieldWork>();
	private Set<IndexSelect_FieldWork> setIndexSelect_FieldWork = new HashSet<IndexSelect_FieldWork>();

	private Set<AbilityAndTeachObj_CourseDesign> setAbilityAndTeachObj_CourseDesign = new HashSet<AbilityAndTeachObj_CourseDesign>();
	private Set<IndexSelect_CourseDesign> setIndexSelect_CourseDesign = new HashSet<IndexSelect_CourseDesign>();

	private Set<AbilityAndTeachObj_InnerExperiment> setAbilityAndTeachObj_InnerExperiment = new HashSet<AbilityAndTeachObj_InnerExperiment>();
	private Set<IndexSelect_InnerExperiment> setIndexSelect_InnerExperiment = new HashSet<IndexSelect_InnerExperiment>();

	private Set<AbilityAndTeachObj_Gra> setAbilityAndTeachObj_Gra = new HashSet<AbilityAndTeachObj_Gra>();
	private Set<IndexSelect_Gra> setIndexSelect_Gra = new HashSet<IndexSelect_Gra>();

	
	
	public Set<AbilityAndTeachObj_FieldWork> getSetAbilityAndTeachObj_FieldWork() {
		return setAbilityAndTeachObj_FieldWork;
	}
	public void setSetAbilityAndTeachObj_FieldWork(
			Set<AbilityAndTeachObj_FieldWork> setAbilityAndTeachObj_FieldWork) {
		this.setAbilityAndTeachObj_FieldWork = setAbilityAndTeachObj_FieldWork;
	}
	public Set<IndexSelect_FieldWork> getSetIndexSelect_FieldWork() {
		return setIndexSelect_FieldWork;
	}
	public void setSetIndexSelect_FieldWork(
			Set<IndexSelect_FieldWork> setIndexSelect_FieldWork) {
		this.setIndexSelect_FieldWork = setIndexSelect_FieldWork;
	}
	public Set<AbilityAndTeachObj_CourseDesign> getSetAbilityAndTeachObj_CourseDesign() {
		return setAbilityAndTeachObj_CourseDesign;
	}
	public void setSetAbilityAndTeachObj_CourseDesign(
			Set<AbilityAndTeachObj_CourseDesign> setAbilityAndTeachObj_CourseDesign) {
		this.setAbilityAndTeachObj_CourseDesign = setAbilityAndTeachObj_CourseDesign;
	}
	public Set<IndexSelect_CourseDesign> getSetIndexSelect_CourseDesign() {
		return setIndexSelect_CourseDesign;
	}
	public void setSetIndexSelect_CourseDesign(
			Set<IndexSelect_CourseDesign> setIndexSelect_CourseDesign) {
		this.setIndexSelect_CourseDesign = setIndexSelect_CourseDesign;
	}
	public Set<AbilityAndTeachObj_InnerExperiment> getSetAbilityAndTeachObj_InnerExperiment() {
		return setAbilityAndTeachObj_InnerExperiment;
	}
	public void setSetAbilityAndTeachObj_InnerExperiment(
			Set<AbilityAndTeachObj_InnerExperiment> setAbilityAndTeachObj_InnerExperiment) {
		this.setAbilityAndTeachObj_InnerExperiment = setAbilityAndTeachObj_InnerExperiment;
	}
	public Set<IndexSelect_InnerExperiment> getSetIndexSelect_InnerExperiment() {
		return setIndexSelect_InnerExperiment;
	}
	public void setSetIndexSelect_InnerExperiment(
			Set<IndexSelect_InnerExperiment> setIndexSelect_InnerExperiment) {
		this.setIndexSelect_InnerExperiment = setIndexSelect_InnerExperiment;
	}
	public Set<AbilityAndTeachObj_Gra> getSetAbilityAndTeachObj_Gra() {
		return setAbilityAndTeachObj_Gra;
	}
	public void setSetAbilityAndTeachObj_Gra(
			Set<AbilityAndTeachObj_Gra> setAbilityAndTeachObj_Gra) {
		this.setAbilityAndTeachObj_Gra = setAbilityAndTeachObj_Gra;
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
	public Set<AbilityAndTeachObj_TheoInnerExperiment> getSetAbilityAndTeachObj_TheoInnerExperiment() {
		return setAbilityAndTeachObj_TheoInnerExperiment;
	}
	public void setSetAbilityAndTeachObj_TheoInnerExperiment(
			Set<AbilityAndTeachObj_TheoInnerExperiment> setAbilityAndTeachObj_TheoInnerExperiment) {
		this.setAbilityAndTeachObj_TheoInnerExperiment = setAbilityAndTeachObj_TheoInnerExperiment;
	}
	public Set<IndexSelect> getSetIndexSelect() {
		return setIndexSelect;
	}
	public void setSetIndexSelect(Set<IndexSelect> setIndexSelect) {
		this.setIndexSelect = setIndexSelect;
	}
	public Set<AbilityAndTeachObj> getSetAbilityAndTeachObj() {
		return setAbilityAndTeachObj;
	}
	public void setSetAbilityAndTeachObj(
			Set<AbilityAndTeachObj> setAbilityAndTeachObj) {
		this.setAbilityAndTeachObj = setAbilityAndTeachObj;
	}
	public Set<CurriculumMatrix> getSetCurriculumMatrix() {
		return setCurriculumMatrix;
	}
	public void setSetCurriculumMatrix(Set<CurriculumMatrix> setCurriculumMatrix) {
		this.setCurriculumMatrix = setCurriculumMatrix;
	}
	public Set<Analysis> getSetAnalysis() {
		return setAnalysis;
	}
	public void setSetAnalysis(Set<Analysis> setAnalysis) {
		this.setAnalysis = setAnalysis;
	}
	public Integer getAbilityid() {
		return abilityid;
	}
	public void setAbilityid(Integer abilityid) {
		this.abilityid = abilityid;
	}
	public String getAbilityname() {
		return abilityname;
	}
	public void setAbilityname(String abilityname) {
		this.abilityname = abilityname;
	}
	public String getAbilitycontent() {
		return abilitycontent;
	}
	public void setAbilitycontent(String abilitycontent) {
		this.abilitycontent = abilitycontent;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	
	
}
