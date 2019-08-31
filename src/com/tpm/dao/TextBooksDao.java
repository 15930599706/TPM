package com.tpm.dao;

import java.util.List;

import com.tpm.entity.TextBooks;

public interface TextBooksDao extends BaseDao<TextBooks> {

	List<TextBooks> getbytextBooks(String syllabusId);

}
