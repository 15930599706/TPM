package com.tpm.entity;

public class PPTrainingConcept {
	private Integer PPTrainingConceptid;
	private String ProfessionalDevelopmentPlanning;
	private String ProfessionalFeatures;
	private Department department;
	public Integer getPPTrainingConceptid() {
		return PPTrainingConceptid;
	}
	public void setPPTrainingConceptid(Integer pPTrainingConceptid) {
		PPTrainingConceptid = pPTrainingConceptid;
	}
	public String getProfessionalDevelopmentPlanning() {
		return ProfessionalDevelopmentPlanning;
	}
	public void setProfessionalDevelopmentPlanning(
			String professionalDevelopmentPlanning) {
		ProfessionalDevelopmentPlanning = professionalDevelopmentPlanning;
	}
	public String getProfessionalFeatures() {
		return ProfessionalFeatures;
	}
	public void setProfessionalFeatures(String professionalFeatures) {
		ProfessionalFeatures = professionalFeatures;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
