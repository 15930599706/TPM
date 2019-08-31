package com.tpm.dao;

import java.util.List;

import com.tpm.entity.TextBooks;

public class TextBooksDaoImpl extends BaseDaoImpl<TextBooks> implements TextBooksDao {
	
	public List<TextBooks> getbytextBooks(String syllabusId){
		List<TextBooks> textBookslist;
			textBookslist = this.getHibernateTemplate().find("from TextBooks where syllabusID=?", syllabusId);
		return textBookslist;
	}
}
