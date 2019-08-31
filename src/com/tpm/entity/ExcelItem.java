package com.tpm.entity;

public class ExcelItem {
	int questionId;
	String abilityName;
	String fs;//分解点的组合字符串
	int numAll;
	int numA;
	int numB;
	int numC;
	int numD;
	int numE;
	
	public ExcelItem(){}
	public ExcelItem(int qi,String an,String fs,int nall,int na,int nb,int nc,int nd,int ne ){
		setQuestionId(qi);
		setAbilityName(an);
		setFs(fs);
		setNumA(na);
		setNumB(nb);
		setNumC(nc);
		setNumD(nd);
		setNumE(ne);
		setNumAll( nall);
	}
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int qi) {
		this.questionId = qi;
	}
	public String getAbilityName() {
		return abilityName;
	}
	public void setAbilityName(String abilityName) {
		this.abilityName = abilityName;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public int getNumA() {
		return numA;
	}
	public void setNumA(int numA) {
		this.numA = numA;
	}
	public int getNumB() {
		return numB;
	}
	public void setNumB(int numB) {
		this.numB = numB;
	}
	public int getNumC() {
		return numC;
	}
	public void setNumC(int numC) {
		this.numC = numC;
	}
	public int getNumD() {
		return numD;
	}
	public void setNumD(int numD) {
		this.numD = numD;
	}
	public int getNumE() {
		return numE;
	}
	public void setNumE(int numE) {
		this.numE = numE;
	}
	public int getNumAll() {
		return numAll;
	}
	public void setNumAll(int numAll) {
		this.numAll = numAll;
	}	

}