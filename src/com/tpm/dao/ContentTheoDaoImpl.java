package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;

public class ContentTheoDaoImpl extends BaseDaoImpl<ContentTheo> implements ContentTheoDao{
	@SuppressWarnings("all")
	public List<ContentTheo> getbycontentTheo(String syllabusId) {
		List<ContentTheo> contentTheolist;
		contentTheolist = this.getHibernateTemplate().find("from ContentTheo where syllabusID=?",syllabusId);
		return contentTheolist;
	}
}
