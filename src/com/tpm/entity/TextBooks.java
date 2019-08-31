package com.tpm.entity;

public class TextBooks {
	private Integer textBooksid;
	private String name;
	private String syllabusID;

	public String getSyllabusID() {
		return syllabusID;
	}
	public void setSyllabusID(String syllabusID) {
		this.syllabusID = syllabusID;
	}
	public Integer getTextBooksid() {
		return textBooksid;
	}
	public void setTextBooksid(Integer textBooksid) {
		this.textBooksid = textBooksid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
