package com.tpm.dao;

import java.util.List;

import com.tpm.entity.PracticeBooks_InnerExperiment;
import com.tpm.entity.TextBooks_InnerExperiment;

public class TextBooks_InnerExperimentDaoImpl extends BaseDaoImpl<TextBooks_InnerExperiment> implements
		TextBooks_InnerExperimentDao {

	public List<TextBooks_InnerExperiment> getbytextBooks_InnerExperimentlist(
			String syllabusId) {
		List<TextBooks_InnerExperiment> textBookslist;
		textBookslist = this.getHibernateTemplate().find("from TextBooks_InnerExperiment where syllabusID=?", syllabusId);
		return textBookslist;

	}

}
