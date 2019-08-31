package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ContentTheo;
import com.tpm.entity.OtherContent;

public class OtherContentDaoImpl extends BaseDaoImpl<OtherContent> implements OtherContentDao {
	public List<OtherContent> getbyOtherContent(String syllabusId){
		List<OtherContent> otherContentlist;
			otherContentlist = this.getHibernateTemplate().find("from OtherContent where syllabusID=?", syllabusId);
		return otherContentlist;
	}
}
