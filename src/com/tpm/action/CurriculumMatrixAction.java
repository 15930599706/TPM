package com.tpm.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.PageBean;
import com.tpm.entity.Professional;
import com.tpm.entity.ProfessionalLegalize;
import com.tpm.service.CurriculumMatrixService;
import com.tpm.service.PPTrainingConceptService;

public class CurriculumMatrixAction extends ActionSupport implements ModelDriven<CurriculumMatrix>{
	private CurriculumMatrix curriculumMatrix;
	public CurriculumMatrix getModel() {
		return curriculumMatrix;
	}
	private CurriculumMatrixService curriculumMatrixService;
	public void setCurriculumMatrixService(
			CurriculumMatrixService curriculumMatrixService) {
		this.curriculumMatrixService = curriculumMatrixService;
	}
	private List<String> ability1dec1;
	private List<String> ability1dec2;
	private List<String> ability1dec3;
	private List<String> ability1dec4;
	private List<String> ability1dec5;
	private List<String> ability1dec6;
	private List<String> ability1dec7;
	private List<String> ability1dec8;
	private List<String> ability1dec9;
	private List<String> ability1dec10;
	private List<String> ability2dec1;
	private List<String> ability2dec2;
	private List<String> ability2dec3;
	private List<String> ability2dec4;
	private List<String> ability2dec5;
	private List<String> ability2dec6;
	private List<String> ability2dec7;
	private List<String> ability2dec8;
	private List<String> ability2dec9;
	private List<String> ability2dec10;
	private List<String> ability3dec1;
	private List<String> ability3dec2;
	private List<String> ability3dec3;
	private List<String> ability3dec4;
	private List<String> ability3dec5;
	private List<String> ability3dec6;
	private List<String> ability3dec7;
	private List<String> ability3dec8;
	private List<String> ability3dec9;
	private List<String> ability3dec10;
	private List<String> ability4dec1;
	private List<String> ability4dec2;
	private List<String> ability4dec3;
	private List<String> ability4dec4;
	private List<String> ability4dec5;
	private List<String> ability4dec6;
	private List<String> ability4dec7;
	private List<String> ability4dec8;
	private List<String> ability4dec9;
	private List<String> ability4dec10;
	private List<String> ability5dec1;
	private List<String> ability5dec2;
	private List<String> ability5dec3;
	private List<String> ability5dec4;
	private List<String> ability5dec5;
	private List<String> ability5dec6;
	private List<String> ability5dec7;
	private List<String> ability5dec8;
	private List<String> ability5dec9;
	private List<String> ability5dec10;
	private List<String> ability6dec1;
	private List<String> ability6dec2;
	private List<String> ability6dec3;
	private List<String> ability6dec4;
	private List<String> ability6dec5;
	private List<String> ability6dec6;
	private List<String> ability6dec7;
	private List<String> ability6dec8;
	private List<String> ability6dec9;
	private List<String> ability6dec10;
	private List<String> ability7dec1;
	private List<String> ability7dec2;
	private List<String> ability7dec3;
	private List<String> ability7dec4;
	private List<String> ability7dec5;
	private List<String> ability7dec6;
	private List<String> ability7dec7;
	private List<String> ability7dec8;
	private List<String> ability7dec9;
	private List<String> ability7dec10;
	private List<String> ability8dec1;
	private List<String> ability8dec2;
	private List<String> ability8dec3;
	private List<String> ability8dec4;
	private List<String> ability8dec5;
	private List<String> ability8dec6;
	private List<String> ability8dec7;
	private List<String> ability8dec8;
	private List<String> ability8dec9;
	private List<String> ability8dec10;
	private List<String> ability9dec1;
	private List<String> ability9dec2;
	private List<String> ability9dec3;
	private List<String> ability9dec4;
	private List<String> ability9dec5;
	private List<String> ability9dec6;
	private List<String> ability9dec7;
	private List<String> ability9dec8;
	private List<String> ability9dec9;
	private List<String> ability9dec10;
	private List<String> ability10dec1;
	private List<String> ability10dec2;
	private List<String> ability10dec3;
	private List<String> ability10dec4;
	private List<String> ability10dec5;
	private List<String> ability10dec6;
	private List<String> ability10dec7;
	private List<String> ability10dec8;
	private List<String> ability10dec9;
	private List<String> ability10dec10;
	private List<String> ability11dec1;
	private List<String> ability11dec2;
	private List<String> ability11dec3;
	private List<String> ability11dec4;
	private List<String> ability11dec5;
	private List<String> ability11dec6;
	private List<String> ability11dec7;
	private List<String> ability11dec8;
	private List<String> ability11dec9;
	private List<String> ability11dec10;
	private List<String> ability12dec1;
	private List<String> ability12dec2;
	private List<String> ability12dec3;
	private List<String> ability12dec4;
	private List<String> ability12dec5;
	private List<String> ability12dec6;
	private List<String> ability12dec7;
	private List<String> ability12dec8;
	private List<String> ability12dec9;
	private List<String> ability12dec10;
	private List<String> ability13dec1;
	private List<String> ability13dec2;
	private List<String> ability13dec3;
	private List<String> ability13dec4;
	private List<String> ability13dec5;
	private List<String> ability13dec6;
	private List<String> ability13dec7;
	private List<String> ability13dec8;
	private List<String> ability13dec9;
	private List<String> ability13dec10;
	private List<String> ability14dec1;
	private List<String> ability14dec2;
	private List<String> ability14dec3;
	private List<String> ability14dec4;
	private List<String> ability14dec5;
	private List<String> ability14dec6;
	private List<String> ability14dec7;
	private List<String> ability14dec8;
	private List<String> ability14dec9;
	private List<String> ability14dec10;
	private List<String> ability15dec1;
	private List<String> ability15dec2;
	private List<String> ability15dec3;
	private List<String> ability15dec4;
	private List<String> ability15dec5;
	private List<String> ability15dec6;
	private List<String> ability15dec7;
	private List<String> ability15dec8;
	private List<String> ability15dec9;
	private List<String> ability15dec10;
	public List<String> getAbility1dec1() {
		return ability1dec1;
	}
	public void setAbility1dec1(List<String> ability1dec1) {
		this.ability1dec1 = ability1dec1;
	}
	public List<String> getAbility1dec2() {
		return ability1dec2;
	}
	public void setAbility1dec2(List<String> ability1dec2) {
		this.ability1dec2 = ability1dec2;
	}
	public List<String> getAbility1dec3() {
		return ability1dec3;
	}
	public void setAbility1dec3(List<String> ability1dec3) {
		this.ability1dec3 = ability1dec3;
	}
	public List<String> getAbility1dec4() {
		return ability1dec4;
	}
	public void setAbility1dec4(List<String> ability1dec4) {
		this.ability1dec4 = ability1dec4;
	}
	public List<String> getAbility1dec5() {
		return ability1dec5;
	}
	public void setAbility1dec5(List<String> ability1dec5) {
		this.ability1dec5 = ability1dec5;
	}
	public List<String> getAbility1dec6() {
		return ability1dec6;
	}
	public void setAbility1dec6(List<String> ability1dec6) {
		this.ability1dec6 = ability1dec6;
	}
	public List<String> getAbility1dec7() {
		return ability1dec7;
	}
	public void setAbility1dec7(List<String> ability1dec7) {
		this.ability1dec7 = ability1dec7;
	}
	public List<String> getAbility1dec8() {
		return ability1dec8;
	}
	public void setAbility1dec8(List<String> ability1dec8) {
		this.ability1dec8 = ability1dec8;
	}
	public List<String> getAbility1dec9() {
		return ability1dec9;
	}
	public void setAbility1dec9(List<String> ability1dec9) {
		this.ability1dec9 = ability1dec9;
	}
	public List<String> getAbility1dec10() {
		return ability1dec10;
	}
	public void setAbility1dec10(List<String> ability1dec10) {
		this.ability1dec10 = ability1dec10;
	}
	public List<String> getAbility2dec1() {
		return ability2dec1;
	}
	public void setAbility2dec1(List<String> ability2dec1) {
		this.ability2dec1 = ability2dec1;
	}
	public List<String> getAbility2dec2() {
		return ability2dec2;
	}
	public void setAbility2dec2(List<String> ability2dec2) {
		this.ability2dec2 = ability2dec2;
	}
	public List<String> getAbility2dec3() {
		return ability2dec3;
	}
	public void setAbility2dec3(List<String> ability2dec3) {
		this.ability2dec3 = ability2dec3;
	}
	public List<String> getAbility2dec4() {
		return ability2dec4;
	}
	public void setAbility2dec4(List<String> ability2dec4) {
		this.ability2dec4 = ability2dec4;
	}
	public List<String> getAbility2dec5() {
		return ability2dec5;
	}
	public void setAbility2dec5(List<String> ability2dec5) {
		this.ability2dec5 = ability2dec5;
	}
	public List<String> getAbility2dec6() {
		return ability2dec6;
	}
	public void setAbility2dec6(List<String> ability2dec6) {
		this.ability2dec6 = ability2dec6;
	}
	public List<String> getAbility2dec7() {
		return ability2dec7;
	}
	public void setAbility2dec7(List<String> ability2dec7) {
		this.ability2dec7 = ability2dec7;
	}
	public List<String> getAbility2dec8() {
		return ability2dec8;
	}
	public void setAbility2dec8(List<String> ability2dec8) {
		this.ability2dec8 = ability2dec8;
	}
	public List<String> getAbility2dec9() {
		return ability2dec9;
	}
	public void setAbility2dec9(List<String> ability2dec9) {
		this.ability2dec9 = ability2dec9;
	}
	public List<String> getAbility2dec10() {
		return ability2dec10;
	}
	public void setAbility2dec10(List<String> ability2dec10) {
		this.ability2dec10 = ability2dec10;
	}
	public List<String> getAbility3dec1() {
		return ability3dec1;
	}
	public void setAbility3dec1(List<String> ability3dec1) {
		this.ability3dec1 = ability3dec1;
	}
	public List<String> getAbility3dec2() {
		return ability3dec2;
	}
	public void setAbility3dec2(List<String> ability3dec2) {
		this.ability3dec2 = ability3dec2;
	}
	public List<String> getAbility3dec3() {
		return ability3dec3;
	}
	public void setAbility3dec3(List<String> ability3dec3) {
		this.ability3dec3 = ability3dec3;
	}
	public List<String> getAbility3dec4() {
		return ability3dec4;
	}
	public void setAbility3dec4(List<String> ability3dec4) {
		this.ability3dec4 = ability3dec4;
	}
	public List<String> getAbility3dec5() {
		return ability3dec5;
	}
	public void setAbility3dec5(List<String> ability3dec5) {
		this.ability3dec5 = ability3dec5;
	}
	public List<String> getAbility3dec6() {
		return ability3dec6;
	}
	public void setAbility3dec6(List<String> ability3dec6) {
		this.ability3dec6 = ability3dec6;
	}
	public List<String> getAbility3dec7() {
		return ability3dec7;
	}
	public void setAbility3dec7(List<String> ability3dec7) {
		this.ability3dec7 = ability3dec7;
	}
	public List<String> getAbility3dec8() {
		return ability3dec8;
	}
	public void setAbility3dec8(List<String> ability3dec8) {
		this.ability3dec8 = ability3dec8;
	}
	public List<String> getAbility3dec9() {
		return ability3dec9;
	}
	public void setAbility3dec9(List<String> ability3dec9) {
		this.ability3dec9 = ability3dec9;
	}
	public List<String> getAbility3dec10() {
		return ability3dec10;
	}
	public void setAbility3dec10(List<String> ability3dec10) {
		this.ability3dec10 = ability3dec10;
	}
	public List<String> getAbility4dec1() {
		return ability4dec1;
	}
	public void setAbility4dec1(List<String> ability4dec1) {
		this.ability4dec1 = ability4dec1;
	}
	public List<String> getAbility4dec2() {
		return ability4dec2;
	}
	public void setAbility4dec2(List<String> ability4dec2) {
		this.ability4dec2 = ability4dec2;
	}
	public List<String> getAbility4dec3() {
		return ability4dec3;
	}
	public void setAbility4dec3(List<String> ability4dec3) {
		this.ability4dec3 = ability4dec3;
	}
	public List<String> getAbility4dec4() {
		return ability4dec4;
	}
	public void setAbility4dec4(List<String> ability4dec4) {
		this.ability4dec4 = ability4dec4;
	}
	public List<String> getAbility4dec5() {
		return ability4dec5;
	}
	public void setAbility4dec5(List<String> ability4dec5) {
		this.ability4dec5 = ability4dec5;
	}
	public List<String> getAbility4dec6() {
		return ability4dec6;
	}
	public void setAbility4dec6(List<String> ability4dec6) {
		this.ability4dec6 = ability4dec6;
	}
	public List<String> getAbility4dec7() {
		return ability4dec7;
	}
	public void setAbility4dec7(List<String> ability4dec7) {
		this.ability4dec7 = ability4dec7;
	}
	public List<String> getAbility4dec8() {
		return ability4dec8;
	}
	public void setAbility4dec8(List<String> ability4dec8) {
		this.ability4dec8 = ability4dec8;
	}
	public List<String> getAbility4dec9() {
		return ability4dec9;
	}
	public void setAbility4dec9(List<String> ability4dec9) {
		this.ability4dec9 = ability4dec9;
	}
	public List<String> getAbility4dec10() {
		return ability4dec10;
	}
	public void setAbility4dec10(List<String> ability4dec10) {
		this.ability4dec10 = ability4dec10;
	}
	public List<String> getAbility5dec1() {
		return ability5dec1;
	}
	public void setAbility5dec1(List<String> ability5dec1) {
		this.ability5dec1 = ability5dec1;
	}
	public List<String> getAbility5dec2() {
		return ability5dec2;
	}
	public void setAbility5dec2(List<String> ability5dec2) {
		this.ability5dec2 = ability5dec2;
	}
	public List<String> getAbility5dec3() {
		return ability5dec3;
	}
	public void setAbility5dec3(List<String> ability5dec3) {
		this.ability5dec3 = ability5dec3;
	}
	public List<String> getAbility5dec4() {
		return ability5dec4;
	}
	public void setAbility5dec4(List<String> ability5dec4) {
		this.ability5dec4 = ability5dec4;
	}
	public List<String> getAbility5dec5() {
		return ability5dec5;
	}
	public void setAbility5dec5(List<String> ability5dec5) {
		this.ability5dec5 = ability5dec5;
	}
	public List<String> getAbility5dec6() {
		return ability5dec6;
	}
	public void setAbility5dec6(List<String> ability5dec6) {
		this.ability5dec6 = ability5dec6;
	}
	public List<String> getAbility5dec7() {
		return ability5dec7;
	}
	public void setAbility5dec7(List<String> ability5dec7) {
		this.ability5dec7 = ability5dec7;
	}
	public List<String> getAbility5dec8() {
		return ability5dec8;
	}
	public void setAbility5dec8(List<String> ability5dec8) {
		this.ability5dec8 = ability5dec8;
	}
	public List<String> getAbility5dec9() {
		return ability5dec9;
	}
	public void setAbility5dec9(List<String> ability5dec9) {
		this.ability5dec9 = ability5dec9;
	}
	public List<String> getAbility5dec10() {
		return ability5dec10;
	}
	public void setAbility5dec10(List<String> ability5dec10) {
		this.ability5dec10 = ability5dec10;
	}
	public List<String> getAbility6dec1() {
		return ability6dec1;
	}
	public void setAbility6dec1(List<String> ability6dec1) {
		this.ability6dec1 = ability6dec1;
	}
	public List<String> getAbility6dec2() {
		return ability6dec2;
	}
	public void setAbility6dec2(List<String> ability6dec2) {
		this.ability6dec2 = ability6dec2;
	}
	public List<String> getAbility6dec3() {
		return ability6dec3;
	}
	public void setAbility6dec3(List<String> ability6dec3) {
		this.ability6dec3 = ability6dec3;
	}
	public List<String> getAbility6dec4() {
		return ability6dec4;
	}
	public void setAbility6dec4(List<String> ability6dec4) {
		this.ability6dec4 = ability6dec4;
	}
	public List<String> getAbility6dec5() {
		return ability6dec5;
	}
	public void setAbility6dec5(List<String> ability6dec5) {
		this.ability6dec5 = ability6dec5;
	}
	public List<String> getAbility6dec6() {
		return ability6dec6;
	}
	public void setAbility6dec6(List<String> ability6dec6) {
		this.ability6dec6 = ability6dec6;
	}
	public List<String> getAbility6dec7() {
		return ability6dec7;
	}
	public void setAbility6dec7(List<String> ability6dec7) {
		this.ability6dec7 = ability6dec7;
	}
	public List<String> getAbility6dec8() {
		return ability6dec8;
	}
	public void setAbility6dec8(List<String> ability6dec8) {
		this.ability6dec8 = ability6dec8;
	}
	public List<String> getAbility6dec9() {
		return ability6dec9;
	}
	public void setAbility6dec9(List<String> ability6dec9) {
		this.ability6dec9 = ability6dec9;
	}
	public List<String> getAbility6dec10() {
		return ability6dec10;
	}
	public void setAbility6dec10(List<String> ability6dec10) {
		this.ability6dec10 = ability6dec10;
	}
	public List<String> getAbility7dec1() {
		return ability7dec1;
	}
	public void setAbility7dec1(List<String> ability7dec1) {
		this.ability7dec1 = ability7dec1;
	}
	public List<String> getAbility7dec2() {
		return ability7dec2;
	}
	public void setAbility7dec2(List<String> ability7dec2) {
		this.ability7dec2 = ability7dec2;
	}
	public List<String> getAbility7dec3() {
		return ability7dec3;
	}
	public void setAbility7dec3(List<String> ability7dec3) {
		this.ability7dec3 = ability7dec3;
	}
	public List<String> getAbility7dec4() {
		return ability7dec4;
	}
	public void setAbility7dec4(List<String> ability7dec4) {
		this.ability7dec4 = ability7dec4;
	}
	public List<String> getAbility7dec5() {
		return ability7dec5;
	}
	public void setAbility7dec5(List<String> ability7dec5) {
		this.ability7dec5 = ability7dec5;
	}
	public List<String> getAbility7dec6() {
		return ability7dec6;
	}
	public void setAbility7dec6(List<String> ability7dec6) {
		this.ability7dec6 = ability7dec6;
	}
	public List<String> getAbility7dec7() {
		return ability7dec7;
	}
	public void setAbility7dec7(List<String> ability7dec7) {
		this.ability7dec7 = ability7dec7;
	}
	public List<String> getAbility7dec8() {
		return ability7dec8;
	}
	public void setAbility7dec8(List<String> ability7dec8) {
		this.ability7dec8 = ability7dec8;
	}
	public List<String> getAbility7dec9() {
		return ability7dec9;
	}
	public void setAbility7dec9(List<String> ability7dec9) {
		this.ability7dec9 = ability7dec9;
	}
	public List<String> getAbility7dec10() {
		return ability7dec10;
	}
	public void setAbility7dec10(List<String> ability7dec10) {
		this.ability7dec10 = ability7dec10;
	}
	public List<String> getAbility8dec1() {
		return ability8dec1;
	}
	public void setAbility8dec1(List<String> ability8dec1) {
		this.ability8dec1 = ability8dec1;
	}
	public List<String> getAbility8dec2() {
		return ability8dec2;
	}
	public void setAbility8dec2(List<String> ability8dec2) {
		this.ability8dec2 = ability8dec2;
	}
	public List<String> getAbility8dec3() {
		return ability8dec3;
	}
	public void setAbility8dec3(List<String> ability8dec3) {
		this.ability8dec3 = ability8dec3;
	}
	public List<String> getAbility8dec4() {
		return ability8dec4;
	}
	public void setAbility8dec4(List<String> ability8dec4) {
		this.ability8dec4 = ability8dec4;
	}
	public List<String> getAbility8dec5() {
		return ability8dec5;
	}
	public void setAbility8dec5(List<String> ability8dec5) {
		this.ability8dec5 = ability8dec5;
	}
	public List<String> getAbility8dec6() {
		return ability8dec6;
	}
	public void setAbility8dec6(List<String> ability8dec6) {
		this.ability8dec6 = ability8dec6;
	}
	public List<String> getAbility8dec7() {
		return ability8dec7;
	}
	public void setAbility8dec7(List<String> ability8dec7) {
		this.ability8dec7 = ability8dec7;
	}
	public List<String> getAbility8dec8() {
		return ability8dec8;
	}
	public void setAbility8dec8(List<String> ability8dec8) {
		this.ability8dec8 = ability8dec8;
	}
	public List<String> getAbility8dec9() {
		return ability8dec9;
	}
	public void setAbility8dec9(List<String> ability8dec9) {
		this.ability8dec9 = ability8dec9;
	}
	public List<String> getAbility8dec10() {
		return ability8dec10;
	}
	public void setAbility8dec10(List<String> ability8dec10) {
		this.ability8dec10 = ability8dec10;
	}
	public List<String> getAbility9dec1() {
		return ability9dec1;
	}
	public void setAbility9dec1(List<String> ability9dec1) {
		this.ability9dec1 = ability9dec1;
	}
	public List<String> getAbility9dec2() {
		return ability9dec2;
	}
	public void setAbility9dec2(List<String> ability9dec2) {
		this.ability9dec2 = ability9dec2;
	}
	public List<String> getAbility9dec3() {
		return ability9dec3;
	}
	public void setAbility9dec3(List<String> ability9dec3) {
		this.ability9dec3 = ability9dec3;
	}
	public List<String> getAbility9dec4() {
		return ability9dec4;
	}
	public void setAbility9dec4(List<String> ability9dec4) {
		this.ability9dec4 = ability9dec4;
	}
	public List<String> getAbility9dec5() {
		return ability9dec5;
	}
	public void setAbility9dec5(List<String> ability9dec5) {
		this.ability9dec5 = ability9dec5;
	}
	public List<String> getAbility9dec6() {
		return ability9dec6;
	}
	public void setAbility9dec6(List<String> ability9dec6) {
		this.ability9dec6 = ability9dec6;
	}
	public List<String> getAbility9dec7() {
		return ability9dec7;
	}
	public void setAbility9dec7(List<String> ability9dec7) {
		this.ability9dec7 = ability9dec7;
	}
	public List<String> getAbility9dec8() {
		return ability9dec8;
	}
	public void setAbility9dec8(List<String> ability9dec8) {
		this.ability9dec8 = ability9dec8;
	}
	public List<String> getAbility9dec9() {
		return ability9dec9;
	}
	public void setAbility9dec9(List<String> ability9dec9) {
		this.ability9dec9 = ability9dec9;
	}
	public List<String> getAbility9dec10() {
		return ability9dec10;
	}
	public void setAbility9dec10(List<String> ability9dec10) {
		this.ability9dec10 = ability9dec10;
	}
	public List<String> getAbility10dec1() {
		return ability10dec1;
	}
	public void setAbility10dec1(List<String> ability10dec1) {
		this.ability10dec1 = ability10dec1;
	}
	public List<String> getAbility10dec2() {
		return ability10dec2;
	}
	public void setAbility10dec2(List<String> ability10dec2) {
		this.ability10dec2 = ability10dec2;
	}
	public List<String> getAbility10dec3() {
		return ability10dec3;
	}
	public void setAbility10dec3(List<String> ability10dec3) {
		this.ability10dec3 = ability10dec3;
	}
	public List<String> getAbility10dec4() {
		return ability10dec4;
	}
	public void setAbility10dec4(List<String> ability10dec4) {
		this.ability10dec4 = ability10dec4;
	}
	public List<String> getAbility10dec5() {
		return ability10dec5;
	}
	public void setAbility10dec5(List<String> ability10dec5) {
		this.ability10dec5 = ability10dec5;
	}
	public List<String> getAbility10dec6() {
		return ability10dec6;
	}
	public void setAbility10dec6(List<String> ability10dec6) {
		this.ability10dec6 = ability10dec6;
	}
	public List<String> getAbility10dec7() {
		return ability10dec7;
	}
	public void setAbility10dec7(List<String> ability10dec7) {
		this.ability10dec7 = ability10dec7;
	}
	public List<String> getAbility10dec8() {
		return ability10dec8;
	}
	public void setAbility10dec8(List<String> ability10dec8) {
		this.ability10dec8 = ability10dec8;
	}
	public List<String> getAbility10dec9() {
		return ability10dec9;
	}
	public void setAbility10dec9(List<String> ability10dec9) {
		this.ability10dec9 = ability10dec9;
	}
	public List<String> getAbility10dec10() {
		return ability10dec10;
	}
	public void setAbility10dec10(List<String> ability10dec10) {
		this.ability10dec10 = ability10dec10;
	}
	public List<String> getAbility11dec1() {
		return ability11dec1;
	}
	public void setAbility11dec1(List<String> ability11dec1) {
		this.ability11dec1 = ability11dec1;
	}
	public List<String> getAbility11dec2() {
		return ability11dec2;
	}
	public void setAbility11dec2(List<String> ability11dec2) {
		this.ability11dec2 = ability11dec2;
	}
	public List<String> getAbility11dec3() {
		return ability11dec3;
	}
	public void setAbility11dec3(List<String> ability11dec3) {
		this.ability11dec3 = ability11dec3;
	}
	public List<String> getAbility11dec4() {
		return ability11dec4;
	}
	public void setAbility11dec4(List<String> ability11dec4) {
		this.ability11dec4 = ability11dec4;
	}
	public List<String> getAbility11dec5() {
		return ability11dec5;
	}
	public void setAbility11dec5(List<String> ability11dec5) {
		this.ability11dec5 = ability11dec5;
	}
	public List<String> getAbility11dec6() {
		return ability11dec6;
	}
	public void setAbility11dec6(List<String> ability11dec6) {
		this.ability11dec6 = ability11dec6;
	}
	public List<String> getAbility11dec7() {
		return ability11dec7;
	}
	public void setAbility11dec7(List<String> ability11dec7) {
		this.ability11dec7 = ability11dec7;
	}
	public List<String> getAbility11dec8() {
		return ability11dec8;
	}
	public void setAbility11dec8(List<String> ability11dec8) {
		this.ability11dec8 = ability11dec8;
	}
	public List<String> getAbility11dec9() {
		return ability11dec9;
	}
	public void setAbility11dec9(List<String> ability11dec9) {
		this.ability11dec9 = ability11dec9;
	}
	public List<String> getAbility11dec10() {
		return ability11dec10;
	}
	public void setAbility11dec10(List<String> ability11dec10) {
		this.ability11dec10 = ability11dec10;
	}
	public List<String> getAbility12dec1() {
		return ability12dec1;
	}
	public void setAbility12dec1(List<String> ability12dec1) {
		this.ability12dec1 = ability12dec1;
	}
	public List<String> getAbility12dec2() {
		return ability12dec2;
	}
	public void setAbility12dec2(List<String> ability12dec2) {
		this.ability12dec2 = ability12dec2;
	}
	public List<String> getAbility12dec3() {
		return ability12dec3;
	}
	public void setAbility12dec3(List<String> ability12dec3) {
		this.ability12dec3 = ability12dec3;
	}
	public List<String> getAbility12dec4() {
		return ability12dec4;
	}
	public void setAbility12dec4(List<String> ability12dec4) {
		this.ability12dec4 = ability12dec4;
	}
	public List<String> getAbility12dec5() {
		return ability12dec5;
	}
	public void setAbility12dec5(List<String> ability12dec5) {
		this.ability12dec5 = ability12dec5;
	}
	public List<String> getAbility12dec6() {
		return ability12dec6;
	}
	public void setAbility12dec6(List<String> ability12dec6) {
		this.ability12dec6 = ability12dec6;
	}
	public List<String> getAbility12dec7() {
		return ability12dec7;
	}
	public void setAbility12dec7(List<String> ability12dec7) {
		this.ability12dec7 = ability12dec7;
	}
	public List<String> getAbility12dec8() {
		return ability12dec8;
	}
	public void setAbility12dec8(List<String> ability12dec8) {
		this.ability12dec8 = ability12dec8;
	}
	public List<String> getAbility12dec9() {
		return ability12dec9;
	}
	public void setAbility12dec9(List<String> ability12dec9) {
		this.ability12dec9 = ability12dec9;
	}
	public List<String> getAbility12dec10() {
		return ability12dec10;
	}
	public void setAbility12dec10(List<String> ability12dec10) {
		this.ability12dec10 = ability12dec10;
	}
	public List<String> getAbility13dec1() {
		return ability13dec1;
	}
	public void setAbility13dec1(List<String> ability13dec1) {
		this.ability13dec1 = ability13dec1;
	}
	public List<String> getAbility13dec2() {
		return ability13dec2;
	}
	public void setAbility13dec2(List<String> ability13dec2) {
		this.ability13dec2 = ability13dec2;
	}
	public List<String> getAbility13dec3() {
		return ability13dec3;
	}
	public void setAbility13dec3(List<String> ability13dec3) {
		this.ability13dec3 = ability13dec3;
	}
	public List<String> getAbility13dec4() {
		return ability13dec4;
	}
	public void setAbility13dec4(List<String> ability13dec4) {
		this.ability13dec4 = ability13dec4;
	}
	public List<String> getAbility13dec5() {
		return ability13dec5;
	}
	public void setAbility13dec5(List<String> ability13dec5) {
		this.ability13dec5 = ability13dec5;
	}
	public List<String> getAbility13dec6() {
		return ability13dec6;
	}
	public void setAbility13dec6(List<String> ability13dec6) {
		this.ability13dec6 = ability13dec6;
	}
	public List<String> getAbility13dec7() {
		return ability13dec7;
	}
	public void setAbility13dec7(List<String> ability13dec7) {
		this.ability13dec7 = ability13dec7;
	}
	public List<String> getAbility13dec8() {
		return ability13dec8;
	}
	public void setAbility13dec8(List<String> ability13dec8) {
		this.ability13dec8 = ability13dec8;
	}
	public List<String> getAbility13dec9() {
		return ability13dec9;
	}
	public void setAbility13dec9(List<String> ability13dec9) {
		this.ability13dec9 = ability13dec9;
	}
	public List<String> getAbility13dec10() {
		return ability13dec10;
	}
	public void setAbility13dec10(List<String> ability13dec10) {
		this.ability13dec10 = ability13dec10;
	}
	public List<String> getAbility14dec1() {
		return ability14dec1;
	}
	public void setAbility14dec1(List<String> ability14dec1) {
		this.ability14dec1 = ability14dec1;
	}
	public List<String> getAbility14dec2() {
		return ability14dec2;
	}
	public void setAbility14dec2(List<String> ability14dec2) {
		this.ability14dec2 = ability14dec2;
	}
	public List<String> getAbility14dec3() {
		return ability14dec3;
	}
	public void setAbility14dec3(List<String> ability14dec3) {
		this.ability14dec3 = ability14dec3;
	}
	public List<String> getAbility14dec4() {
		return ability14dec4;
	}
	public void setAbility14dec4(List<String> ability14dec4) {
		this.ability14dec4 = ability14dec4;
	}
	public List<String> getAbility14dec5() {
		return ability14dec5;
	}
	public void setAbility14dec5(List<String> ability14dec5) {
		this.ability14dec5 = ability14dec5;
	}
	public List<String> getAbility14dec6() {
		return ability14dec6;
	}
	public void setAbility14dec6(List<String> ability14dec6) {
		this.ability14dec6 = ability14dec6;
	}
	public List<String> getAbility14dec7() {
		return ability14dec7;
	}
	public void setAbility14dec7(List<String> ability14dec7) {
		this.ability14dec7 = ability14dec7;
	}
	public List<String> getAbility14dec8() {
		return ability14dec8;
	}
	public void setAbility14dec8(List<String> ability14dec8) {
		this.ability14dec8 = ability14dec8;
	}
	public List<String> getAbility14dec9() {
		return ability14dec9;
	}
	public void setAbility14dec9(List<String> ability14dec9) {
		this.ability14dec9 = ability14dec9;
	}
	public List<String> getAbility14dec10() {
		return ability14dec10;
	}
	public void setAbility14dec10(List<String> ability14dec10) {
		this.ability14dec10 = ability14dec10;
	}
	public List<String> getAbility15dec1() {
		return ability15dec1;
	}
	public void setAbility15dec1(List<String> ability15dec1) {
		this.ability15dec1 = ability15dec1;
	}
	public List<String> getAbility15dec2() {
		return ability15dec2;
	}
	public void setAbility15dec2(List<String> ability15dec2) {
		this.ability15dec2 = ability15dec2;
	}
	public List<String> getAbility15dec3() {
		return ability15dec3;
	}
	public void setAbility15dec3(List<String> ability15dec3) {
		this.ability15dec3 = ability15dec3;
	}
	public List<String> getAbility15dec4() {
		return ability15dec4;
	}
	public void setAbility15dec4(List<String> ability15dec4) {
		this.ability15dec4 = ability15dec4;
	}
	public List<String> getAbility15dec5() {
		return ability15dec5;
	}
	public void setAbility15dec5(List<String> ability15dec5) {
		this.ability15dec5 = ability15dec5;
	}
	public List<String> getAbility15dec6() {
		return ability15dec6;
	}
	public void setAbility15dec6(List<String> ability15dec6) {
		this.ability15dec6 = ability15dec6;
	}
	public List<String> getAbility15dec7() {
		return ability15dec7;
	}
	public void setAbility15dec7(List<String> ability15dec7) {
		this.ability15dec7 = ability15dec7;
	}
	public List<String> getAbility15dec8() {
		return ability15dec8;
	}
	public void setAbility15dec8(List<String> ability15dec8) {
		this.ability15dec8 = ability15dec8;
	}
	public List<String> getAbility15dec9() {
		return ability15dec9;
	}
	public void setAbility15dec9(List<String> ability15dec9) {
		this.ability15dec9 = ability15dec9;
	}
	public List<String> getAbility15dec10() {
		return ability15dec10;
	}
	public void setAbility15dec10(List<String> ability15dec10) {
		this.ability15dec10 = ability15dec10;
	}
	private String departmentid;
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String tobjjzpage(){
		//慢
		List<Curriculum> curriculumlist = curriculumMatrixService.findcurriculumbydepartment(departmentid);
		List<Professional> professionalList = curriculumMatrixService.findProfessionbydepartment(departmentid);
		List<List<Curriculum>> curriculumlist_pro = new ArrayList<List<Curriculum>>();//存储有专业方向的课程
		if(professionalList != null && professionalList.size() !=0)
		{
			//慢
			curriculumlist_pro =  curriculumMatrixService.findcurriculumbydepartment_pro(departmentid);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			ServletActionContext.getRequest().setAttribute("curriculumlist_pro", curriculumlist_pro);
		}
		if((curriculumlist == null || curriculumlist.size() == 0) && (curriculumlist_pro == null || curriculumlist_pro.size() == 0)){
			ServletActionContext.getRequest().setAttribute("tag", "tozyjzpage");
			ServletActionContext.getRequest().setAttribute("msg", "该专业尚未选择培养计划课程！");
			return "notobjjzpage";
		}else{
			List<Ability> abilitielist = curriculumMatrixService.findabilitielist(departmentid);
			if(abilitielist == null || abilitielist.size() == 0){
				ServletActionContext.getRequest().setAttribute("tag", "tozyjzpage");
				ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写毕业生应获得的知识和能力！");
				return "notobjjzpage";
			}		
			else{				
				for(int i=0;i<abilitielist.size();i++){
					if(abilitielist.get(i).getAbilityname() == null)
					{
						ServletActionContext.getRequest().setAttribute("tag", "tozyjzpage");
						ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整毕业生应获得的知识和能力！");
						return "notobjjzpage";
					}
				}				
				List<List<Analysis>> anlist = curriculumMatrixService.findanalysisbyability(abilitielist);
				if(anlist == null || anlist.size() == 0){
					ServletActionContext.getRequest().setAttribute("tag", "tozyjzpage");
					ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写指标分解！");
					return "notobjjzpage";
				}else{
					for(int i=0;i<anlist.size();i++){
						if(anlist.get(i) == null || anlist.get(i).size() == 0){
							ServletActionContext.getRequest().setAttribute("tag", "tozyjzpage");
							ServletActionContext.getRequest().setAttribute("msg", "该专业尚未填写完整指标分解！");
							return "notobjjzpage";
						}
					}
					//慢
					List<List<List<CurriculumMatrix>>> curriculumMatrixlist = curriculumMatrixService.findcurriculumMatrixbyall(curriculumlist,abilitielist,anlist);
					
					if(professionalList != null && professionalList.size() !=0)
					{
						//慢
						List<List<List<List<CurriculumMatrix>>>> curriculumMatrixlist_pro = curriculumMatrixService.findcurriculumMatrixbyall_pro(curriculumlist_pro,abilitielist,anlist,professionalList);
						ServletActionContext.getRequest().setAttribute("curriculumMatrixlist_pro", curriculumMatrixlist_pro);
					}
					
					ServletActionContext.getRequest().setAttribute("curriculumlist", curriculumlist);
					ServletActionContext.getRequest().setAttribute("abilitielist", abilitielist);
					ServletActionContext.getRequest().setAttribute("anlist", anlist);
					ServletActionContext.getRequest().setAttribute("curriculumMatrixlist", curriculumMatrixlist);
					return "tobjjzpage";
				}
			}
		}
		
	}
	public String revisetobjjzpage(){
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
		return tobjjzpage();
	}
	
