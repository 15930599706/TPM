package com.tpm.dao;

import java.util.List;
import com.tpm.entity.DiscussContent;

public class DiscussContentDaoImpl extends BaseDaoImpl<DiscussContent> implements DiscussContentDao {
	public List<DiscussContent> getbydiscussContent(String syllabusId){
		List<DiscussContent> discussContentlist;
		discussContentlist = this.getHibernateTemplate().find("from DiscussContent where syllabusID=?",syllabusId);
		return discussContentlist;
	}
}