	public String update(){
		List<Curriculum> curriculumlist = curriculumMatrixService.findcurriculumbydepartment(departmentid);
		List<Ability> abilitielist = curriculumMatrixService.findabilitielist(departmentid);
		List<List<Analysis>> anlist = curriculumMatrixService.findanalysisbyability(abilitielist);
		List<List<List<CurriculumMatrix>>> curriculumMatrixlist = curriculumMatrixService.findcurriculumMatrixbyall(curriculumlist,abilitielist,anlist);
		

		
		for(int i=0;i<curriculumMatrixlist.size();i++){
			for(int j=0;j<curriculumMatrixlist.get(i).size();j++){
				for(int k=0;k<curriculumMatrixlist.get(i).get(j).size();k++){
					if(curriculumMatrixlist.get(i).get(j).get(k).getScore().equals("K")){
						if(j==0&&k==0){if(!ability1dec1.get(i).equals("K")){curriculumMatrixService.add(ability1dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==1){if(!ability1dec2.get(i).equals("K")){curriculumMatrixService.add(ability1dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==2){if(!ability1dec3.get(i).equals("K")){curriculumMatrixService.add(ability1dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==3){if(!ability1dec4.get(i).equals("K")){curriculumMatrixService.add(ability1dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==4){if(!ability1dec5.get(i).equals("K")){curriculumMatrixService.add(ability1dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==5){if(!ability1dec6.get(i).equals("K")){curriculumMatrixService.add(ability1dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==6){if(!ability1dec7.get(i).equals("K")){curriculumMatrixService.add(ability1dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==7){if(!ability1dec8.get(i).equals("K")){curriculumMatrixService.add(ability1dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==8){if(!ability1dec9.get(i).equals("K")){curriculumMatrixService.add(ability1dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==9){if(!ability1dec10.get(i).equals("K")){curriculumMatrixService.add(ability1dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==0){if(!ability2dec1.get(i).equals("K")){curriculumMatrixService.add(ability2dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==1){if(!ability2dec2.get(i).equals("K")){curriculumMatrixService.add(ability2dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==2){if(!ability2dec3.get(i).equals("K")){curriculumMatrixService.add(ability2dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==3){if(!ability2dec4.get(i).equals("K")){curriculumMatrixService.add(ability2dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==4){if(!ability2dec5.get(i).equals("K")){curriculumMatrixService.add(ability2dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==5){if(!ability2dec6.get(i).equals("K")){curriculumMatrixService.add(ability2dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==6){if(!ability2dec7.get(i).equals("K")){curriculumMatrixService.add(ability2dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==7){if(!ability2dec8.get(i).equals("K")){curriculumMatrixService.add(ability2dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==8){if(!ability2dec9.get(i).equals("K")){curriculumMatrixService.add(ability2dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==9){if(!ability2dec10.get(i).equals("K")){curriculumMatrixService.add(ability2dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==0){if(!ability3dec1.get(i).equals("K")){curriculumMatrixService.add(ability3dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==1){if(!ability3dec2.get(i).equals("K")){curriculumMatrixService.add(ability3dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==2){if(!ability3dec3.get(i).equals("K")){curriculumMatrixService.add(ability3dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==3){if(!ability3dec4.get(i).equals("K")){curriculumMatrixService.add(ability3dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==4){if(!ability3dec5.get(i).equals("K")){curriculumMatrixService.add(ability3dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==5){if(!ability3dec6.get(i).equals("K")){curriculumMatrixService.add(ability3dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==6){if(!ability3dec7.get(i).equals("K")){curriculumMatrixService.add(ability3dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==7){if(!ability3dec8.get(i).equals("K")){curriculumMatrixService.add(ability3dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==8){if(!ability3dec9.get(i).equals("K")){curriculumMatrixService.add(ability3dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==9){if(!ability3dec10.get(i).equals("K")){curriculumMatrixService.add(ability3dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==0){if(!ability4dec1.get(i).equals("K")){curriculumMatrixService.add(ability4dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==1){if(!ability4dec2.get(i).equals("K")){curriculumMatrixService.add(ability4dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==2){if(!ability4dec3.get(i).equals("K")){curriculumMatrixService.add(ability4dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==3){if(!ability4dec4.get(i).equals("K")){curriculumMatrixService.add(ability4dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==4){if(!ability4dec5.get(i).equals("K")){curriculumMatrixService.add(ability4dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==5){if(!ability4dec6.get(i).equals("K")){curriculumMatrixService.add(ability4dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==6){if(!ability4dec7.get(i).equals("K")){curriculumMatrixService.add(ability4dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==7){if(!ability4dec8.get(i).equals("K")){curriculumMatrixService.add(ability4dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==8){if(!ability4dec9.get(i).equals("K")){curriculumMatrixService.add(ability4dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==9){if(!ability4dec10.get(i).equals("K")){curriculumMatrixService.add(ability4dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==0){if(!ability5dec1.get(i).equals("K")){curriculumMatrixService.add(ability5dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==1){if(!ability5dec2.get(i).equals("K")){curriculumMatrixService.add(ability5dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==2){if(!ability5dec3.get(i).equals("K")){curriculumMatrixService.add(ability5dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==3){if(!ability5dec4.get(i).equals("K")){curriculumMatrixService.add(ability5dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==4){if(!ability5dec5.get(i).equals("K")){curriculumMatrixService.add(ability5dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==5){if(!ability5dec6.get(i).equals("K")){curriculumMatrixService.add(ability5dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==6){if(!ability5dec7.get(i).equals("K")){curriculumMatrixService.add(ability5dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==7){if(!ability5dec8.get(i).equals("K")){curriculumMatrixService.add(ability5dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==8){if(!ability5dec9.get(i).equals("K")){curriculumMatrixService.add(ability5dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==9){if(!ability5dec10.get(i).equals("K")){curriculumMatrixService.add(ability5dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==0){if(!ability6dec1.get(i).equals("K")){curriculumMatrixService.add(ability6dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==1){if(!ability6dec2.get(i).equals("K")){curriculumMatrixService.add(ability6dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==2){if(!ability6dec3.get(i).equals("K")){curriculumMatrixService.add(ability6dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==3){if(!ability6dec4.get(i).equals("K")){curriculumMatrixService.add(ability6dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==4){if(!ability6dec5.get(i).equals("K")){curriculumMatrixService.add(ability6dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==5){if(!ability6dec6.get(i).equals("K")){curriculumMatrixService.add(ability6dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==6){if(!ability6dec7.get(i).equals("K")){curriculumMatrixService.add(ability6dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==7){if(!ability6dec8.get(i).equals("K")){curriculumMatrixService.add(ability6dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==8){if(!ability6dec9.get(i).equals("K")){curriculumMatrixService.add(ability6dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==9){if(!ability6dec10.get(i).equals("K")){curriculumMatrixService.add(ability6dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==0){if(!ability7dec1.get(i).equals("K")){curriculumMatrixService.add(ability7dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==1){if(!ability7dec2.get(i).equals("K")){curriculumMatrixService.add(ability7dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==2){if(!ability7dec3.get(i).equals("K")){curriculumMatrixService.add(ability7dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==3){if(!ability7dec4.get(i).equals("K")){curriculumMatrixService.add(ability7dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==4){if(!ability7dec5.get(i).equals("K")){curriculumMatrixService.add(ability7dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==5){if(!ability7dec6.get(i).equals("K")){curriculumMatrixService.add(ability7dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==6){if(!ability7dec7.get(i).equals("K")){curriculumMatrixService.add(ability7dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==7){if(!ability7dec8.get(i).equals("K")){curriculumMatrixService.add(ability7dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==8){if(!ability7dec9.get(i).equals("K")){curriculumMatrixService.add(ability7dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==9){if(!ability7dec10.get(i).equals("K")){curriculumMatrixService.add(ability7dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==0){if(!ability8dec1.get(i).equals("K")){curriculumMatrixService.add(ability8dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==1){if(!ability8dec2.get(i).equals("K")){curriculumMatrixService.add(ability8dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==2){if(!ability8dec3.get(i).equals("K")){curriculumMatrixService.add(ability8dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==3){if(!ability8dec4.get(i).equals("K")){curriculumMatrixService.add(ability8dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==4){if(!ability8dec5.get(i).equals("K")){curriculumMatrixService.add(ability8dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==5){if(!ability8dec6.get(i).equals("K")){curriculumMatrixService.add(ability8dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==6){if(!ability8dec7.get(i).equals("K")){curriculumMatrixService.add(ability8dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==7){if(!ability8dec8.get(i).equals("K")){curriculumMatrixService.add(ability8dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==8){if(!ability8dec9.get(i).equals("K")){curriculumMatrixService.add(ability8dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==9){if(!ability8dec10.get(i).equals("K")){curriculumMatrixService.add(ability8dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==0){if(!ability9dec1.get(i).equals("K")){curriculumMatrixService.add(ability9dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==1){if(!ability9dec2.get(i).equals("K")){curriculumMatrixService.add(ability9dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==2){if(!ability9dec3.get(i).equals("K")){curriculumMatrixService.add(ability9dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==3){if(!ability9dec4.get(i).equals("K")){curriculumMatrixService.add(ability9dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==4){if(!ability9dec5.get(i).equals("K")){curriculumMatrixService.add(ability9dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==5){if(!ability9dec6.get(i).equals("K")){curriculumMatrixService.add(ability9dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==6){if(!ability9dec7.get(i).equals("K")){curriculumMatrixService.add(ability9dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==7){if(!ability9dec8.get(i).equals("K")){curriculumMatrixService.add(ability9dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==8){if(!ability9dec9.get(i).equals("K")){curriculumMatrixService.add(ability9dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==9){if(!ability9dec10.get(i).equals("K")){curriculumMatrixService.add(ability9dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==0){if(!ability10dec1.get(i).equals("K")){curriculumMatrixService.add(ability10dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==1){if(!ability10dec2.get(i).equals("K")){curriculumMatrixService.add(ability10dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==2){if(!ability10dec3.get(i).equals("K")){curriculumMatrixService.add(ability10dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==3){if(!ability10dec4.get(i).equals("K")){curriculumMatrixService.add(ability10dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==4){if(!ability10dec5.get(i).equals("K")){curriculumMatrixService.add(ability10dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==5){if(!ability10dec6.get(i).equals("K")){curriculumMatrixService.add(ability10dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==6){if(!ability10dec7.get(i).equals("K")){curriculumMatrixService.add(ability10dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==7){if(!ability10dec8.get(i).equals("K")){curriculumMatrixService.add(ability10dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==8){if(!ability10dec9.get(i).equals("K")){curriculumMatrixService.add(ability10dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==9){if(!ability10dec10.get(i).equals("K")){curriculumMatrixService.add(ability10dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==0){if(!ability11dec1.get(i).equals("K")){curriculumMatrixService.add(ability11dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==1){if(!ability11dec2.get(i).equals("K")){curriculumMatrixService.add(ability11dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==2){if(!ability11dec3.get(i).equals("K")){curriculumMatrixService.add(ability11dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==3){if(!ability11dec4.get(i).equals("K")){curriculumMatrixService.add(ability11dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==4){if(!ability11dec5.get(i).equals("K")){curriculumMatrixService.add(ability11dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==5){if(!ability11dec6.get(i).equals("K")){curriculumMatrixService.add(ability11dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==6){if(!ability11dec7.get(i).equals("K")){curriculumMatrixService.add(ability11dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==7){if(!ability11dec8.get(i).equals("K")){curriculumMatrixService.add(ability11dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==8){if(!ability11dec9.get(i).equals("K")){curriculumMatrixService.add(ability11dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==9){if(!ability11dec10.get(i).equals("K")){curriculumMatrixService.add(ability11dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==0){if(!ability12dec1.get(i).equals("K")){curriculumMatrixService.add(ability12dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==1){if(!ability12dec2.get(i).equals("K")){curriculumMatrixService.add(ability12dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==2){if(!ability12dec3.get(i).equals("K")){curriculumMatrixService.add(ability12dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==3){if(!ability12dec4.get(i).equals("K")){curriculumMatrixService.add(ability12dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==4){if(!ability12dec5.get(i).equals("K")){curriculumMatrixService.add(ability12dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==5){if(!ability12dec6.get(i).equals("K")){curriculumMatrixService.add(ability12dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==6){if(!ability12dec7.get(i).equals("K")){curriculumMatrixService.add(ability12dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==7){if(!ability12dec8.get(i).equals("K")){curriculumMatrixService.add(ability12dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==8){if(!ability12dec9.get(i).equals("K")){curriculumMatrixService.add(ability12dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==9){if(!ability12dec10.get(i).equals("K")){curriculumMatrixService.add(ability12dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==0){if(!ability13dec1.get(i).equals("K")){curriculumMatrixService.add(ability13dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==1){if(!ability13dec2.get(i).equals("K")){curriculumMatrixService.add(ability13dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==2){if(!ability13dec3.get(i).equals("K")){curriculumMatrixService.add(ability13dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==3){if(!ability13dec4.get(i).equals("K")){curriculumMatrixService.add(ability13dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==4){if(!ability13dec5.get(i).equals("K")){curriculumMatrixService.add(ability13dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==5){if(!ability13dec6.get(i).equals("K")){curriculumMatrixService.add(ability13dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==6){if(!ability13dec7.get(i).equals("K")){curriculumMatrixService.add(ability13dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==7){if(!ability13dec8.get(i).equals("K")){curriculumMatrixService.add(ability13dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==8){if(!ability13dec9.get(i).equals("K")){curriculumMatrixService.add(ability13dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==9){if(!ability13dec10.get(i).equals("K")){curriculumMatrixService.add(ability14dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==0){if(!ability14dec1.get(i).equals("K")){curriculumMatrixService.add(ability14dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==1){if(!ability14dec2.get(i).equals("K")){curriculumMatrixService.add(ability14dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==2){if(!ability14dec3.get(i).equals("K")){curriculumMatrixService.add(ability14dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==3){if(!ability14dec4.get(i).equals("K")){curriculumMatrixService.add(ability14dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==4){if(!ability14dec5.get(i).equals("K")){curriculumMatrixService.add(ability14dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==5){if(!ability14dec6.get(i).equals("K")){curriculumMatrixService.add(ability14dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==6){if(!ability14dec7.get(i).equals("K")){curriculumMatrixService.add(ability14dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==7){if(!ability14dec8.get(i).equals("K")){curriculumMatrixService.add(ability14dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==8){if(!ability14dec9.get(i).equals("K")){curriculumMatrixService.add(ability14dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==9){if(!ability14dec10.get(i).equals("K")){curriculumMatrixService.add(ability14dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==0){if(!ability15dec1.get(i).equals("K")){curriculumMatrixService.add(ability15dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==1){if(!ability15dec2.get(i).equals("K")){curriculumMatrixService.add(ability15dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==2){if(!ability15dec3.get(i).equals("K")){curriculumMatrixService.add(ability15dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==3){if(!ability15dec4.get(i).equals("K")){curriculumMatrixService.add(ability15dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==4){if(!ability15dec5.get(i).equals("K")){curriculumMatrixService.add(ability15dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==5){if(!ability15dec6.get(i).equals("K")){curriculumMatrixService.add(ability15dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==6){if(!ability15dec7.get(i).equals("K")){curriculumMatrixService.add(ability15dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==7){if(!ability15dec8.get(i).equals("K")){curriculumMatrixService.add(ability15dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==8){if(!ability15dec9.get(i).equals("K")){curriculumMatrixService.add(ability15dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==9){if(!ability15dec10.get(i).equals("K")){curriculumMatrixService.add(ability15dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}}
					}else{
						if(j==0&&k==0){if(!ability1dec1.get(i).equals("K")){curriculumMatrixService.update(ability1dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==1){if(!ability1dec2.get(i).equals("K")){curriculumMatrixService.update(ability1dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==2){if(!ability1dec3.get(i).equals("K")){curriculumMatrixService.update(ability1dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==3){if(!ability1dec4.get(i).equals("K")){curriculumMatrixService.update(ability1dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==4){if(!ability1dec5.get(i).equals("K")){curriculumMatrixService.update(ability1dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==5){if(!ability1dec6.get(i).equals("K")){curriculumMatrixService.update(ability1dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==6){if(!ability1dec7.get(i).equals("K")){curriculumMatrixService.update(ability1dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==7){if(!ability1dec8.get(i).equals("K")){curriculumMatrixService.update(ability1dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==8){if(!ability1dec9.get(i).equals("K")){curriculumMatrixService.update(ability1dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==9){if(!ability1dec10.get(i).equals("K")){curriculumMatrixService.update(ability1dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==0){if(!ability2dec1.get(i).equals("K")){curriculumMatrixService.update(ability2dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==1){if(!ability2dec2.get(i).equals("K")){curriculumMatrixService.update(ability2dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==2){if(!ability2dec3.get(i).equals("K")){curriculumMatrixService.update(ability2dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==3){if(!ability2dec4.get(i).equals("K")){curriculumMatrixService.update(ability2dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==4){if(!ability2dec5.get(i).equals("K")){curriculumMatrixService.update(ability2dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==5){if(!ability2dec6.get(i).equals("K")){curriculumMatrixService.update(ability2dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==6){if(!ability2dec7.get(i).equals("K")){curriculumMatrixService.update(ability2dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==7){if(!ability2dec8.get(i).equals("K")){curriculumMatrixService.update(ability2dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==8){if(!ability2dec9.get(i).equals("K")){curriculumMatrixService.update(ability2dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==1&&k==9){if(!ability2dec10.get(i).equals("K")){curriculumMatrixService.update(ability2dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==0){if(!ability3dec1.get(i).equals("K")){curriculumMatrixService.update(ability3dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==1){if(!ability3dec2.get(i).equals("K")){curriculumMatrixService.update(ability3dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==2){if(!ability3dec3.get(i).equals("K")){curriculumMatrixService.update(ability3dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==3){if(!ability3dec4.get(i).equals("K")){curriculumMatrixService.update(ability3dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==4){if(!ability3dec5.get(i).equals("K")){curriculumMatrixService.update(ability3dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==5){if(!ability3dec6.get(i).equals("K")){curriculumMatrixService.update(ability3dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==6){if(!ability3dec7.get(i).equals("K")){curriculumMatrixService.update(ability3dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==7){if(!ability3dec8.get(i).equals("K")){curriculumMatrixService.update(ability3dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==8){if(!ability3dec9.get(i).equals("K")){curriculumMatrixService.update(ability3dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==2&&k==9){if(!ability3dec10.get(i).equals("K")){curriculumMatrixService.update(ability3dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==0){if(!ability4dec1.get(i).equals("K")){curriculumMatrixService.update(ability4dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==1){if(!ability4dec2.get(i).equals("K")){curriculumMatrixService.update(ability4dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==2){if(!ability4dec3.get(i).equals("K")){curriculumMatrixService.update(ability4dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==3){if(!ability4dec4.get(i).equals("K")){curriculumMatrixService.update(ability4dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==4){if(!ability4dec5.get(i).equals("K")){curriculumMatrixService.update(ability4dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==5){if(!ability4dec6.get(i).equals("K")){curriculumMatrixService.update(ability4dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==6){if(!ability4dec7.get(i).equals("K")){curriculumMatrixService.update(ability4dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==7){if(!ability4dec8.get(i).equals("K")){curriculumMatrixService.update(ability4dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==8){if(!ability4dec9.get(i).equals("K")){curriculumMatrixService.update(ability4dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==3&&k==9){if(!ability4dec10.get(i).equals("K")){curriculumMatrixService.update(ability4dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==0){if(!ability5dec1.get(i).equals("K")){curriculumMatrixService.update(ability5dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==1){if(!ability5dec2.get(i).equals("K")){curriculumMatrixService.update(ability5dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==2){if(!ability5dec3.get(i).equals("K")){curriculumMatrixService.update(ability5dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==3){if(!ability5dec4.get(i).equals("K")){curriculumMatrixService.update(ability5dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==4){if(!ability5dec5.get(i).equals("K")){curriculumMatrixService.update(ability5dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==5){if(!ability5dec6.get(i).equals("K")){curriculumMatrixService.update(ability5dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==6){if(!ability5dec7.get(i).equals("K")){curriculumMatrixService.update(ability5dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==7){if(!ability5dec8.get(i).equals("K")){curriculumMatrixService.update(ability5dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==8){if(!ability5dec9.get(i).equals("K")){curriculumMatrixService.update(ability5dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==4&&k==9){if(!ability5dec10.get(i).equals("K")){curriculumMatrixService.update(ability5dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==0){if(!ability6dec1.get(i).equals("K")){curriculumMatrixService.update(ability6dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==1){if(!ability6dec2.get(i).equals("K")){curriculumMatrixService.update(ability6dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==2){if(!ability6dec3.get(i).equals("K")){curriculumMatrixService.update(ability6dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==3){if(!ability6dec4.get(i).equals("K")){curriculumMatrixService.update(ability6dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==4){if(!ability6dec5.get(i).equals("K")){curriculumMatrixService.update(ability6dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==5){if(!ability6dec6.get(i).equals("K")){curriculumMatrixService.update(ability6dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==6){if(!ability6dec7.get(i).equals("K")){curriculumMatrixService.update(ability6dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==7){if(!ability6dec8.get(i).equals("K")){curriculumMatrixService.update(ability6dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==8){if(!ability6dec9.get(i).equals("K")){curriculumMatrixService.update(ability6dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==5&&k==9){if(!ability6dec10.get(i).equals("K")){curriculumMatrixService.update(ability6dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==0){if(!ability7dec1.get(i).equals("K")){curriculumMatrixService.update(ability7dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==1){if(!ability7dec2.get(i).equals("K")){curriculumMatrixService.update(ability7dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==2){if(!ability7dec3.get(i).equals("K")){curriculumMatrixService.update(ability7dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==3){if(!ability7dec4.get(i).equals("K")){curriculumMatrixService.update(ability7dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==4){if(!ability7dec5.get(i).equals("K")){curriculumMatrixService.update(ability7dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==5){if(!ability7dec6.get(i).equals("K")){curriculumMatrixService.update(ability7dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==6){if(!ability7dec7.get(i).equals("K")){curriculumMatrixService.update(ability7dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==7){if(!ability7dec8.get(i).equals("K")){curriculumMatrixService.update(ability7dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==8){if(!ability7dec9.get(i).equals("K")){curriculumMatrixService.update(ability7dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==6&&k==9){if(!ability7dec10.get(i).equals("K")){curriculumMatrixService.update(ability7dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==0){if(!ability8dec1.get(i).equals("K")){curriculumMatrixService.update(ability8dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==1){if(!ability8dec2.get(i).equals("K")){curriculumMatrixService.update(ability8dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==2){if(!ability8dec3.get(i).equals("K")){curriculumMatrixService.update(ability8dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==3){if(!ability8dec4.get(i).equals("K")){curriculumMatrixService.update(ability8dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==4){if(!ability8dec5.get(i).equals("K")){curriculumMatrixService.update(ability8dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==5){if(!ability8dec6.get(i).equals("K")){curriculumMatrixService.update(ability8dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==6){if(!ability8dec7.get(i).equals("K")){curriculumMatrixService.update(ability8dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==7){if(!ability8dec8.get(i).equals("K")){curriculumMatrixService.update(ability8dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==8){if(!ability8dec9.get(i).equals("K")){curriculumMatrixService.update(ability8dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==7&&k==9){if(!ability8dec10.get(i).equals("K")){curriculumMatrixService.update(ability8dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==0){if(!ability9dec1.get(i).equals("K")){curriculumMatrixService.update(ability9dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==1){if(!ability9dec2.get(i).equals("K")){curriculumMatrixService.update(ability9dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==2){if(!ability9dec3.get(i).equals("K")){curriculumMatrixService.update(ability9dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==3){if(!ability9dec4.get(i).equals("K")){curriculumMatrixService.update(ability9dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==4){if(!ability9dec5.get(i).equals("K")){curriculumMatrixService.update(ability9dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==5){if(!ability9dec6.get(i).equals("K")){curriculumMatrixService.update(ability9dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==6){if(!ability9dec7.get(i).equals("K")){curriculumMatrixService.update(ability9dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==7){if(!ability9dec8.get(i).equals("K")){curriculumMatrixService.update(ability9dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==8){if(!ability9dec9.get(i).equals("K")){curriculumMatrixService.update(ability9dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==8&&k==9){if(!ability9dec10.get(i).equals("K")){curriculumMatrixService.update(ability9dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==0){if(!ability10dec1.get(i).equals("K")){curriculumMatrixService.update(ability10dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==1){if(!ability10dec2.get(i).equals("K")){curriculumMatrixService.update(ability10dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==2){if(!ability10dec3.get(i).equals("K")){curriculumMatrixService.update(ability10dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==3){if(!ability10dec4.get(i).equals("K")){curriculumMatrixService.update(ability10dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==4){if(!ability10dec5.get(i).equals("K")){curriculumMatrixService.update(ability10dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==5){if(!ability10dec6.get(i).equals("K")){curriculumMatrixService.update(ability10dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==6){if(!ability10dec7.get(i).equals("K")){curriculumMatrixService.update(ability10dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==7){if(!ability10dec8.get(i).equals("K")){curriculumMatrixService.update(ability10dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==8){if(!ability10dec9.get(i).equals("K")){curriculumMatrixService.update(ability10dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==9&&k==9){if(!ability10dec10.get(i).equals("K")){curriculumMatrixService.update(ability10dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==0){if(!ability11dec1.get(i).equals("K")){curriculumMatrixService.update(ability11dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==1){if(!ability11dec2.get(i).equals("K")){curriculumMatrixService.update(ability11dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==2){if(!ability11dec3.get(i).equals("K")){curriculumMatrixService.update(ability11dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==3){if(!ability11dec4.get(i).equals("K")){curriculumMatrixService.update(ability11dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==4){if(!ability11dec5.get(i).equals("K")){curriculumMatrixService.update(ability11dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==5){if(!ability11dec6.get(i).equals("K")){curriculumMatrixService.update(ability11dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==6){if(!ability11dec7.get(i).equals("K")){curriculumMatrixService.update(ability11dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==7){if(!ability11dec8.get(i).equals("K")){curriculumMatrixService.update(ability11dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==8){if(!ability11dec9.get(i).equals("K")){curriculumMatrixService.update(ability11dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==10&&k==9){if(!ability11dec10.get(i).equals("K")){curriculumMatrixService.update(ability11dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==0){if(!ability12dec1.get(i).equals("K")){curriculumMatrixService.update(ability12dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==1){if(!ability12dec2.get(i).equals("K")){curriculumMatrixService.update(ability12dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==2){if(!ability12dec3.get(i).equals("K")){curriculumMatrixService.update(ability12dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==3){if(!ability12dec4.get(i).equals("K")){curriculumMatrixService.update(ability12dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==4){if(!ability12dec5.get(i).equals("K")){curriculumMatrixService.update(ability12dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==5){if(!ability12dec6.get(i).equals("K")){curriculumMatrixService.update(ability12dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==6){if(!ability12dec7.get(i).equals("K")){curriculumMatrixService.update(ability12dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==7){if(!ability12dec8.get(i).equals("K")){curriculumMatrixService.update(ability12dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==8){if(!ability12dec9.get(i).equals("K")){curriculumMatrixService.update(ability12dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==11&&k==9){if(!ability12dec10.get(i).equals("K")){curriculumMatrixService.update(ability12dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==0){if(!ability13dec1.get(i).equals("K")){curriculumMatrixService.update(ability13dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==1){if(!ability13dec2.get(i).equals("K")){curriculumMatrixService.update(ability13dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==2){if(!ability13dec3.get(i).equals("K")){curriculumMatrixService.update(ability13dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==3){if(!ability13dec4.get(i).equals("K")){curriculumMatrixService.update(ability13dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==4){if(!ability13dec5.get(i).equals("K")){curriculumMatrixService.update(ability13dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==5){if(!ability13dec6.get(i).equals("K")){curriculumMatrixService.update(ability13dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==6){if(!ability13dec7.get(i).equals("K")){curriculumMatrixService.update(ability13dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==7){if(!ability13dec8.get(i).equals("K")){curriculumMatrixService.update(ability13dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==8){if(!ability13dec9.get(i).equals("K")){curriculumMatrixService.update(ability13dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==12&&k==9){if(!ability13dec10.get(i).equals("K")){curriculumMatrixService.update(ability13dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==0){if(!ability14dec1.get(i).equals("K")){curriculumMatrixService.update(ability14dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==1){if(!ability14dec2.get(i).equals("K")){curriculumMatrixService.update(ability14dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==2){if(!ability14dec3.get(i).equals("K")){curriculumMatrixService.update(ability14dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==3){if(!ability14dec4.get(i).equals("K")){curriculumMatrixService.update(ability14dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==4){if(!ability14dec5.get(i).equals("K")){curriculumMatrixService.update(ability14dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==5){if(!ability14dec6.get(i).equals("K")){curriculumMatrixService.update(ability14dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==6){if(!ability14dec7.get(i).equals("K")){curriculumMatrixService.update(ability14dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==7){if(!ability14dec8.get(i).equals("K")){curriculumMatrixService.update(ability14dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==8){if(!ability14dec9.get(i).equals("K")){curriculumMatrixService.update(ability14dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==13&&k==9){if(!ability14dec10.get(i).equals("K")){curriculumMatrixService.update(ability14dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==0){if(!ability15dec1.get(i).equals("K")){curriculumMatrixService.update(ability15dec1.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==1){if(!ability15dec2.get(i).equals("K")){curriculumMatrixService.update(ability15dec2.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==2){if(!ability15dec3.get(i).equals("K")){curriculumMatrixService.update(ability15dec3.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==3){if(!ability15dec4.get(i).equals("K")){curriculumMatrixService.update(ability15dec4.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==4){if(!ability15dec5.get(i).equals("K")){curriculumMatrixService.update(ability15dec5.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==5){if(!ability15dec6.get(i).equals("K")){curriculumMatrixService.update(ability15dec6.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==6){if(!ability15dec7.get(i).equals("K")){curriculumMatrixService.update(ability15dec7.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==7){if(!ability15dec8.get(i).equals("K")){curriculumMatrixService.update(ability15dec8.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==8){if(!ability15dec9.get(i).equals("K")){curriculumMatrixService.update(ability15dec9.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
						else if(j==14&&k==9){if(!ability15dec10.get(i).equals("K")){curriculumMatrixService.update(ability15dec10.get(i),curriculumlist.get(i),abilitielist.get(j),k+1);}else{curriculumMatrixService.delete(curriculumlist.get(i),abilitielist.get(j),k+1);}}
					}
				}
			}
		}		
		//专业选修课操作
		List<Professional> professionalList = curriculumMatrixService.findProfessionbydepartment(departmentid);
		if(professionalList != null && professionalList.size() !=0)
		{
			update_pro(curriculumMatrixlist.size(),abilitielist,anlist,professionalList);				
		
		}
		
		ServletActionContext.getRequest().setAttribute("tag", "toViewTopologyPage");
		ServletActionContext.getRequest().setAttribute("msg", "保存成功！");
		return "update";
	}
	
	public void update_pro(int count, List<Ability> abilitielist, List<List<Analysis>> anlist, List<Professional> professionalList)
	{
		List<List<Curriculum>> curriculumlist_pro =  curriculumMatrixService.findcurriculumbydepartment_pro(departmentid);
		List<List<List<List<CurriculumMatrix>>>> curriculumMatrixlist_pro = curriculumMatrixService.findcurriculumMatrixbyall_pro(curriculumlist_pro,abilitielist,anlist,professionalList);
	
	for(int m=0;m<curriculumMatrixlist_pro.size();m++){
		for(int i=0;i<curriculumMatrixlist_pro.get(m).size();i++,count++){
			for(int j=0;j<curriculumMatrixlist_pro.get(m).get(i).size();j++){
				for(int k=0;k<curriculumMatrixlist_pro.get(m).get(i).get(j).size();k++){
					if(curriculumMatrixlist_pro.get(m).get(i).get(j).get(k).getScore().equals("K")){
						if(j==0&&k==0){if(!ability1dec1.get(count).equals("K")){curriculumMatrixService.add(ability1dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==1){if(!ability1dec2.get(count).equals("K")){curriculumMatrixService.add(ability1dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==2){if(!ability1dec3.get(count).equals("K")){curriculumMatrixService.add(ability1dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==3){if(!ability1dec4.get(count).equals("K")){curriculumMatrixService.add(ability1dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==4){if(!ability1dec5.get(count).equals("K")){curriculumMatrixService.add(ability1dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==5){if(!ability1dec6.get(count).equals("K")){curriculumMatrixService.add(ability1dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==6){if(!ability1dec7.get(count).equals("K")){curriculumMatrixService.add(ability1dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==7){if(!ability1dec8.get(count).equals("K")){curriculumMatrixService.add(ability1dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==8){if(!ability1dec9.get(count).equals("K")){curriculumMatrixService.add(ability1dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==9){if(!ability1dec10.get(count).equals("K")){curriculumMatrixService.add(ability1dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==0){if(!ability2dec1.get(count).equals("K")){curriculumMatrixService.add(ability2dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==1){if(!ability2dec2.get(count).equals("K")){curriculumMatrixService.add(ability2dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==2){if(!ability2dec3.get(count).equals("K")){curriculumMatrixService.add(ability2dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==3){if(!ability2dec4.get(count).equals("K")){curriculumMatrixService.add(ability2dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==4){if(!ability2dec5.get(count).equals("K")){curriculumMatrixService.add(ability2dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==5){if(!ability2dec6.get(count).equals("K")){curriculumMatrixService.add(ability2dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==6){if(!ability2dec7.get(count).equals("K")){curriculumMatrixService.add(ability2dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==7){if(!ability2dec8.get(count).equals("K")){curriculumMatrixService.add(ability2dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==8){if(!ability2dec9.get(count).equals("K")){curriculumMatrixService.add(ability2dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==9){if(!ability2dec10.get(count).equals("K")){curriculumMatrixService.add(ability2dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==0){if(!ability3dec1.get(count).equals("K")){curriculumMatrixService.add(ability3dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==1){if(!ability3dec2.get(count).equals("K")){curriculumMatrixService.add(ability3dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==2){if(!ability3dec3.get(count).equals("K")){curriculumMatrixService.add(ability3dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==3){if(!ability3dec4.get(count).equals("K")){curriculumMatrixService.add(ability3dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==4){if(!ability3dec5.get(count).equals("K")){curriculumMatrixService.add(ability3dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==5){if(!ability3dec6.get(count).equals("K")){curriculumMatrixService.add(ability3dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==6){if(!ability3dec7.get(count).equals("K")){curriculumMatrixService.add(ability3dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==7){if(!ability3dec8.get(count).equals("K")){curriculumMatrixService.add(ability3dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==8){if(!ability3dec9.get(count).equals("K")){curriculumMatrixService.add(ability3dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==9){if(!ability3dec10.get(count).equals("K")){curriculumMatrixService.add(ability3dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==0){if(!ability4dec1.get(count).equals("K")){curriculumMatrixService.add(ability4dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==1){if(!ability4dec2.get(count).equals("K")){curriculumMatrixService.add(ability4dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==2){if(!ability4dec3.get(count).equals("K")){curriculumMatrixService.add(ability4dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==3){if(!ability4dec4.get(count).equals("K")){curriculumMatrixService.add(ability4dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==4){if(!ability4dec5.get(count).equals("K")){curriculumMatrixService.add(ability4dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==5){if(!ability4dec6.get(count).equals("K")){curriculumMatrixService.add(ability4dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==6){if(!ability4dec7.get(count).equals("K")){curriculumMatrixService.add(ability4dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==7){if(!ability4dec8.get(count).equals("K")){curriculumMatrixService.add(ability4dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==8){if(!ability4dec9.get(count).equals("K")){curriculumMatrixService.add(ability4dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==9){if(!ability4dec10.get(count).equals("K")){curriculumMatrixService.add(ability4dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==0){if(!ability5dec1.get(count).equals("K")){curriculumMatrixService.add(ability5dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==1){if(!ability5dec2.get(count).equals("K")){curriculumMatrixService.add(ability5dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==2){if(!ability5dec3.get(count).equals("K")){curriculumMatrixService.add(ability5dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==3){if(!ability5dec4.get(count).equals("K")){curriculumMatrixService.add(ability5dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==4){if(!ability5dec5.get(count).equals("K")){curriculumMatrixService.add(ability5dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==5){if(!ability5dec6.get(count).equals("K")){curriculumMatrixService.add(ability5dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==6){if(!ability5dec7.get(count).equals("K")){curriculumMatrixService.add(ability5dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==7){if(!ability5dec8.get(count).equals("K")){curriculumMatrixService.add(ability5dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==8){if(!ability5dec9.get(count).equals("K")){curriculumMatrixService.add(ability5dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==9){if(!ability5dec10.get(count).equals("K")){curriculumMatrixService.add(ability5dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==0){if(!ability6dec1.get(count).equals("K")){curriculumMatrixService.add(ability6dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==1){if(!ability6dec2.get(count).equals("K")){curriculumMatrixService.add(ability6dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==2){if(!ability6dec3.get(count).equals("K")){curriculumMatrixService.add(ability6dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==3){if(!ability6dec4.get(count).equals("K")){curriculumMatrixService.add(ability6dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==4){if(!ability6dec5.get(count).equals("K")){curriculumMatrixService.add(ability6dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==5){if(!ability6dec6.get(count).equals("K")){curriculumMatrixService.add(ability6dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==6){if(!ability6dec7.get(count).equals("K")){curriculumMatrixService.add(ability6dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==7){if(!ability6dec8.get(count).equals("K")){curriculumMatrixService.add(ability6dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==8){if(!ability6dec9.get(count).equals("K")){curriculumMatrixService.add(ability6dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==9){if(!ability6dec10.get(count).equals("K")){curriculumMatrixService.add(ability6dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==0){if(!ability7dec1.get(count).equals("K")){curriculumMatrixService.add(ability7dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==1){if(!ability7dec2.get(count).equals("K")){curriculumMatrixService.add(ability7dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==2){if(!ability7dec3.get(count).equals("K")){curriculumMatrixService.add(ability7dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==3){if(!ability7dec4.get(count).equals("K")){curriculumMatrixService.add(ability7dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==4){if(!ability7dec5.get(count).equals("K")){curriculumMatrixService.add(ability7dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==5){if(!ability7dec6.get(count).equals("K")){curriculumMatrixService.add(ability7dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==6){if(!ability7dec7.get(count).equals("K")){curriculumMatrixService.add(ability7dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==7){if(!ability7dec8.get(count).equals("K")){curriculumMatrixService.add(ability7dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==8){if(!ability7dec9.get(count).equals("K")){curriculumMatrixService.add(ability7dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==9){if(!ability7dec10.get(count).equals("K")){curriculumMatrixService.add(ability7dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==0){if(!ability8dec1.get(count).equals("K")){curriculumMatrixService.add(ability8dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==1){if(!ability8dec2.get(count).equals("K")){curriculumMatrixService.add(ability8dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==2){if(!ability8dec3.get(count).equals("K")){curriculumMatrixService.add(ability8dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==3){if(!ability8dec4.get(count).equals("K")){curriculumMatrixService.add(ability8dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==4){if(!ability8dec5.get(count).equals("K")){curriculumMatrixService.add(ability8dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==5){if(!ability8dec6.get(count).equals("K")){curriculumMatrixService.add(ability8dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==6){if(!ability8dec7.get(count).equals("K")){curriculumMatrixService.add(ability8dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==7){if(!ability8dec8.get(count).equals("K")){curriculumMatrixService.add(ability8dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==8){if(!ability8dec9.get(count).equals("K")){curriculumMatrixService.add(ability8dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==9){if(!ability8dec10.get(count).equals("K")){curriculumMatrixService.add(ability8dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==0){if(!ability9dec1.get(count).equals("K")){curriculumMatrixService.add(ability9dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==1){if(!ability9dec2.get(count).equals("K")){curriculumMatrixService.add(ability9dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==2){if(!ability9dec3.get(count).equals("K")){curriculumMatrixService.add(ability9dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==3){if(!ability9dec4.get(count).equals("K")){curriculumMatrixService.add(ability9dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==4){if(!ability9dec5.get(count).equals("K")){curriculumMatrixService.add(ability9dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==5){if(!ability9dec6.get(count).equals("K")){curriculumMatrixService.add(ability9dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==6){if(!ability9dec7.get(count).equals("K")){curriculumMatrixService.add(ability9dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==7){if(!ability9dec8.get(count).equals("K")){curriculumMatrixService.add(ability9dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==8){if(!ability9dec9.get(count).equals("K")){curriculumMatrixService.add(ability9dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==9){if(!ability9dec10.get(count).equals("K")){curriculumMatrixService.add(ability9dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==0){if(!ability10dec1.get(count).equals("K")){curriculumMatrixService.add(ability10dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==1){if(!ability10dec2.get(count).equals("K")){curriculumMatrixService.add(ability10dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==2){if(!ability10dec3.get(count).equals("K")){curriculumMatrixService.add(ability10dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==3){if(!ability10dec4.get(count).equals("K")){curriculumMatrixService.add(ability10dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==4){if(!ability10dec5.get(count).equals("K")){curriculumMatrixService.add(ability10dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==5){if(!ability10dec6.get(count).equals("K")){curriculumMatrixService.add(ability10dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==6){if(!ability10dec7.get(count).equals("K")){curriculumMatrixService.add(ability10dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==7){if(!ability10dec8.get(count).equals("K")){curriculumMatrixService.add(ability10dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==8){if(!ability10dec9.get(count).equals("K")){curriculumMatrixService.add(ability10dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==9){if(!ability10dec10.get(count).equals("K")){curriculumMatrixService.add(ability10dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==0){if(!ability11dec1.get(count).equals("K")){curriculumMatrixService.add(ability11dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==1){if(!ability11dec2.get(count).equals("K")){curriculumMatrixService.add(ability11dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==2){if(!ability11dec3.get(count).equals("K")){curriculumMatrixService.add(ability11dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==3){if(!ability11dec4.get(count).equals("K")){curriculumMatrixService.add(ability11dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==4){if(!ability11dec5.get(count).equals("K")){curriculumMatrixService.add(ability11dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==5){if(!ability11dec6.get(count).equals("K")){curriculumMatrixService.add(ability11dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==6){if(!ability11dec7.get(count).equals("K")){curriculumMatrixService.add(ability11dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==7){if(!ability11dec8.get(count).equals("K")){curriculumMatrixService.add(ability11dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==8){if(!ability11dec9.get(count).equals("K")){curriculumMatrixService.add(ability11dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==9){if(!ability11dec10.get(count).equals("K")){curriculumMatrixService.add(ability11dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==0){if(!ability12dec1.get(count).equals("K")){curriculumMatrixService.add(ability12dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==1){if(!ability12dec2.get(count).equals("K")){curriculumMatrixService.add(ability12dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==2){if(!ability12dec3.get(count).equals("K")){curriculumMatrixService.add(ability12dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==3){if(!ability12dec4.get(count).equals("K")){curriculumMatrixService.add(ability12dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==4){if(!ability12dec5.get(count).equals("K")){curriculumMatrixService.add(ability12dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==5){if(!ability12dec6.get(count).equals("K")){curriculumMatrixService.add(ability12dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==6){if(!ability12dec7.get(count).equals("K")){curriculumMatrixService.add(ability12dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==7){if(!ability12dec8.get(count).equals("K")){curriculumMatrixService.add(ability12dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==8){if(!ability12dec9.get(count).equals("K")){curriculumMatrixService.add(ability12dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==9){if(!ability12dec10.get(count).equals("K")){curriculumMatrixService.add(ability12dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==0){if(!ability13dec1.get(count).equals("K")){curriculumMatrixService.add(ability13dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==1){if(!ability13dec2.get(count).equals("K")){curriculumMatrixService.add(ability13dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==2){if(!ability13dec3.get(count).equals("K")){curriculumMatrixService.add(ability13dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==3){if(!ability13dec4.get(count).equals("K")){curriculumMatrixService.add(ability13dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==4){if(!ability13dec5.get(count).equals("K")){curriculumMatrixService.add(ability13dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==5){if(!ability13dec6.get(count).equals("K")){curriculumMatrixService.add(ability13dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==6){if(!ability13dec7.get(count).equals("K")){curriculumMatrixService.add(ability13dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==7){if(!ability13dec8.get(count).equals("K")){curriculumMatrixService.add(ability13dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==8){if(!ability13dec9.get(count).equals("K")){curriculumMatrixService.add(ability13dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==9){if(!ability13dec10.get(count).equals("K")){curriculumMatrixService.add(ability14dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==0){if(!ability14dec1.get(count).equals("K")){curriculumMatrixService.add(ability14dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==1){if(!ability14dec2.get(count).equals("K")){curriculumMatrixService.add(ability14dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==2){if(!ability14dec3.get(count).equals("K")){curriculumMatrixService.add(ability14dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==3){if(!ability14dec4.get(count).equals("K")){curriculumMatrixService.add(ability14dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==4){if(!ability14dec5.get(count).equals("K")){curriculumMatrixService.add(ability14dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==5){if(!ability14dec6.get(count).equals("K")){curriculumMatrixService.add(ability14dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==6){if(!ability14dec7.get(count).equals("K")){curriculumMatrixService.add(ability14dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==7){if(!ability14dec8.get(count).equals("K")){curriculumMatrixService.add(ability14dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==8){if(!ability14dec9.get(count).equals("K")){curriculumMatrixService.add(ability14dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==9){if(!ability14dec10.get(count).equals("K")){curriculumMatrixService.add(ability14dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==0){if(!ability15dec1.get(count).equals("K")){curriculumMatrixService.add(ability15dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==1){if(!ability15dec2.get(count).equals("K")){curriculumMatrixService.add(ability15dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==2){if(!ability15dec3.get(count).equals("K")){curriculumMatrixService.add(ability15dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==3){if(!ability15dec4.get(count).equals("K")){curriculumMatrixService.add(ability15dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==4){if(!ability15dec5.get(count).equals("K")){curriculumMatrixService.add(ability15dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==5){if(!ability15dec6.get(count).equals("K")){curriculumMatrixService.add(ability15dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==6){if(!ability15dec7.get(count).equals("K")){curriculumMatrixService.add(ability15dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==7){if(!ability15dec8.get(count).equals("K")){curriculumMatrixService.add(ability15dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==8){if(!ability15dec9.get(count).equals("K")){curriculumMatrixService.add(ability15dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==9){if(!ability15dec10.get(count).equals("K")){curriculumMatrixService.add(ability15dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
					}else{
						if(j==0&&k==0){if(!ability1dec1.get(count).equals("K")){curriculumMatrixService.update(ability1dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==1){if(!ability1dec2.get(count).equals("K")){curriculumMatrixService.update(ability1dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==2){if(!ability1dec3.get(count).equals("K")){curriculumMatrixService.update(ability1dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1);}}
						else if(j==0&&k==3){if(!ability1dec4.get(count).equals("K")){curriculumMatrixService.update(ability1dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==4){if(!ability1dec5.get(count).equals("K")){curriculumMatrixService.update(ability1dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==5){if(!ability1dec6.get(count).equals("K")){curriculumMatrixService.update(ability1dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==6){if(!ability1dec7.get(count).equals("K")){curriculumMatrixService.update(ability1dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==7){if(!ability1dec8.get(count).equals("K")){curriculumMatrixService.update(ability1dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==8){if(!ability1dec9.get(count).equals("K")){curriculumMatrixService.update(ability1dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==0&&k==9){if(!ability1dec10.get(count).equals("K")){curriculumMatrixService.update(ability1dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==0){if(!ability2dec1.get(count).equals("K")){curriculumMatrixService.update(ability2dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==1){if(!ability2dec2.get(count).equals("K")){curriculumMatrixService.update(ability2dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==2){if(!ability2dec3.get(count).equals("K")){curriculumMatrixService.update(ability2dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==3){if(!ability2dec4.get(count).equals("K")){curriculumMatrixService.update(ability2dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==4){if(!ability2dec5.get(count).equals("K")){curriculumMatrixService.update(ability2dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==5){if(!ability2dec6.get(count).equals("K")){curriculumMatrixService.update(ability2dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==6){if(!ability2dec7.get(count).equals("K")){curriculumMatrixService.update(ability2dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==7){if(!ability2dec8.get(count).equals("K")){curriculumMatrixService.update(ability2dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==8){if(!ability2dec9.get(count).equals("K")){curriculumMatrixService.update(ability2dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==1&&k==9){if(!ability2dec10.get(count).equals("K")){curriculumMatrixService.update(ability2dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==0){if(!ability3dec1.get(count).equals("K")){curriculumMatrixService.update(ability3dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==1){if(!ability3dec2.get(count).equals("K")){curriculumMatrixService.update(ability3dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==2){if(!ability3dec3.get(count).equals("K")){curriculumMatrixService.update(ability3dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==3){if(!ability3dec4.get(count).equals("K")){curriculumMatrixService.update(ability3dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==4){if(!ability3dec5.get(count).equals("K")){curriculumMatrixService.update(ability3dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==5){if(!ability3dec6.get(count).equals("K")){curriculumMatrixService.update(ability3dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==6){if(!ability3dec7.get(count).equals("K")){curriculumMatrixService.update(ability3dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==7){if(!ability3dec8.get(count).equals("K")){curriculumMatrixService.update(ability3dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==8){if(!ability3dec9.get(count).equals("K")){curriculumMatrixService.update(ability3dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==2&&k==9){if(!ability3dec10.get(count).equals("K")){curriculumMatrixService.update(ability3dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==0){if(!ability4dec1.get(count).equals("K")){curriculumMatrixService.update(ability4dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==1){if(!ability4dec2.get(count).equals("K")){curriculumMatrixService.update(ability4dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==2){if(!ability4dec3.get(count).equals("K")){curriculumMatrixService.update(ability4dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==3){if(!ability4dec4.get(count).equals("K")){curriculumMatrixService.update(ability4dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==4){if(!ability4dec5.get(count).equals("K")){curriculumMatrixService.update(ability4dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==5){if(!ability4dec6.get(count).equals("K")){curriculumMatrixService.update(ability4dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==6){if(!ability4dec7.get(count).equals("K")){curriculumMatrixService.update(ability4dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==7){if(!ability4dec8.get(count).equals("K")){curriculumMatrixService.update(ability4dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==8){if(!ability4dec9.get(count).equals("K")){curriculumMatrixService.update(ability4dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==3&&k==9){if(!ability4dec10.get(count).equals("K")){curriculumMatrixService.update(ability4dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==0){if(!ability5dec1.get(count).equals("K")){curriculumMatrixService.update(ability5dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==1){if(!ability5dec2.get(count).equals("K")){curriculumMatrixService.update(ability5dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==2){if(!ability5dec3.get(count).equals("K")){curriculumMatrixService.update(ability5dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==3){if(!ability5dec4.get(count).equals("K")){curriculumMatrixService.update(ability5dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==4){if(!ability5dec5.get(count).equals("K")){curriculumMatrixService.update(ability5dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==5){if(!ability5dec6.get(count).equals("K")){curriculumMatrixService.update(ability5dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==6){if(!ability5dec7.get(count).equals("K")){curriculumMatrixService.update(ability5dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==7){if(!ability5dec8.get(count).equals("K")){curriculumMatrixService.update(ability5dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==8){if(!ability5dec9.get(count).equals("K")){curriculumMatrixService.update(ability5dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==4&&k==9){if(!ability5dec10.get(count).equals("K")){curriculumMatrixService.update(ability5dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==0){if(!ability6dec1.get(count).equals("K")){curriculumMatrixService.update(ability6dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==1){if(!ability6dec2.get(count).equals("K")){curriculumMatrixService.update(ability6dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==2){if(!ability6dec3.get(count).equals("K")){curriculumMatrixService.update(ability6dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==3){if(!ability6dec4.get(count).equals("K")){curriculumMatrixService.update(ability6dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==4){if(!ability6dec5.get(count).equals("K")){curriculumMatrixService.update(ability6dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==5){if(!ability6dec6.get(count).equals("K")){curriculumMatrixService.update(ability6dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==6){if(!ability6dec7.get(count).equals("K")){curriculumMatrixService.update(ability6dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==7){if(!ability6dec8.get(count).equals("K")){curriculumMatrixService.update(ability6dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==8){if(!ability6dec9.get(count).equals("K")){curriculumMatrixService.update(ability6dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==5&&k==9){if(!ability6dec10.get(count).equals("K")){curriculumMatrixService.update(ability6dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==0){if(!ability7dec1.get(count).equals("K")){curriculumMatrixService.update(ability7dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==1){if(!ability7dec2.get(count).equals("K")){curriculumMatrixService.update(ability7dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==2){if(!ability7dec3.get(count).equals("K")){curriculumMatrixService.update(ability7dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==3){if(!ability7dec4.get(count).equals("K")){curriculumMatrixService.update(ability7dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==4){if(!ability7dec5.get(count).equals("K")){curriculumMatrixService.update(ability7dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==5){if(!ability7dec6.get(count).equals("K")){curriculumMatrixService.update(ability7dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==6){if(!ability7dec7.get(count).equals("K")){curriculumMatrixService.update(ability7dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==7){if(!ability7dec8.get(count).equals("K")){curriculumMatrixService.update(ability7dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==8){if(!ability7dec9.get(count).equals("K")){curriculumMatrixService.update(ability7dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==6&&k==9){if(!ability7dec10.get(count).equals("K")){curriculumMatrixService.update(ability7dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==0){if(!ability8dec1.get(count).equals("K")){curriculumMatrixService.update(ability8dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==1){if(!ability8dec2.get(count).equals("K")){curriculumMatrixService.update(ability8dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==2){if(!ability8dec3.get(count).equals("K")){curriculumMatrixService.update(ability8dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==3){if(!ability8dec4.get(count).equals("K")){curriculumMatrixService.update(ability8dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==4){if(!ability8dec5.get(count).equals("K")){curriculumMatrixService.update(ability8dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==5){if(!ability8dec6.get(count).equals("K")){curriculumMatrixService.update(ability8dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==6){if(!ability8dec7.get(count).equals("K")){curriculumMatrixService.update(ability8dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==7){if(!ability8dec8.get(count).equals("K")){curriculumMatrixService.update(ability8dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==8){if(!ability8dec9.get(count).equals("K")){curriculumMatrixService.update(ability8dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==7&&k==9){if(!ability8dec10.get(count).equals("K")){curriculumMatrixService.update(ability8dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==0){if(!ability9dec1.get(count).equals("K")){curriculumMatrixService.update(ability9dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==1){if(!ability9dec2.get(count).equals("K")){curriculumMatrixService.update(ability9dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==2){if(!ability9dec3.get(count).equals("K")){curriculumMatrixService.update(ability9dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==3){if(!ability9dec4.get(count).equals("K")){curriculumMatrixService.update(ability9dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==4){if(!ability9dec5.get(count).equals("K")){curriculumMatrixService.update(ability9dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==5){if(!ability9dec6.get(count).equals("K")){curriculumMatrixService.update(ability9dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==6){if(!ability9dec7.get(count).equals("K")){curriculumMatrixService.update(ability9dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==7){if(!ability9dec8.get(count).equals("K")){curriculumMatrixService.update(ability9dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==8){if(!ability9dec9.get(count).equals("K")){curriculumMatrixService.update(ability9dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==8&&k==9){if(!ability9dec10.get(count).equals("K")){curriculumMatrixService.update(ability9dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==0){if(!ability10dec1.get(count).equals("K")){curriculumMatrixService.update(ability10dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==1){if(!ability10dec2.get(count).equals("K")){curriculumMatrixService.update(ability10dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==2){if(!ability10dec3.get(count).equals("K")){curriculumMatrixService.update(ability10dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==3){if(!ability10dec4.get(count).equals("K")){curriculumMatrixService.update(ability10dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==4){if(!ability10dec5.get(count).equals("K")){curriculumMatrixService.update(ability10dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==5){if(!ability10dec6.get(count).equals("K")){curriculumMatrixService.update(ability10dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==6){if(!ability10dec7.get(count).equals("K")){curriculumMatrixService.update(ability10dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==7){if(!ability10dec8.get(count).equals("K")){curriculumMatrixService.update(ability10dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==8){if(!ability10dec9.get(count).equals("K")){curriculumMatrixService.update(ability10dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==9&&k==9){if(!ability10dec10.get(count).equals("K")){curriculumMatrixService.update(ability10dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==0){if(!ability11dec1.get(count).equals("K")){curriculumMatrixService.update(ability11dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==1){if(!ability11dec2.get(count).equals("K")){curriculumMatrixService.update(ability11dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==2){if(!ability11dec3.get(count).equals("K")){curriculumMatrixService.update(ability11dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==3){if(!ability11dec4.get(count).equals("K")){curriculumMatrixService.update(ability11dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==4){if(!ability11dec5.get(count).equals("K")){curriculumMatrixService.update(ability11dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==5){if(!ability11dec6.get(count).equals("K")){curriculumMatrixService.update(ability11dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==6){if(!ability11dec7.get(count).equals("K")){curriculumMatrixService.update(ability11dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==7){if(!ability11dec8.get(count).equals("K")){curriculumMatrixService.update(ability11dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==8){if(!ability11dec9.get(count).equals("K")){curriculumMatrixService.update(ability11dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==10&&k==9){if(!ability11dec10.get(count).equals("K")){curriculumMatrixService.update(ability11dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==0){if(!ability12dec1.get(count).equals("K")){curriculumMatrixService.update(ability12dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==1){if(!ability12dec2.get(count).equals("K")){curriculumMatrixService.update(ability12dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==2){if(!ability12dec3.get(count).equals("K")){curriculumMatrixService.update(ability12dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==3){if(!ability12dec4.get(count).equals("K")){curriculumMatrixService.update(ability12dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==4){if(!ability12dec5.get(count).equals("K")){curriculumMatrixService.update(ability12dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==5){if(!ability12dec6.get(count).equals("K")){curriculumMatrixService.update(ability12dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==6){if(!ability12dec7.get(count).equals("K")){curriculumMatrixService.update(ability12dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==7){if(!ability12dec8.get(count).equals("K")){curriculumMatrixService.update(ability12dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==8){if(!ability12dec9.get(count).equals("K")){curriculumMatrixService.update(ability12dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==11&&k==9){if(!ability12dec10.get(count).equals("K")){curriculumMatrixService.update(ability12dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==0){if(!ability13dec1.get(count).equals("K")){curriculumMatrixService.update(ability13dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==1){if(!ability13dec2.get(count).equals("K")){curriculumMatrixService.update(ability13dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==2){if(!ability13dec3.get(count).equals("K")){curriculumMatrixService.update(ability13dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==3){if(!ability13dec4.get(count).equals("K")){curriculumMatrixService.update(ability13dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==4){if(!ability13dec5.get(count).equals("K")){curriculumMatrixService.update(ability13dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==5){if(!ability13dec6.get(count).equals("K")){curriculumMatrixService.update(ability13dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==6){if(!ability13dec7.get(count).equals("K")){curriculumMatrixService.update(ability13dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==7){if(!ability13dec8.get(count).equals("K")){curriculumMatrixService.update(ability13dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==8){if(!ability13dec9.get(count).equals("K")){curriculumMatrixService.update(ability13dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==12&&k==9){if(!ability13dec10.get(count).equals("K")){curriculumMatrixService.update(ability13dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==0){if(!ability14dec1.get(count).equals("K")){curriculumMatrixService.update(ability14dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==1){if(!ability14dec2.get(count).equals("K")){curriculumMatrixService.update(ability14dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==2){if(!ability14dec3.get(count).equals("K")){curriculumMatrixService.update(ability14dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==3){if(!ability14dec4.get(count).equals("K")){curriculumMatrixService.update(ability14dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==4){if(!ability14dec5.get(count).equals("K")){curriculumMatrixService.update(ability14dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==5){if(!ability14dec6.get(count).equals("K")){curriculumMatrixService.update(ability14dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==6){if(!ability14dec7.get(count).equals("K")){curriculumMatrixService.update(ability14dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==7){if(!ability14dec8.get(count).equals("K")){curriculumMatrixService.update(ability14dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==8){if(!ability14dec9.get(count).equals("K")){curriculumMatrixService.update(ability14dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==13&&k==9){if(!ability14dec10.get(count).equals("K")){curriculumMatrixService.update(ability14dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==0){if(!ability15dec1.get(count).equals("K")){curriculumMatrixService.update(ability15dec1.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==1){if(!ability15dec2.get(count).equals("K")){curriculumMatrixService.update(ability15dec2.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==2){if(!ability15dec3.get(count).equals("K")){curriculumMatrixService.update(ability15dec3.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==3){if(!ability15dec4.get(count).equals("K")){curriculumMatrixService.update(ability15dec4.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==4){if(!ability15dec5.get(count).equals("K")){curriculumMatrixService.update(ability15dec5.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==5){if(!ability15dec6.get(count).equals("K")){curriculumMatrixService.update(ability15dec6.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==6){if(!ability15dec7.get(count).equals("K")){curriculumMatrixService.update(ability15dec7.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==7){if(!ability15dec8.get(count).equals("K")){curriculumMatrixService.update(ability15dec8.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==8){if(!ability15dec9.get(count).equals("K")){curriculumMatrixService.update(ability15dec9.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
						else if(j==14&&k==9){if(!ability15dec10.get(count).equals("K")){curriculumMatrixService.update(ability15dec10.get(count),curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}else{curriculumMatrixService.delete(curriculumlist_pro.get(m).get(i),abilitielist.get(j),k+1,professionalList.get(m));}}
					}
				}
			}
		}
	}
	}
	
	
	private String tnum;
	private Integer currentpage;
	private String collegeid;
	private PPTrainingConceptService ppTrainingConceptService;
	public void setPpTrainingConceptService(
			PPTrainingConceptService ppTrainingConceptService) {
		this.ppTrainingConceptService = ppTrainingConceptService;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public String getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	public String tozyjzpage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = ppTrainingConceptService.zypy(tnum,currentpage,collegeid);
		ServletActionContext.getRequest().setAttribute("collegeid", collegeid);
		setAttr(pageBean);
		return "tozyjzpage";
	}
	
	
	
	
	
	
	
	
	private void setAttr(PageBean pageBean) {
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	}
	private Integer getcurrentpage(Integer currentpage) {
		if(currentpage == null||currentpage == 0){
			currentpage=1;
		}
		return currentpage;
	}
	
	private String departmentID;
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String modifyCurriculum()
	{
		List<Professional> professionalList = curriculumMatrixService.findProfessionbydepartment(departmentID);
		if(professionalList != null && professionalList.size() !=0)
		{
			List<ProfessionalLegalize> curriculumlist_pro = new ArrayList<ProfessionalLegalize>();//存储有专业方向的课程
			curriculumlist_pro = curriculumMatrixService.find_HaveDepartmentTag(departmentID);
	       	ServletActionContext.getRequest().setAttribute("curriculumlist_pro", curriculumlist_pro);
		}
		else
		{
			List<ProfessionalLegalize> curriculumlist_pro = new ArrayList<ProfessionalLegalize>();//存储有专业方向的课程
			curriculumlist_pro = curriculumMatrixService.find_NoDepartmentTag(departmentID);
	       	ServletActionContext.getRequest().setAttribute("curriculumlist_pro", curriculumlist_pro);
		}

		return "modifyCurriculum";
	}
	
	
	private List<String> departmentCnameID;
	private List<String> professionalnameID;
	private List<String> HavecurriculumId;
	private List<String> renzheng;

	public List<String> getDepartmentCnameID() {
		return departmentCnameID;
	}
	public void setDepartmentCnameID(List<String> departmentCnameID) {
		this.departmentCnameID = departmentCnameID;
	}
	public List<String> getProfessionalnameID() {
		return professionalnameID;
	}
	public void setProfessionalnameID(List<String> professionalnameID) {
		this.professionalnameID = professionalnameID;
	}
	public List<String> getHavecurriculumId() {
		return HavecurriculumId;
	}
	public void setHavecurriculumId(List<String> havecurriculumId) {
		HavecurriculumId = havecurriculumId;
	}
	public List<String> getRenzheng() {
		return renzheng;
	}
	public void setRenzheng(List<String> renzheng) {
		this.renzheng = renzheng;
	}
	public String modifyDetermine()
	{
		curriculumMatrixService.modifyDetermine(departmentCnameID,professionalnameID,HavecurriculumId,renzheng);
		return "modifyDetermine";
	}
}





